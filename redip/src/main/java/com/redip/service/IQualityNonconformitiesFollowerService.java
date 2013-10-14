/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformitiesFollower;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IQualityNonconformitiesFollowerService {
    
    List<QualityNonconformitiesFollower> findQualityNonconformitiesFollowerByID(Integer ncid);
    
    String addNonconformityFollower(QualityNonconformitiesFollower qnc);
    
    String deleteQualityNonConformitiesFollower(Integer idnce, String value);
}
