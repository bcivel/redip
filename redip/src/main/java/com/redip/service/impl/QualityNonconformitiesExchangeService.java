/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesExchangeDAO;
import com.redip.entity.QualityNonconformitiesExchange;
import com.redip.service.IQualityNonconformitiesExchangeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesExchangeService implements IQualityNonconformitiesExchangeService{

    @Autowired
    IQualityNonconformitiesExchangeDAO nonconformitiesExchangeDao;
    
    @Override
    public List<QualityNonconformitiesExchange> findQualityNonconformitiesExchangeByID(Integer ncid) {
       return nonconformitiesExchangeDao.findQualityNonconformitiesActionByID(ncid);
    }

    @Override
    public String addNonconformityExchange(QualityNonconformitiesExchange qnc) {
        return nonconformitiesExchangeDao.addNonconformityExchange(qnc);
    }

    @Override
    public String updateQualityNonConformitiesExchange(Integer idnca, String column, String value) {
        return nonconformitiesExchangeDao.updateQualityNonConformitiesExchange(idnca, column, value);
    }
    
}
