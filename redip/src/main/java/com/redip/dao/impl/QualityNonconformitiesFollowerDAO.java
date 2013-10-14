/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.dao.IQualityNonconformitiesFollowerDAO;
import com.redip.entity.QualityNonconformitiesFollower;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bcivel
 */
@Repository
public class QualityNonconformitiesFollowerDAO implements IQualityNonconformitiesFollowerDAO{

    @Override
    public List<QualityNonconformitiesFollower> findQualityNonconformitiesActionByID(Integer ncid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addNonconformityFollower(QualityNonconformitiesFollower qnc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteQualityNonConformitiesFollower(Integer idnce, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
