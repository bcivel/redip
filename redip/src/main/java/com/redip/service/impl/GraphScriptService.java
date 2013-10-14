/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IGraphScriptDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.GraphScript;
import com.redip.service.IGraphScriptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class GraphScriptService implements IGraphScriptService{

    @Autowired
    IGraphScriptDAO graphScriptDao;
    
    @Override
    public List<GraphScript> findGraphScriptByGraphName(String graphName) {
       return  graphScriptDao.findGraphScriptByTitle(graphName);
    }
    
}
