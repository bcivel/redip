/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.GraphScript;
import com.redip.factory.IFactoryGraphScript;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryGraphScriptImpl implements IFactoryGraphScript {

    @Override
    public GraphScript create(Integer id, String title, String type, String script) {
        GraphScript graphScript = new GraphScript();
        graphScript.setId(id);
        graphScript.setScript(script);
        graphScript.setTitle(title);
        graphScript.setType(type);
        return graphScript;
    }
    
}
