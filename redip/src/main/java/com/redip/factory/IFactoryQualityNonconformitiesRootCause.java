/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesRootCause;

/**
 *
 * @author bcivel
 */
public interface IFactoryQualityNonconformitiesRootCause {
     
    QualityNonconformitiesRootCause create(int idqualitynonconformitiesrootcause, String rootCauseCategory,
            String rootCauseDescription,String responsabilities,String status,String component,
            String severity, String startDate,String startTime,String endDate,
            String endTime);

    
    }