/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.dao.IQualityNonconformitiesReportingDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.calculated.NonconformitiesReporting;
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfLostOrders( rs.getInt(1) == 0 ? 0 : rs.getInt(1) );
                ncPerRespToAdd.setResponsabilities( rs.getString(2) == null ? "" : rs.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
  
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            rs.first();
            numberOfNC=rs.getInt(1);
             } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( rs.getInt(1) == 0 ? 0 : rs.getInt(1) );
                ncPerRespToAdd.setProblemCategory( rs.getString(2) == null ? "" : rs.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfLostOrders( rs.getInt(1) == 0 ? 0 : rs.getInt(1) );
                ncPerRespToAdd.setRootCauseCategory( rs.getString(2) == null ? "" : rs.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
  
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            if (rs.next()) {
                orderLost = rs.getInt(1);
                }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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


        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            rs.first();
            unavail=rs.getString(1);
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                rcDescListToAdd = new NonconformitiesReporting();
                rcDescListToAdd.setStartDate( rs.getString(1) == null ? "" : rs.getString(1) );
                rcDescListToAdd.setUnavailabilityDuration( rs.getString(2) == null ? "" : rs.getString(2) );
                rcDescListToAdd.setSeverity( rs.getString(3) == null ? "" : rs.getString(3) );
                rcDescListToAdd.setRootCauseCategory( rs.getString(4) == null ? "" : rs.getString(4) );
                rcDescListToAdd.setRootCauseDescritpion( rs.getString(5) == null ? "" : rs.getString(5) );
                rcDescListToAdd.setNumberOfLostOrders( rs.getInt(6) == 0 ? 0 : rs.getInt(6) );
                rcDescListToAdd.setId( rs.getInt(7) == 0 ? 0 : rs.getInt(7) );
                rcDescList.add(rcDescListToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( rs.getString(1) == null ? 0 : rs.getInt(1) );
                ncPerRespToAdd.setStartDate( rs.getString(2) == null ? "" : rs.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( rs.getString(1) == null ? 0 : rs.getInt(1) );
                ncPerRespToAdd.setStartDate( rs.getString(2) == null ? "" : rs.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setProblemCategory( rs.getString(1) == null ? "" : rs.getString(1) );
                ncPerRespToAdd.setWeekNumber( rs.getString(2) == null ? 0 : rs.getInt(2) );
                ncPerRespToAdd.setNumberOfNonconformities( rs.getString(3) == null ? 0 : rs.getInt(3) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
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
                
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            
            while (rs.next()) {
                ncPerRespToAdd = new NonconformitiesReporting();
                ncPerRespToAdd.setNumberOfNonconformities( rs.getInt(1) == 0 ? 0 : rs.getInt(1) );
                ncPerRespToAdd.setSeverity( rs.getString(2) == null ? "" : rs.getString(2) );
                ncPerResp.add(ncPerRespToAdd);
            }
                        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
        }finally {
                try {
                    databaseSpring.disconnect();
                } catch (Exception ex) {
            Logger.log(QualityNonconformitiesReportingDAOImpl.class.getName(),Level.FATAL, ""+ ex);
            }
            }
        
        
        return ncPerResp;
    }
}
