package com.redip.dao.impl;

import com.redip.dao.ILogDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.Log;
import com.redip.config.MessageGeneral;
import com.redip.config.MessageGeneralEnum;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryLog;
import com.redip.log.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bcivel
 */
@Repository
public class LogDAO implements ILogDAO {

    /**
     * Description of the variable here.
     */
    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    private IFactoryLog factoryLog;

    @Override
    public List<Log> findAllLogEvent() throws QualityException {
        List<Log> list = null;
        boolean throwExe = true;
        final String query = "SELECT * FROM log order by logid ; ";
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query);
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    list = new ArrayList<Log>();
                    while (resultSet.next()) {
                        throwExe = false;
                        long logId = resultSet.getLong("logid");
                        String time = resultSet.getString("time");
                        String user = resultSet.getString("user");
                        String type = resultSet.getString("type");
                        String table = resultSet.getString("table");
                        String row = resultSet.getString("row");
                        String field = resultSet.getString("field");
                        String value = resultSet.getString("value");
                        Log myLogEvent = factoryLog.create(logId, user, time, type, table, row, field, value);
                        list.add(myLogEvent);
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
        if (throwExe) {
            throw new QualityException(new MessageGeneral(MessageGeneralEnum.NO_DATA_FOUND));
        }
        return list;
    }

    @Override
    public List<Log> findAllLogEvent(int start, int amount, String column, String dir, String searchTerm, String individualSearch) throws QualityException {
        List<Log> list = null;
        boolean throwExe = true;
        StringBuilder gSearch = new StringBuilder();
        StringBuilder query = new StringBuilder();
        String searchSQL = "";
        
        query.append("SELECT * FROM log ");
        
        gSearch.append(" where (logid like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or time like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or user like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or type like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or table like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or row like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or field like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or value like '%");
        gSearch.append(searchTerm);
        gSearch.append("%')");
        
        if(!searchTerm.equals("") && !individualSearch.equals("")){
            searchSQL = gSearch.toString() + " and " + individualSearch;
        }
        else if(!individualSearch.equals("")){
            searchSQL = " where " + individualSearch;
        }else if(!searchTerm.equals("")){
            searchSQL=gSearch.toString();
        }
       
        query.append(searchSQL);
        query.append(" order by ");
        query.append(column);
        query.append(" ");
        query.append(dir);
        query.append(" limit ");
        query.append(start);
        query.append(" , ");
        query.append(amount);
        
        
        Logger.log(LogDAO.class.getName(), Level.DEBUG, query.toString());
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    list = new ArrayList<Log>();
                    while (resultSet.next()) {
                        throwExe = false;
                        long logId = resultSet.getLong("logid");
                        String time = resultSet.getString("time");
                        String user = resultSet.getString("user");
                        String type = resultSet.getString("type");
                        String table = resultSet.getString("table");
                        String row = resultSet.getString("row");
                        String field = resultSet.getString("field");
                        String value = resultSet.getString("value");
                        Log myLogEvent = factoryLog.create(logId, user, time, type, table, row, field, value);
                        list.add(myLogEvent);
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
        if (throwExe) {
            throw new QualityException(new MessageGeneral(MessageGeneralEnum.NO_DATA_FOUND));
        }
        return list;
    }

    @Override
    public Integer getNumberOfLogEvent() throws QualityException {
        boolean throwExe = true;
        final String query = "SELECT count(*) c FROM log ; ";
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query);
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    if (resultSet.first()) {
                        throwExe = false;
                        return resultSet.getInt("c");
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
        if (throwExe) {
            throw new QualityException(new MessageGeneral(MessageGeneralEnum.NO_DATA_FOUND));
        }
        return 0;
    }

    @Override
    public boolean insertLogEvent(Log logevent) {
        boolean bool = false;
        final String query = "INSERT INTO log (`time`, `user`, `type`, `table`, `row`, `field`, `value`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preStat = this.databaseSpring.connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preStat.setString(1, logevent.getTime());
            preStat.setString(2, logevent.getUser());
            preStat.setString(3, logevent.getType());
            preStat.setString(4, logevent.getTable());
            preStat.setString(5, logevent.getRow());
            preStat.setString(6, logevent.getField());
            preStat.setString(7, logevent.getValue());
            try {
                preStat.executeUpdate();
                ResultSet resultSet = preStat.getGeneratedKeys();
                try {
                    if (resultSet.first()) {
                        bool = true;
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
        return bool;
    }
}
