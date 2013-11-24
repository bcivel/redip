/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redip.service;

import com.redip.entity.QualityNonconformitiesDoc;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesDocService {
    
    String addNonconformityDoc(QualityNonconformitiesDoc qualityNonconformityDoc);
    
    String deleteNonconformityDoc(int idQualityNonconformityDoc);
    
    List<QualityNonconformitiesDoc> getDocLink(int idQualityNonconformity);
}
