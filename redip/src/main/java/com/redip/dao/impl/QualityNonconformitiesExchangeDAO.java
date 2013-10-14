/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesExchangeDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.QualityNonconformitiesExchange;
import com.redip.factory.IFactoryQualityNonconformitiesExchange;
import com.redip.log.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bcivel
 */
@Repository
public class QualityNonconformitiesExchangeDAO implements IQualityNonconformitiesExchangeDAO {

    @Autowired
    DatabaseSpring databaseSpring;
    @Autowired
    IFactoryQualityNonconformitiesExchange factoryNonconformitiesExchange;
    
    @Override
    public List<QualityNonconformitiesExchange> findQualityNonconformitiesActionByID(Integer ncid) {
        List<QualityNonconformitiesExchange> result = new ArrayList<QualityNonconformitiesExchange>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesexchange, ");
        query.append(" `date`,  ");
        query.append(" exchangetitle, exchangecontent, user ");
        query.append(" FROM qualitynonconformitiesexchange ");
        query.append("where idqualitynonconformities like ?");

        ArrayList<String> al = new ArrayList<String>();
        al.add(String.valueOf(ncid));
        
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString(), al);

            while (rs.next()) {
                Integer idqualitynonconformitiesexchange = rs.getString("idqualitynonconformitiesexchange") == null ? 0 : rs.getInt("idqualitynonconformitiesexchange");
                String date = rs.getString("date") == null ? "" : rs.getString("date");
                String exchangetitle = rs.getString("exchangetitle") == null ? "" : rs.getString("exchangetitle");
                String exchangecontent = rs.getString("exchangecontent") == null ? "" : rs.getString("exchangecontent");
                String user = rs.getString("user") == null ? "" : rs.getString("user");
                result.add(factoryNonconformitiesExchange.create(idqualitynonconformitiesexchange, ncid, date, user, exchangetitle, exchangecontent));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;
    }

    @Override
    public String addNonconformityExchange(QualityNonconformitiesExchange qnc) {
        String statusmessage = "";
        
        StringBuilder sql = new StringBuilder(); 
        sql.append("INSERT INTO qualitynonconformitiesexchange ( `idQualityNonconformities`,");
        sql.append(" `date`, `exchangecontent`, `exchangetitle`, `user`) ");
        sql.append(" values (?,?,?,?,?)");
        
        ArrayList<String> al = new ArrayList<String>();
        al.add(qnc.getIdQualityNonconformities() == null ? "" : String.valueOf(qnc.getIdQualityNonconformities()));
        al.add(qnc.getDate() == null ? "" : qnc.getDate());
        al.add(qnc.getExchangeContent() == null ? "" : qnc.getExchangeContent());
        al.add(qnc.getExchangeTitle() == null ? "" : qnc.getExchangeTitle());
        al.add(qnc.getUser() == null ? "" : qnc.getUser());
        
        
        try {
            databaseSpring.connect();
            if (databaseSpring.update(sql.toString(), al) > 0) {
                statusmessage = StatusMessage.SUCCESS_NONCONFORMITYCREATED;
            } else {
                statusmessage = StatusMessage.ERROR_NONCONFORMITYCREATEDCREATED;
            }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }

        return statusmessage;
    }

    @Override
    public String updateQualityNonConformitiesExchange(Integer idnca, String column, String value) {
        String statusmessage = "";
        final String sql = "UPDATE qualitynonconformitiesexchange SET ? = ? WHERE Idqualitynonconformitiesexchange LIKE ?";
        ArrayList<String> al = new ArrayList<String>();
        al.add(column);
        al.add(value);
        al.add(String.valueOf(idnca));

        try {
            databaseSpring.connect();
            if (databaseSpring.update(sql, al) > 0) {
                statusmessage = "[Success] IDNC:"+idnca+" -- Field "+column+" has successfully been updated with value "+value;
            } else {
                statusmessage = "[Error] IDNC:"+idnca+" -- Field "+column+" has not been updated with value "+value;
            }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesExchangeDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }
        return statusmessage;
    }
    
}
