/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.QualityNonconformitiesAction;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesActionDAO {
    
    List<QualityNonconformitiesAction> findQualityNonconformitiesActionByID(Integer ncid);
    
    String addNonconformityAction(QualityNonconformitiesAction qnc);
    
    String updateQualityNonConformitiesAction(Integer idnca, String column, String value);
    
    List<QualityNonconformitiesAction> findQualityNonconformitiesActionByCriteria();
    
    List<QualityNonconformitiesAction> findQualityNonconformitiesActionUsingLimit(String fromLimit, String toLimit);
    
    Integer getMaxPriorityNumber();
}
