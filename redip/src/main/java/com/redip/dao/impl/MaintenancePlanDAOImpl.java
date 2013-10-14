/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.dao.IMaintenancePlanDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.MaintenancePlan;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryMaintenancePlan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bcivel
 */
@Repository
public class MaintenancePlanDAOImpl implements IMaintenancePlanDAO {

    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    private IFactoryMaintenancePlan factoryMaintenancePlan;
    
    @Override
    public MaintenancePlan findMaintenancePlanById(String idMaintenancePlan) throws QualityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<MaintenancePlan> findListOfMaintenancePlanByDate(String idName) throws QualityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
