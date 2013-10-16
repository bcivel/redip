/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

/**
 *
 * @author bcivel
 */
public class QualityNonconformities {

    private int idqualitynonconformities;
    private String problemCategory;
    private String problemDescription;
    private String problemTitle;
    private String rootCauseCategory;
    private String rootCauseDescription;
    private String responsabilities;
    private String status;
    private String comments;
    private int count;
    private String severity;
    private String application;
    private String applicationFunctionnality;
    private String problemType;
    private String deadline;
    private String detection;
    private String linkToDoc;
    private String showInReporting;
    private String qualityFollower;
    private String testToAvoid;
    private String reproductibility;
    private String behaviorExpected;
    private String priority;
    private String startDate;
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBehaviorExpected() {
        return behaviorExpected;
    }

    public void setBehaviorExpected(String behaviorexpected) {
        this.behaviorExpected = behaviorexpected;
    }

    public String getReproductibility() {
        return reproductibility;
    }

    public void setReproductibility(String reproductibility) {
        this.reproductibility = reproductibility;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getApplicationFunctionnality() {
        return applicationFunctionnality;
    }

    public void setApplicationFunctionnality(String applicationFunctionnality) {
        this.applicationFunctionnality = applicationFunctionnality;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDetection() {
        return detection;
    }

    public void setDetection(String detection) {
        this.detection = detection;
    }

    public String getLinkToDoc() {
        return linkToDoc;
    }

    public void setLinkToDoc(String linkToDoc) {
        this.linkToDoc = linkToDoc;
    }

    public String getShowInReporting() {
        return showInReporting;
    }

    public void setShowInReporting(String showInReporting) {
        this.showInReporting = showInReporting;
    }

    public String getQualityFollower() {
        return qualityFollower;
    }

    public void setQualityFollower(String qualityFollower) {
        this.qualityFollower = qualityFollower;
    }

    public String getTestToAvoid() {
        return testToAvoid;
    }

    public void setTestToAvoid(String testToAvoid) {
        this.testToAvoid = testToAvoid;
    }



    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

   public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIdqualitynonconformities() {
        return idqualitynonconformities;
    }

    public void setIdqualitynonconformities(int idqualitynonconformities) {
        this.idqualitynonconformities = idqualitynonconformities;
    }

    public String getProblemCategory() {
        return problemCategory;
    }

    public void setProblemCategory(String problemCategory) {
        this.problemCategory = problemCategory;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getResponsabilities() {
        return responsabilities;
    }

    public void setResponsabilities(String responsabilities) {
        this.responsabilities = responsabilities;
    }

    public String getRootCauseCategory() {
        return rootCauseCategory;
    }

    public void setRootCauseCategory(String rootCauseCategory) {
        this.rootCauseCategory = rootCauseCategory;
    }

    public String getRootCauseDescription() {
        return rootCauseDescription;
    }

    public void setRootCauseDescription(String rootCauseDescription) {
        this.rootCauseDescription = rootCauseDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}