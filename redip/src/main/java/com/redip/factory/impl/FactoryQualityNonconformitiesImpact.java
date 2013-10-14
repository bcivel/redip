/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.QualityNonconformitiesImpact;
import com.redip.factory.IFactoryQualityNonconformitiesImpact;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryQualityNonconformitiesImpact implements IFactoryQualityNonconformitiesImpact{
    
    
    @Override
    public QualityNonconformitiesImpact create(int idqualitynonconformitiesimpact, int idqualitynonconformities, String country, String application, String startDate, String startTime, String endDate, String endTime, String impactOrCost, String orderImpacted, String errorPages, String timeConsumed) {
        QualityNonconformitiesImpact qnc = new QualityNonconformitiesImpact();
        qnc.setApplication(application);
        qnc.setCountry(country);
        qnc.setEndDate(endDate);
        qnc.setEndTime(endTime);
        qnc.setIdqualitynonconformities(idqualitynonconformities);
        qnc.setIdqualitynonconformitiesimpact(idqualitynonconformitiesimpact);
        qnc.setImpactOrCost(impactOrCost);
        qnc.setStartDate(startDate);
        qnc.setStartTime(startTime);
        qnc.setOrderImpacted(orderImpacted);
        qnc.setTimeConsumed(timeConsumed);
        qnc.setErrorPages(errorPages);
        return qnc;
    }
}
