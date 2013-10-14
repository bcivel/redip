package com.redip.dao;

import com.redip.entity.Log;
import com.redip.entity.User;
import com.redip.exception.QualityException;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface ILogDAO {

    /**
     * @return a list of all Log.
     * @throws CerberusException in case no Log can be found.
     */
    List<Log> findAllLogEvent() throws QualityException;

    /**
     * @return a list of all Log.
     * @throws CerberusException in case no Log can be found.
     */
    List<Log> findAllLogEvent(int start, int amount, String column, String dir, String searchTerm, String individualSearch) throws QualityException;

    /**
     *
     * @return Total number of Log inside the database.
     * @throws CerberusException
     */
    Integer getNumberOfLogEvent() throws QualityException;

    /**
     * Insert user into the database.
     *
     * @param logevent
     * @return true is log was inserted
     * @throws CerberusException if we did not manage to insert the user.
     */
    boolean insertLogEvent(Log logevent);
}
