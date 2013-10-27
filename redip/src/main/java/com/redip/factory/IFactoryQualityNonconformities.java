/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.QualityNonconformities;

/**
 *
 * @author bcivel
 */
public interface IFactoryQualityNonconformities {
     
    QualityNonconformities create(int idqualitynonconformities, String problemCategory, 
            String problemDescription, String problemTitle, String rootCauseCategory, String rootCauseDescription,
            String responsabilities, String status, String comments, String severity, String application, 
            String applicationFunctionnality, String problemType, String deadline, String detection, 
            String linkToDoc, String showInReporting, String qualityFollower, String testToAvoid, String reproductibility,
            String behaviorExpected, String screenshot, String partnerId);



    QualityNonconformities create(String problemTitle, String problemDescription,
                String severity, String reproductibility, String linkToDoc, String behaviorExpected, 
                String detection, String startDate, String startTime, String screenshot);
    
    QualityNonconformities create(int idqualitynonconformities, String problemTitle, String problemDescription);

    
    }