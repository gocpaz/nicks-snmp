/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

import Model.IfTableRfc1213;
import Model.TcpConnTableRfc1213;
import javax.persistence.Query;
import javax.swing.JFrame;

/**
 *
 * @author Nikolaos
 */
public class Report2_2 extends javax.swing.JPanel {

    SnmpUtil snmpUtil = null;    
    private JFrame parentFrame;
    private boolean accessDB = false;    
    /**
     * Creates new form Report1
     */
    public Report2_2() {
        initComponents();        
    }

    public Report2_2(JFrame parentFrame, SnmpUtil snmpUtil, boolean accessDB) {
        this.parentFrame = parentFrame;
        this.snmpUtil = snmpUtil;        
        this.accessDB = accessDB;
        initComponents();          
        
        report();        
        
        //tblIfTable.setModel(getTableModel());
    }
    private void report(){

        snmpUtil.setTargetVersion(2);
        
        String result = null;        
        String[] arr = new String[22];
        int int1 = 0,int2 = 0,int3 = 0;
                
        for(int i = 1; i<23 ; i++)
        {
            arr[i-1] = "1.3.6.1.2.1.2.2.1."+i;
        }                                   
        if(accessDB)
        {
            snmpUtil.insertTcpConnTableRFC1213IntoDB(em,snmpUtil.snmpGetTable(arr));
        }                
        
        TcpConnTableRfc1213 conn1;        
                                
        Query query = em.createNamedQuery("Oid.findByOidName");
        // 1.
        query.setParameter("oidName","tcpInSegs");                              
        int1 = Integer.parseInt(snmpUtil.snmpGet(((Model.Oid) query.getResultList().get(0) ).getOidIndex()+ ".0"));                               
        txtTotalTCPSegments.setText(int1+"");        
        // 2.
        query.setParameter("oidName","tcpInErrs");                              
        int2 = Integer.parseInt(snmpUtil.snmpGet(((Model.Oid) query.getResultList().get(0) ).getOidIndex()+ ".0"));
        txtErrorPercentage.setText( (int2/(int1))*100 +"%");
        
        // 3.
        query.setParameter("oidName","tcpRetransSegs");                              
        int2 = Integer.parseInt(snmpUtil.snmpGet(((Model.Oid) query.getResultList().get(0) ).getOidIndex()+ ".0"));
        txtRetransPercentage.setText( (int2/(int1))*100 +"%");
        
        // 4.
        query.setParameter("oidName","tcpRtoAlgorithm");                              
        result = RFCUtils.getTcpRtoAlgorithm(Integer.parseInt(snmpUtil.snmpGet(((Model.Oid) query.getResultList().get(0) ).getOidIndex()+ ".0"))) +"";
        txtAlgorithm.setText(result);
        
        // 5.
        query.setParameter("oidName","tcpRtoMin");                              
        result = Integer.parseInt(snmpUtil.snmpGet(((Model.Oid) query.getResultList().get(0) ).getOidIndex()+ ".0"))/1000 +"";
        txtMinTimeout.setText(result);
        
        query.setParameter("oidName","tcpRtoMax");                              
        result = Integer.parseInt(snmpUtil.snmpGet(((Model.Oid) query.getResultList().get(0) ).getOidIndex()+ ".0"))/1000 +"";
        txtMaxTimeout.setText(result);
        // 6.
        query = em.createNamedQuery("TcpConnTableRfc1213.findEstablished");        
        result = query.getResultList().get(0).toString();
        txtEstablished.setText(result);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        em = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("NicksSNMPPU").createEntityManager();
        jLabel1 = new javax.swing.JLabel();
        txtTotalTCPSegments = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtErrorPercentage = new javax.swing.JTextField();
        txtRetransPercentage = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAlgorithm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMinTimeout = new javax.swing.JTextField();
        txtMaxTimeout = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEstablished = new javax.swing.JTextField();

        jLabel1.setText("1. TCP IN segments :");

        txtTotalTCPSegments.setText("jTextField1");

        jLabel2.setText("2. Error received percentage :");

        jLabel3.setText("3. Retransmitted percentage :");

        txtErrorPercentage.setText("jTextField1");

        txtRetransPercentage.setText("jTextField2");

        jLabel4.setText("4. Algorithm for timeout value :");

        txtAlgorithm.setText("jTextField1");

        jLabel5.setText("5. Min timeout :");

        jLabel6.setText("Max timeout : ");

        txtMinTimeout.setText("jTextField1");

        txtMaxTimeout.setText("jTextField2");

        jLabel7.setText("6. Established Connections :");

        txtEstablished.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtErrorPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(txtEstablished)
                    .addComponent(txtAlgorithm)
                    .addComponent(txtRetransPercentage)
                    .addComponent(txtMinTimeout)
                    .addComponent(txtMaxTimeout)
                    .addComponent(txtTotalTCPSegments))
                .addContainerGap(417, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTotalTCPSegments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtErrorPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRetransPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMinTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaxTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEstablished, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(337, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager em;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtAlgorithm;
    private javax.swing.JTextField txtErrorPercentage;
    private javax.swing.JTextField txtEstablished;
    private javax.swing.JTextField txtMaxTimeout;
    private javax.swing.JTextField txtMinTimeout;
    private javax.swing.JTextField txtRetransPercentage;
    private javax.swing.JTextField txtTotalTCPSegments;
    // End of variables declaration//GEN-END:variables
}
