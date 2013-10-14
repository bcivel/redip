/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.GraphScript;

/**
 *
 * @author bcivel
 */
public interface IFactoryGraphScript {
    
    GraphScript create (Integer id,String title,String type,String script);
}
