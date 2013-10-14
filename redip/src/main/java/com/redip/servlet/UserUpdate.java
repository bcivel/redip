/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.Group;
import com.redip.entity.User;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryGroup;
import com.redip.factory.IFactoryLog;
import com.redip.factory.impl.FactoryGroup;
import com.redip.factory.impl.FactoryLog;
import com.redip.log.Logger;
import com.redip.service.ILogService;
import com.redip.service.IUserGroupService;
import com.redip.service.IUserService;
import com.redip.service.impl.LogService;
import com.redip.service.impl.UserGroupService;
import com.redip.service.impl.UserService;
import org.apache.log4j.Level;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ip100003
 */
public class UserUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO create class Validator to validate all parameter from page
        String login = request.getParameter("id");
        int columnPosition = Integer.parseInt(request.getParameter("columnPosition"));
        String value = request.getParameter("value").replaceAll("'", "");

        Logger.log(UserUpdate.class.getName(), Level.INFO, "value : " + value + " columnPosition : " + columnPosition + " login : " + login);

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IUserService userService = appContext.getBean(UserService.class);
        IUserGroupService userGroupService = appContext.getBean(UserGroupService.class);

        IFactoryGroup factoryGroup = new FactoryGroup();

        User myUser;
        List<Group> newGroups = null;
        try {
            myUser = userService.findUserByKey(login);
            switch (columnPosition) {
                case 0:
                    newGroups = new ArrayList<Group>();
                    for (String group : request.getParameterValues(login + "_group")) {
                        newGroups.add(factoryGroup.create(group));
                    }
                    break;
                case 1:
                    myUser.setLogin(value);
                    break;
                case 2:
                    myUser.setName(value);
                    break;
                case 3:
                    myUser.setTeam(value);
                    break;
                case 4:
                    myUser.setRequest(value);
                    break;
            }
            try {
                if (newGroups != null) {
                    userGroupService.updateUserGroups(myUser, newGroups);

                    /**
                     * Adding Log entry.
                     */
                    ILogService logEventService = appContext.getBean(LogService.class);
                    IFactoryLog factoryLogEvent = appContext.getBean(FactoryLog.class);
                    try {
                        logEventService.insertLog(factoryLogEvent.create(0, request.getUserPrincipal().getName(), null, "/UpdateUserAjax", "UPDATE", "Updated user : " + login, "", ""));
                    } catch (Exception ex) {
                        Logger.log(UserService.class.getName(), Level.ERROR, ex.toString());
                    }

                } else {
                    userService.updateUser(myUser);
                }
                response.getWriter().print(value);
            } catch (QualityException ex) {
                response.getWriter().print(ex.getMessageError().getDescription());
            }
        } catch (QualityException ex) {
            response.getWriter().print(ex.getMessageError().getDescription());
        }

    }
}
