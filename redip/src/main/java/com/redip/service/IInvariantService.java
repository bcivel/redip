/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.Invariant;
import com.redip.exception.QualityException;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IInvariantService {
    
    Invariant findInvariantByIdValue(String idName, String value) throws QualityException;
    
    List<Invariant> findListOfInvariantById(String idName) throws QualityException;

    List<Invariant> findInvariantByIdGp1(String idName, String gp) throws QualityException;
}
