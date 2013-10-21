package com.redip.servlet;

import com.redip.entity.Group;
import com.redip.entity.User;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryGroup;
import com.redip.factory.IFactoryLog;
import com.redip.factory.IFactoryUser;
import com.redip.factory.impl.FactoryGroup;
import com.redip.factory.impl.FactoryLog;
import com.redip.factory.impl.FactoryUser;
import com.redip.service.ILogService;
import com.redip.service.IUserGroupService;
import com.redip.service.IUserService;
import com.redip.service.impl.LogService;
import com.redip.service.impl.UserGroupService;
import com.redip.service.impl.UserService;
import com.redip.util.ParameterParserUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Tiago Bernardes <tbernardes@redoute.pt>
 * @version 2.0
 * @since 2011-03-28
 */
public class UserAdd extends HttpServlet {

    /**
     * Create a new User.
     * <p/>
     * Use {@link UserService#insertUser(User user)} to create a user. Parse the
     * login, name, group and requestPassword from the HttpServletRequest and
     * use the class UserService to create a new user. If the user is
     * successfully created the servlet return the login information so the data
     * table can add the new user to the table. If the user can't be created an
     * ERROR is return to be shown on page.
     *
     * @param request http servlet request
     * @param response http servlet response
     * @see com.redip.service.impl.UserService
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO create class Validator to validate all parameter from page
        String login = request.getParameter("login").replaceAll("'", "");
        if (login.length() > 10) {
            login = login.substring(0, 10);
        }
        String name = ParameterParserUtil.parseStringParam(request.getParameter("name"), "");
        String password = "";
        String newPassword = ParameterParserUtil.parseStringParam(request.getParameter("newPassword"), "Y");
        String team = ParameterParserUtil.parseStringParam(request.getParameter("team"), "");
        String defaultSystem = ParameterParserUtil.parseStringParam(request.getParameter("defaultSystem"), "");

        IFactoryGroup factoryGroup = new FactoryGroup();
        List<Group> groups = new ArrayList<Group>();
        for (String group : request.getParameterValues("groups")) {
            groups.add(factoryGroup.create(group));
        }

        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IUserService userService = appContext.getBean(UserService.class);
        IUserGroupService userGroupService = appContext.getBean(UserGroupService.class);
        IFactoryUser factory = appContext.getBean(FactoryUser.class);

        /**
         * Creating user.
         */
        User myUser = factory.create(0, login, password, newPassword, name, team);
        try {
            userService.insertUser(myUser);
            userGroupService.updateUserGroups(myUser, groups);

            /**
             * Adding Log entry.
             */
            ILogService logEventService = appContext.getBean(LogService.class);
            IFactoryLog factoryLogEvent = appContext.getBean(FactoryLog.class);
            try {
                logEventService.insertLog(factoryLogEvent.create(0, request.getUserPrincipal().getName(), null, "/AddUserAjax", "CREATE", "Insert user : " + login, "", ""));
            } catch (Exception ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.ERROR, null, ex);
            }

            response.getWriter().print(myUser.getLogin());
        } catch (QualityException myexception) {
            response.getWriter().print(myexception.getMessageError().getDescription());
        }
    }
}
