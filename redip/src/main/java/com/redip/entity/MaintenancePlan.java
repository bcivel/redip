/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

/**
 *
 * @author bcivel
 */
public class MaintenancePlan {

    Integer idmaintenanceplan;
    String type;
    String name;
    String descritpion;
    String startdate;
    String starttime;
    String enddate;
    String endtime;
    String details;
    String impact;
    String rollback;
    String externalreference;
    String status;
    String comment;
    String displayInReporting;

    public String getDisplayInReporting() {
        return displayInReporting;
    }

    public void setDisplayInReporting(String displayInReporting) {
        this.displayInReporting = displayInReporting;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getExternalreference() {
        return externalreference;
    }

    public void setExternalreference(String externalreference) {
        this.externalreference = externalreference;
    }

    public Integer getIdmaintenanceplan() {
        return idmaintenanceplan;
    }

    public void setIdmaintenanceplan(Integer idmaintenanceplan) {
        this.idmaintenanceplan = idmaintenanceplan;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollback() {
        return rollback;
    }

    public void setRollback(String rollback) {
        this.rollback = rollback;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
