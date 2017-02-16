package mainpkg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.host;

/**
 *
 * @author Fernando
 */
public class hostMgr {

    public String hostsFolder;
    
    public hostMgr() {
        
    }
    
    /**
     * Creates the hosts folder
     * @return True if created else false
     */
    public boolean createDir(){
        File dir = new File(hostsFolder);

        boolean dirCreated = false;
        if(!dir.exists()){
            dirCreated = dir.mkdir();
        }
        
        return dirCreated;
    }
    
    /**
     * Removes the hosts folder if exists
     * @return True if removed else false
     */
    public boolean removeDir(){
        File dir = new File(hostsFolder);

        boolean dirRemoved = false;
        if(dir.exists()){
            dirRemoved = dir.delete();
        }
        
        return dirRemoved;
    }
    
    /**
     * Check if hosts folder exists
     * @return True if exists else false
     */
    public boolean dirExists(){
        File dir = new File(hostsFolder);
        
        return dir.exists();
    }
    
    /**
     * Saves a new host
     * @param h Host object to save
     * @return True if saved
     */
    public boolean newHost(host h){
        boolean saved = false;
        
        if(dirExists()){
            try{
                File f = new File(hostsFolder + "\\" + h.getId()+"");
                saved = f.createNewFile();
                
                Properties prop = new Properties();
                prop.setProperty("name", h.getName());
                prop.setProperty("ip", h.getIp());
                prop.setProperty("port", h.getPort());
                prop.setProperty("user", h.getUser());
                prop.setProperty("pass", h.getPass());
                
                BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
                FileOutputStream fileOut = new FileOutputStream(f);

                prop.store(fileOut, "HOST " + h.getId());
                writer.close();
                fileOut.close();
                saved = f.exists();
                
            } catch(IOException ex){
                ex.printStackTrace();
            }
            
        }
        
        return saved;
    }
    
    
    /**
     * Removes a host
     * @param h Host object to remove
     * @return True if removed else false
     */
    public boolean delHost(host h){
        boolean deleted = false;
        
        if(dirExists()){
            try{
                File f = new File(hostsFolder + "\\" + h.getId()+"");
                deleted = f.delete();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return deleted;
    }
    
    
    /**
     * Reads all hosts in hostsFolder and returns a list
     * @return The list of hosts objects
     */
    public List<host> readHosts(){
        List<host> hosts = new ArrayList<>();
        File[] listFilesInFolder = listFilesInFolder();
        for(int i = 0; i < listFilesInFolder.length; i++){
            if(listFilesInFolder[i].isFile()){
                FileInputStream fis = null;
                BufferedReader in = null;
                try {
                    Properties prop = new Properties();
                    fis = new FileInputStream(listFilesInFolder[i]);
                    in = new BufferedReader(new InputStreamReader(fis));
                    prop.load(in);
                    
                    int hostId = Integer.parseInt(listFilesInFolder[i].getName());
                    host h = new host(hostId, prop.getProperty("name"), prop.getProperty("ip"), prop.getProperty("port"), prop.getProperty("user"), prop.getProperty("pass"));
                    hosts.add(h);
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(hostMgr.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(hostMgr.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fis.close();
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(hostMgr.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return hosts;
    }
    
    
    /**
     * Reads all files in hostsFolder and returns an array
     * @return The array of files
     */
    public File[] listFilesInFolder() {
        File dir = new File(hostsFolder);
        File[] listOfFiles = dir.listFiles();
        return listOfFiles;
    }
    
    
    /**
     * Read all files in hostsFolder and returns the last id
     * @return The last host id
     */
    public int nextIdHost(){
        int id = 0;
        List<host> readHosts = readHosts();
        if(readHosts.size() != 0){
            id = readHosts.get(readHosts.size()-1).getId();
        }
        return id+1;
    }
}
