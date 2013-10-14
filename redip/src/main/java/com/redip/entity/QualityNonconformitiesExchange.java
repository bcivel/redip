/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.entity;

/**
 *
 * @author bcivel
 */
public class QualityNonconformitiesExchange {
    
    Integer idQualityNonconformitiesExchange;
    Integer idQualityNonconformities;
    String date;
    String user;
    String exchangeTitle;
    String exchangeContent;

    public Integer getIdQualityNonconformitiesExchange() {
        return idQualityNonconformitiesExchange;
    }

    public void setIdQualityNonconformitiesExchange(Integer idQualityNonconformitiesExchange) {
        this.idQualityNonconformitiesExchange = idQualityNonconformitiesExchange;
    }

    public Integer getIdQualityNonconformities() {
        return idQualityNonconformities;
    }

    public void setIdQualityNonconformities(Integer idQualityNonconformities) {
        this.idQualityNonconformities = idQualityNonconformities;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExchangeTitle() {
        return exchangeTitle;
    }

    public void setExchangeTitle(String exchangeTitle) {
        this.exchangeTitle = exchangeTitle;
    }

    public String getExchangeContent() {
        return exchangeContent;
    }

    public void setExchangeContent(String exchangeContent) {
        this.exchangeContent = exchangeContent;
    }

}