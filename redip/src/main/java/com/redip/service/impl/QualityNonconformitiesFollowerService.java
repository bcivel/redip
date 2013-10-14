/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesFollowerDAO;
import com.redip.entity.QualityNonconformitiesFollower;
import com.redip.service.IQualityNonconformitiesFollowerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesFollowerService implements IQualityNonconformitiesFollowerService{

    @Autowired
    IQualityNonconformitiesFollowerDAO nonconformitiesFollowerDao;
    
    @Override
    public List<QualityNonconformitiesFollower> findQualityNonconformitiesFollowerByID(Integer ncid) {
        return nonconformitiesFollowerDao.findQualityNonconformitiesActionByID(ncid);
    }

    @Override
    public String addNonconformityFollower(QualityNonconformitiesFollower qnc) {
       return nonconformitiesFollowerDao.addNonconformityFollower(qnc);
    }

    @Override
    public String deleteQualityNonConformitiesFollower(Integer idnce, String value) {
       return nonconformitiesFollowerDao.deleteQualityNonConformitiesFollower(idnce, value);
    }
    
    
}
