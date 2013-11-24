/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.nonconformity;

import com.redip.entity.QualityNonconformities;
import com.redip.factory.IFactoryQualityNonconformities;
import com.redip.log.Logger;
import com.redip.service.IEmailService;
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
public class NonConformityUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // response.setContentType("text/html;charset=UTF-8");
            this.processRequest(request, response);
        } catch (JSONException ex) {
            java.util.logging.Logger.getLogger(NonConformityUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            // response.setContentType("text/html;charset=UTF-8");
            this.processRequest(request, response);
        } catch (JSONException ex) {
            java.util.logging.Logger.getLogger(NonConformityUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException, JSONException {
        //request.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        JSONObject jObj = new JSONObject(request.getReader().readLine());
            
        String id = jObj.getString("id");
        String value = jObj.getString("value");
        String columnName = jObj.getString("columnName");
        
        Logger.log("NonConformityUpdate", Level.INFO, id + value + columnName);
        int login = Integer.parseInt(id);
        //int columnPosition = Integer.parseInt(request.getParameter("columnPosition"));


        //String value = request.getParameter("value");
        //String columnName = request.getParameter("columnName");

        Logger.log("NonConformityUpdate", Level.INFO, "id:" + login + ";value:" + value + ";columnName:" + columnName);

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesService nonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
        IEmailService emailService = appContext.getBean(IEmailService.class);
        IFactoryQualityNonconformities factoryQNC = appContext.getBean(IFactoryQualityNonconformities.class);
        
        QualityNonconformities nonconformities = factoryQNC.create(login, null, null);
        
        String str = nonconformitiesService.updateNonconformity(login, columnName, value);
        
        emailService.sendEmailEvent("update"+columnName, nonconformities, columnName, value);
        
        out.print(value);
    }
}