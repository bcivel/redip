package com.redip.dao.impl;

import com.redip.dao.IParameterDAO;
import com.redip.database.DatabaseSpring;
import com.redip.config.MessageGeneral;
import com.redip.config.MessageGeneralEnum;
import com.redip.entity.Invariant;
import com.redip.entity.Parameter;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryParameter;
import com.redip.log.Logger;
import java.sql.Connection;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author bcivel
 */
@Repository
public class ParameterDAO implements IParameterDAO {

    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    private IFactoryParameter factoryParameter;

    @Override
    public Parameter findParameterByKey(String key) throws QualityException{
        boolean throwExep = false;
        Parameter result = null;
        final String query = "SELECT * FROM parameter p WHERE p.param = ? ";

        Logger.log(ParameterDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findParameterByKey");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query);
            preStat.setString(1, key);

            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    if (resultSet.first()) {
                        String value = resultSet.getString("value");
                        String desc = resultSet.getString("description");
                        result = factoryParameter.create(key, value, desc);
                    }else{
                        throwExep = true;
                    }
                } catch (SQLException exception) {
                    Logger.log(ParameterDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(ParameterDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(ParameterDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findParameterByKey");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;
    }
    
    @Override
    public List<Parameter> findAllParameter() throws QualityException{
        boolean throwExep = true;
        List<Parameter> result = null;
        Parameter paramet = null;
        final String query = "SELECT * FROM parameter p ";

        Logger.log(ParameterDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findAllParameter");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query);

            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    result = new ArrayList<Parameter>();
                    while (resultSet.next()) {
                        String param = resultSet.getString("param");
                        String value = resultSet.getString("value");
                        String desc = resultSet.getString("description");
                        paramet = factoryParameter.create(param, value, desc);
                        result.add(paramet);
                        throwExep = false;
                    }
                } catch (SQLException exception) {
                    Logger.log(ParameterDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(ParameterDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(ParameterDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findAllParameter");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;
    }

    @Override
    public String updateParameter(String param, String column, String value){
     String statusmessage = "";
        final String sql = "UPDATE parameter SET `"
                + column
                + "` = ? WHERE parameter LIKE ?";
       
        Logger.log(ParameterDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateParameter");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            try {
                preStat.setString(1, value);
                preStat.setString(2, param);
                int res = preStat.executeUpdate(sql);
                
                try {
                
                    if (res > 0){
                        statusmessage = "[Success] Param:"+param+" -- Field "+column+" has successfully been updated with value "+value;
                     }
                     else {
                         statusmessage = "[Error] Param:"+param+" -- Field "+column+" has not been updated with value "+value;
                        }
                } catch (Exception exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                }
         
            } catch (SQLException exception) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(ParameterDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateParameter");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return statusmessage;
    }
    
    
}
