/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.email;

import com.redip.log.Logger;
import com.redip.service.IEmailService;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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
public class SendEmail extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            //response.setContentType("text/html;charset=UTF-8");
            //request.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            
        try {
            
            
            JSONObject jObj = new JSONObject(request.getReader().readLine());
            
            String host = jObj.getString("host");
            Integer port = Integer.valueOf(jObj.getString("port"));
            String template = jObj.getString("template");
            String subject = jObj.getString("subject");
            String from = jObj.getString("from");
            String to = jObj.getString("to");
            String cc = jObj.getString("cc");
            
            Logger.log(SendEmail.class.getName(), Level.INFO, "Sendind email : " + template);
                
                ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
                IEmailService emailService = appContext.getBean(IEmailService.class);
                
                Logger.log(SendEmail.class.getName(), Level.INFO, "Sendind email : " + template.length());
                emailService.sendHtmlMail(host, port, template, subject, from, to, cc);
                
                response.setContentType("application/json");
//            response.getWriter().print(jsonResponse.toString());
            } catch (JSONException e) {
                Logger.log(SendEmail.class.getName(), Level.FATAL, "" + e);
                response.setContentType("text/html");
                response.getWriter().print(e.getMessage());
            } catch (Exception ex) {
                Logger.log(SendEmail.class.getName(), Level.FATAL, ex.toString());
            }finally {
                out.close();
            }
        
    }
}
