/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesActionDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesAction;
import com.redip.factory.IFactoryQualityNonconformities;
import com.redip.factory.IFactoryQualityNonconformitiesAction;
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
public class QualityNonconformitiesActionDAO implements IQualityNonconformitiesActionDAO {

    @Autowired
    DatabaseSpring databaseSpring;
    @Autowired
    IFactoryQualityNonconformitiesAction factoryNonconformitiesAction;
    @Autowired
    IFactoryQualityNonconformities factoryNonconformities;
    
    @Override
    public List<QualityNonconformitiesAction> findQualityNonconformitiesActionByID(Integer ncid) {
        List<QualityNonconformitiesAction> result = new ArrayList<QualityNonconformitiesAction>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesaction, ");
        query.append(" action,  ");
        query.append(" deadline, follower, status, ");
        query.append(" `date`, percentage, priority FROM qualitynonconformitiesaction ");
        query.append("where idqualitynonconformities like ?");

        ArrayList<String> al = new ArrayList<String>();
        al.add(String.valueOf(ncid));
        
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString(), al);

            while (rs.next()) {
                Integer idqualitynonconformitiesaction = rs.getString("idqualitynonconformitiesaction") == null ? 0 : rs.getInt("idqualitynonconformitiesaction");
                String action = rs.getString("action") == null ? "" : rs.getString("action");
                String deadline = rs.getString("deadline") == null ? "" : rs.getString("deadline");
                String follower = rs.getString("follower") == null ? "" : rs.getString("follower");
                String status = rs.getString("status") == null ? "" : rs.getString("status");
                String date = rs.getString("date") == null ? "" : rs.getString("date");
                String percentage = rs.getString("percentage") == null ? "" : rs.getString("percentage");
                String priority = rs.getString("priority") == null ? "" : rs.getString("priority");
                result.add(factoryNonconformitiesAction.create(idqualitynonconformitiesaction, ncid, action, deadline, follower, status, date, percentage, priority));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;
    }

    @Override
    public String addNonconformityAction(QualityNonconformitiesAction qnc) {
        String statusmessage = "";
        StringBuilder sql = new StringBuilder(); 
        sql.append("INSERT INTO qualitynonconformitiesaction ( `idQualityNonconformities`,");
        sql.append(" `action`, `deadline`, `follower`, `status`, `date`, `percentage`, priority) ");
        sql.append(" values (?,?,?,?,?,?,?,?)");
        
        ArrayList<String> al = new ArrayList<String>();
        al.add(qnc.getIdQualityNonconformities() == null ? "" : String.valueOf(qnc.getIdQualityNonconformities()));
        al.add(qnc.getAction() == null ? "" : qnc.getAction());
        al.add(qnc.getDeadline() == null ? "" : qnc.getDeadline());
        al.add(qnc.getFollower() == null ? "" : qnc.getFollower());
        al.add(qnc.getStatus() == null ? "" : qnc.getStatus());
        al.add(qnc.getDate() == null ? "" : qnc.getDate());
        al.add(qnc.getPercentage() == null ? "" : qnc.getPercentage());
        al.add(qnc.getPriority() == null ? "" : qnc.getPriority());
        
        try {
            databaseSpring.connect();
            if (databaseSpring.update(sql.toString(), al) > 0) {
                statusmessage = StatusMessage.SUCCESS_NONCONFORMITYCREATED;
            } else {
                statusmessage = StatusMessage.ERROR_NONCONFORMITYCREATEDCREATED;
            }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }

        return statusmessage;
    }

    @Override
    public String updateQualityNonConformitiesAction(Integer idnca, String column, String value) {
        String statusmessage = "";
        final String sql = "UPDATE qualitynonconformitiesaction SET `"
                + column
                + "` = ? WHERE Idqualitynonconformitiesaction LIKE ?";
        ArrayList<String> al = new ArrayList<String>();
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
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }
        return statusmessage;
     
     }
    
   
    @Override
    public List<QualityNonconformitiesAction> findQualityNonconformitiesActionByCriteria() {
        List<QualityNonconformitiesAction> result = new ArrayList<QualityNonconformitiesAction>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesaction, ");
        query.append(" a.idqualitynonconformities, a.action,  ");
        query.append(" a.deadline, a.follower, a.status, ");
        query.append(" a.`date`, a.percentage, a.priority, b.problemTitle, b.problemDescription FROM qualitynonconformitiesaction a ");
        query.append(" join qualitynonconformities b on a.idqualitynonconformities=b.idqualitynonconformities ");
        query.append("where a.priority != ? and a.percentage!=? and b.status not in (?) ");
        query.append(" order by a.priority");

        ArrayList<String> al = new ArrayList<String>();
        al.add("");
        al.add("100");
        al.add("CLOSED");
        
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString(), al);

            while (rs.next()) {
                Integer idqualitynonconformitiesaction = rs.getString("idqualitynonconformitiesaction") == null ? 0 : rs.getInt("idqualitynonconformitiesaction");
                Integer idqualitynonconformities = rs.getString("idqualitynonconformities") == null ? 0 : rs.getInt("idqualitynonconformities");
                String action = rs.getString("a.action") == null ? "" : rs.getString("a.action");
                String deadline = rs.getString("a.deadline") == null ? "" : rs.getString("a.deadline");
                String follower = rs.getString("a.follower") == null ? "" : rs.getString("a.follower");
                String status = rs.getString("a.status") == null ? "" : rs.getString("a.status");
                String date = rs.getString("a.date") == null ? "" : rs.getString("a.date");
                String percentage = rs.getString("a.percentage") == null ? "" : rs.getString("a.percentage");
                String priority = rs.getString("a.priority") == null ? "" : rs.getString("a.priority");
                String problemTitle = rs.getString("b.problemTitle") == null ? "" : rs.getString("b.problemTitle");
                String problemDescription = rs.getString("b.problemDescription") == null ? "" : rs.getString("b.problemDescription");
                QualityNonconformities qnc = factoryNonconformities.create(idqualitynonconformities, problemTitle, problemDescription);
                result.add(factoryNonconformitiesAction.create(idqualitynonconformitiesaction, idqualitynonconformities, action, deadline, follower, status, date, percentage, priority, qnc));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;
    }

    @Override
    public List<QualityNonconformitiesAction> findQualityNonconformitiesActionUsingLimit(String fromLimit, String toLimit) {
        List<QualityNonconformitiesAction> result = new ArrayList<QualityNonconformitiesAction>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesaction, ");
        query.append(" a.idqualitynonconformities, action,  ");
        query.append(" a.deadline, a.follower, a.status, ");
        query.append(" a.`date`, a.percentage, a.priority, b.problemTitle, b.problemDescription FROM qualitynonconformitiesaction a ");
        query.append(" join qualitynonconformities b on a.idqualitynonconformities=b.idqualitynonconformities ");
        query.append("where priority != ? and a.percentage!=? and b.status not in (?) ");
        query.append("and priority between ");
        query.append(fromLimit);
        query.append(" and ");
        query.append(toLimit);
        query.append(" order by priority");

        ArrayList<String> al = new ArrayList<String>();
        al.add("");
        al.add("100");
        al.add("CLOSED");
        
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString(), al);

            while (rs.next()) {
                Integer idqualitynonconformitiesaction = rs.getString("idqualitynonconformitiesaction") == null ? 0 : rs.getInt("idqualitynonconformitiesaction");
                Integer idqualitynonconformities = rs.getString("idqualitynonconformities") == null ? 0 : rs.getInt("idqualitynonconformities");
                String action = rs.getString("a.action") == null ? "" : rs.getString("a.action");
                String deadline = rs.getString("a.deadline") == null ? "" : rs.getString("a.deadline");
                String follower = rs.getString("a.follower") == null ? "" : rs.getString("a.follower");
                String status = rs.getString("a.status") == null ? "" : rs.getString("a.status");
                String date = rs.getString("a.date") == null ? "" : rs.getString("a.date");
                String percentage = rs.getString("a.percentage") == null ? "" : rs.getString("a.percentage");
                String priority = rs.getString("a.priority") == null ? "" : rs.getString("a.priority");
                String problemTitle = rs.getString("b.problemTitle") == null ? "" : rs.getString("b.problemTitle");
                String problemDescription = rs.getString("b.problemDescription") == null ? "" : rs.getString("b.problemDescription");
                QualityNonconformities qnc = factoryNonconformities.create(idqualitynonconformities, problemTitle, problemDescription);
                result.add(factoryNonconformitiesAction.create(idqualitynonconformitiesaction, idqualitynonconformities, action, deadline, follower, status, date, percentage, priority, qnc));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;
    }

    @Override
    public Integer getMaxPriorityNumber() {
        Integer result = 99;
        StringBuilder query = new StringBuilder();
        query.append("SELECT max(priority) as response FROM qualitynonconformitiesaction");

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            if (rs.first()) {
                result = (rs.getInt("response"));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;

    }
    
}
