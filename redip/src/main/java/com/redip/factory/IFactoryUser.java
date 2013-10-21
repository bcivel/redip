/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.User;

/**
 * @author vertigo
 */
public interface IFactoryUser {

    /**
     * @param userID            Internal ID of the user
     * @param login             login name of the user.
     * @param password          Password of the user
     * @param request           Y if the user needs to change the password on next login
     * @param name              Name of the user
     * @param team              Team the user belong to.
     * @return A User.
     */
    User create(int userID, String login, String password, String request, String name, String team);

}
