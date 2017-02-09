package mainpkg;

import java.util.List;
import objects.database;
import objects.host;
import objects.table;

/**
 *
 * @author Fernando
 */
public class main {

    public static void main(String[] args){
        //gui g = new gui();
        //g.setVisible(true);
        
        host testHost = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        List<database> dBs = new queries().getDBs(testHost);
        
        System.out.println("\nDATABASES");
        for(int i = 0; i < dBs.size(); i++){
            System.out.println(dBs.get(i).getName());
        }
        
        
        database db = new database("mail");
        List<table> tables = new queries().getTables(testHost, db);
        
        System.out.println("\nTABLES");
        for(int i = 0; i < tables.size(); i++){
            System.out.println(tables.get(i).getName());
        }
    }
}
