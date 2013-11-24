/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesDAO;
import com.redip.entity.Log;
import com.redip.entity.QualityNonconformities;
import com.redip.factory.IFactoryLog;
import com.redip.factory.IFactoryQualityNonconformities;
import com.redip.service.ILogService;
import com.redip.service.IQualityNonconformitiesService;
import com.redip.util.DateUtil;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesServiceImpl implements IQualityNonconformitiesService {

    @Autowired
    private IQualityNonconformitiesDAO qualityNonconformitiesDao;
    @Autowired
    private IFactoryQualityNonconformities factoryQNC;
    @Autowired
    private ILogService logService;
    @Autowired
    private IFactoryLog factoryLog;
    

    @Override
    public List<QualityNonconformities> getAllNonconformities() {
        return qualityNonconformitiesDao.getAllNonconformities();
    }

    @Override
    public List<QualityNonconformities> getAllNonconformities(int start, int amount, String column, String dir, String searchTerm, String individualSearch) {
        return qualityNonconformitiesDao.getAllNonconformities(start, amount, column, dir, searchTerm, individualSearch);
    }

    @Override
    public QualityNonconformities getNumberOfNonconformities(String searchTerm, String inds) {
        return qualityNonconformitiesDao.getNumberOfNonconformities(searchTerm, inds);
    }

    @Override
    public QualityNonconformities getOneNonconformities(int id) {
        return qualityNonconformitiesDao.getOneNonconformities(id);
    }

    @Override
    public QualityNonconformities getMaxId() {
        return qualityNonconformitiesDao.getMaxId();
    }

    @Override
    public String addNonconformity(QualityNonconformities nonconformitiestoadd) {
        String result = "";
        String date = DateUtil.getTodayFormat("yyyy/MM/dd HH:mm:ss");
                
        result = qualityNonconformitiesDao.addNonconformity(nonconformitiestoadd);
        Integer id = qualityNonconformitiesDao.getMaxId().getIdqualitynonconformities();
        
        Log creationLog = factoryLog.create(0, null, date, "CREATE", "qualitynonconformities", id.toString(),
                null, nonconformitiestoadd.getProblemTitle()+";-;"+nonconformitiestoadd.getProblemDescription()
               +";-;"+nonconformitiestoadd.getSeverity()+";-;"+nonconformitiestoadd.getReproductibility()+";-;"
                        +nonconformitiestoadd.getLinkToDoc()+";-;"+nonconformitiestoadd.getBehaviorExpected());
        
        logService.insertLog(creationLog);
        
        return result;
    }

    @Override
    public String updateNonconformity(int id, String column, String value) {
        String result = qualityNonconformitiesDao.updateQualityNonConformities(id, column, value);
        
        
        String date = DateUtil.getTodayFormat("yyyy-MM-dd HH:mm:ss");
                
        Log creationLog = factoryLog.create(0, null, date, "UPDATE", "qualitynonconformities", String.valueOf(id),
                column, value);
        
        logService.insertLog(creationLog);
        
        return result;
    }

    @Override
    public List<QualityNonconformities> findNonconformitiesOpenedByResponsability(int start, int amount, String column, String dir, String searchTerm, String individualSearch,
    String responsability, String fromPosition, String toPosition) {
        return qualityNonconformitiesDao.findNonconformitiesOpenedByResponsability(start, amount, column, dir, searchTerm, individualSearch, responsability, fromPosition, toPosition);
    }

    @Override
    public List<QualityNonconformities> findNonconformitiesOpenedByResponsability(String responsability, String fromPosition, String toPosition) {
        return qualityNonconformitiesDao.findNonconformitiesOpenedByResponsability(responsability, fromPosition, toPosition);
    }

    @Override
    public List<String> findDistinctValueFromParameter(String parameter) {
        return qualityNonconformitiesDao.findDistinctValuesfromParameter(parameter);
                }
}
