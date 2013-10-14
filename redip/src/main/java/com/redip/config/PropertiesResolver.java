package com.redip.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * @author bcivel
 */
public class PropertiesResolver extends PropertyPlaceholderConfigurer {

    public PropertiesResolver() {
        super();
    }

    /**
     * 
     * @param props The property to load
     * @throws IOException 
     */
    @Override
    protected void loadProperties(Properties props) throws IOException {
        String env = System.getProperty("com.redcats.qualityfollowup.environment");

        if (env == null) {
            env = "dev";
        }

        props.setProperty("env", env);

    }
}
