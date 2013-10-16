/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.Invariant;
import com.redip.exception.QualityException;
import com.redip.log.Logger;
import com.redip.service.IInvariantService;
import com.redip.service.IQualityNonconformitiesService;
import com.redip.service.impl.InvariantServiceImpl;
import com.redip.util.ParameterParserUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class GetDistinctValueFromNonconformities extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        String id = policy.sanitize(request.getParameter("parameter"));
        String parameter = ParameterParserUtil.parseStringParam(id, "");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesService nqcService = appContext.getBean(IQualityNonconformitiesService.class);
        try {
            JSONArray valueList = new JSONArray();
            try {
                for (String value : nqcService.findDistinctValueFromParameter(parameter)) {
                    valueList.put(value);
                }
            } catch (Exception ex) {
                response.setContentType("text/html");
                response.getWriter().print(ex);

            }
            response.setContentType("application/json");
            response.getWriter().print(valueList.toString());
        } catch (Exception e) {
            Logger.log(GetInvariantList.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
