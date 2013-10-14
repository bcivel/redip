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

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            if (rs.first()) {
                String graph = rs.getString("graph") == null ? "" : rs.getString("graph");
                String type = rs.getString("type") == null ? "" : rs.getString("type");
                String title = rs.getString("title") == null ? "" : rs.getString("title");
                
                result.setGraph(graph);
                result.setTitle(title);
                result.setType(type);
                
            }
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;
    }
    
}
