/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IGraphDAO;
import com.redip.entity.Graph;
import com.redip.service.IGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class GraphService implements IGraphService {

    @Autowired
    IGraphDAO graphDao;
    
    @Override
    public Graph findGraphByID(String graphName) {
        return graphDao.findGraphByID(graphName);
    }
    
}
