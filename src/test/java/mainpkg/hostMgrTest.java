package mainpkg;

import java.io.File;
import java.util.List;
import objects.host;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernando
 */
public class hostMgrTest {

    @Test
    public void testDir() {
        hostMgr hm = new hostMgr();
        hm.hostsFolder = "hosts";
        
        boolean createDir = hm.createDir();
        assertEquals(true, createDir);
        System.out.println("\nTest---createDir()");
        
        boolean removedDir = hm.removeDir();
        assertEquals(true, removedDir);
        System.out.println("Test---removedDir()");
    }
    
    @Test
    public void testFile() {
        hostMgr hm = new hostMgr();
        hm.hostsFolder = "hosts";
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "testpass");
        
        System.out.println("\nTest---createDir()");
        boolean createDir = hm.createDir();
        assertEquals(true, createDir);
        
        System.out.println("Test---newHost()");
        boolean hostWritten = hm.newHost(h);
        assertEquals(true, hostWritten);
        
        System.out.println("Test---delHost()");
        boolean hostDeleted = hm.delHost(h);
        assertEquals(true, hostDeleted);
        
        System.out.println("Test---removedDir()");
        boolean removedDir = hm.removeDir();
        assertEquals(true, removedDir);
    }

    @Test
    public void testListFilesInFolder() {
        hostMgr hm = new hostMgr();
        hm.hostsFolder = "hosts";
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "testpass");
        
        hm.createDir();
        hm.newHost(h);
        
        File[] listFilesInFolder = hm.listFilesInFolder();
        System.out.println("\nTest---listFilesInFolder()");
        for(File f : listFilesInFolder){
            if(f.isFile()){
                System.out.println(f.getName());
            }
        }
        
        hm.delHost(h);
        hm.removeDir();
        assertEquals(true, listFilesInFolder != null);
    }
    
    @Test
    public void testReadHosts(){
        hostMgr hm = new hostMgr();
        hm.hostsFolder = "hosts";
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "testpass");
        
        hm.createDir();
        hm.newHost(h);
        
        List<host> hosts = hm.readHosts();
        host readHost = null;
        System.out.println("\nTest---readHosts()");
        for(int i = 0; i < hosts.size(); i++){
            readHost = hosts.get(i);
            System.out.println("Host ID: " + readHost.getId());
            System.out.println("Name: " + readHost.getName());
            System.out.println("IP: " + readHost.getIp());
            System.out.println("Port: " + readHost.getPort());
            System.out.println("User: " + readHost.getUser());
            System.out.println("Pass: " + readHost.getPass());
        }
        
        hm.delHost(h);
        hm.removeDir();
        assertEquals("127.0.0.1", readHost.getIp());
    }
    
    
    @Test
    public void testNextId(){
        hostMgr hm = new hostMgr();
        hm.hostsFolder = "hosts";
        
        host h = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "testpass");
        
        hm.createDir();
        hm.newHost(h);
        
        int result = hm.nextIdHost();
        System.out.println("\nTest---nextIdHost()");
        System.out.println(result + "");
        hm.delHost(h);
        hm.removeDir();
        assertEquals(2, result);
    }
}
