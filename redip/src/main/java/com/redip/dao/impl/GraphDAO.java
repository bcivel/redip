/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.dao.IGraphDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.Graph;
import com.redip.entity.QualityNonconformities;
import com.redip.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bcivel
 */
@Repository
public class GraphDAO implements IGraphDAO{

    @Autowired
    DatabaseSpring databaseSpring;
    
    @Override
    public Graph findGraphByID(String graphName) {
        Graph result = new Graph();
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT graph, type, title FROM graph where graph = '");
        query.append(graphName);
        query.append("'");

        Logger.log(LogDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findGraphByID");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    if (resultSet.first()) {
                    String graph = resultSet.getString("graph") == null ? "" : resultSet.getString("graph");
                    String type = resultSet.getString("type") == null ? "" : resultSet.getString("type");
                    String title = resultSet.getString("title") == null ? "" : resultSet.getString("title");
                
                    result.setGraph(graph);
                    result.setTitle(title);
                    result.setType(type);
                
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
                    Logger.log(LogDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findGraphByID");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }
    
}
