/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.QualityNonconformitiesFollower;

/**
 *
 * @author bcivel
 */
public interface IFactoryQualityNonconformitiesFollower {
    
    QualityNonconformitiesFollower create(Integer idqualitynonconformitiesfollower,
            Integer idqualitynonconformities, String user);
}
