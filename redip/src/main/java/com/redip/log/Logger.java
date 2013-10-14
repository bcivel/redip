package com.redip.log;

import org.apache.log4j.Level;

/**
 * 
 * @author bcivel
 */
public class Logger {

    public static void log(String className, Level level, String message) {
        org.apache.log4j.Logger.getLogger(className).log(level, message);
    }
}
