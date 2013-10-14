/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformitiesRootCause;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesRootCauseService {
    
    List<QualityNonconformitiesRootCause> findAllNonconformitiesRootCause();
    
    List<QualityNonconformitiesRootCause> findAllNonconformitiesRootCause(int start, int amount, String column, String dir, String searchTerm, String individualSearch);
    
    QualityNonconformitiesRootCause findOneNonconformitiesRootCause(int id);
    
    String addNonconformityRootCause(QualityNonconformitiesRootCause ncrc);
    
    String updateQualityNonConformitiesRootCause(Integer id, String field, String content);
    
}
