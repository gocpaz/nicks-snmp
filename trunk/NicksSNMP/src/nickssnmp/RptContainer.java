/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

import javax.swing.JFrame;

/**
 *
 * @author Nikolaos
 */
public class RptContainer extends JFrame{
    
    public RptContainer(String title , String setup)
    {
        this.setTitle(title);
        this.setupWindow(setup);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
    
    private void setupWindow(String setup)
    {
        if(setup.equalsIgnoreCase("Report1"))
        {
           this.setSize(500,320);
        }
        else if(setup.equalsIgnoreCase("Report2"))
        {
            this.setSize(500,320);
        }
        else if(setup.equalsIgnoreCase("Report3"))
        {
            this.setSize(760,500);
        }
    }
}
