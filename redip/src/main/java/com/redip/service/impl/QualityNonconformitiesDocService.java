/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesActionDAO;
import com.redip.dao.IQualityNonconformitiesDocDAO;
import com.redip.entity.QualityNonconformitiesDoc;
import com.redip.service.IQualityNonconformitiesDocService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesDocService implements IQualityNonconformitiesDocService{

    @Autowired
    IQualityNonconformitiesDocDAO qualityNonconformitiesDocDao;
    
    @Override
    public String addNonconformityDoc(QualityNonconformitiesDoc qualityNonconformityDoc) {
        return qualityNonconformitiesDocDao.addNonconformityDoc(qualityNonconformityDoc);
    }

    @Override
    public String deleteNonconformityDoc(int idQualityNonconformityDoc) {
        return qualityNonconformitiesDocDao.deleteNonconformityDoc(idQualityNonconformityDoc);
    }

    @Override
    public List<QualityNonconformitiesDoc> getDocLink(int idQualityNonconformity) {
        return qualityNonconformitiesDocDao.getDocLink(idQualityNonconformity);
    }
    
}
