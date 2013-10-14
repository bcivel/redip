/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

/**
 *
 * @author bcivel
 */
public class QualityNonconformitiesAction {

    Integer idQualityNonconformitiesActions;
    Integer idQualityNonconformities;
    String action;
    String deadline;
    String follower;
    String status;
    String date;
    String percentage;
    String priority;
    QualityNonconformities qualityNonconformities;

    public QualityNonconformities getQualityNonconformities() {
        return qualityNonconformities;
    }

    public void setQualityNonconformities(QualityNonconformities qualityNonconformities) {
        this.qualityNonconformities = qualityNonconformities;
    }
    
    

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    
    public Integer getIdQualityNonconformitiesActions() {
        return idQualityNonconformitiesActions;
    }

    public void setIdQualityNonconformitiesActions(Integer idQualityNonconformitiesActions) {
        this.idQualityNonconformitiesActions = idQualityNonconformitiesActions;
    }

    public Integer getIdQualityNonconformities() {
        return idQualityNonconformities;
    }

    public void setIdQualityNonconformities(Integer idQualityNonconformities) {
        this.idQualityNonconformities = idQualityNonconformities;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

   
}
