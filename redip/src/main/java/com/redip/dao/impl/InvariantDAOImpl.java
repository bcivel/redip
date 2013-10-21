package com.redip.dao.impl;

import com.redip.dao.IInvariantDAO;
import com.redip.database.DatabaseSpring;
import com.redip.entity.Invariant;
import com.redip.exception.QualityException;
import com.redip.factory.IFactoryInvariant;
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
 * {Insert class description here}
 *
 * @author bcivel
 */
@Repository
public class InvariantDAOImpl implements IInvariantDAO {

    /**
     * Description of the variable here.
     */
    @Autowired
    private DatabaseSpring databaseSpring;
    @Autowired
    private IFactoryInvariant factoryInvariant;

    /**
     * Short one line description.
     * <p/>
     * Longer description. If there were any, it would be here. <p> And even
     * more explanations to follow in consecutive paragraphs separated by HTML
     * paragraph breaks.
     *
     * @param variable Description text text text.
     * @return Description text text text.
     */
    @Override
    public Invariant findInvariantByIdValue(String idName, String value) throws QualityException{
        boolean throwException = true;
        Invariant result = null;
        final String query = "SELECT * FROM invariant i  WHERE i.idname = ? AND i.value = ?";

        Logger.log(LogDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findInvariantByIdValue");
        Connection connection = databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query);
            preStat.setString(1, idName);
            preStat.setString(2, value);
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                    while (resultSet.next()) {
                        throwException = false;
                        int sort = resultSet.getInt("sort");
                        int id = resultSet.getInt("id");
                        String description = resultSet.getString("Description");
                        String gp1 = resultSet.getString("gp1");
                        String gp2 = resultSet.getString("gp2");
                        String gp3 = resultSet.getString("gp3");
                        result = factoryInvariant.create(idName, value, sort, id, description, gp1, gp2, gp3);
                    }
                } catch (SQLException exception) {
                    Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(LogDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findInvariantByIdValue");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;
    }

    @Override
    public List<Invariant> findListOfInvariantById(String idName) throws QualityException {
        boolean throwException = true;
        List<Invariant> result = null;
        final String query = "SELECT * FROM invariant i  WHERE i.idname = ? ORDER BY sort";

        Logger.log(LogDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findListOfInvariantById");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query);
            
            try {
                preStat.setString(1, idName);
                ResultSet resultSet = preStat.executeQuery();
                result = new ArrayList<Invariant>();
                try {
                    while (resultSet.next()) {
                        throwException = false;
                        int sort = resultSet.getInt("sort");
                        int id = resultSet.getInt("id");
                        String description = resultSet.getString("Description");
                        String gp1 = resultSet.getString("gp1");
                        String gp2 = resultSet.getString("gp2");
                        String gp3 = resultSet.getString("gp3");
                        String value = resultSet.getString("value");
                        result.add(factoryInvariant.create(idName, value, sort, id, description, gp1, gp2, gp3));
                    }
                } catch (SQLException exception) {
                    Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(LogDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findListOfInvariantById");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;
    }
    
    @Override
    public List<Invariant> findListOfInvariant() throws QualityException {
        boolean throwException = true;
        List<Invariant> result = null;
        final String query = "SELECT * FROM invariant i ORDER BY sort";

        Logger.log(LogDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findListOfInvariant");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query);
            try {
                ResultSet resultSet = preStat.executeQuery();
                result = new ArrayList<Invariant>();
                try {
                    while (resultSet.next()) {
                        throwException = false;
                        int sort = resultSet.getInt("sort");
                        int id = resultSet.getInt("id");
                        String description = resultSet.getString("Description");
                        String gp1 = resultSet.getString("gp1");
                        String gp2 = resultSet.getString("gp2");
                        String gp3 = resultSet.getString("gp3");
                        String value = resultSet.getString("value");
                        result.add(factoryInvariant.create(null, value, sort, id, description, gp1, gp2, gp3));
                    }
                } catch (SQLException exception) {
                    Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(LogDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findListOfInvariant");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }
        
        return result;
    }

    @Override
    public List<Invariant> findInvariantByIdGp1(String idName, String gp) throws QualityException {
        boolean throwException = true;
        List<Invariant> result = null;
        final String query = "SELECT * FROM invariant i  WHERE i.idname = ? AND i.gp1 = ? ORDER BY sort";

        Logger.log(LogDAO.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup from findInvariantByIdGp1");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query);
            try {
                preStat.setString(1, idName);
                preStat.setString(2, gp);
                ResultSet resultSet = preStat.executeQuery();
                result = new ArrayList<Invariant>();
                try {
                    while (resultSet.next()) {
                        throwException = false;
                        int sort = resultSet.getInt("sort");
                        int id = resultSet.getInt("id");
                        String description = resultSet.getString("Description");
                        String gp1 = resultSet.getString("gp1");
                        String gp2 = resultSet.getString("gp2");
                        String gp3 = resultSet.getString("gp3");
                        String value = resultSet.getString("value");
                        result.add(factoryInvariant.create(idName, value, sort, id, description, gp1, gp2, gp3));
                    }
                } catch (SQLException exception) {
                    Logger.log(QualityNonconformitiesDAOImpl.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(InvariantDAOImpl.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(LogDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/qualityfollowup from findInvariantByIdGp1");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(InvariantDAOImpl.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }
}
