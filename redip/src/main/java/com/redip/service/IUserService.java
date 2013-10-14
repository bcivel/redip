/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.User;
import com.redip.exception.QualityException;

import java.util.List;

/**
 * @author vertigo
 */
public interface IUserService {

    /**
     * @param login
     * @return the user that match the login
     * @throws CerberusException
     */
    User findUserByKey(String login) throws QualityException;

    /**
     * @return a list of all the users
     * @throws CerberusException
     */
    List<User> findallUser() throws QualityException;

    /**
     * @param user
     * @return
     * @throws CerberusException
     */
    void insertUser(User user) throws QualityException;

    /**
     * @param user
     * @return
     * @throws CerberusException
     */
    void deleteUser(User user) throws QualityException;

    /**
     * @param user
     * @throws CerberusException
     */
    void updateUser(User user) throws QualityException;

    /**
     * @param user
     * @param currentPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     * @throws CerberusException
     */
    User updateUserPassword(User user, String currentPassword, String newPassword, String confirmPassword) throws QualityException;

    /**
     * @param user
     * @param password
     * @return
     */
    boolean verifyPassword(User user, String password);
}
