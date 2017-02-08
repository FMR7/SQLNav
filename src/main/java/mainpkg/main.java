package mainpkg;

import objects.database;
import objects.host;

/**
 *
 * @author Fernando
 */
public class main {

    public static void main(String[] args){
        //gui g = new gui();
        //g.setVisible(true);
        
        host testHost = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        new queries().getDBs(testHost);
        
        database db = new database("musica");
        new queries().getTables(testHost, db);
        
        
    }
}
