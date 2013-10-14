/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.dao.impl;

import com.redip.config.StatusMessage;
import com.redip.dao.IQualityNonconformitiesDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.QualityNonconformities;
import com.redip.factory.IFactoryQualityNonconformities;
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
public class QualityNonconformitiesDAOImpl implements IQualityNonconformitiesDAO {

    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    IFactoryQualityNonconformities factory;
    

    @Override
    public List<QualityNonconformities> getAllNonconformities() {
        List<QualityNonconformities> nonconformities = new ArrayList<QualityNonconformities>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformities, ProblemCategory, ");
        query.append(" ProblemDescription,  ");
        query.append(" RootCauseCategory, RootCauseDescription, Responsabilities, ");
        query.append(" Status, Comments, Severity, problemTitle,StartDate FROM qualitynonconformities");

        QualityNonconformities nonconformitiestoadd;

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            while (rs.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(rs.getInt(1));
                nonconformitiestoadd.setProblemCategory(rs.getString(2) == null ? "" : rs.getString(2));
                nonconformitiestoadd.setProblemDescription(rs.getString(3) == null ? "" : rs.getString(3));
                nonconformitiestoadd.setRootCauseCategory(rs.getString(4) == null ? "" : rs.getString(4));
                nonconformitiestoadd.setRootCauseDescription(rs.getString(5) == null ? "" : rs.getString(5));
                nonconformitiestoadd.setResponsabilities(rs.getString(6) == null ? "" : rs.getString(6));
                nonconformitiestoadd.setStatus(rs.getString(7) == null ? "" : rs.getString(7));
                nonconformitiestoadd.setComments(rs.getString(8) == null ? "" : rs.getString(8));
                nonconformitiestoadd.setSeverity(rs.getString(9) == null ? "" : rs.getString(9));
                nonconformitiestoadd.setProblemTitle(rs.getString(10) == null ? "" : rs.getString(10));
                nonconformitiestoadd.setStartDate(rs.getString(11) == null ? "" : rs.getString(11));

                nonconformities.add(nonconformitiestoadd);
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
    public List<QualityNonconformities> getAllNonconformities(int start, int amount, String column, String dir, String searchTerm, String individualSearch) {
        List<QualityNonconformities> nonconformities = new ArrayList<QualityNonconformities>();
        StringBuilder gSearch = new StringBuilder();
        String searchSQL = "";
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformities, ProblemCategory, ");
        query.append(" ProblemDescription, ");
        query.append(" RootCauseCategory, RootCauseDescription, Responsabilities, ");
        query.append(" Status, Comments, Severity, problemTitle FROM qualitynonconformities ");
        
        gSearch.append(" where (idqualitynonconformities like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or ProblemCategory like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or ProblemDescription like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or RootCauseCategory like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or RootCauseDescription like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Responsabilities like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Status like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Comments like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Severity like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or problemTitle like '%");
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

        QualityNonconformities nonconformitiestoadd;

        try {
            Logger.log("LogtoDelete QNCDAO", Level.WARN, query.toString());
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            while (rs.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(rs.getInt(1));
                nonconformitiestoadd.setProblemCategory(rs.getString(2) == null ? "" : rs.getString(2));
                nonconformitiestoadd.setProblemDescription(rs.getString(3) == null ? "" : rs.getString(3));
                nonconformitiestoadd.setRootCauseCategory(rs.getString(4) == null ? "" : rs.getString(4));
                nonconformitiestoadd.setRootCauseDescription(rs.getString(5) == null ? "" : rs.getString(5));
                nonconformitiestoadd.setResponsabilities(rs.getString(6) == null ? "" : rs.getString(6));
                nonconformitiestoadd.setStatus(rs.getString(7) == null ? "" : rs.getString(7));
                nonconformitiestoadd.setComments(rs.getString(8) == null ? "" : rs.getString(8));
                nonconformitiestoadd.setSeverity(rs.getString(9) == null ? "" : rs.getString(9));
                nonconformitiestoadd.setProblemTitle(rs.getString(10) == null ? "" : rs.getString(10));

                nonconformities.add(nonconformitiestoadd);
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
    public QualityNonconformities getNumberOfNonconformities() {
        QualityNonconformities nonconformitiestoadd = new QualityNonconformities();
        StringBuilder query = new StringBuilder();
        query.append("SELECT count(*) FROM qualitynonconformities");

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            if (rs.first()) {
                nonconformitiestoadd.setCount(rs.getInt(1));
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


        return nonconformitiestoadd;

    }

    @Override
    public QualityNonconformities getMaxId() {
        QualityNonconformities nonconformitiestoadd = new QualityNonconformities();
        StringBuilder query = new StringBuilder();
        query.append("SELECT max(idqualitynonconformities) FROM qualitynonconformities");

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            if (rs.first()) {
                nonconformitiestoadd.setIdqualitynonconformities(rs.getInt(1));
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


        return nonconformitiestoadd;

    }

    @Override
    public QualityNonconformities getOneNonconformities(int id) {
        QualityNonconformities result = null;
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT idqualitynonconformities, ProblemCategory, ");
        query.append(" ProblemDescription, problemtitle, ");
        query.append(" RootCauseCategory, RootCauseDescription, Responsabilities, ");
        query.append(" Status, Comments, Severity, application, applicationfunctionnality, ");
        query.append(" problemType, deadline, detection, linktodoc, showinreporting, ");
        query.append(" qualityfollower, testtoavoid, reproductibility, behaviorexpected FROM qualitynonconformities where idqualitynonconformities = '");
        query.append(id);
        query.append("'");

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());
            if (rs.first()) {
                Integer Idqualitynonconformities = rs.getString("Idqualitynonconformities") == null ? 0 : rs.getInt("Idqualitynonconformities");
                String problemCategory = rs.getString("problemCategory") == null ? "" : rs.getString("problemCategory");
                String problemDescription = rs.getString("problemDescription") == null ? "" : rs.getString("problemDescription");
                String problemTitle = rs.getString("problemTitle") == null ? "" : rs.getString("problemTitle");
                String rootCauseCategory = rs.getString("rootCauseCategory") == null ? "" : rs.getString("rootCauseCategory");
                String rootCauseDescription = rs.getString("rootCauseDescription") == null ? "" : rs.getString("rootCauseDescription");
                String responsabilities = rs.getString("responsabilities") == null ? "" : rs.getString("responsabilities");
                String status = rs.getString("status") == null ? "" : rs.getString("status");
                String comments = rs.getString("comments") == null ? "" : rs.getString("comments");
                String severity = rs.getString("severity") == null ? "" : rs.getString("severity");
                String application = rs.getString("application") == null ? "" : rs.getString("application");
                String applicationFunctionnality = rs.getString("applicationFunctionnality") == null ? "" : rs.getString("applicationFunctionnality");
                String problemType = rs.getString("problemType") == null ? "" : rs.getString("problemType");
                String deadline = rs.getString("deadline") == null ? "" : rs.getString("deadline");
                String detection = rs.getString("detection") == null ? "" : rs.getString("detection");
                String linktodoc = rs.getString("linktodoc") == null ? "" : rs.getString("linktodoc"); 
                String showinreporting  = rs.getString("showinreporting") == null ? "" : rs.getString("showinreporting");
                String qualityfollower  = rs.getString("qualityfollower") == null ? "" : rs.getString("qualityfollower");
                String testtoavoid = rs.getString("testtoavoid") == null ? "" : rs.getString("testtoavoid");
                String reproductibility = rs.getString("reproductibility") == null ? "" : rs.getString("reproductibility");
                String behaviorExpected = rs.getString("behaviorexpected") == null ? "" : rs.getString("behaviorexpected");
            result = factory.create(id, problemCategory, problemDescription, problemTitle, 
                    rootCauseCategory, rootCauseDescription, responsabilities, status, comments, 
                    severity, application, applicationFunctionnality, problemType, deadline, detection,
                    linktodoc, showinreporting, qualityfollower, testtoavoid, reproductibility, behaviorExpected);
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
    public String addNonconformity(QualityNonconformities qualitync) {
        String statusmessage = "";
        final String sql = "INSERT INTO qualitynonconformities ( ProblemTitle, "
                + "ProblemDescription, Severity, reproductibility, linkToDoc, behaviorExpected, status  ) values (?,?,?,?,?,?,?)";
        ArrayList<String> al = new ArrayList<String>();
        al.add(qualitync.getProblemTitle() == null ? "" : qualitync.getProblemTitle());
        al.add(qualitync.getProblemDescription() == null ? "" : qualitync.getProblemDescription());
        al.add(qualitync.getSeverity() == null ? "" : qualitync.getSeverity());
        al.add(qualitync.getReproductibility() == null ? "" : qualitync.getReproductibility());
        al.add(qualitync.getLinkToDoc() == null ? "" : qualitync.getLinkToDoc());
        al.add(qualitync.getBehaviorExpected()== null ? "" : qualitync.getBehaviorExpected());
        al.add("NEW");
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
     public String updateQualityNonConformities(Integer id, String field, String content){
     String statusmessage = "";
        final String sql = "UPDATE qualitynonconformities SET `"
                + field
                + "` = ? WHERE Idqualitynonconformities LIKE ?";
        ArrayList<String> al = new ArrayList<String>();
        al.add(content.replace("\"","&#34"));
        al.add(String.valueOf(id));

        try {
            databaseSpring.connect();
            if (databaseSpring.update(sql, al) > 0) {
                statusmessage = "[Success] IDNC:"+id+" -- Field "+field+" has successfully been updated with value "+content;
            } else {
                statusmessage = "[Error] IDNC:"+id+" -- Field "+field+" has not been updated with value "+content;
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
    public List<QualityNonconformities> findNonconformitiesOpenedByResponsability(int start, int amount, String column, String dir, String searchTerm, String individualSearch, String responsability, String fromPriority, String toPriority) {
        List<QualityNonconformities> nonconformities = new ArrayList<QualityNonconformities>();
        StringBuilder gSearch = new StringBuilder();
        String searchSQL = "";
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformities, ProblemCategory, ");
        query.append(" ProblemDescription, ");
        query.append(" RootCauseCategory, RootCauseDescription, Responsabilities, ");
        query.append(" Status, Comments, Severity, problemTitle, priority, deadline FROM qualitynonconformities where status not in ('CLOSED') and responsabilities = '");
        query.append(responsability);
        query.append("'");
        
        if (fromPriority!=null && toPriority!=null){
        query.append(" and priority between ");
        query.append(fromPriority);
        query.append(" and ");
        query.append(toPriority);
        }
        gSearch.append(" and (idqualitynonconformities like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or ProblemCategory like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or ProblemDescription like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or RootCauseCategory like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or RootCauseDescription like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Responsabilities like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Status like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Comments like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Severity like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or Deadline like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or problemTitle like '%");
        gSearch.append(searchTerm);
        gSearch.append("%')");
        
        if(!searchTerm.equals("") && !individualSearch.equals("")){
            searchSQL = gSearch.toString() + " and " + individualSearch;
        }
        else if(!individualSearch.equals("")){
            searchSQL = " and " + individualSearch;
        }else if(!searchTerm.equals("")){
            searchSQL=gSearch.toString();
        }
       
        query.append(searchSQL);
        query.append("order by ");
        query.append(" case when priority is null then 1 else 0 end, priority , ");
        
        query.append(column);
        query.append(" ");
        query.append(dir);
        query.append(" limit ");
        query.append(start);
        query.append(" , ");
        query.append(amount);

        QualityNonconformities nonconformitiestoadd;

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            while (rs.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(rs.getInt(1));
                nonconformitiestoadd.setProblemCategory(rs.getString(2) == null ? "" : rs.getString(2));
                nonconformitiestoadd.setProblemDescription(rs.getString(3) == null ? "" : rs.getString(3));
                nonconformitiestoadd.setRootCauseCategory(rs.getString(4) == null ? "" : rs.getString(4));
                nonconformitiestoadd.setRootCauseDescription(rs.getString(5) == null ? "" : rs.getString(5));
                nonconformitiestoadd.setResponsabilities(rs.getString(6) == null ? "" : rs.getString(6));
                nonconformitiestoadd.setStatus(rs.getString(7) == null ? "" : rs.getString(7));
                nonconformitiestoadd.setComments(rs.getString(8) == null ? "" : rs.getString(8));
                nonconformitiestoadd.setSeverity(rs.getString(9) == null ? "" : rs.getString(9));
                nonconformitiestoadd.setProblemTitle(rs.getString(10) == null ? "" : rs.getString(10));
                nonconformitiestoadd.setPriority(rs.getString(11) == null ? "" : rs.getString(11));
                nonconformitiestoadd.setDeadline(rs.getString(12) == null ? "" : rs.getString(12));

                nonconformities.add(nonconformitiestoadd);
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
    public List<QualityNonconformities> findNonconformitiesOpenedByResponsability(String responsability, String fromPriority, String toPriority) {
    List<QualityNonconformities> nonconformities = new ArrayList<QualityNonconformities>();
        StringBuilder gSearch = new StringBuilder();
        String searchSQL = "";
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformities, ProblemCategory, ");
        query.append(" ProblemDescription, ");
        query.append(" RootCauseCategory, RootCauseDescription, Responsabilities, ");
        query.append(" Status, Comments, Severity, problemTitle, priority FROM qualitynonconformities where status not in ('CLOSED') and responsabilities = '");
        query.append(responsability);
        query.append("'");
        query.append(" and priority between ");
        query.append(fromPriority);
        query.append(" and ");
        query.append(toPriority);
        query.append(" order by priority asc");
        
        QualityNonconformities nonconformitiestoadd;

        try {
            databaseSpring.connect();
            ResultSet rs = databaseSpring.query(query.toString());

            while (rs.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(rs.getInt(1));
                nonconformitiestoadd.setProblemCategory(rs.getString(2) == null ? "" : rs.getString(2));
                nonconformitiestoadd.setProblemDescription(rs.getString(3) == null ? "" : rs.getString(3));
                nonconformitiestoadd.setRootCauseCategory(rs.getString(4) == null ? "" : rs.getString(4));
                nonconformitiestoadd.setRootCauseDescription(rs.getString(5) == null ? "" : rs.getString(5));
                nonconformitiestoadd.setResponsabilities(rs.getString(6) == null ? "" : rs.getString(6));
                nonconformitiestoadd.setStatus(rs.getString(7) == null ? "" : rs.getString(7));
                nonconformitiestoadd.setComments(rs.getString(8) == null ? "" : rs.getString(8));
                nonconformitiestoadd.setSeverity(rs.getString(9) == null ? "" : rs.getString(9));
                nonconformitiestoadd.setProblemTitle(rs.getString(10) == null ? "" : rs.getString(10));
                nonconformitiestoadd.setPriority(rs.getString(11) == null ? "" : rs.getString(11));

                nonconformities.add(nonconformitiestoadd);
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
}