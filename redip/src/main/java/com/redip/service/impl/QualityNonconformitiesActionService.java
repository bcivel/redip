/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesActionDAO;
import com.redip.entity.QualityNonconformitiesAction;
import com.redip.log.Logger;
import com.redip.service.IQualityNonconformitiesActionService;
import java.util.List;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesActionService implements IQualityNonconformitiesActionService{

    @Autowired
    IQualityNonconformitiesActionDAO qualityNonconformitiesDao;
    
    @Override
    public List<QualityNonconformitiesAction> findQualityNonconformitiesActionByID(Integer ncid) {
        return qualityNonconformitiesDao.findQualityNonconformitiesActionByID(ncid);
    }

    @Override
    public String addNonconformityAction(QualityNonconformitiesAction qnc) {
        Integer newPriority = qualityNonconformitiesDao.getMaxPriorityNumber()+1;
        Logger.log(QualityNonconformitiesActionService.class.getName(), Level.INFO, "SetPriority = "+newPriority);
        qnc.setPriority(newPriority.toString());
        return qualityNonconformitiesDao.addNonconformityAction(qnc);
    }

    @Override
    public String updateQualityNonConformitiesAction(Integer idnca, String column, String value) {
       return qualityNonconformitiesDao.updateQualityNonConformitiesAction(idnca, column, value);
    }

    @Override
    public List<QualityNonconformitiesAction> findQualityNonconformitiesByCriteria() {
        return qualityNonconformitiesDao.findQualityNonconformitiesActionByCriteria();
    }

    @Override
    public List<QualityNonconformitiesAction> findQualityNonconformitiesUsingLimit(String fromLimit, String toLimit) {
        return qualityNonconformitiesDao.findQualityNonconformitiesActionUsingLimit(fromLimit, toLimit);
    }
    
}
