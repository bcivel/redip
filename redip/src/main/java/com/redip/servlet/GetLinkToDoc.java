/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redip.servlet;

import com.redip.entity.Invariant;
import com.redip.entity.QualityNonconformitiesDoc;
import com.redip.exception.QualityException;
import com.redip.log.Logger;
import com.redip.service.IInvariantService;
import com.redip.service.IQualityNonconformitiesDocService;
import com.redip.service.impl.InvariantServiceImpl;
import com.redip.util.ParameterParserUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class GetLinkToDoc extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        String id = policy.sanitize(request.getParameter("idNC"));
        String idName = ParameterParserUtil.parseStringParam(id, "");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesDocService docService = appContext.getBean(IQualityNonconformitiesDocService.class);
        List<QualityNonconformitiesDoc> myDocs = docService.getDocLink(Integer.valueOf(id));
        
        try {
            JSONArray docList = new JSONArray();
            
            try {
                for (QualityNonconformitiesDoc myDoc : myDocs) {
                    JSONObject doc = new JSONObject();
                    doc.put("link", myDoc.getLinkToDoc());
                    doc.put("id", myDoc.getIdQualityNonconformitiesDoc());
                    docList.put(doc);
                }
            } catch (Exception ex) {
                response.setContentType("text/html");
                response.getWriter().print(ex);

            }
            response.setContentType("application/json");
            response.getWriter().print(docList.toString());
        } catch (Exception e) {
            Logger.log(GetInvariantList.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
