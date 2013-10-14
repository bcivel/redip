/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesAction;
import com.redip.log.Logger;
import com.redip.service.IQualityNonconformitiesActionService;
import com.redip.service.IQualityNonconformitiesService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
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
import sun.util.calendar.BaseCalendar.Date;
import sun.util.calendar.LocalGregorianCalendar;

/**
 *
 * @author bcivel
 */
public class NonConformityCalendar extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        JSONArray data = new JSONArray(); //data that will be shown in the table

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesService qualityNonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);

        List<QualityNonconformities> nonconformitieslist = qualityNonconformitiesService.getAllNonconformities();

        try {
            JSONArray jsonResponse = new JSONArray();
            
            for (QualityNonconformities listofnonconformities : nonconformitieslist) {
            JSONObject json = new JSONObject();
            
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            json.put("title", listofnonconformities.getProblemTitle());
            json.put("start", listofnonconformities.getStartDate());
            //json.put("end", "2013-10-13 14:00");
            json.put("url", "qualitynonconformitydetails.jsp?ncid="+listofnonconformities.getIdqualitynonconformities());
            
            jsonResponse.put(json);
            }
            
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse.toString());
        } catch (JSONException e) {
            Logger.log(NonConformityDetails.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        } 
    }
}
