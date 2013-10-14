/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.factory.impl;

import com.redip.entity.QualityNonconformities;
import com.redip.factory.IFactoryQualityNonconformities;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class FactoryQualityNonconformities implements IFactoryQualityNonconformities {

    @Override
    public QualityNonconformities create(int idqualitynonconformities, String problemCategory, String problemDescription, String problemTitle, String rootCauseCategory, String rootCauseDescription, String responsabilities, String status, String comments, String severity, String application, String applicationFunctionnality, String problemType, String deadline, String detection, String linkToDoc, String showInReporting, String qualityFollower, String testToAvoid, String reproductibility, String behaviorExpected) {
    QualityNonconformities qnc = new QualityNonconformities();
    qnc.setApplication(application);
    qnc.setApplicationFunctionnality(applicationFunctionnality);
    qnc.setComments(comments);
    qnc.setDeadline(deadline);
    qnc.setDetection(detection);
    qnc.setIdqualitynonconformities(idqualitynonconformities);
    qnc.setLinkToDoc(linkToDoc);
    qnc.setProblemCategory(problemCategory);
    qnc.setProblemDescription(problemDescription);
    qnc.setProblemTitle(problemTitle);
    qnc.setProblemType(problemType);
    qnc.setQualityFollower(qualityFollower);
    qnc.setResponsabilities(responsabilities);
    qnc.setRootCauseCategory(rootCauseCategory);
    qnc.setRootCauseDescription(rootCauseDescription);
    qnc.setSeverity(severity);
    qnc.setShowInReporting(showInReporting);
    qnc.setStatus(status);
    qnc.setTestToAvoid(testToAvoid);
    qnc.setReproductibility(reproductibility);
    qnc.setBehaviorExpected(behaviorExpected);
            
    return qnc;
    }

    @Override
    public QualityNonconformities create(String problemTitle, String problemDescription, String severity, 
    String reproductibility, String linkToDoc, String behaviorExpected) {
        QualityNonconformities qnc = new QualityNonconformities();
        qnc.setProblemTitle(problemTitle);
        qnc.setProblemDescription(problemDescription);
        qnc.setSeverity(severity);
        qnc.setReproductibility(reproductibility);
        qnc.setLinkToDoc(linkToDoc);
        qnc.setBehaviorExpected(behaviorExpected);
        return qnc;
    }

    @Override
    public QualityNonconformities create(int idqualitynonconformities, String problemTitle, String problemDescription) {
        QualityNonconformities qnc = new QualityNonconformities();
        qnc.setIdqualitynonconformities(idqualitynonconformities);
        qnc.setProblemTitle(problemTitle);
        qnc.setProblemDescription(problemDescription);
        
        return qnc;
    }
}
