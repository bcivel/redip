package com.redip.database;

import java.util.List;

public interface Database {

    boolean execute(String sql);

    /**
     * 
     * @param script Script to run.
     * @return List of String containing data for the graph generation.
     */
    List<List<String>> getDataForGraph(String script);
}
