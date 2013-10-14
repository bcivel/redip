package com.redip.dao;

import com.redip.entity.Invariant;
import com.redip.exception.QualityException;
import java.util.List;

/**
 * {Insert class description here}
 *
 * @author bcivel
 */
public interface IInvariantDAO {

    Invariant findInvariantByIdValue(String idName, String value) throws QualityException;

    List<Invariant> findListOfInvariantById(String idName) throws QualityException;
    
     List<Invariant> findListOfInvariant() throws QualityException;

    List<Invariant> findInvariantByIdGp1(String idName, String gp) throws QualityException;
}
