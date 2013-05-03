/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicksnmp;

import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import model.Mib;
import model.Oid;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author nikos
 */
public class MainFrame extends javax.swing.JFrame {
    private Snmp snmp;
    private String address;
    private List<Mib> lstMIBs = null;
    private List<model.Oid> lstOIDs = null;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        // Button Groups        
        btnGroupCMD.add(rbtnBulkGet);
        btnGroupCMD.add(rbtnBulkWalk);
        btnGroupCMD.add(rbtnGet);
        btnGroupCMD.add(rbtnGetNext);
        btnGroupCMD.add(rbtnSet);
        btnGroupCMD.add(rbtnTable);
        btnGroupCMD.add(rbtnTranslate);
        btnGroupCMD.add(rbtnTrap);
        btnGroupCMD.add(rbtnWalk);
        
        btnGroupVersion.add(rbtnv1);
        btnGroupVersion.add(rbtnv2);
        btnGroupVersion.add(rbtnv3);
        //-----------------------------------
        
        try{
            start();
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(this,"No Agent was found","No Agent", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
            
        }
        
        cmbMIBs.addItem("Select a MIB");
        Query query = em.createNamedQuery("Mib.findAll");
        lstMIBs = query.getResultList();
        for(Mib mib : lstMIBs)
        {
            cmbMIBs.addItem(mib.getMibName());
        } 
        cmbOIDs.addItem("Select an OID");
        Query query2 = em.createNamedQuery("Oid.findAll");
        lstOIDs = query2.getResultList();
        for(model.Oid oid : lstOIDs)
        {
            cmbOIDs.addItem(oid.getOidName());
        } 
        
    }

    
    private void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);   
        transport.listen();
    }
    public void stop() throws IOException{
        snmp.close();
    }
    
    public String getAsString(OID oid) throws IOException {
        ResponseEvent responseEvent = get(new OID[]{oid});        
        PDU responsePDU=null;                
        String sVar = null;
        if (responseEvent != null)
        {
            responsePDU = responseEvent.getResponse();
            if ( responsePDU != null)
            {
                Vector <VariableBinding> tmpv = (Vector <VariableBinding>) responsePDU.getVariableBindings();
                if(tmpv != null)
                {
                    for(int k=0; k <tmpv.size();k++)
                    {
                        VariableBinding vb = (VariableBinding) tmpv.get(k);
                        String output = null;
                        if ( vb.isException())
                        {

                            String errorstring = vb.getVariable().getSyntaxString();
                            System.out.println("Error:"+errorstring);
                        }
                        else
                        {
                            String sOid = vb.getOid().toString();
                            Variable var = vb.getVariable();
                            OctetString oct = new OctetString((OctetString)var);
                            sVar =  oct.toString();                            
                        }
                    }
                }         
            }
        }
        return sVar;
    }
    
    public void getAsString(OID oids, ResponseListener listener){
        try{
            snmp.send(getPDU(new OID[]{oids}),getTarget(), null,listener);
        }catch(IOException e){
            throw new RuntimeException();
        }
    }
    
    private PDU getPDU(OID oids[]){
        PDU pdu = new PDU();
        for(OID oid : oids){
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        return pdu;
    }
    
    public ResponseEvent get(OID oids[]) throws IOException {               
        ResponseEvent event = snmp.send(getPDU(oids), getTarget(), null);
        if(event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }
    
    private Target getTarget() {
        Address targetAddress = GenericAddress.parse(address);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(2);
        target.setTimeout(1500);
        
        int version = -1;       
        if(rbtnv1.isSelected()){version = SnmpConstants.version1;}
        else if(rbtnv2.isSelected()){version = SnmpConstants.version2c;}
        else if(rbtnv3.isSelected()){version = SnmpConstants.version3;}
        target.setVersion(version);
        
        return target;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupCMD = new javax.swing.ButtonGroup();
        em = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("NickSNMPPU").createEntityManager();
        btnGroupVersion = new javax.swing.ButtonGroup();
        rbtnTranslate = new javax.swing.JRadioButton();
        rbtnGet = new javax.swing.JRadioButton();
        rbtnGetNext = new javax.swing.JRadioButton();
        rbtnWalk = new javax.swing.JRadioButton();
        rbtnTable = new javax.swing.JRadioButton();
        rbtnSet = new javax.swing.JRadioButton();
        rbtnBulkGet = new javax.swing.JRadioButton();
        rbtnBulkWalk = new javax.swing.JRadioButton();
        rbtnTrap = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rbtnv1 = new javax.swing.JRadioButton();
        rbtnv2 = new javax.swing.JRadioButton();
        rbtnv3 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIp1 = new javax.swing.JTextField();
        txtIp2 = new javax.swing.JTextField();
        txtIp3 = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaResponse = new javax.swing.JTextArea();
        lblOID = new javax.swing.JLabel();
        cmbMIBs = new javax.swing.JComboBox();
        jTextField6 = new javax.swing.JTextField();
        cmdGo = new javax.swing.JButton();
        cmbOIDs = new javax.swing.JComboBox();
        lblMIB = new javax.swing.JLabel();
        txtIp4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rbtnTranslate.setText("snmptranslate");

        rbtnGet.setText("snmpget");

        rbtnGetNext.setText("snmpgetnext");

        rbtnWalk.setText("snmpwalk");

        rbtnTable.setText("snmptable");

        rbtnSet.setText("snmpset");

        rbtnBulkGet.setText("snmpbulkget");

        rbtnBulkWalk.setText("snmpbulkwalk");

        rbtnTrap.setText("snmptrap");

        jLabel1.setText("Command");

        jLabel2.setText("Version");
        jLabel2.setToolTipText("");

        rbtnv1.setText("1");

        rbtnv2.setText("2c");

        rbtnv3.setText("3");

        jLabel3.setText("Other options");

        jTextField1.setText("jTextField1");

        jLabel4.setText("IP");

        txtIp1.setText("83");
        txtIp1.setPreferredSize(new java.awt.Dimension(40, 27));

        txtIp2.setText("212");
        txtIp2.setPreferredSize(new java.awt.Dimension(40, 27));

        txtIp3.setText("238");
        txtIp3.setPreferredSize(new java.awt.Dimension(40, 27));

        txtPort.setText("161");

        jLabel5.setText("Port");

        txtAreaResponse.setBackground(new java.awt.Color(1, 1, 1));
        txtAreaResponse.setColumns(20);
        txtAreaResponse.setForeground(new java.awt.Color(16, 255, 0));
        txtAreaResponse.setRows(5);
        txtAreaResponse.setText("Response");
        jScrollPane1.setViewportView(txtAreaResponse);

        lblOID.setText("OID");

        jTextField6.setText("jTextField6");

        cmdGo.setText("GO");
        cmdGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGoActionPerformed(evt);
            }
        });

        lblMIB.setText("MIB");

        txtIp4.setText("193");
        txtIp4.setPreferredSize(new java.awt.Dimension(40, 27));

        jButton1.setText("Report 1");

        jButton2.setText("Report 2");

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnTranslate)
                                    .addComponent(jLabel1)
                                    .addComponent(rbtnGet))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(rbtnv1)
                                    .addComponent(rbtnv2)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbtnGetNext)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnv3))
                            .addComponent(rbtnWalk)
                            .addComponent(rbtnTable)
                            .addComponent(rbtnSet)
                            .addComponent(rbtnBulkGet)
                            .addComponent(rbtnBulkWalk)
                            .addComponent(rbtnTrap))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jTextField6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbMIBs, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMIB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblOID)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cmbOIDs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdGo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton1)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(btnClear))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnTranslate)
                    .addComponent(rbtnv1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnGet)
                    .addComponent(rbtnv2)
                    .addComponent(jLabel4)
                    .addComponent(txtIp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtIp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnGetNext)
                    .addComponent(rbtnv3)
                    .addComponent(lblOID)
                    .addComponent(lblMIB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnWalk)
                    .addComponent(cmbMIBs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbOIDs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnTable)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnSet)
                    .addComponent(cmdGo)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnBulkGet)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnBulkWalk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnTrap)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGoActionPerformed
       address = "udp:"+txtIp1.getText()+"."+txtIp2.getText()+"."+txtIp3.getText()+"."+txtIp4.getText()+"/"+txtPort.getText();                                  
        try {
            txtAreaResponse.append("-----------------------\n");
            if(rbtnGet.isSelected()){
                txtAreaResponse.append(getAsString(new OID("1.3.6.1.2.1.1.4.0")) +"\n");
            }            
            txtAreaResponse.append("-----------------------\n");
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
               
    }//GEN-LAST:event_cmdGoActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtAreaResponse.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.ButtonGroup btnGroupCMD;
    private javax.swing.ButtonGroup btnGroupVersion;
    private javax.swing.JComboBox cmbMIBs;
    private javax.swing.JComboBox cmbOIDs;
    private javax.swing.JButton cmdGo;
    private javax.persistence.EntityManager em;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblMIB;
    private javax.swing.JLabel lblOID;
    private javax.swing.JRadioButton rbtnBulkGet;
    private javax.swing.JRadioButton rbtnBulkWalk;
    private javax.swing.JRadioButton rbtnGet;
    private javax.swing.JRadioButton rbtnGetNext;
    private javax.swing.JRadioButton rbtnSet;
    private javax.swing.JRadioButton rbtnTable;
    private javax.swing.JRadioButton rbtnTranslate;
    private javax.swing.JRadioButton rbtnTrap;
    private javax.swing.JRadioButton rbtnWalk;
    private javax.swing.JRadioButton rbtnv1;
    private javax.swing.JRadioButton rbtnv2;
    private javax.swing.JRadioButton rbtnv3;
    private javax.swing.JTextArea txtAreaResponse;
    private javax.swing.JTextField txtIp1;
    private javax.swing.JTextField txtIp2;
    private javax.swing.JTextField txtIp3;
    private javax.swing.JTextField txtIp4;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
