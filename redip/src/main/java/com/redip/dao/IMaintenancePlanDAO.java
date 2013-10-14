/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.MaintenancePlan;
import com.redip.exception.QualityException;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IMaintenancePlanDAO {
   
    MaintenancePlan findMaintenancePlanById(String idMaintenancePlan) throws QualityException;

    List<MaintenancePlan> findListOfMaintenancePlanByDate(String idName) throws QualityException;
}
