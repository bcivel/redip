/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IParameterDAO;
import com.redip.entity.Parameter;
import com.redip.exception.QualityException;
import com.redip.service.IParameterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class ParameterService implements IParameterService {

    @Autowired
    private IParameterDAO parameterDao;
    
    @Override
    public Parameter findParameterByKey(String key){
        return parameterDao.findParameterByKey(key);
    }
    
    @Override
    public List<Parameter> findAllParameter() throws QualityException{
        return parameterDao.findAllParameter();
    }
    
    @Override
    public void updateParameter(String param, String column, String value) throws QualityException{
        parameterDao.updateParameter(param, column, value);
    }
}
