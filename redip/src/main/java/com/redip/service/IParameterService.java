/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.Parameter;
import com.redip.exception.QualityException;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IParameterService {

    Parameter findParameterByKey(String key) throws QualityException;
    
    List<Parameter> findAllParameter() throws QualityException;
    
    void updateParameter(String param, String column, String value) throws QualityException;
    
}
