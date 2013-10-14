/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.Log;
import com.redip.factory.IFactoryLog;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;

/**
 *
 * @author vertigo
 */
@Service
public class FactoryLog implements IFactoryLog {
    
    @Override
    public Log create(long LogID, String user, String time, String type, String table,
            String row, String field, String value) {
        Log newLogEvent = new Log();
        newLogEvent.setField(field);
        newLogEvent.setLogID(LogID);
        newLogEvent.setRow(row);
        newLogEvent.setTable(table);
        newLogEvent.setTime(time);
        newLogEvent.setType(type);
        newLogEvent.setUser(user);
        newLogEvent.setValue(value);
        
        return newLogEvent;
    }

}
