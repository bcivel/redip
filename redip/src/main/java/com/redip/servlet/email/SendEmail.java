/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.email;

import com.redip.entity.QualityNonconformities;
import com.redip.exception.QualityException;
import com.redip.log.Logger;
import com.redip.service.IEmailService;
import com.redip.service.IParameterService;
import com.redip.service.IQualityNonconformitiesService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class SendEmail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String host = request.getParameter("host");
        Integer port = Integer.valueOf(request.getParameter("port"));
        String template = request.getParameter("template");
        String subject = request.getParameter("subject");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String cc = request.getParameter("cc");

        try {
            
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IEmailService emailService = appContext.getBean(IEmailService.class);
            
            emailService.sendHtmlMail(host, port, template, subject, from, to, cc);
            
            response.setContentType("application/json");
//            response.getWriter().print(jsonResponse.toString());
        } catch (JSONException e) {
            Logger.log(SendEmail.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        } catch (Exception ex) {
            Logger.log(SendEmail.class.getName(), Level.INFO, ex.toString());
        } finally {
            out.close();
        }
    }
}
