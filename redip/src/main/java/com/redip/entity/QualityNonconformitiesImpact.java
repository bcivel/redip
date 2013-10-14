/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

/**
 *
 * @author bcivel
 */
public class QualityNonconformitiesImpact {

    private int idqualitynonconformitiesimpact;
    private int idqualitynonconformities;
    private String country;
    private String application;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String impactOrCost;
    private int count;
    private String orderImpacted;
    private String errorPages; 
    private String timeConsumed; 

    public String getOrderImpacted() {
        return orderImpacted;
    }

    public void setOrderImpacted(String orderImpacted) {
        this.orderImpacted = orderImpacted;
    }

    public String getErrorPages() {
        return errorPages;
    }

    public void setErrorPages(String errorPages) {
        this.errorPages = errorPages;
    }

    public String getTimeConsumed() {
        return timeConsumed;
    }

    public void setTimeConsumed(String timeConsumed) {
        this.timeConsumed = timeConsumed;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getIdqualitynonconformities() {
        return idqualitynonconformities;
    }

    public void setIdqualitynonconformities(int idqualitynonconformities) {
        this.idqualitynonconformities = idqualitynonconformities;
    }

    public int getIdqualitynonconformitiesimpact() {
        return idqualitynonconformitiesimpact;
    }

    public void setIdqualitynonconformitiesimpact(int idqualitynonconformitiesimpact) {
        this.idqualitynonconformitiesimpact = idqualitynonconformitiesimpact;
    }

    public String getImpactOrCost() {
        return impactOrCost;
    }

    public void setImpactOrCost(String impactOrCost) {
        this.impactOrCost = impactOrCost;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}