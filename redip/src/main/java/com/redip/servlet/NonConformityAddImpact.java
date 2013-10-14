/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.log.Logger;
import com.redip.service.IQualityNonconformitiesImpactService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Level;

/**
 * @author bcivel
 */
public class NonConformityAddImpact extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("IdLog_");
        String[] application = request.getParameterValues("Application_");
        String[] startDate = request.getParameterValues("StartDate_");
        String[] startTime = request.getParameterValues("StartTime_");
        String[] endDate = request.getParameterValues("EndDate_");
        String[] endTime = request.getParameterValues("EndTime_");
        String[] impactOrCost = request.getParameterValues("ImpactOrCost_");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesImpactService nonconformitiesImpactService = appContext.getBean(IQualityNonconformitiesImpactService.class);

        
        Logger.log(NonConformityAddImpact.class.getName(), Level.INFO, application[0]);
        String str = "";
        for (int a = 0; a < ids.length; a++) {
            Logger.log(NonConformityAddImpact.class.getName(), Level.INFO, Integer.valueOf(ids[a])+ application[a]+
                    startDate[a]+ startTime[a]+ endDate[a]+ endTime[a]+ impactOrCost[a]);
            str = nonconformitiesImpactService.addNonconformityImpact(Integer.valueOf(ids[a]), application[a],
                    startDate[a], startTime[a], endDate[a], endTime[a], impactOrCost[a]);
        }
        response.sendRedirect("qualitynonconformitydetails.jsp?ncid=" + ids[0]);

    }
}
