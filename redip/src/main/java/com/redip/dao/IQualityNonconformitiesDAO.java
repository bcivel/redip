/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.QualityNonconformities;
import java.util.List;
/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesDAO {
    
    List<QualityNonconformities> getAllNonconformities();
    
    List<QualityNonconformities> getAllNonconformities(int start, int amount, String column, String dir, String searchTerm, String individualSearch);
    
    List<QualityNonconformities> findNonconformitiesOpenedByResponsability(int start, int amount, String column, 
            String dir, String searchTerm, String individualSearch, String responsability, String fromPriority, String toPriority);
    
    List<QualityNonconformities> findNonconformitiesOpenedByResponsability(String responsability, String fromPriority, String toPriority);
    
    QualityNonconformities  getNumberOfNonconformities();
    
    QualityNonconformities  getMaxId();
    
    QualityNonconformities getOneNonconformities(int id);
    
    String addNonconformity(QualityNonconformities qualitync);
    
    String updateQualityNonConformities(Integer id, String field, String content);
    
    List<String> findDistinctValuesfromParameter (String parameter);
    
   }
