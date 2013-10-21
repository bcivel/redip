/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesImpactDAO;
import com.redip.database.DatabaseSpring;
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
public class QualityNonconformitiesImpactDAOImpl implements IQualityNonconformitiesImpactDAO {
    

@Autowired
   private DatabaseSpring databaseSpring;
    
@Override
    public List<QualityNonconformitiesImpact> getAllNonconformitiesImpact() {
        List<QualityNonconformitiesImpact> nonconformitiesImpact = new ArrayList<QualityNonconformitiesImpact>();
        StringBuilder query = new StringBuilder();
               query.append("SELECT idqualitynonconformitiesimpact, idqualitynonconformities, Country, Application, ");
               query.append(" StartDate, StartTime, EndDate, EndTime, ");
               query.append(" ImpactOrCost, orderImpacted, errorPages, timeConsumed ");
               query.append(" FROM qualitynonconformitiesimpact");
  
        QualityNonconformitiesImpact nonconformitiesImpacttoadd;
                
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getAllNonconformitiesImpact");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
                    while (resultSet.next()) {
                    nonconformitiesImpacttoadd = new QualityNonconformitiesImpact();
                    nonconformitiesImpacttoadd.setIdqualitynonconformitiesimpact(resultSet.getInt(1));
                    nonconformitiesImpacttoadd.setIdqualitynonconformities( resultSet.getInt(2) );
                    nonconformitiesImpacttoadd.setCountry( resultSet.getString(3) == null ? "" : resultSet.getString(3) );
                    nonconformitiesImpacttoadd.setApplication( resultSet.getString(4)== null ? "" : resultSet.getString(4) );
                    nonconformitiesImpacttoadd.setStartDate(resultSet.getString(5)== null ? "" : resultSet.getString(5) );
                    nonconformitiesImpacttoadd.setStartTime(resultSet.getString(6)== null ? "" : resultSet.getString(6)  );
                    nonconformitiesImpacttoadd.setEndDate( resultSet.getString(7)== null ? "" : resultSet.getString(7));
                    nonconformitiesImpacttoadd.setEndTime( resultSet.getString(8)== null ? "" : resultSet.getString(8) );
                    nonconformitiesImpacttoadd.setImpactOrCost( resultSet.getString(9) == null ? "" : resultSet.getString(9));
                    nonconformitiesImpacttoadd.setOrderImpacted(resultSet.getString(10) == null ? "" : resultSet.getString(10));
                    nonconformitiesImpacttoadd.setErrorPages(resultSet.getString(11) == null ? "" : resultSet.getString(11));
                    nonconformitiesImpacttoadd.setTimeConsumed(resultSet.getString(12) == null ? "" : resultSet.getString(12));

                    nonconformitiesImpact.add(nonconformitiesImpacttoadd);
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getAllNonconformitiesImpact");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformitiesImpact;
    }



@Override
    public QualityNonconformitiesImpact getNumberOfNonconformitiesImpact(){
        QualityNonconformitiesImpact nonconformitiesImpacttoadd = new QualityNonconformitiesImpact();
        StringBuilder query = new StringBuilder();
               query.append("SELECT count(*) FROM qualitynonconformitiesimpact");
               
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getNumberOfNonconformitiesImpact");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            if (resultSet.first()) {
                nonconformitiesImpacttoadd.setCount(resultSet.getInt(1));
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getNumberOfNonconformitiesImpact");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformitiesImpacttoadd;

}
        
@Override
    public List<QualityNonconformitiesImpact> getImpactForNonconformitiesId(int id) {
    List<QualityNonconformitiesImpact> nonconformitiesImpact = new ArrayList<QualityNonconformitiesImpact>();    
    QualityNonconformitiesImpact nonconformitiesImpacttoadd;
        StringBuilder query = new StringBuilder();
               query.append("SELECT idqualitynonconformitiesimpact, idqualitynonconformities, Country, Application,  ");
               query.append(" StartDate, StartTime, EndDate, EndTime, ");
               query.append(" ImpactOrCost, orderImpacted, errorPages, timeConsumed ");
               query.append(" FROM qualitynonconformitiesimpact where idqualitynonconformities = '");
               query.append(id);
               query.append("'");
        
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getImpactForNonconformitiesId");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            while (resultSet.next()) {
                nonconformitiesImpacttoadd = new QualityNonconformitiesImpact();
                nonconformitiesImpacttoadd.setIdqualitynonconformitiesimpact( resultSet.getInt(1) );
                nonconformitiesImpacttoadd.setIdqualitynonconformities( resultSet.getInt(2) );
                nonconformitiesImpacttoadd.setCountry( resultSet.getString(3) == null ? "" : resultSet.getString(3));
                nonconformitiesImpacttoadd.setApplication( resultSet.getString(4) == null ? "" : resultSet.getString(4));
                nonconformitiesImpacttoadd.setStartDate( resultSet.getString(5) == null ? "" : resultSet.getString(5));
                nonconformitiesImpacttoadd.setStartTime( resultSet.getString(6) == null ? "" : resultSet.getString(6));
                nonconformitiesImpacttoadd.setEndDate( resultSet.getString(7) == null ? "" : resultSet.getString(7));
                nonconformitiesImpacttoadd.setEndTime( resultSet.getString(8) == null ? "" : resultSet.getString(8));
                nonconformitiesImpacttoadd.setImpactOrCost( resultSet.getString(9) == null ? "" : resultSet.getString(9));
                nonconformitiesImpacttoadd.setOrderImpacted(resultSet.getString(10) == null ? "" : resultSet.getString(10));
                nonconformitiesImpacttoadd.setErrorPages(resultSet.getString(11) == null ? "" : resultSet.getString(11));
                nonconformitiesImpacttoadd.setTimeConsumed(resultSet.getString(12) == null ? "" : resultSet.getString(12));
                nonconformitiesImpact.add(nonconformitiesImpacttoadd);
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getImpactForNonconformitiesId");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformitiesImpact;
    }

@Override
    public String addNonconformityImpact(QualityNonconformitiesImpact qualitynci) {
        String statusmessage = "";
        final String sql = "INSERT INTO qualitynonconformitiesimpact (  idqualitynonconformities, Application "
                + ",StartDate,StartTime, EndDate, EndTime, ImpactOrCost, orderImpacted, errorPages, timeConsumed  "
                + " ) values (?,?,?,?,?,?,?,?,?,?)";
                
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityImpact");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                preStat.setString(1, String.valueOf(qualitynci.getIdqualitynonconformities()));
                preStat.setString(2, qualitynci.getApplication()== null ? "" : qualitynci.getApplication());
                preStat.setString(3, qualitynci.getStartDate()== null ? "" : qualitynci.getStartDate());
                preStat.setString(4, qualitynci.getStartTime()== null ? "" : qualitynci.getStartTime());
                preStat.setString(5, qualitynci.getEndDate()== null ? "" : qualitynci.getEndDate());
                preStat.setString(6, qualitynci.getEndTime()== null ? "" : qualitynci.getEndTime());
                preStat.setString(7, qualitynci.getImpactOrCost()== null ? "" : qualitynci.getImpactOrCost());
                preStat.setString(8, qualitynci.getOrderImpacted()== null ? "" : qualitynci.getOrderImpacted());
                preStat.setString(9, qualitynci.getErrorPages()== null ? "" : qualitynci.getErrorPages());
                preStat.setString(10, qualitynci.getTimeConsumed()== null ? "" : qualitynci.getTimeConsumed());
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0){
                    statusmessage = StatusMessage.SUCCESS_NONCONFORMITYIMPACTCREATED;        }
                    else {
                    statusmessage = StatusMessage.ERROR_NONCONFORMITYIMPACTCREATEDCREATED;
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from addNonconformityImpact");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        
        return statusmessage;
    }

@Override
public String updateNonconformitiesImpact(Integer idnci, String column, String value){
     String statusmessage = "";
        final String sql = "UPDATE qualitynonconformitiesimpact SET `"
                + column
                + "` = ? WHERE Idqualitynonconformitiesimpact LIKE ?";
                
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateNonconformitiesImpact");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                preStat.setString(1, value);
                preStat.setString(2, String.valueOf(idnci));
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0){
                    statusmessage = "[Success] IDNCI:"+idnci+" -- Field "+column+" has successfully been updated with value "+value;
                    }
                    else {
                    statusmessage = "[Error] IDNCI:"+idnci+" -- Field "+column+" has not been updated with value "+value;
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from updateNonconformitiesImpact");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return statusmessage;
}

    @Override
    public String deleteNonconformitiesImpact(Integer idnci) {
     String statusmessage = "";
        final String sql = "Delete from qualitynonconformitiesimpact "
                + " WHERE Idqualitynonconformitiesimpact = '"
                + idnci
                + "'";
        
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from deleteNonconformitiesImpact");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0){
                    statusmessage = "[Success] IDNCI:"+idnci+" has successfully been deleted";
                    }
                    else {
                    statusmessage = "[Error] IDNCI:"+idnci+" has not been deleted";
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

        return statusmessage; }

    @Override
    public QualityNonconformitiesImpact getMaxId() {
        QualityNonconformitiesImpact nonconformitiesImpact = null;
        StringBuilder query = new StringBuilder();
               query.append("SELECT max(idqualitynonconformitiesimpact) FROM qualitynonconformitiesimpact");
  
        Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getMaxId");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            if (resultSet.next()) {
                nonconformitiesImpact = new QualityNonconformitiesImpact();
                nonconformitiesImpact.setIdqualitynonconformitiesimpact(resultSet.getInt(1));
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
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getMaxId");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformitiesImpact;
    }

}