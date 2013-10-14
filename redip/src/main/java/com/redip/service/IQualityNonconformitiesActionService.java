/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformitiesAction;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesActionService {
    
    List<QualityNonconformitiesAction> findQualityNonconformitiesActionByID(Integer ncid);
    
    String addNonconformityAction(QualityNonconformitiesAction qnc);
    
    String updateQualityNonConformitiesAction(Integer idnca, String column, String value);
    
    List<QualityNonconformitiesAction> findQualityNonconformitiesByCriteria();
    
    List<QualityNonconformitiesAction> findQualityNonconformitiesUsingLimit(String fromLimit, String toLimit);
    
    
}
