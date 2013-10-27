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

        Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findQualityNonconformitiesActionByID");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                preStat.setString(1, String.valueOf(ncid));
                ResultSet resultSet = preStat.executeQuery();
                try {
                

            while (resultSet.next()) {
                Integer idqualitynonconformitiesaction = resultSet.getString("idqualitynonconformitiesaction") == null ? 0 : resultSet.getInt("idqualitynonconformitiesaction");
                String action = resultSet.getString("action") == null ? "" : resultSet.getString("action");
                String deadline = resultSet.getString("deadline") == null ? "" : resultSet.getString("deadline");
                String follower = resultSet.getString("follower") == null ? "" : resultSet.getString("follower");
                String status = resultSet.getString("status") == null ? "" : resultSet.getString("status");
                String date = resultSet.getString("date") == null ? "" : resultSet.getString("date");
                String percentage = resultSet.getString("percentage") == null ? "" : resultSet.getString("percentage");
                String priority = resultSet.getString("priority") == null ? "" : resultSet.getString("priority");
                result.add(factoryNonconformitiesAction.create(idqualitynonconformitiesaction, ncid, action, deadline, follower, status, date, percentage, priority));
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findQualityNonconformitiesActionByID");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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
        
        Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from addNonconformityAction");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                preStat.setString(1, qnc.getIdQualityNonconformities() == null ? "" : String.valueOf(qnc.getIdQualityNonconformities()));
                preStat.setString(2, qnc.getAction() == null ? "" : qnc.getAction());
                preStat.setString(3, qnc.getDeadline() == null ? "" : qnc.getDeadline());
                preStat.setString(4, qnc.getFollower() == null ? "" : qnc.getFollower());
                preStat.setString(5, qnc.getStatus() == null ? "" : qnc.getStatus());
                preStat.setString(6, qnc.getDate() == null ? "" : qnc.getDate());
                preStat.setString(7, qnc.getPercentage() == null ? "" : qnc.getPercentage());
                preStat.setString(8, qnc.getPriority() == null ? "" : qnc.getPriority());
                int resultSet = preStat.executeUpdate();
                try {
                    if (resultSet > 0) {
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from addNonconformityAction");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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
        
        Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from updateQualityNonConformitiesAction");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(sql.toString());
            try {
                preStat.setString(1, value);
                preStat.setInt(2, idnca);
                int resultSet = preStat.executeUpdate();
                try{
                    if (resultSet > 0) {
                        statusmessage = "[Success] IDNC:"+idnca+" -- Field "+column+" has successfully been updated with value "+value;
                    } else {
                        statusmessage = "[Error] IDNC:"+idnca+" -- Field "+column+" has not been updated with value "+value;
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from updateQualityNonConformitiesAction");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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

        Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findQualityNonconformitiesActionByCriteria");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                preStat.setString(1, "");
                preStat.setString(2, "100");
                preStat.setString(3, "CLOSED");
                ResultSet resultSet = preStat.executeQuery();
                try {
                    
                    while (resultSet.next()) {
                    Integer idqualitynonconformitiesaction = resultSet.getString("idqualitynonconformitiesaction") == null ? 0 : resultSet.getInt("idqualitynonconformitiesaction");
                    Integer idqualitynonconformities = resultSet.getString("idqualitynonconformities") == null ? 0 : resultSet.getInt("idqualitynonconformities");
                    String action = resultSet.getString("a.action") == null ? "" : resultSet.getString("a.action");
                    String deadline = resultSet.getString("a.deadline") == null ? "" : resultSet.getString("a.deadline");
                    String follower = resultSet.getString("a.follower") == null ? "" : resultSet.getString("a.follower");
                    String status = resultSet.getString("a.status") == null ? "" : resultSet.getString("a.status");
                    String date = resultSet.getString("a.date") == null ? "" : resultSet.getString("a.date");
                    String percentage = resultSet.getString("a.percentage") == null ? "" : resultSet.getString("a.percentage");
                    String priority = resultSet.getString("a.priority") == null ? "" : resultSet.getString("a.priority");
                    String problemTitle = resultSet.getString("b.problemTitle") == null ? "" : resultSet.getString("b.problemTitle");
                    String problemDescription = resultSet.getString("b.problemDescription") == null ? "" : resultSet.getString("b.problemDescription");
                    QualityNonconformities qnc = factoryNonconformities.create(idqualitynonconformities, problemTitle, problemDescription);
                    result.add(factoryNonconformitiesAction.create(idqualitynonconformitiesaction, idqualitynonconformities, action, deadline, follower, status, date, percentage, priority, qnc));
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findQualityNonconformitiesActionByCriteria");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
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

        
        Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findQualityNonconformitiesActionUsingLimit");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                preStat.setString(1, "");
                preStat.setString(2, "100");
                preStat.setString(3, "CLOSED");
                ResultSet resultSet = preStat.executeQuery();
                try {
                    while (resultSet.next()) {
                    Integer idqualitynonconformitiesaction = resultSet.getString("idqualitynonconformitiesaction") == null ? 0 : resultSet.getInt("idqualitynonconformitiesaction");
                    Integer idqualitynonconformities = resultSet.getString("idqualitynonconformities") == null ? 0 : resultSet.getInt("idqualitynonconformities");
                    String action = resultSet.getString("a.action") == null ? "" : resultSet.getString("a.action");
                    String deadline = resultSet.getString("a.deadline") == null ? "" : resultSet.getString("a.deadline");
                    String follower = resultSet.getString("a.follower") == null ? "" : resultSet.getString("a.follower");
                    String status = resultSet.getString("a.status") == null ? "" : resultSet.getString("a.status");
                    String date = resultSet.getString("a.date") == null ? "" : resultSet.getString("a.date");
                    String percentage = resultSet.getString("a.percentage") == null ? "" : resultSet.getString("a.percentage");
                    String priority = resultSet.getString("a.priority") == null ? "" : resultSet.getString("a.priority");
                    String problemTitle = resultSet.getString("b.problemTitle") == null ? "" : resultSet.getString("b.problemTitle");
                    String problemDescription = resultSet.getString("b.problemDescription") == null ? "" : resultSet.getString("b.problemDescription");
                    QualityNonconformities qnc = factoryNonconformities.create(idqualitynonconformities, problemTitle, problemDescription);
                    result.add(factoryNonconformitiesAction.create(idqualitynonconformitiesaction, idqualitynonconformities, action, deadline, follower, status, date, percentage, priority, qnc));
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findQualityNonconformitiesActionUsingLimit");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }


        return result;
    }

    @Override
    public Integer getMaxPriorityNumber() {
        Integer result = 99;
        StringBuilder query = new StringBuilder();
        query.append("SELECT max(priority) as response FROM qualitynonconformitiesaction");

       Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getMaxPriorityNumber");
       Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try{
            if (resultSet.first()) {
                result = (resultSet.getInt("response"));
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getMaxPriorityNumber");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }


        return result;

    }
    
    @Override
    public List<QualityNonconformitiesAction> getAllNonconformitiesAction(int start, int amount, String column, String dir, String searchTerm, String individualSearch) {
        List<QualityNonconformitiesAction> nonconformitiesaction = new ArrayList<QualityNonconformitiesAction>();
        StringBuilder gSearch = new StringBuilder();
        String searchSQL = "";
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idqualitynonconformitiesaction, ");
        query.append(" action,  ");
        query.append(" deadline, follower, status, ");
        query.append(" `date`, percentage, priority,  idqualitynonconformities FROM qualitynonconformitiesaction ");
        
        gSearch.append(" where (idqualitynonconformitiesaction like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or idqualitynonconformities like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or action like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or deadline like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or follower like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or status like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or `date` like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or percentage like '%");
        gSearch.append(searchTerm);
        gSearch.append("%'");
        gSearch.append(" or priority like '%");
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

        QualityNonconformitiesAction nonconformitiesactiontoadd;

        Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from getAllNonconformitiesAction");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                
                while (resultSet.next()) {
                nonconformitiesactiontoadd = new QualityNonconformitiesAction();

                nonconformitiesactiontoadd.setIdQualityNonconformitiesActions(resultSet.getInt(1));
                nonconformitiesactiontoadd.setAction(resultSet.getString(2) == null ? "" : resultSet.getString(2));
                nonconformitiesactiontoadd.setDeadline(resultSet.getString(3) == null ? "" : resultSet.getString(3));
                nonconformitiesactiontoadd.setFollower(resultSet.getString(4) == null ? "" : resultSet.getString(4));
                nonconformitiesactiontoadd.setStatus(resultSet.getString(5) == null ? "" : resultSet.getString(5));
                nonconformitiesactiontoadd.setDate(resultSet.getString(6) == null ? "" : resultSet.getString(6));
                nonconformitiesactiontoadd.setPercentage(resultSet.getString(7) == null ? "" : resultSet.getString(7));
                nonconformitiesactiontoadd.setPriority(resultSet.getString(8) == null ? "" : resultSet.getString(8));
                nonconformitiesactiontoadd.setIdQualityNonconformities(resultSet.getString(9) == null ? 0 : resultSet.getInt(9));
                
                nonconformitiesaction.add(nonconformitiesactiontoadd);
            }

                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from getAllNonconformitiesAction");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.WARN, e.toString());
            }
        }

        return nonconformitiesaction;
    }
    
    @Override
    public List<String> findDistinctValuesfromParameter(String parameter) {
        List<String> result = new ArrayList<String>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT distinct ");
        query.append(parameter);
        query.append(" FROM qualitynonconformitiesaction order by ");
        query.append(parameter);
        query.append(" asc");
        
        Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findDistinctValuesfromParameter");
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
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                } 
         
            } catch (SQLException exception) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findDistinctValuesfromParameter");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(QualityNonconformitiesActionDAO.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;  }
    
}
