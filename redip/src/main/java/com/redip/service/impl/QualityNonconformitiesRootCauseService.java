/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.dao.IQualityNonconformitiesRootCauseDAO;
import com.redip.entity.QualityNonconformitiesRootCause;
import com.redip.service.IQualityNonconformitiesRootCauseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class QualityNonconformitiesRootCauseService implements IQualityNonconformitiesRootCauseService {

    @Autowired
    IQualityNonconformitiesRootCauseDAO qnrcDao;
    
    @Override
    public List<QualityNonconformitiesRootCause> findAllNonconformitiesRootCause() {
        return qnrcDao.findAllNonconformitiesRootCause();
    }

    @Override
    public List<QualityNonconformitiesRootCause> findAllNonconformitiesRootCause(int start, int amount, String column, String dir, String searchTerm, String individualSearch) {
        return qnrcDao.findAllNonconformitiesRootCause(start, amount, column, dir, searchTerm, individualSearch);
    }

    @Override
    public QualityNonconformitiesRootCause findOneNonconformitiesRootCause(int id) {
        return qnrcDao.findOneNonconformitiesRootCause(id);
    }

    @Override
    public String addNonconformityRootCause(QualityNonconformitiesRootCause ncrc) {
        return qnrcDao.addNonconformityRootCause(ncrc);
    }

    @Override
    public String updateQualityNonConformitiesRootCause(Integer id, String field, String content) {
        return qnrcDao.updateQualityNonConformitiesRootCause(id, field, content);
    }

    @Override
    public List<String> findDistinctValueFromParameter(String parameter) {
        return qnrcDao.findDistinctValuesfromParameter(parameter);
    }
    
}
