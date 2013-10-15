/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.Parameter;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryLog;
import com.redip.factory.impl.FactoryLog;
import com.redip.log.Logger;
import com.redip.service.ILogService;
import com.redip.service.IParameterService;
import com.redip.service.impl.LogService;
import com.redip.service.impl.ParameterService;
import com.redip.service.impl.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author ip100003
 */
public class UpdateParameter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO create class Validator to validate all parameter from page
        String param = request.getParameter("id");
        String columnName = request.getParameter("columnName");
        String value = request.getParameter("value").replaceAll("'", "");

        Logger.log(UpdateParameter.class.getName(), Level.INFO, "value : " + value + " columnName : " + columnName + " param : " + param);

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IParameterService parameterService = appContext.getBean(ParameterService.class);

        try {
            parameterService.updateParameter(param, columnName, value);

            /**
             * Adding Log entry.
             */
            response.getWriter().print(value);
        } catch (Exception ex) {
            response.getWriter().print(ex);
        }


    }
}
