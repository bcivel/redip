/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.calculated.NonconformitiesReporting;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesReportingDAO {
    
    List<NonconformitiesReporting> getNonconformitiesPerResponsabilities(String fromDate, String toDate, String country);
    
    int numberOfNonconformities(String fromDate, String toDate, String country); 
    
    int orderLost(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> getNonconformitiesPerProblemCategory(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> getNonconformitiesPerRootCause(String fromDate, String toDate, String country);
    
    String unavailability(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> getRootCauseDescriptionList(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> unavailabilityPerDay(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> performancePerDay(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> nonconformityPerWeek(String fromDate, String toDate, String country);
    
    List<NonconformitiesReporting> getNonconformitiesPerSeverity(String fromDate, String toDate, String country);
    
    
}
