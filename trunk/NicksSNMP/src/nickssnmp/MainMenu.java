/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

import java.awt.Container;
import java.awt.Cursor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author Nikolaos
 */
public class MainMenu extends javax.swing.JFrame {

    Snmp snmp4j;
    Address address;
    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGVersion = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtIP1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnRpt_1_1 = new javax.swing.JButton();
        btnRpt2 = new javax.swing.JButton();
        btnRpt3 = new javax.swing.JButton();
        txtIP2 = new javax.swing.JTextField();
        txtIP3 = new javax.swing.JTextField();
        txtIP4 = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        btnRpt_1_2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP");

        txtIP1.setText("83");
        txtIP1.setMinimumSize(new java.awt.Dimension(24, 20));

        jLabel2.setText("Port");

        btnRpt_1_1.setText("Report 1.1");
        btnRpt_1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRpt_1_1ActionPerformed(evt);
            }
        });

        btnRpt2.setText("Report 2");

        btnRpt3.setText("Report 3");

        txtIP2.setText("212");
        txtIP2.setMinimumSize(new java.awt.Dimension(24, 20));

        txtIP3.setText("238");
        txtIP3.setMinimumSize(new java.awt.Dimension(24, 20));

        txtIP4.setText("193");
        txtIP4.setMinimumSize(new java.awt.Dimension(24, 20));

        txtPort.setText("161");
        txtPort.setMinimumSize(new java.awt.Dimension(24, 20));

        btnRpt_1_2.setText("Report 1.2");
        btnRpt_1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRpt_1_2ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIP1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIP3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIP4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnRpt_1_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRpt_1_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRpt2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRpt3)))
                .addContainerGap(488, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtIP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRpt_1_1)
                    .addComponent(btnRpt2)
                    .addComponent(btnRpt3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRpt_1_2)
                .addContainerGap(378, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRpt_1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRpt_1_1ActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        RptContainer frame = new RptContainer("Report 1.1","Report1");
        Report1_1 rpt1  =  new Report1_1(frame,new SnmpUtil(txtIP1.getText()+"."+txtIP2.getText()+"."+txtIP3.getText()+"."+txtIP4.getText(),"public",1,1000));
        frame.add(rpt1);
        frame.setVisible(true); 
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));                         
    }//GEN-LAST:event_btnRpt_1_1ActionPerformed

    private void btnRpt_1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRpt_1_2ActionPerformed
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        RptContainer frame = new RptContainer("Report 1.2","Report1");
        Report1_2 rpt1  =  new Report1_2(frame,new SnmpUtil(txtIP1.getText()+"."+txtIP2.getText()+"."+txtIP3.getText()+"."+txtIP4.getText(),"public",1,5000));
        frame.add(rpt1);
        frame.setVisible(true); 
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
    }//GEN-LAST:event_btnRpt_1_2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGVersion;
    private javax.swing.JButton btnRpt2;
    private javax.swing.JButton btnRpt3;
    private javax.swing.JButton btnRpt_1_1;
    private javax.swing.JButton btnRpt_1_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField txtIP1;
    private javax.swing.JTextField txtIP2;
    private javax.swing.JTextField txtIP3;
    private javax.swing.JTextField txtIP4;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
