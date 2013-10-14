/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.QualityNonconformities;
import com.redip.service.IQualityNonconformitiesImpactService;
import com.redip.service.IQualityNonconformitiesService;
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
public class NonConformityAdd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String application = request.getParameter("Application");
        String problemTitle = request.getParameter("ProblemTitle");
        String problemDescription = request.getParameter("ProblemDescription");
        String startDate = request.getParameter("StartDate");
        String startTime = request.getParameter("StartTime");
        String endDate = request.getParameter("EndDate");
        String endTime = request.getParameter("EndTime");
        String severity = request.getParameter("Severity");
        String reproductibility = request.getParameter("Reproductibility");
        String linkToDoc = request.getParameter("LinkToDoc");
        String behaviorExpected = request.getParameter("BehaviorExpected");
        String impactOrCost = "";

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

        IQualityNonconformitiesService nonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
        IQualityNonconformitiesImpactService nonconformitiesImpactService = appContext.getBean(IQualityNonconformitiesImpactService.class);

        String str = nonconformitiesService.addNonconformity(problemTitle, problemDescription,
                severity, reproductibility, linkToDoc, behaviorExpected);

        QualityNonconformities qncCount = nonconformitiesService.getMaxId();
        int id = qncCount.getIdqualitynonconformities();

        String str2 = nonconformitiesImpactService.addNonconformityImpact(id, application,
                startDate, startTime, endDate, endTime, impactOrCost);

        //response.getWriter().print(str.concat("/").concat(str2));
        response.sendRedirect("qualitynonconformities.jsp");

    }
}
