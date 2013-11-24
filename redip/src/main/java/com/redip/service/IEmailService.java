/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

import com.redip.entity.QualityNonconformities;
import org.json.JSONObject;

/**
 *
 * @author bcivel
 */
public interface IEmailService {
   
    public void sendHtmlMail(String host, int port, String body, String subject,
            String from, String to, String cc) throws Exception ;
    
    public void sendEmailEvent(String event, QualityNonconformities nonconformities, String field, String update);
}
