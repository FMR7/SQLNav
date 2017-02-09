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
     * 
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
            e.printStackTrace();
        }
        return(dbs);
    }
    
    
    /**
     * 
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
            rs = stat.executeQuery("show tables from " + db.getName());
            
            while(rs.next())
            {
                tables.add(new table(rs.getObject(1).toString()));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(tables);
    }
    
}
