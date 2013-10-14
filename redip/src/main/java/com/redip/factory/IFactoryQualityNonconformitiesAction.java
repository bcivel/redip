/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesAction;

/**
 *
 * @author bcivel
 */
public interface IFactoryQualityNonconformitiesAction {
    
    QualityNonconformitiesAction create(Integer idQualityNonconformitiesActions,
            Integer idQualityNonconformities, String action, String deadline, String follower,
            String status, String date, String percentage, String priority);
    
    QualityNonconformitiesAction create(Integer idQualityNonconformitiesActions,
            Integer idQualityNonconformities, String action, String deadline, String follower,
            String status, String date, String percentage, String priority, QualityNonconformities qnc);
}
