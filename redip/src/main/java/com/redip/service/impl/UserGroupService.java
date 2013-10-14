package com.redip.service.impl;

import com.redip.dao.IUserGroupDAO;
import com.redip.entity.Group;
import com.redip.config.MessageGeneral;
import com.redip.config.MessageGeneralEnum;
import com.redip.entity.User;
import com.redip.exception.QualityException;
import com.redip.service.IUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {Insert class description here}
 *
 * @author Tiago Bernardes
 * @version 1.0, 14/08/2013
 * @since 2.0.0
 */
@Service
public class UserGroupService implements IUserGroupService {

    @Autowired
    private IUserGroupDAO userGroupDAO;

    @Override
    public void updateUserGroups(User user, List<Group> newGroups) throws QualityException {

        List<Group> oldGroups = this.findGroupByKey(user.getLogin());

        //delete if don't exist in new
        for (Group old : oldGroups) {
            if (!newGroups.contains(old)) {
                this.removeGroupFromUser(old, user);
            }
        }
        //insert if don't exist in old
        for (Group group : newGroups) {
            if (!oldGroups.contains(group)) {
                this.addGroupToUser(group, user);
            }
        }
    }

    private void addGroupToUser(Group group, User user) throws QualityException {
        if (!userGroupDAO.addGroupToUser(group, user)) {
            //TODO define message => error occur trying to add group user
            throw new QualityException(new MessageGeneral(MessageGeneralEnum.NO_DATA_FOUND));
        }
    }

    private void removeGroupFromUser(Group group, User user) throws QualityException {
        if (!userGroupDAO.removeGroupFromUser(group, user)) {
            //TODO define message => error occur trying to delete group user
            throw new QualityException(new MessageGeneral(MessageGeneralEnum.NO_DATA_FOUND));
        }
    }

    @Override
    public List<Group> findGroupByKey(String login) throws QualityException {
        List<Group> list = userGroupDAO.findGroupByKey(login);
        if (list == null) {
            //TODO define message => error occur trying to find group user
            throw new QualityException(new MessageGeneral(MessageGeneralEnum.NO_DATA_FOUND));
        }
        return list;
    }
}
