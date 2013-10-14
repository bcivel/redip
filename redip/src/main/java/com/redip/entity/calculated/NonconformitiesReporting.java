/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity.calculated;

/**
 *
 * @author bcivel
 */
public class NonconformitiesReporting {

    public int numberOfLostOrders;
    public String responsabilities;
    public int numberOfNonconformities;
    public String problemCategory;
    public String rootCauseCategory;
    public String unavailabilityDuration;
    public String rootCauseDescritpion;
    public String startDate;
    public String country;
    public String severity;
    public int weekNumber;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRootCauseDescritpion() {
        return rootCauseDescritpion;
    }

    public void setRootCauseDescritpion(String rootCauseDescritpion) {
        this.rootCauseDescritpion = rootCauseDescritpion;
    }

    public String getProblemCategory() {
        return problemCategory;
    }

    public void setProblemCategory(String problemCategory) {
        this.problemCategory = problemCategory;
    }

    public String getRootCauseCategory() {
        return rootCauseCategory;
    }

    public void setRootCauseCategory(String rootCauseCategory) {
        this.rootCauseCategory = rootCauseCategory;
    }

    public String getUnavailabilityDuration() {
        return unavailabilityDuration;
    }

    public void setUnavailabilityDuration(String unavailabilityDuration) {
        this.unavailabilityDuration = unavailabilityDuration;
    }

    public int getNumberOfLostOrders() {
        return numberOfLostOrders;
    }

    public int getNumberOfNonconformities() {
        return numberOfNonconformities;
    }

    public void setNumberOfNonconformities(int numberOfNonconformities) {
        this.numberOfNonconformities = numberOfNonconformities;
    }

    public void setNumberOfLostOrders(int numberOfLostOrders) {
        this.numberOfLostOrders = numberOfLostOrders;
    }

    public String getResponsabilities() {
        return responsabilities;
    }

    public void setResponsabilities(String responsabilities) {
        this.responsabilities = responsabilities;
    }
}
