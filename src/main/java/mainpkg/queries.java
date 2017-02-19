package mainpkg;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.database;
import objects.host;
import objects.table;
import objects.view;

/**
 *
 * @author Fernando
 */
public class queries {
    
    private Connection conn;
    private Statement stat;
    private ResultSet rs;
    
    public queries() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns the databases of a given host
     * @param h Host object
     * @return The databases from the host in a list
     */
    public List<database> getDBs(host h){
        List<database> dbs = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + h.getIp() + ":" + h.getPort() + "/", h.getUser(), h.getPass());

            DatabaseMetaData meta = conn.getMetaData();
            rs = meta.getCatalogs();
            
            while(rs.next())
            {
                dbs.add(new database(rs.getString("TABLE_CAT")));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            main.infoBox("No connection to MySQL server.\nCheck if it's online.", "INFO");
        }
        return(dbs);
    }
    
    
    /**
     * Returns the tables of a given host and database 
     * @param h Host object
     * @param db Database object
     * @return The tables from the database in a list
     */
    public List<table> getTables(host h, database db){
        List<table> tables = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + h.getIp() + ":" + h.getPort() + "/", h.getUser(), h.getPass());

            stat = conn.createStatement();
            rs = stat.executeQuery("show full tables from " + db.getName() + " where TABLE_TYPE like \'BASE_TABLE\' or TABLE_TYPE like \'SYSTEM_VIEW\'");
            
            while(rs.next())
            {
                tables.add(new table(rs.getObject(1).toString()));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            main.infoBox("No connection to MySQL server or incorrect table.", "INFO");
        }
        return(tables);
    }
    
    /**
     * Returns the views of a given host and database 
     * @param h Host object
     * @param db Database object
     * @return The views from the database in a list
     */
    public List<view> getViews(host h, database db){
        List<view> views = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + h.getIp() + ":" + h.getPort() + "/", h.getUser(), h.getPass());

            stat = conn.createStatement();
            rs = stat.executeQuery("show full tables from " + db.getName() + " where TABLE_TYPE like \'VIEW\'");
            
            while(rs.next())
            {
                views.add(new view(rs.getObject(1).toString()));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            main.infoBox("No connection to MySQL server or incorrect view.", "INFO");
        }
        return(views);
    }
    
    /**
     * Returns a ResultSet to build the table
     * @param h Host object
     * @param db Database object
     * @param t Table object
     * @return The ResultSet with the table data
     */
    public ResultSet getTable(host h, database db, table t){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + h.getIp() + ":" + h.getPort() + "/", h.getUser(), h.getPass());

            stat = conn.createStatement();
            System.out.println("select * from " + db.getName() + "." + t.getName());
            rs = stat.executeQuery("select * from " + db.getName() + "." + t.getName());
        } catch (SQLException e) {
            main.infoBox("No connection to MySQL server or incorrect table.", "INFO");
        }
        return(rs);
    }
    
    
}
