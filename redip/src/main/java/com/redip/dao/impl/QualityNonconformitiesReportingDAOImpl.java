/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.dao.IQualityNonconformitiesReportingDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.calculated.NonconformitiesReporting;
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
public class QualityNonconformitiesReportingDAOImpl implements IQualityNonconformitiesReportingDAO {

@Autowired
   private DatabaseSpring databaseSpring;
    
@Override
    public List<NonconformitiesReporting> getNonconformitiesPerResponsabilities(String fromDate, String toDate, String country) {
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("select sum(b.impactOrCost), responsabilities from qualitynonconformities a ");
               query.append("join qualitynonconformitiesimpact b on a.idqualitynonconformities=b.idqualitynonconformities ");
               query.append(" where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append("group by responsabilities");
               
               NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
             
                    while (resultSet.next()) {
                    ncPerRespToAdd = new NonconformitiesReporting();
                    ncPerRespToAdd.setNumberOfLostOrders( resultSet.getInt(1) == 0 ? 0 : resultSet.getInt(1) );
                    ncPerRespToAdd.setResponsabilities( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                    ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        
        return ncPerResp;
    }
    
@Override
    public int numberOfNonconformities(String fromDate, String toDate, String country) {
        int numberOfNC = 0;
        StringBuilder query = new StringBuilder();
               query.append("SELECT count(*) from qualitynonconformitiesimpact ");
               query.append(" where StartDate >= '");
               query.append(fromDate);
               query.append("' and StartDate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append("group by idqualitynonconformities");
  
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            resultSet.first();
            numberOfNC=resultSet.getInt(1);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return numberOfNC;
    }

@Override
    public List<NonconformitiesReporting> getNonconformitiesPerProblemCategory(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("select count(distinct(a.idqualitynonconformities)), problemcategory ");
               query.append(" from qualitynonconformities a join qualitynonconformitiesimpact b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append("group by problemcategory");

        NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( resultSet.getInt(1) == 0 ? 0 : resultSet.getInt(1) );
                ncPerRespToAdd.setProblemCategory( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        
        return ncPerResp;
    }
    
@Override
    public List<NonconformitiesReporting> getNonconformitiesPerRootCause(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("select sum(b.impactOrCost), rootcausecategory from qualitynonconformities a ");
               query.append(" join qualitynonconformitiesimpact b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append("group by rootcausecategory");
  
        NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfLostOrders( resultSet.getInt(1) == 0 ? 0 : resultSet.getInt(1) );
                ncPerRespToAdd.setRootCauseCategory( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return ncPerResp;
    }

@Override
    public int orderLost(String fromDate, String toDate, String country){
        int orderLost = 0;
        StringBuilder query = new StringBuilder();
               query.append("select sum(b.impactOrCost) from qualitynonconformities a ");
               query.append(" join qualitynonconformitiesimpact b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
  
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            if (resultSet.next()) {
                orderLost = resultSet.getInt(1);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return orderLost;
    }

    
@Override
    public String unavailability(String fromDate, String toDate, String country){
        String unavail = "";
        StringBuilder query = new StringBuilder();
               query.append("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(");
               query.append("concat(a.EndDate, \" \",a.EndTime), concat(a.StartDate, \" \",a.StartTime)))))");
               query.append(" FROM qualitynonconformitiesimpact a join qualitynonconformities b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where a.startdate >= '");
               query.append(fromDate);
               query.append("' and a.startdate <= '");
               query.append(toDate);
               query.append("' and b.problemCategory='UNAVAILABILITY' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and a.country = '");
               query.append(country);
               query.append("' ");
               }


        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            resultSet.first();
            unavail=resultSet.getString(1);
                        
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return unavail;
    }
        
 @Override
    public List<NonconformitiesReporting> getRootCauseDescriptionList(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> rcDescList = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("select b.startdate,");
               query.append("SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(");
               query.append("concat(b.EndDate, \" \",b.EndTime), concat(b.StartDate, \" \",b.StartTime)))))");
               query.append(", severity, a.rootcausecategory,a.rootcausedescription, sum(a.impactorcost), a.idqualitynonconformities from qualitynonconformities a ");
               query.append(" join qualitynonconformitiesimpact b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append("group by a.idqualitynonconformities");
  
        NonconformitiesReporting rcDescListToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                rcDescListToAdd = new NonconformitiesReporting();
                rcDescListToAdd.setStartDate( resultSet.getString(1) == null ? "" : resultSet.getString(1) );
                rcDescListToAdd.setUnavailabilityDuration( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                rcDescListToAdd.setSeverity( resultSet.getString(3) == null ? "" : resultSet.getString(3) );
                rcDescListToAdd.setRootCauseCategory( resultSet.getString(4) == null ? "" : resultSet.getString(4) );
                rcDescListToAdd.setRootCauseDescritpion( resultSet.getString(5) == null ? "" : resultSet.getString(5) );
                rcDescListToAdd.setNumberOfLostOrders( resultSet.getInt(6) == 0 ? 0 : resultSet.getInt(6) );
                rcDescListToAdd.setId( resultSet.getInt(7) == 0 ? 0 : resultSet.getInt(7) );
                rcDescList.add(rcDescListToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return rcDescList;
    }  
 
 @Override
    public List<NonconformitiesReporting> unavailabilityPerDay(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(");
               query.append("concat(a.EndDate, \" \",a.EndTime), concat(a.StartDate, \" \",a.StartTime))))/count(*))");
               query.append(", a.startdate FROM qualitynonconformitiesimpact a join qualitynonconformities b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where a.startdate >= '");
               query.append(fromDate);
               query.append("' and a.startdate <= '");
               query.append(toDate);
               query.append("' and b.problemCategory='UNAVAILABILITY' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and a.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append(" group by a.startdate");
 
        NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( resultSet.getString(1) == null ? 0 : resultSet.getInt(1) );
                ncPerRespToAdd.setStartDate( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return ncPerResp;
    }
 
 @Override
    public List<NonconformitiesReporting> performancePerDay(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(");
               query.append("concat(a.EndDate, \" \",a.EndTime), concat(a.StartDate, \" \",a.StartTime))))/count(*))");
               query.append(", a.startdate FROM qualitynonconformitiesimpact a join qualitynonconformities b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where a.startdate >= '");
               query.append(fromDate);
               query.append("' and a.startdate <= '");
               query.append(toDate);
               query.append("' and b.severity='2' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and a.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append(" group by a.startdate");
 
        NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( resultSet.getString(1) == null ? 0 : resultSet.getInt(1) );
                ncPerRespToAdd.setStartDate( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return ncPerResp;
    }
 
 @Override
    public List<NonconformitiesReporting> nonconformityPerWeek(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
        
               query.append("select value,WEEK(FROM_UNIXTIME(unix_timestamp(startdate))), cat1 from invariant i ");
               query.append("left join (SELECT severity, b.startDate, count(distinct(a.idqualitynonconformities)) as cat1 ");
               query.append("from qualitynonconformitiesimpact b join qualitynonconformities a ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append(" group by problemCategory,WEEK(FROM_UNIXTIME(unix_timestamp(b.startdate))) )c on c.severity=i.value ");
               query.append(" where id=34 order by WEEK(FROM_UNIXTIME(unix_timestamp(startdate))) asc");
 
        NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setProblemCategory( resultSet.getString(1) == null ? "" : resultSet.getString(1) );
                ncPerRespToAdd.setWeekNumber( resultSet.getString(2) == null ? 0 : resultSet.getInt(2) );
                ncPerRespToAdd.setNumberOfNonconformities( resultSet.getString(3) == null ? 0 : resultSet.getInt(3) );
                ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return ncPerResp;
    }
 
 @Override
    public List<NonconformitiesReporting> getNonconformitiesPerSeverity(String fromDate, String toDate, String country){
        List<NonconformitiesReporting> ncPerResp = new ArrayList<NonconformitiesReporting>();
        StringBuilder query = new StringBuilder();
               query.append("select sum(b.impactOrCost), severity from qualitynonconformities a ");
               query.append(" join qualitynonconformitiesimpact b ");
               query.append(" on a.idqualitynonconformities=b.idqualitynonconformities where b.startdate >= '");
               query.append(fromDate);
               query.append("' and b.startdate <= '");
               query.append(toDate);
               query.append("' ");
               
               if (country != null && !country.equals("all")){
               query.append(" and b.country = '");
               query.append(country);
               query.append("' ");
               }
               
               query.append("group by rootcausecategory");
  
        NonconformitiesReporting ncPerRespToAdd;
                
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            
            while (resultSet.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( resultSet.getInt(1) == 0 ? 0 : resultSet.getInt(1) );
                ncPerRespToAdd.setSeverity( resultSet.getString(2) == null ? "" : resultSet.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
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
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return ncPerResp;
    }
}
