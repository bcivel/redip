/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory;

import com.redip.entity.Log;
import java.sql.Timestamp;

/**
 *
 * @author vertigo
 */
public interface IFactoryLog {

    Log create(long LogID, String user, String time, String type, String table,
            String row, String field, String value);
}
