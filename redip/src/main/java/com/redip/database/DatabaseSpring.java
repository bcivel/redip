package com.redip.database;

import com.redip.log.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseSpring implements Database {

    @Autowired
    private DataSource dataSource;
    
    public Connection connect() {
        try {
            return this.dataSource.getConnection();
            } catch (SQLException exception) {
            Logger.log(DatabaseSpring.class.getName(), Level.ERROR, "Cannot connect to datasource jdbc/qualityfollowup : " + exception.toString());
        }

        return null;
    }
    
    public Connection connect(final String connection) {
        try {
            InitialContext ic = new InitialContext();
            Logger.log(DatabaseSpring.class.getName(), Level.INFO, "connecting to jdbc/" + connection);
            return ((DataSource) ic.lookup("jdbc/" + connection)).getConnection();
        } catch (SQLException ex) {
            Logger.log(DatabaseSpring.class.getName(), Level.ERROR, ex.toString());
        } catch (NamingException ex) {
            Logger.log(DatabaseSpring.class.getName(), Level.FATAL, ex.toString());
        }
        return null;
    }

    /*     >>>>  PUT THE OVERIDE BELOW   <<<<         */
    @Override
    public List<List<String>> getDataForGraph(String script) {
        List<List<String>> result = new ArrayList();

        Logger.log(DatabaseSpring.class.getName(), Level.INFO, "connecting to jdbc/qualityfollowup from databaseSpring");
        Connection connection = this.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(script);
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
            ResultSetMetaData md = resultSet.getMetaData();
            int col = md.getColumnCount();

            while (resultSet.next()) {
                List<String> row = new ArrayList();
                for (int i = 1; i <= col; i++) {
                    String value = resultSet.getString(i);
                    row.add(value);
                }
                result.add(row);
            }

        } catch (SQLException exception) {
                    Logger.log(DatabaseSpring.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
            } catch (SQLException exception) {
                Logger.log(DatabaseSpring.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        } catch (SQLException exception) {
            Logger.log(DatabaseSpring.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(DatabaseSpring.class.getName(), Level.WARN, e.toString());
            }
        }
        return result;
    }
}
