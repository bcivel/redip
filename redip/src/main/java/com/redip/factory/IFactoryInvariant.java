/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.Invariant;

/**
 * @author bcivel
 */
public interface IFactoryInvariant {

    Invariant create(String idName, String value, int sort, int id, String description,
                     String gp1, String gp2, String gp3);
}
