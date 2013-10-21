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
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        
        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getAllNonconformities");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    while (resultSet.next()) {
                        nonconformitiestoadd = new QualityNonconformities();
                        nonconformitiestoadd.setIdqualitynonconformities(resultSet.getInt(1));
                        nonconformitiestoadd.setProblemCategory(resultSet.getString(2) == null ? "" : resultSet.getString(2));
                        nonconformitiestoadd.setProblemDescription(resultSet.getString(3) == null ? "" : resultSet.getString(3));
                        nonconformitiestoadd.setRootCauseCategory(resultSet.getString(4) == null ? "" : resultSet.getString(4));
                        nonconformitiestoadd.setRootCauseDescription(resultSet.getString(5) == null ? "" : resultSet.getString(5));
                        nonconformitiestoadd.setResponsabilities(resultSet.getString(6) == null ? "" : resultSet.getString(6));
                        nonconformitiestoadd.setStatus(resultSet.getString(7) == null ? "" : resultSet.getString(7));
                        nonconformitiestoadd.setComments(resultSet.getString(8) == null ? "" : resultSet.getString(8));
                        nonconformitiestoadd.setSeverity(resultSet.getString(9) == null ? "" : resultSet.getString(9));
                        nonconformitiestoadd.setProblemTitle(resultSet.getString(10) == null ? "" : resultSet.getString(10));
                        nonconformitiestoadd.setStartDate(resultSet.getString(11) == null ? "" : resultSet.getString(11));
                        nonconformities.add(nonconformitiestoadd);
            }

                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getAllNonconformities");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getAllNonconformities");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                
                while (resultSet.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(resultSet.getInt(1));
                nonconformitiestoadd.setProblemCategory(resultSet.getString(2) == null ? "" : resultSet.getString(2));
                nonconformitiestoadd.setProblemDescription(resultSet.getString(3) == null ? "" : resultSet.getString(3));
                nonconformitiestoadd.setRootCauseCategory(resultSet.getString(4) == null ? "" : resultSet.getString(4));
                nonconformitiestoadd.setRootCauseDescription(resultSet.getString(5) == null ? "" : resultSet.getString(5));
                nonconformitiestoadd.setResponsabilities(resultSet.getString(6) == null ? "" : resultSet.getString(6));
                nonconformitiestoadd.setStatus(resultSet.getString(7) == null ? "" : resultSet.getString(7));
                nonconformitiestoadd.setComments(resultSet.getString(8) == null ? "" : resultSet.getString(8));
                nonconformitiestoadd.setSeverity(resultSet.getString(9) == null ? "" : resultSet.getString(9));
                nonconformitiestoadd.setProblemTitle(resultSet.getString(10) == null ? "" : resultSet.getString(10));

                nonconformities.add(nonconformitiestoadd);
            }

                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getAllNonconformities");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformities;
    }

    @Override
    public QualityNonconformities getNumberOfNonconformities() {
        QualityNonconformities nonconformitiestoadd = new QualityNonconformities();
        StringBuilder query = new StringBuilder();
        query.append("SELECT count(*) FROM qualitynonconformities");

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getNumberOfNonconformities");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                
                if (resultSet.first()) {
                nonconformitiestoadd.setCount(resultSet.getInt(1));
                }

        } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getNumberOfNonconformities");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return nonconformitiestoadd;

    }

    @Override
    public QualityNonconformities getMaxId() {
        QualityNonconformities nonconformitiestoadd = new QualityNonconformities();
        StringBuilder query = new StringBuilder();
        query.append("SELECT max(idqualitynonconformities) FROM qualitynonconformities");

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getMaxId");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    if (resultSet.first()) {
                        nonconformitiestoadd.setIdqualitynonconformities(resultSet.getInt(1));
                    }

                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getMaxId");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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
        query.append(" qualityfollower, testtoavoid, reproductibility, behaviorexpected, screenshot FROM qualitynonconformities where idqualitynonconformities = '");
        query.append(id);
        query.append("'");

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getOneNonconformities");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    if (resultSet.first()) {
                        Integer Idqualitynonconformities = resultSet.getString("Idqualitynonconformities") == null ? 0 : resultSet.getInt("Idqualitynonconformities");
                        String problemCategory = resultSet.getString("problemCategory") == null ? "" : resultSet.getString("problemCategory");
                        String problemDescription = resultSet.getString("problemDescription") == null ? "" : resultSet.getString("problemDescription");
                        String problemTitle = resultSet.getString("problemTitle") == null ? "" : resultSet.getString("problemTitle");
                        String rootCauseCategory = resultSet.getString("rootCauseCategory") == null ? "" : resultSet.getString("rootCauseCategory");
                        String rootCauseDescription = resultSet.getString("rootCauseDescription") == null ? "" : resultSet.getString("rootCauseDescription");
                        String responsabilities = resultSet.getString("responsabilities") == null ? "" : resultSet.getString("responsabilities");
                        String status = resultSet.getString("status") == null ? "" : resultSet.getString("status");
                        String comments = resultSet.getString("comments") == null ? "" : resultSet.getString("comments");
                        String severity = resultSet.getString("severity") == null ? "" : resultSet.getString("severity");
                        String application = resultSet.getString("application") == null ? "" : resultSet.getString("application");
                        String applicationFunctionnality = resultSet.getString("applicationFunctionnality") == null ? "" : resultSet.getString("applicationFunctionnality");
                        String problemType = resultSet.getString("problemType") == null ? "" : resultSet.getString("problemType");
                        String deadline = resultSet.getString("deadline") == null ? "" : resultSet.getString("deadline");
                        String detection = resultSet.getString("detection") == null ? "" : resultSet.getString("detection");
                        String linktodoc = resultSet.getString("linktodoc") == null ? "" : resultSet.getString("linktodoc"); 
                        String showinreporting  = resultSet.getString("showinreporting") == null ? "" : resultSet.getString("showinreporting");
                        String qualityfollower  = resultSet.getString("qualityfollower") == null ? "" : resultSet.getString("qualityfollower");
                        String testtoavoid = resultSet.getString("testtoavoid") == null ? "" : resultSet.getString("testtoavoid");
                        String reproductibility = resultSet.getString("reproductibility") == null ? "" : resultSet.getString("reproductibility");
                        String behaviorExpected = resultSet.getString("behaviorexpected") == null ? "" : resultSet.getString("behaviorexpected");
                    String screenshot = resultSet.getString("screenshot") == null ? "" : resultSet.getString("screenshot");
                    result = factory.create(id, problemCategory, problemDescription, problemTitle, 
                            rootCauseCategory, rootCauseDescription, responsabilities, status, comments, 
                            severity, application, applicationFunctionnality, problemType, deadline, detection,
                            linktodoc, showinreporting, qualityfollower, testtoavoid, reproductibility, behaviorExpected, screenshot);
            }
                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getOneNonconformities");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }

    @Override
    public String addNonconformity(QualityNonconformities qualitync) {
        String statusmessage = "";
        final String sql = "INSERT INTO qualitynonconformities ( ProblemTitle, "
                + "ProblemDescription, Severity, reproductibility, linkToDoc, behaviorExpected, status, detection"
                + ",startdate, starttime, screenshot  ) values (?,?,?,?,?,?,?,?,?,?,?)";
        String problemTitle = qualitync.getProblemTitle() == null ? "" : qualitync.getProblemTitle();
        String problemDesc = qualitync.getProblemDescription() == null ? "" : qualitync.getProblemDescription();
        String severity = qualitync.getSeverity() == null ? "" : qualitync.getSeverity();
        String reproductibility = qualitync.getReproductibility() == null ? "" : qualitync.getReproductibility();
        String linkToDoc = qualitync.getLinkToDoc() == null ? "" : qualitync.getLinkToDoc();
        String behaviorExpected = qualitync.getBehaviorExpected()== null ? "" : qualitync.getBehaviorExpected();
        String status = "NEW";
        String detection = qualitync.getDetection()== null ? "" : qualitync.getDetection();
        String startDate = qualitync.getStartDate()== null ? "" : qualitync.getStartDate();
        String startTime = qualitync.getStartTime()== null ? "" : qualitync.getStartTime();
        String screenshot = qualitync.getScreenshot()== null ? "" : qualitync.getScreenshot();
        
        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformity");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            try {
                preStat.setString(1, problemTitle);
                preStat.setString(2, problemDesc);
                preStat.setString(3, severity);
                preStat.setString(4, reproductibility);
                preStat.setString(5, linkToDoc);
                preStat.setString(6, behaviorExpected);
                preStat.setString(7, status);
                preStat.setString(8, detection);
                preStat.setString(9, startDate);
                preStat.setString(10, startTime);
                preStat.setString(11, screenshot);
                int res = preStat.executeUpdate();
                try {
                    
                    if ( res > 0) {
                    statusmessage = StatusMessage.SUCCESS_NONCONFORMITYCREATED;
                    } else {
                    statusmessage = StatusMessage.ERROR_NONCONFORMITYCREATEDCREATED;
                    }
                } catch (Exception exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from addNonconformity");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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
        content = content.replace("\"","&#34");
        
        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateQualityNonConformities");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            try {
                preStat.setString(1, content);
                preStat.setInt(2, id);
                int res = preStat.executeUpdate();
                try {
                    if (res > 0) {
                        statusmessage = "[Success] IDNC:"+id+" -- Field "+field+" has successfully been updated with value "+content;
                    } else {
                        statusmessage = "[Error] IDNC:"+id+" -- Field "+field+" has not been updated with value "+content;
                    }
                } catch (Exception exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from updateQualityNonConformities");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findNonconformitiesOpenedByResponsability");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                 
                while (resultSet.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(resultSet.getInt(1));
                nonconformitiestoadd.setProblemCategory(resultSet.getString(2) == null ? "" : resultSet.getString(2));
                nonconformitiestoadd.setProblemDescription(resultSet.getString(3) == null ? "" : resultSet.getString(3));
                nonconformitiestoadd.setRootCauseCategory(resultSet.getString(4) == null ? "" : resultSet.getString(4));
                nonconformitiestoadd.setRootCauseDescription(resultSet.getString(5) == null ? "" : resultSet.getString(5));
                nonconformitiestoadd.setResponsabilities(resultSet.getString(6) == null ? "" : resultSet.getString(6));
                nonconformitiestoadd.setStatus(resultSet.getString(7) == null ? "" : resultSet.getString(7));
                nonconformitiestoadd.setComments(resultSet.getString(8) == null ? "" : resultSet.getString(8));
                nonconformitiestoadd.setSeverity(resultSet.getString(9) == null ? "" : resultSet.getString(9));
                nonconformitiestoadd.setProblemTitle(resultSet.getString(10) == null ? "" : resultSet.getString(10));
                nonconformitiestoadd.setPriority(resultSet.getString(11) == null ? "" : resultSet.getString(11));
                nonconformitiestoadd.setDeadline(resultSet.getString(12) == null ? "" : resultSet.getString(12));

                nonconformities.add(nonconformitiestoadd);
            }

                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findNonconformitiesOpenedByResponsability");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findNonconformitiesOpenedByResponsability");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    
                while (resultSet.next()) {
                nonconformitiestoadd = new QualityNonconformities();

                nonconformitiestoadd.setIdqualitynonconformities(resultSet.getInt(1));
                nonconformitiestoadd.setProblemCategory(resultSet.getString(2) == null ? "" : resultSet.getString(2));
                nonconformitiestoadd.setProblemDescription(resultSet.getString(3) == null ? "" : resultSet.getString(3));
                nonconformitiestoadd.setRootCauseCategory(resultSet.getString(4) == null ? "" : resultSet.getString(4));
                nonconformitiestoadd.setRootCauseDescription(resultSet.getString(5) == null ? "" : resultSet.getString(5));
                nonconformitiestoadd.setResponsabilities(resultSet.getString(6) == null ? "" : resultSet.getString(6));
                nonconformitiestoadd.setStatus(resultSet.getString(7) == null ? "" : resultSet.getString(7));
                nonconformitiestoadd.setComments(resultSet.getString(8) == null ? "" : resultSet.getString(8));
                nonconformitiestoadd.setSeverity(resultSet.getString(9) == null ? "" : resultSet.getString(9));
                nonconformitiestoadd.setProblemTitle(resultSet.getString(10) == null ? "" : resultSet.getString(10));
                nonconformitiestoadd.setPriority(resultSet.getString(11) == null ? "" : resultSet.getString(11));

                nonconformities.add(nonconformitiestoadd);
            }

                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findNonconformitiesOpenedByResponsability");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return nonconformities;    
    }

    @Override
    public List<String> findDistinctValuesfromParameter(String parameter) {
        List<String> result = new ArrayList<String>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct ");
        query.append(parameter);
        query.append(" FROM qualitynonconformities");
        
        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findDistinctValuesfromParameter");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                 
                    while (resultSet.next()) {
                        result.add(resultSet.getString(1)== null ? "" : resultSet.getString(1) );
                    }

            resultSet.close();
                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findDistinctValuesfromParameter");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;  }
}