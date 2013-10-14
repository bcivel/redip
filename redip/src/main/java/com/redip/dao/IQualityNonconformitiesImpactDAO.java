/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.QualityNonconformitiesImpact;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesImpactDAO {
    
    List<QualityNonconformitiesImpact> getAllNonconformitiesImpact();
    
    QualityNonconformitiesImpact  getNumberOfNonconformitiesImpact();
    
    QualityNonconformitiesImpact  getMaxId();
    
    List<QualityNonconformitiesImpact> getImpactForNonconformitiesId(int id);
    
    String addNonconformityImpact(QualityNonconformitiesImpact qualitync);
    
    String updateNonconformitiesImpact(Integer idnci, String column, String value);
    
    String deleteNonconformitiesImpact(Integer idnci);
        
   }
    