package mainpkg;

import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class main {

    static String hostsFolder = "hosts";
    
    public static void main(String[] args){
        gui g = new gui();
        g.setLocationRelativeTo(null);
        g.setVisible(true);
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
