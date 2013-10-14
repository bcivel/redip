/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

import java.sql.Timestamp;

/**
 *
 * @author bcivel
 */
public class Log {

    private long LogID;
    private String user;
    private String time;
    private String type;
    private String table;
    private String row;
    private String field;
    private String value;

    public long getLogID() {
        return LogID;
    }

    public void setLogID(long LogID) {
        this.LogID = LogID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    
}
