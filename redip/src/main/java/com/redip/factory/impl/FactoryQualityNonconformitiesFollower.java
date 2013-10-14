/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.QualityNonconformitiesFollower;
import com.redip.factory.IFactoryQualityNonconformitiesFollower;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryQualityNonconformitiesFollower implements IFactoryQualityNonconformitiesFollower {

    @Override
    public QualityNonconformitiesFollower create(Integer idqualitynonconformitiesfollower, Integer idqualitynonconformities, String user) {
        QualityNonconformitiesFollower qnc = new QualityNonconformitiesFollower();
        qnc.setIdqualitynonconformities(idqualitynonconformities);
        qnc.setIdqualitynonconformitiesfollower(idqualitynonconformitiesfollower);
        qnc.setUser(user);
        return qnc; 
    }
    
}
