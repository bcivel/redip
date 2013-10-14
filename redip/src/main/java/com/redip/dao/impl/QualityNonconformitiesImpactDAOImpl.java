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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                nonconformitiesImpacttoadd = new QualityNonconformitiesImpact();
                nonconformitiesImpacttoadd.setIdqualitynonconformitiesimpact(rs.getInt(1));
                nonconformitiesImpacttoadd.setIdqualitynonconformities( rs.getInt(2) );
                nonconformitiesImpacttoadd.setCountry( rs.getString(3) == null ? "" : rs.getString(3) );
                nonconformitiesImpacttoadd.setApplication( rs.getString(4)== null ? "" : rs.getString(4) );
                nonconformitiesImpacttoadd.setStartDate(rs.getString(5)== null ? "" : rs.getString(5) );
                nonconformitiesImpacttoadd.setStartTime(rs.getString(6)== null ? "" : rs.getString(6)  );
                nonconformitiesImpacttoadd.setEndDate( rs.getString(7)== null ? "" : rs.getString(7));
                nonconformitiesImpacttoadd.setEndTime( rs.getString(8)== null ? "" : rs.getString(8) );
                nonconformitiesImpacttoadd.setImpactOrCost( rs.getString(9) == null ? "" : rs.getString(9));
                nonconformitiesImpacttoadd.setOrderImpacted(rs.getString(10) == null ? "" : rs.getString(10));
                nonconformitiesImpacttoadd.setErrorPages(rs.getString(11) == null ? "" : rs.getString(11));
                nonconformitiesImpacttoadd.setTimeConsumed(rs.getString(12) == null ? "" : rs.getString(12));
                
                nonconformitiesImpact.add(nonconformitiesImpacttoadd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
            }
            }
        
        
        return nonconformitiesImpact;
    }



@Override
    public QualityNonconformitiesImpact getNumberOfNonconformitiesImpact(){
        QualityNonconformitiesImpact nonconformitiesImpacttoadd = new QualityNonconformitiesImpact();
        StringBuilder query = new StringBuilder();
               query.append("SELECT count(*) FROM qualitynonconformitiesimpact");
               
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            if (rs.first()) {
                nonconformitiesImpacttoadd.setCount(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
        
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            while (rs.next()) {
                nonconformitiesImpacttoadd = new QualityNonconformitiesImpact();
                nonconformitiesImpacttoadd.setIdqualitynonconformitiesimpact( rs.getInt(1) );
                nonconformitiesImpacttoadd.setIdqualitynonconformities( rs.getInt(2) );
                nonconformitiesImpacttoadd.setCountry( rs.getString(3) == null ? "" : rs.getString(3));
                nonconformitiesImpacttoadd.setApplication( rs.getString(4) == null ? "" : rs.getString(4));
                nonconformitiesImpacttoadd.setStartDate( rs.getString(5) == null ? "" : rs.getString(5));
                nonconformitiesImpacttoadd.setStartTime( rs.getString(6) == null ? "" : rs.getString(6));
                nonconformitiesImpacttoadd.setEndDate( rs.getString(7) == null ? "" : rs.getString(7));
                nonconformitiesImpacttoadd.setEndTime( rs.getString(8) == null ? "" : rs.getString(8));
                nonconformitiesImpacttoadd.setImpactOrCost( rs.getString(9) == null ? "" : rs.getString(9));
                nonconformitiesImpacttoadd.setOrderImpacted(rs.getString(10) == null ? "" : rs.getString(10));
                nonconformitiesImpacttoadd.setErrorPages(rs.getString(11) == null ? "" : rs.getString(11));
                nonconformitiesImpacttoadd.setTimeConsumed(rs.getString(12) == null ? "" : rs.getString(12));
                nonconformitiesImpact.add(nonconformitiesImpacttoadd);
            }
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
        ArrayList<String> al = new ArrayList<String>();
        al.add(String.valueOf(qualitynci.getIdqualitynonconformities()));
        al.add(qualitynci.getApplication()== null ? "" : qualitynci.getApplication());
        al.add(qualitynci.getStartDate()== null ? "" : qualitynci.getStartDate());
        al.add(qualitynci.getStartTime()== null ? "" : qualitynci.getStartTime());
        al.add(qualitynci.getEndDate()== null ? "" : qualitynci.getEndDate());
        al.add(qualitynci.getEndTime()== null ? "" : qualitynci.getEndTime());
        al.add(qualitynci.getImpactOrCost()== null ? "" : qualitynci.getImpactOrCost());
        al.add(qualitynci.getOrderImpacted()== null ? "" : qualitynci.getOrderImpacted());
        al.add(qualitynci.getErrorPages()== null ? "" : qualitynci.getErrorPages());
        al.add(qualitynci.getTimeConsumed()== null ? "" : qualitynci.getTimeConsumed());
        
        try{
        databaseSpring.connect("QualityNonconformitiesImpactDAOImpl", "addNonconformityImpact");
        if (databaseSpring.update(sql, al) > 0){
        statusmessage = StatusMessage.SUCCESS_NONCONFORMITYIMPACTCREATED;        }
        else {
        statusmessage = StatusMessage.ERROR_NONCONFORMITYIMPACTCREATEDCREATED;
        }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect("QualityNonconformitiesImpactDAOImpl", "addNonconformityImpact");
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
        ArrayList<String> al = new ArrayList<String>();
        al.add(value);
        al.add(String.valueOf(idnci));
        
try{
        databaseSpring.connect();
        if (databaseSpring.update(sql, al) > 0){
        statusmessage = "[Success] IDNCI:"+idnci+" -- Field "+column+" has successfully been updated with value "+value;
        }
        else {
        statusmessage = "[Error] IDNCI:"+idnci+" -- Field "+column+" has not been updated with value "+value;
        }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
        
try{
        databaseSpring.connect();
        if (databaseSpring.execute(sql) == true){
        statusmessage = "[Success] IDNCI:"+idnci+" has successfully been deleted";
        }
        else {
        statusmessage = "[Error] IDNCI:"+idnci+" has not been deleted";
        }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
                }
            }
        return statusmessage; }

    @Override
    public QualityNonconformitiesImpact getMaxId() {
        QualityNonconformitiesImpact nonconformitiesImpact = null;
        StringBuilder query = new StringBuilder();
               query.append("SELECT max(idqualitynonconformitiesimpact) FROM qualitynonconformitiesimpact");
  
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            if (rs.next()) {
                nonconformitiesImpact = new QualityNonconformitiesImpact();
                nonconformitiesImpact.setIdqualitynonconformitiesimpact(rs.getInt(1));
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesImpactDAOImpl.class.getName(),Level.FATAL, ""+ ex);
            }
            }
        
        
        return nonconformitiesImpact;
    }

}