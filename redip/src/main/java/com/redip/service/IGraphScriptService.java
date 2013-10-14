/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.GraphScript;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IGraphScriptService {
    
    List<GraphScript> findGraphScriptByGraphName(String graphName);
}
