package mainpkg;

import java.util.List;
import objects.database;
import objects.host;
import objects.table;
import objects.view;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class queriesTest {
    
    /**
     * Test of getDBs method, of class queries.
     */
    @Test
    public void testGetDBs() {
        System.out.println("\nTest---getDBs");
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        queries instance = new queries();
        List<database> result = instance.getDBs(h);
        boolean b = result.size() > 0;
        System.out.println(b);
        
        assertEquals(true, b);
    }

    /**
     * Test of getTables method, of class queries.
     */
    @Test
    public void testGetTables() {
        System.out.println("\nTest---getTables");
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        database db = new database("musica");
        queries instance = new queries();
        List<table> result = instance.getTables(h, db);
        boolean b = result.size() > 0;
        System.out.println(b);
        
        assertEquals(true, b);
    }

    /**
     * Test of getViews method, of class queries.
     */
    @Test
    public void testGetViews() {
        System.out.println("\nTest---getViews");
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        database db = new database("musica");
        queries instance = new queries();
        List<view> result = instance.getViews(h, db);
        boolean b = result.size() > 0;
        System.out.println(b);
        
        assertEquals(true, b);
    }
    
}
