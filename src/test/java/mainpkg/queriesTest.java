/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.util.List;
import objects.database;
import objects.host;
import objects.table;
import objects.view;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

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
        System.out.println("getDBs");
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        queries instance = new queries();
        int expResult = 6;
        List<database> result = instance.getDBs(h);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getTables method, of class queries.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        database db = new database("musica");
        queries instance = new queries();
        int expResult = 8;
        List<table> result = instance.getTables(h, db);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getViews method, of class queries.
     */
    @Test
    public void testGetViews() {
        System.out.println("getViews");
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        database db = new database("musica");
        queries instance = new queries();
        int expResult = 8;
        List<view> result = instance.getViews(h, db);
        assertEquals(expResult, result.size());
    }
    
}
