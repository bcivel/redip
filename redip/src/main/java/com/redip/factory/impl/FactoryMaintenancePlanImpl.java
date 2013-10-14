/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.MaintenancePlan;
import com.redip.factory.IFactoryMaintenancePlan;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryMaintenancePlanImpl implements IFactoryMaintenancePlan{

    @Override
    public MaintenancePlan create(Integer idmaintenanceplan, String type, String name, String descritpion,
    String startdate, String starttime, String enddate, String endtime, String details, String impact, 
    String rollback, String externalreference, String status, String comment, String displayInReporting) {
        
        MaintenancePlan maintenancePlan = new MaintenancePlan();
        maintenancePlan.setComment(comment);
        maintenancePlan.setDescritpion(descritpion);
        maintenancePlan.setDetails(details);
        maintenancePlan.setEnddate(enddate);
        maintenancePlan.setEndtime(endtime);
        maintenancePlan.setExternalreference(externalreference);
        maintenancePlan.getIdmaintenanceplan();
        maintenancePlan.setImpact(impact);
        maintenancePlan.setName(name);
        maintenancePlan.setRollback(rollback);
        maintenancePlan.setStartdate(startdate);
        maintenancePlan.setStarttime(starttime);
        maintenancePlan.setStatus(status);
        maintenancePlan.setType(type);
        maintenancePlan.setDisplayInReporting(displayInReporting);
        return maintenancePlan;
    }
    
}
