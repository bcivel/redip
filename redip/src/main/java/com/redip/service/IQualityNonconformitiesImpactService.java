/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformitiesImpact;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesImpactService {
    
    List<QualityNonconformitiesImpact> getAllNonconformitiesImpact();
    
    QualityNonconformitiesImpact  getNumberOfNonconformitiesImpact();
    
    List<QualityNonconformitiesImpact> getImpactForNonconformitiesId(int id);
    
    String addNonconformityImpact(int id, String application, 
            String startDate, String startTime, String endDate, String endTime, String impactOrCost);
    
    String updateNonconformityImpact(int id, String column, String value);
    
    String deleteNonconformitiesImpact(Integer id);
        
}
