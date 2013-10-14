/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.MaintenancePlan;

/**
 *
 * @author bcivel
 */
public interface IFactoryMaintenancePlan {
    
    MaintenancePlan create (Integer idmaintenanceplan,String type,String name,String descritpion,String startdate,
            String starttime,String enddate,String endtime,String details,String impact,String rollback,
            String externalreference,String status,String comment, String displayInReporting);
}
