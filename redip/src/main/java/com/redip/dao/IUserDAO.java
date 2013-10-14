package com.redip.dao;

import com.redip.entity.User;
import com.redip.exception.QualityException;

import java.util.List;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
public interface IUserDAO {

    /**
     * @param login
     * @return the user that correspond to the login.
     * @throws CerberusException in case the user is not found.
     */
    User findUserByKey(String login) throws QualityException;

    /**
     * @return a list of all users.
     * @throws CerberusException in case no user can be found.
     */
    List<User> findAllUser() throws QualityException;

    /**
     * Insert user into the database.
     *
     * @param user
     * @return the inserted user
     * @throws CerberusException if we did not manage to insert the user.
     */
    public boolean insertUser(User user);

    /**
     * delete user from the database.
     *
     * @param user
     * @throws CerberusException if user could not be removed.
     */
    public boolean deleteUser(User user);

    /**
     * update user that correspond to the user.getUserID.
     *
     * @param user
     * @return the updated user
     * @throws CerberusException if the user could not be updated.
     */
    public boolean updateUser(User user);

    /**
     * @param user
     * @param password as the new value of the password.
     * @return the user updated with the new password.
     * @throws CerberusException if the password cannot be updated.
     */
    public User updateUserPassword(User user, String password) throws QualityException;

    /**
     * @param user
     * @param password
     * @return true if password match and false if password does not match.
     */
    boolean verifyPassword(User user, String password);

}
