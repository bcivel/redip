package com.redip.dao;

import com.redip.entity.Group;
import com.redip.entity.User;

import java.util.List;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
public interface IUserGroupDAO {

    /**
     * Adding the group to the user
     *
     * @param group
     * @param user
     * @return true if remove successfully amd false if an error occur
     */
    public boolean addGroupToUser(Group group, User user);

    /**
     * Remove the group from the user.
     *
     * @param group
     * @param user
     * @return true if remove successfully amd false if an error occur
     */
    public boolean removeGroupFromUser(Group group, User user);

    /**
     * @param login
     * @return a list of group user that correspond to the login.
     */
    public List<Group> findGroupByKey(String login);
}
