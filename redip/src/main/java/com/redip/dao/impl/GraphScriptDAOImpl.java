/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.MessageGeneral;
import com.redip.config.MessageGeneralEnum;
import com.redip.dao.IGraphScriptDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.GraphScript;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryGraphScript;
import com.redip.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class GraphScriptDAOImpl implements IGraphScriptDAO {

    @Autowired
    DatabaseSpring databaseSpring;
    @Autowired
    IFactoryGraphScript factoryGraphScript;
    
    @Override
    public List<GraphScript> findGraphScriptByTitle(String title){
        boolean throwException = true;
        List<GraphScript> result = new ArrayList();
        final String query = "SELECT * FROM graphscript i  WHERE i.title = ?";

        Logger.log(GraphScriptDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findGraphScriptByTitle");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                preStat.setString(1, title);
                ResultSet resultSet = preStat.executeQuery();
                try {
            
                    while (resultSet.next()) {
                        throwException = false;
                        int id = resultSet.getInt("idgraphscript");
                        String type = resultSet.getString("type");
                        String script = resultSet.getString("script");
                        result.add(factoryGraphScript.create(id, title, type, script));
                    }
               } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(GraphScriptDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findGraphScriptByTitle");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }
    
}
