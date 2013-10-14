/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service;

/**
 *
 * @author bcivel
 */
public interface IEmailService {
   
    public void sendHtmlMail(String host, int port, String body, String subject,
            String from, String to, String cc) throws Exception ;
    
    
}
