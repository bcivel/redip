/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.user;

import com.redip.entity.Group;
import com.redip.entity.User;
import com.redip.exception.QualityException;
import com.redip.log.Logger;
import com.redip.service.IUserGroupService;
import com.redip.service.IUserService;
import com.redip.service.impl.UserGroupService;
import com.redip.service.impl.UserService;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ip100003
 */
public class UserGetUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String echo = request.getParameter("sEcho");

        JSONArray data = new JSONArray(); //data that will be shown in the table

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IUserService userService = appContext.getBean(UserService.class);
        IUserGroupService userGroupService = appContext.getBean(UserGroupService.class);
        try {
            JSONObject jsonResponse = new JSONObject();
            try {
                for (User myUser : userService.findallUser()) {
                    JSONObject u = new JSONObject();
                    u.put("login", myUser.getLogin());
                    u.put("name", myUser.getName());
                    u.put("team", myUser.getTeam());
                    u.put("request", myUser.getRequest());

                    JSONArray groups = new JSONArray();
                    for (Group group : userGroupService.findGroupByKey(myUser.getLogin())) {
                        groups.put(group.getGroup());
                    }
                    u.put("group", groups);

                    data.put(u);
                }
            } catch (QualityException ex) {
                response.setContentType("text/html");
                response.getWriter().print(ex.getMessageError().getDescription());

            }
            jsonResponse.put("aaData", data);
            jsonResponse.put("sEcho", echo);
            jsonResponse.put("iTotalRecords", data.length());
            jsonResponse.put("iTotalDisplayRecords", data.length());
            response.setContentType("application/json");
            response.getWriter().print(jsonResponse.toString());
        } catch (JSONException e) {
            Logger.log(UserGetUsers.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }
}
