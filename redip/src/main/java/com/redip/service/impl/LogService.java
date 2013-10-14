/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.ILogDAO;
import com.redip.entity.Log;
import com.redip.exception.QualityException;
import com.redip.service.ILogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vertigo
 */
@Service
public class LogService implements ILogService {

    @Autowired
    private ILogDAO logEventDAO;
    
    @Override
    public List<Log> findAllLog() throws QualityException {
        return logEventDAO.findAllLogEvent();
    }

    @Override
    public List<Log> findAllLog(int start, int amount, String column, String dir, String searchTerm, String individualSearch) throws QualityException {
        return logEventDAO.findAllLogEvent(start, amount, column, dir, searchTerm, individualSearch);
    }

    @Override
    public Integer getNumberOfLog() throws QualityException {
        return logEventDAO.getNumberOfLogEvent();
    }

    
    @Override
    public boolean insertLog(Log logevent) {
        return logEventDAO.insertLogEvent(logevent);
    }
    
    
    
}
