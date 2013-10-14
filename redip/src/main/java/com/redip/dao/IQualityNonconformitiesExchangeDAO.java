/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.QualityNonconformitiesExchange;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesExchangeDAO {
    
    List<QualityNonconformitiesExchange> findQualityNonconformitiesActionByID(Integer ncid);
    
    String addNonconformityExchange(QualityNonconformitiesExchange qnc);
    
    String updateQualityNonConformitiesExchange(Integer idnca, String column, String value); 
}
