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

        Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findAllNonconformitiesRootCause");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                
                    while (resultSet.next()) {
                    int idqualitynonconformitiesrootcause = resultSet.getInt("idqualitynonconformitiesrootcause");
                    String rootCauseCategory = resultSet.getString("rootCauseCategory") == null ? "" : resultSet.getString("rootCauseCategory");
                    String rootCauseDescription = resultSet.getString("rootCauseDescription") == null ? "" : resultSet.getString("rootCauseDescription");
                    String responsabilities = resultSet.getString("responsabilities") == null ? "" : resultSet.getString("responsabilities");
                    String status = resultSet.getString("status") == null ? "" : resultSet.getString("status");
                    String component = resultSet.getString("component") == null ? "" : resultSet.getString("component");
                    String severity = resultSet.getString("severity") == null ? "" : resultSet.getString("severity");
                    String startDate = resultSet.getString("startDate") == null ? "" : resultSet.getString("startDate");
                    String startTime = resultSet.getString("startTime") == null ? "" : resultSet.getString("startTime");
                    String endDate = resultSet.getString("endDate") == null ? "" : resultSet.getString("endDate");
                    String endTime = resultSet.getString("endTime") == null ? "" : resultSet.getString("endTime");

                    nonconformities.add(factory.create(idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription, responsabilities, status, component, severity, startDate, startTime, endDate, endTime));
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
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findAllNonconformitiesRootCause");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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

        Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findAllNonconformitiesRootCause");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {

            while (resultSet.next()) {
                int idqualitynonconformitiesrootcause = resultSet.getInt("idqualitynonconformitiesrootcause");
                String rootCauseCategory = resultSet.getString("rootCauseCategory") == null ? "" : resultSet.getString("rootCauseCategory");
                String rootCauseDescription = resultSet.getString("rootCauseDescription") == null ? "" : resultSet.getString("rootCauseDescription");
                String responsabilities = resultSet.getString("responsabilities") == null ? "" : resultSet.getString("responsabilities");
                String status = resultSet.getString("status") == null ? "" : resultSet.getString("status");
                String component = resultSet.getString("component") == null ? "" : resultSet.getString("component");
                String severity = resultSet.getString("severity") == null ? "" : resultSet.getString("severity");
                String startDate = resultSet.getString("startDate") == null ? "" : resultSet.getString("startDate");
                String startTime = resultSet.getString("startTime") == null ? "" : resultSet.getString("startTime");
                String endDate = resultSet.getString("endDate") == null ? "" : resultSet.getString("endDate");
                String endTime = resultSet.getString("endTime") == null ? "" : resultSet.getString("endTime");
                
                nonconformities.add(factory.create(idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription, responsabilities, status, component, severity, startDate, startTime, endDate, endTime));
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
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findAllNonconformitiesRootCause");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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
        
        Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findOneNonconformitiesRootCause");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {

                    if (resultSet.next()) {
                        int idqualitynonconformitiesrootcause = resultSet.getInt("idqualitynonconformitiesrootcause");
                        String rootCauseCategory = resultSet.getString("rootCauseCategory") == null ? "" : resultSet.getString("rootCauseCategory");
                        String rootCauseDescription = resultSet.getString("rootCauseDescription") == null ? "" : resultSet.getString("rootCauseDescription");
                        String responsabilities = resultSet.getString("responsabilities") == null ? "" : resultSet.getString("responsabilities");
                        String status = resultSet.getString("status") == null ? "" : resultSet.getString("status");
                        String component = resultSet.getString("component") == null ? "" : resultSet.getString("component");
                        String severity = resultSet.getString("severity") == null ? "" : resultSet.getString("severity");
                        String startDate = resultSet.getString("startDate") == null ? "" : resultSet.getString("startDate");
                        String startTime = resultSet.getString("startTime") == null ? "" : resultSet.getString("startTime");
                        String endDate = resultSet.getString("endDate") == null ? "" : resultSet.getString("endDate");
                        String endTime = resultSet.getString("endTime") == null ? "" : resultSet.getString("endTime");

                        result = factory.create(idqualitynonconformitiesrootcause, rootCauseCategory, rootCauseDescription, responsabilities, status, component, severity, startDate, startTime, endDate, endTime);
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
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findOneNonconformitiesRootCause");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }


        return result;
    }

    @Override
    public String addNonconformityRootCause(QualityNonconformitiesRootCause ncrc) {
        String statusmessage = "";
        final String sql = "INSERT INTO qualitynonconformitiesrootcause (rootCauseCategory, "
                + "rootCauseDescription,responsabilities,status,component,severity"
                + ") values (?,?,?,?,?,?)";
       
        Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityRootCause");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            try {
                preStat.setString(1, ncrc.getRootCauseCategory() == null ? "" : ncrc.getRootCauseCategory());
                preStat.setString(2, ncrc.getRootCauseDescription() == null ? "" : ncrc.getRootCauseDescription());
                preStat.setString(3, ncrc.getResponsabilities() == null ? "" : ncrc.getResponsabilities());
                preStat.setString(4, ncrc.getStatus() == null ? "" : ncrc.getStatus());
                preStat.setString(5, ncrc.getComponent() == null ? "" : ncrc.getComponent());
                preStat.setString(6, ncrc.getSeverity()== null ? "" : ncrc.getSeverity());
//                preStat.setString(7, ncrc.getStartDate() == null ? "" : ncrc.getStartDate());
//                preStat.setString(8, ncrc.getStartTime() == null ? "" : ncrc.getStartTime());
//                preStat.setString(9, ncrc.getEndDate() == null ? "" : ncrc.getEndDate());
//                preStat.setString(10, ncrc.getEndTime()== null ? "" : ncrc.getEndTime());
                
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0) {
                        statusmessage = StatusMessage.SUCCESS_NONCONFORMITYCREATED;
                    } else {
                        statusmessage = StatusMessage.ERROR_NONCONFORMITYCREATEDCREATED;
                    }
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, ex.toString());
                } 
         
            } catch (SQLException ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, ex.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, ex.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityRootCause");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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
        
        Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateQualityNonConformitiesRootCause");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql);
            try {
                preStat.setString(1, content.replace("\"","&#34"));
                preStat.setString(2, String.valueOf(id));
                int resultSet = preStat.executeUpdate();
                try {
            
                    if (resultSet > 0) {
                        statusmessage = "[Success] IDNCRC:"+id+" -- Field "+field+" has successfully been updated with value "+content;
                    } else {
                        statusmessage = "[Error] IDNCRC:"+id+" -- Field "+field+" has not been updated with value "+content;
                    }
                
                } catch (Exception ex) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, ex.toString());
                } 
         
            } catch (SQLException ex) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, ex.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException ex) {
            Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, ex.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateQualityNonConformitiesRootCause");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }


        return statusmessage;
    }

    @Override
    public List<String> findDistinctValuesfromParameter(String parameter) {
        List<String> result = new ArrayList<String>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct ");
        query.append(parameter);
        query.append(" FROM qualitynonconformitiesrootcause");
        
        Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findDistinctValuesfromParameter");
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
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findDistinctValuesfromParameter");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesRootCauseDAO.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;  }
    
}
