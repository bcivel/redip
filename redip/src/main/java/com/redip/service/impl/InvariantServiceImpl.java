/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IInvariantDAO;
import com.redip.entity.Invariant;
import com.redip.exception.QualityException;
import com.redip.service.IInvariantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class InvariantServiceImpl implements IInvariantService {

    @Autowired
    IInvariantDAO invariantDao;

    @Override
    public Invariant findInvariantByIdValue(String idName, String value) throws QualityException {
        return invariantDao.findInvariantByIdValue(idName, value);
    }

    @Override
    public List<Invariant> findListOfInvariantById(String idName) throws QualityException {
        return invariantDao.findListOfInvariantById(idName);
    }

    @Override
    public List<Invariant> findInvariantByIdGp1(String idName, String gp) throws QualityException {
        return invariantDao.findInvariantByIdGp1(idName, gp);
    }
}
