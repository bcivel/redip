/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesRootCauseDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesRootCause;
import com.redip.factory.IFactoryQualityNonconformities;
import com.redip.factory.IFactoryQualityNonconformitiesRootCause;
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
public class QualityNonconformitiesRootCauseDAO implements IQualityNonconformitiesRootCauseDAO{

    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    IFactoryQualityNonconformitiesRootCause factory;
    
    @Override
    public List<QualityNonconformitiesRootCause> findAllNonconformitiesRootCause() {
        List<QualityNonconformitiesRootCause> nonconformities = new ArrayList<QualityNonconformitiesRootCause>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription");
        query.append(",responsabilities,status,component,severity,startDate,startTime");
        query.append(",endDate,endTime FROM qualitynonconformitiesrootcause");

        QualityNonconformitiesRootCause nonconformitiestoadd;

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            while (rs.next()) {
                int idqualitynonconformitiesrootcause = rs.getInt("idqualitynonconformitiesrootcause");
                String rootCauseCategory = rs.getString("rootCauseCategory") == null ? "" : rs.getString("rootCauseCategory");
                String rootCauseDescription = rs.getString("rootCauseDescription") == null ? "" : rs.getString("rootCauseDescription");
                String responsabilities = rs.getString("responsabilities") == null ? "" : rs.getString("responsabilities");
                String status = rs.getString("status") == null ? "" : rs.getString("status");
                String component = rs.getString("component") == null ? "" : rs.getString("component");
                String severity = rs.getString("severity") == null ? "" : rs.getString("severity");
                String startDate = rs.getString("startDate") == null ? "" : rs.getString("startDate");
                String startTime = rs.getString("startTime") == null ? "" : rs.getString("startTime");
                String endDate = rs.getString("endDate") == null ? "" : rs.getString("endDate");
                String endTime = rs.getString("endTime") == null ? "" : rs.getString("endTime");
                
                nonconformities.add(factory.create(idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription, responsabilities, status, component, severity, startDate, startTime, endDate, endTime));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return nonconformities;
    }

    @Override
    public List<QualityNonconformitiesRootCause> findAllNonconformitiesRootCause(int start, int amount, String column, String dir, String searchTerm, String individualSearch) {
       List<QualityNonconformitiesRootCause> nonconformities = new ArrayList<QualityNonconformitiesRootCause>();
        StringBuilder gSearch = new StringBuilder();
        String searchSQL = "";
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription");
        query.append(",responsabilities,status,component,severity,startDate,startTime");
        query.append(",endDate,endTime FROM qualitynonconformitiesrootcause ");

        gSearch.append(" where (idqualitynonconformitiesrootcause like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or rootCauseCategory like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or rootCauseDescription like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or responsabilities like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or status like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or component like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or severity like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or startDate like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or startTime like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or endDate like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or endTime like '%");
        gSearch.append(searchTerm);
        gSearch.append("%')");
        
        if(!searchTerm.equals("") && !individualSearch.equals("")){
            searchSQL = gSearch.toString() + " and " + individualSearch;
        }
        else if(!individualSearch.equals("")){
            searchSQL = " where " + individualSearch;
        }else if(!searchTerm.equals("")){
            searchSQL=gSearch.toString();
        }
       
        query.append(searchSQL);
        query.append("order by ");
        query.append(column);
        query.append(" ");
        query.append(dir);
        query.append(" limit ");
        query.append(start);
        query.append(" , ");
        query.append(amount);

        QualityNonconformitiesRootCause nonconformitiestoadd;

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            while (rs.next()) {
                int idqualitynonconformitiesrootcause = rs.getInt("idqualitynonconformitiesrootcause");
                String rootCauseCategory = rs.getString("rootCauseCategory") == null ? "" : rs.getString("rootCauseCategory");
                String rootCauseDescription = rs.getString("rootCauseDescription") == null ? "" : rs.getString("rootCauseDescription");
                String responsabilities = rs.getString("responsabilities") == null ? "" : rs.getString("responsabilities");
                String status = rs.getString("status") == null ? "" : rs.getString("status");
                String component = rs.getString("component") == null ? "" : rs.getString("component");
                String severity = rs.getString("severity") == null ? "" : rs.getString("severity");
                String startDate = rs.getString("startDate") == null ? "" : rs.getString("startDate");
                String startTime = rs.getString("startTime") == null ? "" : rs.getString("startTime");
                String endDate = rs.getString("endDate") == null ? "" : rs.getString("endDate");
                String endTime = rs.getString("endTime") == null ? "" : rs.getString("endTime");
                
                nonconformities.add(factory.create(idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription, responsabilities, status, component, severity, startDate, startTime, endDate, endTime));
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return nonconformities;
    }

    @Override
    public QualityNonconformitiesRootCause findOneNonconformitiesRootCause(int id) {
        QualityNonconformitiesRootCause result = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription");
        query.append(",responsabilities,status,component,severity,startDate,startTime");
        query.append(",endDate,endTime FROM qualitynonconformitiesrootcause where idqualitynonconformitiesrootcause = '");
        query.append(id);
        query.append("'");
        
        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            if (rs.next()) {
                int idqualitynonconformitiesrootcause = rs.getInt("idqualitynonconformitiesrootcause");
                String rootCauseCategory = rs.getString("rootCauseCategory") == null ? "" : rs.getString("rootCauseCategory");
                String rootCauseDescription = rs.getString("rootCauseDescription") == null ? "" : rs.getString("rootCauseDescription");
                String responsabilities = rs.getString("responsabilities") == null ? "" : rs.getString("responsabilities");
                String status = rs.getString("status") == null ? "" : rs.getString("status");
                String component = rs.getString("component") == null ? "" : rs.getString("component");
                String severity = rs.getString("severity") == null ? "" : rs.getString("severity");
                String startDate = rs.getString("startDate") == null ? "" : rs.getString("startDate");
                String startTime = rs.getString("startTime") == null ? "" : rs.getString("startTime");
                String endDate = rs.getString("endDate") == null ? "" : rs.getString("endDate");
                String endTime = rs.getString("endTime") == null ? "" : rs.getString("endTime");
                
                result = factory.create(idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription, responsabilities, status, component, severity, startDate, startTime, endDate, endTime);
            }

        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
            }
        }


        return result;
    }

    @Override
    public String addNonconformityRootCause(QualityNonconformitiesRootCause ncrc) {
        String statusmessage = "";
        final String sql = "INSERT INTO qualitynonconformitiesrootcause (rootCauseCategory, "
                + "rootCauseDescription,responsabilities,status,component,severity,"
                + "startDate,startTime,endDate,endTime) values (?,?,?,?,?,?,?,?,?,?,?)";
        ArrayList<String> al = new ArrayList<String>();
        al.add(ncrc.getRootCauseCategory() == null ? "" : ncrc.getRootCauseCategory());
        al.add(ncrc.getRootCauseDescription() == null ? "" : ncrc.getRootCauseDescription());
        al.add(ncrc.getResponsabilities() == null ? "" : ncrc.getResponsabilities());
        al.add(ncrc.getStatus() == null ? "" : ncrc.getStatus());
        al.add(ncrc.getComponent() == null ? "" : ncrc.getComponent());
        al.add(ncrc.getSeverity()== null ? "" : ncrc.getSeverity());
        al.add(ncrc.getStartDate() == null ? "" : ncrc.getStartDate());
        al.add(ncrc.getStartTime() == null ? "" : ncrc.getStartTime());
        al.add(ncrc.getEndDate() == null ? "" : ncrc.getEndDate());
        al.add(ncrc.getEndTime()== null ? "" : ncrc.getEndTime());
        
        try {
            databaseSpring.connect();
            if (databaseSpring.update(sql, al) > 0) {
                statusmessage = StatusMessage.SUCCESS_NONCONFORMITYCREATED;
            } else {
                statusmessage = StatusMessage.ERROR_NONCONFORMITYCREATEDCREATED;
            }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
            }
        }

        return statusmessage;
    }

    @Override
    public String updateQualityNonConformitiesRootCause(Integer id, String field, String content) {
        String statusmessage = "";
        final String sql = "UPDATE qualitynonconformitiesrootcause SET `"
                + field
                + "` = ? WHERE Idqualitynonconformitiesrootcause LIKE ?";
        ArrayList<String> al = new ArrayList<String>();
        al.add(content.replace("\"","&#34"));
        al.add(String.valueOf(id));

        try {
            databaseSpring.connect();
            if (databaseSpring.update(sql, al) > 0) {
                statusmessage = "[Success] IDNCRC:"+id+" -- Field "+field+" has successfully been updated with value "+content;
            } else {
                statusmessage = "[Error] IDNCRC:"+id+" -- Field "+field+" has not been updated with value "+content;
            }
        } catch (Exception ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                databaseSpring.disconnect();
            } catch (Exception ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.FATAL, "" + ex);
            }
        }
        return statusmessage;
    }
    
}
