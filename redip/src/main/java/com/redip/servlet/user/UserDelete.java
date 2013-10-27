/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.user;

import com.redip.exception.QualityException;
import com.redip.factory.IFactoryLog;
import com.redip.factory.impl.FactoryLog;
import com.redip.service.ILogService;
import com.redip.service.IUserService;
import com.redip.service.impl.LogService;
import com.redip.service.impl.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author ip100003
 */
public class UserDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("id");

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IUserService userService = appContext.getBean(UserService.class);

        try {
            userService.deleteUser(userService.findUserByKey(login));

            /**
             * Adding Log entry.
             */
            ILogService logEventService = appContext.getBean(LogService.class);
            IFactoryLog factoryLogEvent = appContext.getBean(FactoryLog.class);
            try {
                logEventService.insertLog(factoryLogEvent.create(0, request.getUserPrincipal().getName(), null, "/DeleteUsersAjax", "DELETE", "Delete user : " + login, "", ""));
            } catch (Exception ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.ERROR, null, ex);
            }


        } catch (QualityException ex) {
            response.getWriter().print(ex.getMessageError().getDescription());
        }
    }
}