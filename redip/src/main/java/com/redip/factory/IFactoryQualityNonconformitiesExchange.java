/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.QualityNonconformitiesExchange;

/**
 *
 * @author bcivel
 */
public interface IFactoryQualityNonconformitiesExchange {
    
    QualityNonconformitiesExchange create(Integer idQualityNonconformitiesExchange,
            Integer idQualityNonconformities, String date, String user, String exchangeTitle, 
            String exchangeContent);
}
