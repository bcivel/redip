/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

/**
 *
 * @author bcivel
 */
public class QualityNonconformitiesFollower {
    
    Integer idqualitynonconformitiesfollower;
    Integer idqualitynonconformities;
    String user;

    public Integer getIdqualitynonconformitiesfollower() {
        return idqualitynonconformitiesfollower;
    }

    public void setIdqualitynonconformitiesfollower(Integer idqualitynonconformitiesfollower) {
        this.idqualitynonconformitiesfollower = idqualitynonconformitiesfollower;
    }

    public Integer getIdqualitynonconformities() {
        return idqualitynonconformities;
    }

    public void setIdqualitynonconformities(Integer idqualitynonconformities) {
        this.idqualitynonconformities = idqualitynonconformities;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
