/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesImpactDAO;
import com.redip.entity.Log;
import com.redip.entity.QualityNonconformitiesImpact;
import com.redip.factory.IFactoryLog;
import com.redip.service.ILogService;
import com.redip.service.IQualityNonconformitiesImpactService;
import com.redip.util.DateUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesImpactServiceImpl implements IQualityNonconformitiesImpactService {

    @Autowired
    private IQualityNonconformitiesImpactDAO qualityNonconformitiesImpactDao;
    @Autowired
    private ILogService logService;
    @Autowired
    private IFactoryLog factoryLog;

    @Override
    public List<QualityNonconformitiesImpact> getAllNonconformitiesImpact() {
        return qualityNonconformitiesImpactDao.getAllNonconformitiesImpact();
    }

    @Override
    public QualityNonconformitiesImpact getNumberOfNonconformitiesImpact() {
        return qualityNonconformitiesImpactDao.getNumberOfNonconformitiesImpact();
    }

    @Override
    public List<QualityNonconformitiesImpact> getImpactForNonconformitiesId(int id) {
        return qualityNonconformitiesImpactDao.getImpactForNonconformitiesId(id);
    }

    @Override
    public String addNonconformityImpact(int id, String application,
            String startDate, String startTime, String endDate, String endTime, String impactOrCost) {

        String result = "";
        String date = DateUtil.getTodayFormat("yyyy/MM/dd HH:mm:ss");
        
        QualityNonconformitiesImpact nonconformitiesinmpacttoadd = new QualityNonconformitiesImpact();

        nonconformitiesinmpacttoadd.setIdqualitynonconformities(id);
        nonconformitiesinmpacttoadd.setApplication(application);
        nonconformitiesinmpacttoadd.setStartDate(startDate);
        nonconformitiesinmpacttoadd.setStartTime(startTime);
        nonconformitiesinmpacttoadd.setEndDate(endDate);
        nonconformitiesinmpacttoadd.setEndTime(endTime);
        nonconformitiesinmpacttoadd.setImpactOrCost(impactOrCost);

        result =  qualityNonconformitiesImpactDao.addNonconformityImpact(nonconformitiesinmpacttoadd);
        
        Integer maxId = qualityNonconformitiesImpactDao.getMaxId().getIdqualitynonconformitiesimpact();
        
        Log creationLog = factoryLog.create(0, null, date, "CREATE", "qualitynonconformitiesimpact", maxId.toString(),
                null, id+";-;"+application+";-;"+startDate+";-;"+startTime+";-;"+endDate+";-;"+
                impactOrCost);
        
        logService.insertLog(creationLog);
        
        return result;
    }

    @Override
    public String updateNonconformityImpact(int id, String column, String value) {
        String result = qualityNonconformitiesImpactDao.updateNonconformitiesImpact(id, column, value);
        String date = DateUtil.getTodayFormat("yyyy-MM-dd HH:mm:ss");
                
        Log creationLog = factoryLog.create(0, null, date, "UPDATE", "qualitynonconformities", String.valueOf(id),
                column, value);
        
        logService.insertLog(creationLog);
        
        return result;
    }

    @Override
    public String deleteNonconformitiesImpact(Integer id) {
        String result =  qualityNonconformitiesImpactDao.deleteNonconformitiesImpact(id);
        
        String date = DateUtil.getTodayFormat("yyyy-MM-dd HH:mm:ss");
                
        Log creationLog = factoryLog.create(0, null, date, "DELETE", "qualitynonconformitiesimpact", String.valueOf(id),
                null, null);
        
        logService.insertLog(creationLog);
        return result;
    }
}
