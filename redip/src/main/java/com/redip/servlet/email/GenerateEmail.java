/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.email;

import com.redip.entity.QualityNonconformities;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class GenerateEmail extends HttpServlet {

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer id = Integer.valueOf(request.getParameter("id"));

        try {
            JSONObject jsonResponse = new JSONObject();
            
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IQualityNonconformitiesService qualityNonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
            IEmailService emailService = appContext.getBean(IEmailService.class);
            IParameterService parameterService = appContext.getBean(IParameterService.class);

            QualityNonconformities test = qualityNonconformitiesService.getOneNonconformities(id);

            String host = parameterService.findParameterByKey("email_smtp_host").getValue();
            Integer port = Integer.valueOf(parameterService.findParameterByKey("email_smtp_port").getValue());
            String from = parameterService.findParameterByKey("email_from").getValue();

            String team = test.getResponsabilities();
             
            /*
             * SUBJECT
             */
            String subject = parameterService.findParameterByKey("email_title_template_"+team).getValue();
            subject = subject.replace("%TITLE%", test.getProblemTitle());
            subject = subject.replace("%SEVERITY%", test.getSeverity());
            
            /*
             * TO & CC
             */
            String to = parameterService.findParameterByKey("email_to_"+team).getValue();
            String cc = parameterService.findParameterByKey("email_cc_"+team).getValue();;

            /**
             * TEMPLATE
             */
            String template = parameterService.findParameterByKey("email_body_template_"+team).getValue();
            template = template.replace("%PROBLEMTITLE%", test.getProblemTitle());
            template = template.replace("%PROBLEMDESCRIPTION%", test.getProblemDescription());
            template = template.replace("%REPRODUCTIBILITY%", test.getReproductibility());
            template = template.replace("%EXPECTED%", test.getBehaviorExpected());
            template = template.replace("%SEVERITY%", test.getSeverity());
            

           // emailService.sendHtmlMail(host, port, template, subject, from, to, cc);
            jsonResponse.put("host", host);
            jsonResponse.put("port", port);
            jsonResponse.put("template", template);
            jsonResponse.put("subject", subject);
            jsonResponse.put("from", from);
            jsonResponse.put("to", to);
            jsonResponse.put("cc", cc);
            
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse.toString());
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
