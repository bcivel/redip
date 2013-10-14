/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.Group;

/**
 *
 * @author vertigo
 */
public interface IFactoryGroup {
    
    /**
     *
     * @param group
     * @return
     */
    Group create(String group);
        
}
