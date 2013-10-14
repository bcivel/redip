/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import java.awt.image.BufferedImage;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesReportingService {
    
    String rootCauseDescriptionList(String fromDate, String toDate, String country);
    
    BufferedImage impactPerResp(ApplicationContext appContext, String fromDate, String toDate, String country);
    
    BufferedImage nonconformitiesPerProblemCategory(ApplicationContext appContext, String fromDate, String toDate, String country);

    BufferedImage impactPerRootCause(ApplicationContext appContext, String fromDate, String toDate, String country);
    
    String unavailability(String fromDate, String toDate, String country);
    
    String numberOfNonconformities(String fromDate, String toDate, String country);
    
    String orderLost(String fromDate, String toDate, String country);
    
    BufferedImage toto(ApplicationContext appContext, String fromDate, String toDate, String country);
    
    BufferedImage unavailabilityPerDay(ApplicationContext appContext, String fromDate, String toDate, String country);
    
    BufferedImage performancePerDay(ApplicationContext appContext, String fromDate, String toDate, String country);
    
    BufferedImage nonconformityPerWeek(ApplicationContext appContext, String fromDate, String toDate, String country);
    
    BufferedImage nonconformitiesPerSeverity(ApplicationContext appContext, String fromDate, String toDate, String country);
}
