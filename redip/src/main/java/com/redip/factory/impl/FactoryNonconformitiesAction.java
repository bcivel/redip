/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesAction;
import com.redip.factory.IFactoryQualityNonconformitiesAction;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryNonconformitiesAction implements IFactoryQualityNonconformitiesAction{

    @Override
    public QualityNonconformitiesAction create(Integer idQualityNonconformitiesActions, Integer idQualityNonconformities, String action, String deadline, String follower, String status, String date, String percentage, String priority) {
        QualityNonconformitiesAction qnc = new QualityNonconformitiesAction();
        qnc.setAction(action);
        qnc.setDate(date);
        qnc.setDeadline(deadline);
        qnc.setFollower(follower);
        qnc.setIdQualityNonconformities(idQualityNonconformities);
        qnc.setIdQualityNonconformitiesActions(idQualityNonconformitiesActions);
        qnc.setPercentage(percentage);
        qnc.setStatus(status);
        qnc.setPriority(priority);
        return qnc;
    }

    @Override
    public QualityNonconformitiesAction create(Integer idQualityNonconformitiesActions, Integer idQualityNonconformities, String action, String deadline, String follower, String status, String date, String percentage, String priority, QualityNonconformities qnco) {
        QualityNonconformitiesAction qnc = new QualityNonconformitiesAction();
        qnc.setAction(action);
        qnc.setDate(date);
        qnc.setDeadline(deadline);
        qnc.setFollower(follower);
        qnc.setIdQualityNonconformities(idQualityNonconformities);
        qnc.setIdQualityNonconformitiesActions(idQualityNonconformitiesActions);
        qnc.setPercentage(percentage);
        qnc.setStatus(status);
        qnc.setPriority(priority);
        qnc.setQualityNonconformities(qnco);
        return qnc;
    }
    
}
