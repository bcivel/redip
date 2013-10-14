/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.QualityNonconformitiesImpact;

/**
 *
 * @author bcivel
 */
public interface IFactoryQualityNonconformitiesImpact {
    
    QualityNonconformitiesImpact create(int idqualitynonconformitiesimpact, int idqualitynonconformities,
            String country, String application, String startDate, String startTime, String endDate,
            String endTime, String impactOrCost, String orderImpacted, String errorPages, 
            String timeConsumed);
}
