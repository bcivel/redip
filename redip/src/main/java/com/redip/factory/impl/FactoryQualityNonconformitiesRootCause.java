/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesRootCause;
import com.redip.factory.IFactoryQualityNonconformitiesRootCause;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryQualityNonconformitiesRootCause implements IFactoryQualityNonconformitiesRootCause{

    @Override
    public QualityNonconformitiesRootCause create(int idqualitynonconformitiesrootcause, String rootCauseCategory, String rootCauseDescription, String responsabilities, String status, String component, String severity, String startDate, String startTime, String endDate, String endTime) {
    QualityNonconformitiesRootCause qnc = new QualityNonconformitiesRootCause();
    qnc.setComponent(component);
    qnc.setIdqualitynonconformitiesrootcause(idqualitynonconformitiesrootcause);
    qnc.setResponsabilities(responsabilities);
    qnc.setRootCauseCategory(rootCauseCategory);
    qnc.setRootCauseDescription(rootCauseDescription);
    qnc.setSeverity(severity);
    qnc.setStatus(status);
    qnc.setEndDate(endDate);
    qnc.setEndTime(endTime);
    qnc.setStartDate(startDate);
    qnc.setStartTime(startTime);
        
            
    return qnc;
    }
    
}
