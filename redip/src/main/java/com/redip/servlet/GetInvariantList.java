package com.redip.servlet;

import com.redip.entity.Invariant;
import com.redip.exception.QualityException;
import com.redip.log.Logger;
import com.redip.service.IInvariantService;
import com.redip.service.impl.InvariantServiceImpl;
import com.redip.util.ParameterParserUtil;
import org.apache.log4j.Level;
import org.json.JSONException;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.json.JSONArray;

public class GetInvariantList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        String id = policy.sanitize(request.getParameter("idName"));
        String idName = ParameterParserUtil.parseStringParam(id, "");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IInvariantService invariantService = appContext.getBean(InvariantServiceImpl.class);
        try {
            JSONArray invariantList = new JSONArray();
            try {
                for (Invariant myInvariant : invariantService.findListOfInvariantById(idName)) {
                    invariantList.put(myInvariant.getValue());
                }
            } catch (QualityException ex) {
                response.setContentType("text/html");
                response.getWriter().print(ex.getMessageError().getDescription());

            }
            response.setContentType("application/json");
            response.getWriter().print(invariantList.toString());
        } catch (Exception e) {
            Logger.log(GetInvariantList.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
