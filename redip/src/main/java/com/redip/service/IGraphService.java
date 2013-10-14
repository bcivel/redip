/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.Graph;

/**
 *
 * @author bcivel
 */
public interface IGraphService {
    
    Graph findGraphByID(String graphName);
}
