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
    private Connection connection;
    private Boolean connected;

    public Connection connect() {
        try {
            if (!this.isConnected()) {
                this.connection = dataSource.getConnection();
                this.connected = true;
                Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup");
            }
        } catch (SQLException e) {
            this.connected = false;
            Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + e);
        }
        return this.connection;
    }
    
    public Connection connect(String fromClass, String fromMethod) {
        try {
            if (!this.isConnected()) {
                this.connection = dataSource.getConnection();
                this.connected = true;
                Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Connecting to jdbc/qualityfollowup fromClass:"+fromClass+ " fromMethod:"+fromMethod);
            }
        } catch (SQLException e) {
            this.connected = false;
            Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "fromClass:"+fromClass+" -- " + e);
        }
        return this.connection;
    }

    public Connection connect(final String connection) {
        try {
            if (!this.isConnected()) {
                InitialContext ic = new InitialContext();
                this.connection = ((DataSource) ic.lookup("jdbc/" + connection)).getConnection();
                connected = true;
            }
        } catch (SQLException ex) {
            connected = false;
            Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
        } catch (NamingException ex) {
            connected = false;
            Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
        }
        return this.connection;
    }

    public boolean isConnected() {
        if (this.connection != null) {
            return this.connected;
        }
        return false;
    }

    public boolean isConnected(final boolean tryConnect) {
        if (!this.isConnected()) {
            if (tryConnect) {
                this.connect();
            }
        }
        return this.isConnected();
    }

    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.connection.close();
                this.connected = false;
                Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Disconnecting");
            } catch (final SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            }
        }
    }
    
    public void disconnect(String fromClass, String fromMethod) {
        if (this.isConnected()) {
            try {
                this.connection.close();
                this.connected = false;
                Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Disconnecting fromClass :"+fromClass+" fromMethod :"+fromMethod);
            } catch (final SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            }
        }
    }

    @SuppressWarnings("all")
    public ResultSet query(final String sql) {
        if (this.isConnected(true)) {
            try {
                Statement stmt = this.connection.createStatement();
                return stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            }
        }
        return null;
    }

    @SuppressWarnings("all")
    public ResultSet query(final String prepareStmt, final ArrayList<String> values) {
        if (this.isConnected(true)) {
            try {
                PreparedStatement stmt = this.connection.prepareStatement(prepareStmt);
                for (int i = 1; i <= values.size(); i++) {
                    stmt.setString(i, values.get(i - 1));
                }
                return stmt.executeQuery();
            } catch (SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            }
        }
        return null;
    }

    public int update(final String prepareStmt, final ArrayList<String> values) {
        if (this.isConnected(true)) {
            PreparedStatement stmt = null;
            try {
                stmt = this.connection.prepareStatement(prepareStmt);
                for (int i = 1; i <= values.size(); i++) {
                    stmt.setString(i, values.get(i - 1));
                }
                return stmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Exception on close Statement in execute" + ex);
                }
            }
        }
        return -1;
    }

    public void update(final String sql) {
        if (this.isConnected(true)) {
            Statement stmt = null;
            try {
                stmt = this.connection.createStatement();
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Exception on close Statement in execute" + ex);
                }
            }
        }
    }

    /*     >>>>  PUT THE OVERIDE BELOW   <<<<         */
    @Override
    public boolean execute(final String sql) {
        if (this.isConnected(true)) {
            Statement stmt = null;
            try {
                stmt = this.connection.createStatement();
                stmt.execute(sql);
            } catch (SQLException ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.log(DatabaseSpring.class.getName(), Level.INFO, "Exception on close Statement in execute" + ex);
                }
            }
        }
        return true;
    }

    @Override
    public List<List<String>> getDataForGraph(String script) {
        List<List<String>> result = new ArrayList();

        try {
            this.connect();
            ResultSet rs = this.query(script);
            ResultSetMetaData md = rs.getMetaData();
            int col = md.getColumnCount();

            while (rs.next()) {
                List<String> row = new ArrayList();
                for (int i = 1; i <= col; i++) {
                    String value = rs.getString(i);
                    row.add(value);
                }
                result.add(row);
            }

        } catch (SQLException ex) {
            Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
        } finally {
            try {
                this.disconnect();
            } catch (Exception ex) {
                Logger.log(DatabaseSpring.class.getName(), Level.FATAL, "" + ex);
            }
        }

        return result;
    }
}
