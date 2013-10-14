/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.Log;
import com.redip.exception.QualityException;
import java.util.List;

/**
 *
 * @author vertigo
 */
public interface ILogService {

    public List<Log> findAllLog() throws QualityException;

    public List<Log> findAllLog(int start, int amount, String column, String dir, String searchTerm, String individualSearch) throws QualityException;

    public Integer getNumberOfLog() throws QualityException;

    public boolean insertLog(Log logevent);
}
