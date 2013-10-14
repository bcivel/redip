/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao;

import com.redip.entity.Graph;

/**
 *
 * @author bcivel
 */
public interface IGraphDAO {
    
    Graph findGraphByID(String graphName);
}
