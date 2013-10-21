/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.User;
import com.redip.factory.IFactoryUser;
import org.springframework.stereotype.Service;

/**
 * @author vertigo
 */
@Service
public class FactoryUser implements IFactoryUser {

    @Override
    public User create(int userID, String login, String password, String request, String name, String team) {
        User newUser = new User();
        newUser.setUserID(userID);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRequest(request);
        newUser.setName(name);
        newUser.setTeam(team);
        return newUser;
    }
}
