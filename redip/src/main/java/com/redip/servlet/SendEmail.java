/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

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
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class SendEmail extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer id = Integer.valueOf(request.getParameter("id"));
        
        try {
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IQualityNonconformitiesService qualityNonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
            IEmailService emailService = appContext.getBean(IEmailService.class);
            IParameterService parameterService = appContext.getBean(IParameterService.class);
            
            QualityNonconformities test = qualityNonconformitiesService.getOneNonconformities(id);
            
            String host = parameterService.findParameterByKey("email_smtp_host").getValue();
            Integer port = Integer.valueOf(parameterService.findParameterByKey("email_smtp_port").getValue());
            String from = parameterService.findParameterByKey("email_from").getValue();
            
            String subject = "FR - " +test.getProblemTitle()+ " - ("+test.getSeverity()+")";
                    
            
            String to = "CIVEL Benoit <bcivel@redoute.fr>";
            String cc = "";
            
            StringBuilder body = new StringBuilder();
            body.append("Hello ServiceDesk,</br></br>");
            body.append("Find below description of an issue :</br></br>");
            body.append("<b>Issue Title :</b>");
            body.append(test.getProblemTitle());
            body.append("</br><b>Issue Description : </b>");
            body.append(test.getProblemDescription());
            body.append("</br><b>Reproductibility : </b>");
            body.append(test.getReproductibility());
            body.append("</br><b>Expected : </b>");
            body.append(test.getBehaviorExpected());
            body.append("</br><b>Priority : </b>");
            body.append(test.getSeverity());
            
            
            emailService.sendHtmlMail(host, port, body.toString(), subject, from, to, cc);
            
        } catch (QualityException ex) {
            Logger.log(SendEmail.class.getName(), Level.INFO, ex.toString());
        } catch (Exception ex) {
            Logger.log(SendEmail.class.getName(), Level.INFO, ex.toString());
        } finally {            
            out.close();
        }
    }

}
