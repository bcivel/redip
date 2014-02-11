/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.service.impl;

import com.redip.entity.QualityNonconformities;
import com.redip.exception.QualityException;
import com.redip.service.IEmailService;
import com.redip.service.IParameterService;
import com.redip.log.Logger;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
import javax.mail.util.ByteArrayDataSource;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Level;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class EmailService implements IEmailService {
    
    @Autowired
    IParameterService parameterService;

    @Override
    public void sendHtmlMail(String host, int port, String body, String subject, String from, String to, String cc) throws Exception {

        HtmlEmail email = new HtmlEmail();
        
//        MimeMultipart part = new MimeMultipart();
//BodyPart messageBodyPart = new MimeBodyPart();
//messageBodyPart.setDataHandler (new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain")));
//messageBodyPart.removeHeader("Content-Transfer-Encoding");
//messageBodyPart.addHeader("Content-Transfer-Encoding", "base64");
//part.addBodyPart(messageBodyPart);
//email.addPart(part);


        
        email.setSmtpPort(port);
        
        email.setDebug(false);
        email.setHostName(host);
        email.setFrom(from);
        email.setSubject(subject);
        
        Logger.log(EmailService.class.getName(), Level.INFO, ("length : " +body.length()));
        //email.addHeader("Content-Transfer-Encoding", "base64");
        email.setCharset("utf-8");
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append(body);
        sb.append("</body></html>");
//        byte [] stringToEncodeBytes = body.getBytes();  
//        String text = Base64.encode(stringToEncodeBytes);
        email.setHtmlMsg(sb.toString());

 //email.addTo("bcivel@redoute.fr", "Benoit CIVEL");
        String[] destinataire = to.split(";");

        for (int i = 0; i < destinataire.length; i++) {
//            String[] destinatairedata = destinataire[i].split("<");
//            String name = destinatairedata[0].trim();
//            String emailaddress = destinatairedata[1].replace(">", "").trim();
            email.addTo(destinataire[i], destinataire[i]);
        }

        String[] copy = cc.split(";");

        for (int i = 0; i < copy.length; i++) {
//            String[] copydata = copy[i].split("<");
//            String namecc = copydata[0].trim();
//            String emailaddresscc = copydata[1].replace(">", "").trim();
            email.addCc(copy[i], copy[i]);
        }

        email.setTLS(false);

        email.send();

    }

    @Override
    public void sendEmailEvent(String event, QualityNonconformities nonconformities, String field, String updatedValue) {
            try {
            /*
            TODO Parametrize send in database
            */
            
            String booleanEvent = null;
            booleanEvent = parameterService.findParameterByKey("email_event_boolean_"+event).getValue() == null ? "false" :
                    parameterService.findParameterByKey("email_event_boolean_"+event).getValue();
            
            if (booleanEvent.equals("true")){
            /*
                FIND PARAMETERS
            */
            String host = parameterService.findParameterByKey("email_smtp_host").getValue();
            Integer port = Integer.valueOf(parameterService.findParameterByKey("email_smtp_port").getValue());
            String from = parameterService.findParameterByKey("email_from").getValue();
            String body = parameterService.findParameterByKey("email_event_body_"+event).getValue();
            String subject = parameterService.findParameterByKey("email_event_subject_"+event).getValue();
            String to = parameterService.findParameterByKey("email_event_to_"+event).getValue();
            //String cc = parameterService.findParameterByKey("email_event_cc_"+event).getValue();
            String cc = nonconformities.getDetection() != null ?  nonconformities.getDetection() : "toto@redoute.fr" ;
            
            if (nonconformities.getIdqualitynonconformities()!=0 && body.contains("%NC%")){
            body = body.replace("%NC%", String.valueOf(nonconformities.getIdqualitynonconformities()));
            subject = subject.replace("%NC%", String.valueOf(nonconformities.getIdqualitynonconformities()));
            }
            
            if (nonconformities.getDetection()!=null && body.contains("%CREATOR%")){
            body = body.replace("%CREATOR%", nonconformities.getDetection());
            }
            
            if (nonconformities.getProblemTitle()!=null && body.contains("%PROBLEMTITLE%")){
            body = body.replace("%PROBLEMTITLE%", nonconformities.getProblemTitle());
            }
            
            if (nonconformities.getProblemDescription()!=null && body.contains("%PROBLEMDESCRIPTION%")){
            body = body.replace("%PROBLEMDESCRIPTION%", nonconformities.getProblemDescription());
            }
            
            if (nonconformities.getReproductibility()!=null && body.contains("%REPRODUCTIBILITY%")){
            body = body.replace("%REPRODUCTIBILITY%", nonconformities.getReproductibility());
            }
            
            if (nonconformities.getSeverity()!=null && body.contains("%SEVERITY%")){
            body = body.replace("%SEVERITY%", nonconformities.getSeverity());
            }
            
            if (updatedValue!=null){
                if (body.contains("%VALUE%")){
                    body = body.replace("%VALUE%", updatedValue);
                }
                if (subject.contains("%VALUE%")){
                    subject = subject.replace("%VALUE%", updatedValue);
                }
            }
            
            if (field!=null){
                if (body.contains("%FIELD%")){
                    body = body.replace("%FIELD%", field);
                }
                if (subject.contains("%FIELD%")){
                    subject = subject.replace("%FIELD%", field);
                }
            }
            
            
                sendHtmlMail(host, port, body, subject, from, to, cc);
             
            }
            } catch (QualityException ex) {
                Logger.log(EmailService.class.getName(), Level.FATAL, ex.toString());
            
    }   catch (Exception ex) {
            Logger.log(EmailService.class.getName(), Level.FATAL, ex.toString());
        }
}
    
}


