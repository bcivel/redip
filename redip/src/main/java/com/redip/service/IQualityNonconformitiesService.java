/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformities;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesService {
    
    List<QualityNonconformities> getAllNonconformities();
    
    List<QualityNonconformities> getAllNonconformities(int start, int amount, String column, String dir, String searchTerm, String individualSearch);
    
    List<QualityNonconformities> findNonconformitiesOpenedByResponsability(int start, int amount, String column, String dir, String searchTerm, String individualSearch, String responsability, String fromPosition, String toPosition);
    
    List<QualityNonconformities> findNonconformitiesOpenedByResponsability(String responsability, String fromPosition, String toPosition);
    
    QualityNonconformities  getNumberOfNonconformities();
    
    QualityNonconformities  getMaxId();
    
    QualityNonconformities getOneNonconformities(int id);
    
    String addNonconformity(String problemTitle, String problemDescription,
                String severity, String reproductibility, String linkToDoc, 
                String behaviorExpected, String detection, String startDate, String startTime);
    
    String updateNonconformity(int id, String column, String value);
    
    List<String> findDistinctValueFromParameter (String parameter);
    
}
