/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.service.IEmailService;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class EmailService implements IEmailService {

    public void sendHtmlMail(String host, int port, String body, String subject, String from, String to, String cc) throws Exception {

        HtmlEmail email = new HtmlEmail();
        email.setSmtpPort(port);
        email.setDebug(false);
        email.setHostName(host);
        email.setFrom(from);
        email.setSubject(subject);
        email.setHtmlMsg(body);

 email.addTo("bcivel@redoute.fr", "Benoit CIVEL");
//        String[] destinataire = to.split(";");
//
//        for (int i = 0; i < destinataire.length; i++) {
//            String[] destinatairedata = destinataire[i].split("<");
//            String name = destinatairedata[0].trim();
//            String emailaddress = destinatairedata[1].replace(">", "").trim();
//            email.addTo(emailaddress, name);
//        }
//
//        String[] copy = cc.split(";");
//
//        for (int i = 0; i < copy.length; i++) {
//            String[] copydata = copy[i].split("<");
//            String namecc = copydata[0].trim();
//            String emailaddresscc = copydata[1].replace(">", "").trim();
//            email.addCc(emailaddresscc, namecc);
//        }

        email.setTLS(true);

        email.send();

    }
}
