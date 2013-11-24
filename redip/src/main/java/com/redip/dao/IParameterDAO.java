package com.redip.dao;

import com.redip.entity.Parameter;
import com.redip.exception.QualityException;
import java.util.List;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
public interface IParameterDAO {

    Parameter findParameterByKey(String key);

    public List<Parameter> findAllParameter() throws QualityException;
    
    public String updateParameter(String param, String column, String value);

}
