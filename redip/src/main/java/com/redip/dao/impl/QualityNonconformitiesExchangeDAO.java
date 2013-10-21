/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesExchangeDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.QualityNonconformitiesExchange;
import com.redip.factory.IFactoryQualityNonconformitiesExchange;
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
public class QualityNonconformitiesExchangeDAO implements IQualityNonconformitiesExchangeDAO {

    @Autowired
    DatabaseSpring databaseSpring;
    @Autowired
    IFactoryQualityNonconformitiesExchange factoryNonconformitiesExchange;
    
    @Override
    public List<QualityNonconformitiesExchange> findQualityNonconformitiesActionByID(Integer ncid) {
        List<QualityNonconformitiesExchange> result = new ArrayList<QualityNonconformitiesExchange>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesexchange, ");
        query.append(" `date`,  ");
        query.append(" exchangetitle, exchangecontent, user ");
        query.append(" FROM qualitynonconformitiesexchange ");
        query.append("where idqualitynonconformities like ?");

        ArrayList<String> al = new ArrayList<String>();
        al.add(String.valueOf(ncid));
        
        Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findQualityNonconformitiesActionByID");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {

            while (resultSet.next()) {
                Integer idqualitynonconformitiesexchange = resultSet.getString("idqualitynonconformitiesexchange") == null ? 0 : resultSet.getInt("idqualitynonconformitiesexchange");
                String date = resultSet.getString("date") == null ? "" : resultSet.getString("date");
                String exchangetitle = resultSet.getString("exchangetitle") == null ? "" : resultSet.getString("exchangetitle");
                String exchangecontent = resultSet.getString("exchangecontent") == null ? "" : resultSet.getString("exchangecontent");
                String user = resultSet.getString("user") == null ? "" : resultSet.getString("user");
                result.add(factoryNonconformitiesExchange.create(idqualitynonconformitiesexchange, ncid, date, user, exchangetitle, exchangecontent));
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
                    Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findQualityNonconformitiesActionByID");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }


        return result;
    }

    @Override
    public String addNonconformityExchange(QualityNonconformitiesExchange qnc) {
        String statusmessage = "";
        
        StringBuilder sql = new StringBuilder(); 
        sql.append("INSERT INTO qualitynonconformitiesexchange ( `idQualityNonconformities`,");
        sql.append(" `date`, `exchangecontent`, `exchangetitle`, `user`) ");
        sql.append(" values (?,?,?,?,?)");
        
        
        Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityExchange");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                preStat.setString(1, qnc.getIdQualityNonconformities() == null ? "" : String.valueOf(qnc.getIdQualityNonconformities()));
                preStat.setString(2, qnc.getDate() == null ? "" : qnc.getDate());
                preStat.setString(3, qnc.getExchangeContent() == null ? "" : qnc.getExchangeContent());
                preStat.setString(4, qnc.getExchangeTitle() == null ? "" : qnc.getExchangeTitle());
                preStat.setString(5, qnc.getUser() == null ? "" : qnc.getUser());
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0) {
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
                    Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityExchange");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }


        return statusmessage;
    }

    @Override
    public String updateQualityNonConformitiesExchange(Integer idnca, String column, String value) {
        String statusmessage = "";
        final String sql = "UPDATE qualitynonconformitiesexchange SET ? = ? WHERE Idqualitynonconformitiesexchange LIKE ?";
        

        Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateQualityNonConformitiesExchange");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                preStat.setString(1, column);
                preStat.setString(2, value);
                preStat.setString(3, String.valueOf(idnca));
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0) {
                        statusmessage = "[Success] IDNC:"+idnca+" -- Field "+column+" has successfully been updated with value "+value;
                    } else {
                        statusmessage = "[Error] IDNC:"+idnca+" -- Field "+column+" has not been updated with value "+value;
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
                    Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from updateQualityNonConformitiesExchange");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return statusmessage;
    }
    
}
