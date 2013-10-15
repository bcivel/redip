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
public class NonConformityDeleteImpact extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String idnci = request.getParameter("idnci");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesImpactService nonconformitiesImpactService = appContext.getBean(IQualityNonconformitiesImpactService.class);

        Logger.log(NonConformityDeleteImpact.class.getName(), Level.INFO, "call delete service for idnci :" + idnci);
        String str = nonconformitiesImpactService.deleteNonconformitiesImpact(Integer.valueOf(idnci));

        response.sendRedirect("qualitynonconformitydetails.jsp?ncid=" + id);


    }
}
