/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformitiesExchange;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesExchangeService {
    
    List<QualityNonconformitiesExchange> findQualityNonconformitiesExchangeByID(Integer ncid);
    
    String addNonconformityExchange(QualityNonconformitiesExchange qnc);
    
    String updateQualityNonConformitiesExchange(Integer idnca, String column, String value);
}
