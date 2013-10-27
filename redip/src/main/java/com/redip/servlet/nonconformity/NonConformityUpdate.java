/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.nonconformity;

import com.redip.log.Logger;
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
public class NonConformityUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // response.setContentType("text/html;charset=UTF-8");
        this.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // response.setContentType("text/html;charset=UTF-8");
        this.processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Logger.log("NonConformityUpdate", Level.INFO, request.getParameter("id") + request.getParameter("value") + request.getParameter("columnName"));
        int login = Integer.parseInt(request.getParameter("id"));
        //int columnPosition = Integer.parseInt(request.getParameter("columnPosition"));


        String value = request.getParameter("value");
        String columnName = request.getParameter("columnName");

        Logger.log("NonConformityUpdate", Level.INFO, "id:" + login + ";value:" + value + ";columnName:" + columnName);

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesService nonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);

        String str = nonconformitiesService.updateNonconformity(login, columnName, value);
        out.print(str);
    }
}