/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.Parameter;

/**
 *
 * @author bcivel
 */
public interface IFactoryParameter {
    
    Parameter create(String param,String value,String description);
}
