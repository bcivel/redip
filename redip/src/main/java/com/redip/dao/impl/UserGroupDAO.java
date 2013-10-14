package com.redip.dao.impl;

import com.redip.dao.IUserGroupDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.Group;
import com.redip.entity.User;
import com.redip.factory.IFactoryGroup;
import com.redip.log.Logger;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
@Repository
public class UserGroupDAO implements IUserGroupDAO {

    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    private IFactoryGroup factoryGroup;

    @Override
    public boolean addGroupToUser(Group group, User user) {
        boolean bool = false;
        final String query = "INSERT INTO usergroup (Login, GroupName) VALUES (?, ?)";
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query);
            preStat.setString(1, user.getLogin());
            preStat.setString(2, group.getGroup());
            try {
                int res = preStat.executeUpdate();
                bool = res > 0;
            } catch (SQLException exception) {
                Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            this.databaseSpring.disconnect();
        }
        return bool;
    }

    @Override
    public boolean removeGroupFromUser(Group group, User user) {
        boolean bool = false;
        final String query = "DELETE FROM usergroup WHERE login = ? AND groupname = ?";
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query);
            preStat.setString(1, user.getLogin());
            preStat.setString(2, group.getGroup());
            try {
                int res = preStat.executeUpdate();
                bool = res > 0;
            } catch (SQLException exception) {
                Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            this.databaseSpring.disconnect();
        }
        return bool;
    }

    @Override
    public List<Group> findGroupByKey(String login) {
        List<Group> list = null;
        final String query = "SELECT groupname FROM usergroup WHERE login = ? ORDER BY groupname";
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query);
            preStat.setString(1, login);
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    list = new ArrayList<Group>();
                    while (resultSet.next()) {
                        Group group = factoryGroup.create(resultSet.getString("groupname"));
                        list.add(group);
                    }
                } catch (SQLException exception) {
                    Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(UserDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            this.databaseSpring.disconnect();
        }
        return list;
    }
}
