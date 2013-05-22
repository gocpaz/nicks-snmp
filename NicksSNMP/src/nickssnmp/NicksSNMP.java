/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableListener;
import org.snmp4j.util.TableUtils;

/**
 *
 * @author Nikolaos
 */
public class NicksSNMP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            Snmp snmp4j =  new Snmp(new DefaultUdpTransportMapping());
            snmp4j.listen();
            Address add = new UdpAddress("localhost"+"/"+"161");
            CommunityTarget target = new CommunityTarget();
            target.setAddress(add);
            target.setTimeout(500);            
            target.setRetries(3);
            target.setCommunity(new OctetString("public"));
            target.setVersion(SnmpConstants.version2c);
            
        //-- TABULAR DATA    
        //----------------------------------------
            ScopedPDU pdu = new ScopedPDU();
        
         String []strOIDs = {"1.3.6.1.2.1.3.1"};
         OID oids = new OID("1.3.6.1.2.1.3.1");
         List<OID> list = new ArrayList();
         list.add(oids);
         final OID[] oidArray = new OID[list.size()];
         int i = 0;
         for (OID oid : list) {
            oidArray[i++] = oid;
         }            
                               
        TableUtils tableUtils = new TableUtils(snmp4j, new DefaultPDUFactory(PDU.GETBULK));
        InternalTableListener listener = new InternalTableListener();
        tableUtils.getTable(target, oidArray, listener, null, null, null);             
            
        System.out.println("rows::"+listener.getRows());
         List<TableEvent> tableEvents = tableUtils.getTable(target, 
            parseStrToOID(strOIDs), null, null);
         Iterator<TableEvent> tEvent = tableEvents.iterator();
         while(tEvent.hasNext()) {
            TableEvent event = (TableEvent) tEvent.next();
            System.out.println("Table event:::error::"+ event.getErrorMessage());
            System.out.println("Table event::reportpdu:::"+ event.getReportPDU());
            System.out.println("Table event:::index::"+ event.getIndex());
            VariableBinding[] vb = event.getColumns();
            //System.out.println("vb:::"+vb.getOid());
            for (int count=0; count< vb.length;count++) {
               if (vb[count].getOid() != null)
               System.out.println("Table event::oid:::"+vb[count].getOid());
               System.out.println("Table event::value::"+vb[count].getVariable());               
            
            }
         }
         //-- PRIMITIVE DATA
            //-----------------------------------------------------------------
         PDU request = new PDU();
        request.setType(PDU.GET);
        OID oid= new OID(".1.3.6.1.2.1.1.1.0");
        request.add(new VariableBinding(oid));

        PDU responsePDU=null;
        ResponseEvent responseEvent;
        responseEvent = snmp4j.send(request, target);

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
                            String sVar =  oct.toString();

                            System.out.println("success:"+sVar);
                        }
                }
            }
        }
        }
        } catch (IOException e) {
             
            e.printStackTrace();
        }      
    }
    protected static OID[] parseStrToOID(String[] strOIDs) {
      OID[] oids = new OID[strOIDs.length];
      for (int i = 0; i < strOIDs.length; i++) {
          oids[i] = new OID(strOIDs[i]);
      }
      return oids;
  }
}
class InternalTableListener implements TableListener {

   private List rows = new LinkedList();
   private volatile boolean finished = false;

   @Override
   public boolean next(TableEvent event) {
     rows.add(event);
     return true;
   }

   @Override
   public synchronized void finished(TableEvent event) {
     if ((event.getStatus() != TableEvent.STATUS_OK) ||
         (event.getIndex() != null)) {
       rows.add(event);
     }
     finished = true;
     notify();
   }

   public List getRows() {
     return rows;
   }

   @Override
   public boolean isFinished() {
     return finished;
   }  
 }
