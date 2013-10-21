package com.redip.database;

import java.util.List;

public interface Database {

     /**
     * 
     * @param script Script to run.
     * @return List of String containing data for the graph generation.
     */
    List<List<String>> getDataForGraph(String script);
}
