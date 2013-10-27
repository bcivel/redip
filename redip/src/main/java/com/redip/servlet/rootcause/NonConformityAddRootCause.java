/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.rootcause;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesRootCause;
import com.redip.factory.IFactoryQualityNonconformitiesRootCause;
import com.redip.service.IQualityNonconformitiesImpactService;
import com.redip.service.IQualityNonconformitiesRootCauseService;
import com.redip.service.IQualityNonconformitiesService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class NonConformityAddRootCause extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rootcauseCategory = request.getParameter("rootcauseCategory");
        String rootcauseDescription = request.getParameter("rootcauseDescription");
        String component = request.getParameter("component");
        String responsabilities = request.getParameter("responsabilities");
        String status = request.getParameter("status");
        String severity = request.getParameter("severity");
        

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());

        IQualityNonconformitiesRootCauseService nonconformitiesRCService = appContext.getBean(IQualityNonconformitiesRootCauseService.class);
        IFactoryQualityNonconformitiesRootCause rootcauseFactory = appContext.getBean(IFactoryQualityNonconformitiesRootCause.class);
        
        QualityNonconformitiesRootCause rc = rootcauseFactory.create(0, rootcauseCategory, rootcauseDescription, responsabilities, status, component, severity, null,null,null,null);
        
        String str2 = nonconformitiesRCService.addNonconformityRootCause(rc);

        //response.getWriter().print(str.concat("/").concat(str2));
        response.sendRedirect("qualitynonconformitiesrootcause.jsp");

    }
}
