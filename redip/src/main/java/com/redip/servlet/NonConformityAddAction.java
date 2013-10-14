/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.QualityNonconformitiesAction;
import com.redip.factory.IFactoryQualityNonconformitiesAction;
import com.redip.service.IQualityNonconformitiesActionService;
import com.redip.service.IQualityNonconformitiesImpactService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bcivel
 */
public class NonConformityAddAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("Action_ID");
        String[] action = request.getParameterValues("Action_Action");
        String[] date = request.getParameterValues("Action_Date");
        String[] deadline = request.getParameterValues("Action_Deadline");
        String[] follower = request.getParameterValues("Action_Follower");
        String[] percentage = request.getParameterValues("Action_Percentage");
        String[] status = request.getParameterValues("Action_Status");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IQualityNonconformitiesActionService nonconformitiesActionService = appContext.getBean(IQualityNonconformitiesActionService.class);
        IFactoryQualityNonconformitiesAction factoryNca = appContext.getBean(IFactoryQualityNonconformitiesAction.class);
        
        String str = "";
        for (int a = 0; a < ids.length; a++) {
            QualityNonconformitiesAction qnca = factoryNca.create(Integer.valueOf(ids[0]), 
                    Integer.valueOf(ids[0]), action[a], deadline[a],follower[a],
                    status[a],date[a], percentage[a],  "99");
            str = nonconformitiesActionService.addNonconformityAction(qnca);
        }
        response.sendRedirect("qualitynonconformitydetails.jsp?ncid=" + ids[0]);

    }
}
