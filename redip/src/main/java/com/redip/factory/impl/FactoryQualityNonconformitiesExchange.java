/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.QualityNonconformitiesExchange;
import com.redip.factory.IFactoryQualityNonconformitiesExchange;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryQualityNonconformitiesExchange implements IFactoryQualityNonconformitiesExchange {

    @Override
    public QualityNonconformitiesExchange create(Integer idQualityNonconformitiesExchange, Integer idQualityNonconformities, String date, String user, String exchangeTitle, String exchangeContent) {
         QualityNonconformitiesExchange qnc = new QualityNonconformitiesExchange();
         qnc.setDate(date);
         qnc.setExchangeContent(exchangeContent);
         qnc.setExchangeTitle(exchangeTitle);
         qnc.setIdQualityNonconformities(idQualityNonconformities);
         qnc.setIdQualityNonconformitiesExchange(idQualityNonconformitiesExchange);
         qnc.setUser(user);
         return qnc;
    }
    
}
