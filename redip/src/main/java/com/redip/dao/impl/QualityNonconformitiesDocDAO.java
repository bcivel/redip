/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesDocDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.QualityNonconformitiesDoc;
import com.redip.entity.QualityNonconformitiesImpact;
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
public class QualityNonconformitiesDocDAO implements IQualityNonconformitiesDocDAO{

    @Autowired
    private DatabaseSpring databaseSpring;
     
    @Override
    public String addNonconformityDoc(QualityNonconformitiesDoc qualityNonconformityDoc) {
        String statusmessage = "";
        final String sql = "INSERT INTO qualitynonconformitiesdoc ( idqualitynonconformities, "
                + "linkToDoc) values (?,?)";
        Integer idqnc = qualityNonconformityDoc.getIdQualityNonconformities() == null ? 0 : qualityNonconformityDoc.getIdQualityNonconformities();
        String linkToDoc = qualityNonconformityDoc.getLinkToDoc() == null ? "" : qualityNonconformityDoc.getLinkToDoc();
        
        Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityDoc");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            try {
                preStat.setInt(1, idqnc);
                preStat.setString(2, linkToDoc);
                
                int res = preStat.executeUpdate();
                try {
                    
                    if ( res > 0) {
                    statusmessage = StatusMessage.SUCCESS_NONCONFORMITYCREATED;
                    } else {
                    statusmessage = StatusMessage.ERROR_NONCONFORMITYCREATEDCREATED;
                    }
                } catch (Exception exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
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
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from addNonconformity");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return statusmessage;}

    @Override
    public String deleteNonconformityDoc(int idQualityNonconformityDoc) {
        String statusmessage = "";
        final String sql = "Delete from qualitynonconformitiesdoc "
                + " WHERE Idqualitynonconformitiesdoc = '"
                + idQualityNonconformityDoc
                + "'";
        
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from deleteNonconformitiesDoc");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0){
                    statusmessage = "[Success] IDNCI:"+idQualityNonconformityDoc+" has successfully been deleted";
                    }
                    else {
                    statusmessage = "[Error] IDNCI:"+idQualityNonconformityDoc+" has not been deleted";
                    }
                } catch (Exception exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from deleteNonconformitiesImpact");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return statusmessage;
    }

    @Override
    public List<QualityNonconformitiesDoc> getDocLink(int idQualityNonconformity) {
        List<QualityNonconformitiesDoc> nonconformitiesDoc = new ArrayList<QualityNonconformitiesDoc>();    
        QualityNonconformitiesDoc ncDoc;
        StringBuilder query = new StringBuilder();
               query.append("SELECT idqualitynonconformitiesdoc, idqualitynonconformities, linktodoc ");
               query.append(" FROM qualitynonconformitiesdoc where idqualitynonconformities = '");
               query.append(idQualityNonconformity);
               query.append("'");
        
        Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getDocLink");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            while (resultSet.next()) {
                ncDoc = new QualityNonconformitiesDoc();
                ncDoc.setIdQualityNonconformitiesDoc(resultSet.getInt(1) );
                ncDoc.setIdQualityNonconformities(resultSet.getInt(2) );
                ncDoc.setLinkToDoc(resultSet.getString(3) == null ? "" : resultSet.getString(3));
                nonconformitiesDoc.add(ncDoc);
            }
        } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getDocLink");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDocDAO.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformitiesDoc;
    }
    
}
