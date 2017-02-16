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
    
    static gui g;
    
    public static void main(String[] args){
        hMgr.hostsFolder = "hosts";
        g = new gui();
        g.setVisible(true);
        
        ini();
    }
    
    public static void ini(){
        if(hMgr.dirExists()){
            hosts = hMgr.readHosts();
        } else{
            hMgr.createDir();
        }
        
        treeFill();
    }
    
    /**
     * Fills the tree with the hosts
     * @param g The objective gui with the tree
     */
    public static void treeFill(){
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
