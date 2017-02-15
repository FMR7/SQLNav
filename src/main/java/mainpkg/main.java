package mainpkg;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import objects.host;

/**
 *
 * @author Fernando
 */
public class main {

    static hostMgr hMgr = new hostMgr();
    static List<host> hosts = new ArrayList<>();
    
    public static void main(String[] args){
        hMgr.hostsFolder = "hosts";
        
        if(hMgr.dirExists()){
            hosts = hMgr.readHosts();
        } else{
            hMgr.createDir();
        }
        
        gui g = new gui(hosts);
        g.setVisible(true);
        
        treeFill(g);
        
        
        /*
        host testHost = new host(1, "TEST_HOST", "127.0.0.1", "3306", "root", "");
        List<database> dBs = new queries().getDBs(testHost);
        
        System.out.println("\nDATABASES");
        for(int i = 0; i < dBs.size(); i++){
            System.out.println(dBs.get(i).getName());
        }
        
        
        database db = new database("musica");
        List<table> tables = new queries().getTables(testHost, db);
        
        System.out.println("\nTABLES");
        for(int i = 0; i < tables.size(); i++){
            System.out.println(tables.get(i).getName());
        }
        
        List<view> views = new queries().getViews(testHost, db);
        
        System.out.println("\nVIEWS");
        for(int i = 0; i < views.size(); i++){
            System.out.println(views.get(i).getName());
        }
        */
    }
    
    public static void treeFill(gui g){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Hosts");
        DefaultTreeModel model = new DefaultTreeModel(top);
        g.jTree1.setModel(model);
        
        for (int i = 0; i < hosts.size(); i++) {
            DefaultMutableTreeNode hst = new DefaultMutableTreeNode();
            hst.setUserObject(hosts.get(i).getName());
            top.add(hst);
        }
    }
}
