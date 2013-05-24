/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

import Model.IfTableRfc1213;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import static nickssnmp.NicksSNMP.parseStrToOID;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
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
public class SnmpUtil {
    
    private Snmp snmp4j;
    private Address address;
    private CommunityTarget target = null;
    PDU requestPDU = null;    
    String responseString = null;    
        
    public SnmpUtil(String IP, String commString, int version, long timeout) 
    {
            // Create an instance of the Snmp class, CommunityTarget class 
            try 
            {
                    snmp4j = new Snmp(new DefaultUdpTransportMapping());
            } 
            catch (IOException e) 
            {		
                    e.printStackTrace();
            }
            target = new CommunityTarget();
            Address targetAddress = GenericAddress.parse("udp:" + IP + "/161");
            // Set the address of the target device; This is a mandatory value to be passed by the calling script
            target.setAddress(targetAddress);
            /*
             *  Set the version of the target device;
             *  In cases where the version provided is not 1,2 or 3, the default value set is 1  
             */  		
            if (version == 1)
                    target.setVersion(SnmpConstants.version1);
            else if (version == 2)
                    target.setVersion(SnmpConstants.version2c);
            else if (version == 3)
                    target.setVersion(SnmpConstants.version3);
            // Set the timeout of the target device
            target.setTimeout(timeout);
            /*
             *  Set the community string of the target device;
             *  In cases where the community string provided is not null, the default value is set as "public"  
             */  
            if (commString == null)
                    target.setCommunity(new OctetString("public"));
            else
                    target.setCommunity(new OctetString(commString));		
    }
	
    public IfTableRfc1213 getIfTableRfc1213Data(String OID)
    {
        IfTableRfc1213 ifTable = new IfTableRfc1213();                
        
         String []strOIDs = {OID};
         OID oids = new OID(OID);
         List<OID> list = new ArrayList();
         list.add(oids);
         final OID[] oidArray = new OID[list.size()];
         int i = 0;
         for (OID oid : list) {
            oidArray[i++] = oid;
         }            
                               
        TableUtils tableUtils = new TableUtils(snmp4j, new DefaultPDUFactory(PDU.GETBULK));
        InternalTableListener listener = new InternalTableListener();
        tableUtils.getTable(target, oidArray,listener, null, null, null);             
            
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
            
               //ifIndex
                if(vb[count].getOid().toString().startsWith("1.3.6.1.2.1.2.2.1.1")){
                    
                }
            }
         }
         
         return ifTable;
    }
    
    public String getPrimitiveData(String OID) throws IOException
    {
        String sVar = null;
        PDU request = new PDU();
        request.setType(PDU.GET);
        OID oid= new OID(OID);
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
                            sVar =  oct.toString();

                            System.out.println("success:"+sVar);
                        }
                    }
                }
            }        
        } 
        return sVar;
    }

    protected static OID[] parseStrToOID(String[] strOIDs) {
      OID[] oids = new OID[strOIDs.length];
      for (int i = 0; i < strOIDs.length; i++) {
          oids[i] = new OID(strOIDs[i]);
      }
      return oids;
  }
    /*
    *  SNMP GET method API
    */	
   public String snmpGet(String oid) 
   {
           get(oid, PDU.GET);
           return(responseString);		
   }		
   /*
    *  SNMP GETNEXT method API
    */	
   public String snmpGetNext(String oid) throws IOException 
   {
           get(oid, PDU.GETNEXT);
           return(responseString);	
   }
/*
    *  SNMP getTable operation
    */
   public List snmpGetTable(String[] oid) 
   {
           // Invoke the listen() method on the Snmp object
           try 
           {
                   snmp4j.listen();
           } 
           catch (IOException e) 
           {
                   e.printStackTrace();
           }

           // Create a TableUtils 
           TableUtils utils = new TableUtils(snmp4j, new DefaultPDUFactory());

           // Set the lower/upper bounds for the table operation
           OID lowerIndex = null;
           OID upperIndex = null;

           // Create an array of the OID's that need to be checked
           OID[] arr = new OID[oid.length];
           for (int i=0; i<oid.length; i++)
                   arr[i] = new OID(oid[i]);

           // Transfer output to a data structure
           List list = utils.getTable(target, arr, lowerIndex, upperIndex);
        
           return list;
   }	
 /*
    *  SNMP get/getNext operation logic
    */ 
   public void get(String oid, int pduType)  
   {

           // Create a PDU with the OID and type
           requestPDU = new PDU();
           requestPDU.add(new VariableBinding(new OID(oid))); 
           requestPDU.setType(pduType);

           ResponseEvent response = null;
           try
           {
                   // Invoke the listen() method on the Snmp object
                   snmp4j.listen();

                   // Send the PDU constructed, to the target
                   response = snmp4j.send(requestPDU, target);
           }
           catch (IOException e)
           {
                   e.printStackTrace();
           }

           // Retrieve the response details and put into an array called "responseArray"
           PDU responsePdu = response.getResponse(); 
           if(responsePdu.getErrorStatus() == 0)
           {
                  
                Vector <VariableBinding> tmpv = (Vector <VariableBinding>) responsePdu.getVariableBindings();
                if(tmpv != null)
                {
                    for(int k=0; k <tmpv.size();k++)
                    {
                        VariableBinding vb = (VariableBinding) tmpv.get(k);
                        String output = null;
                        if ( vb.isException())
                        {

                            String errorstring = vb.getVariable().getSyntaxString();
                          //  System.out.println("Error:"+errorstring);
                        }
                        else
                        {
                            String sOid = vb.getOid().toString();
                            Variable var = vb.getVariable();
                            try{
                                OctetString oct = new OctetString((OctetString)var);
                                responseString =  oct.toString();
                            }catch(Exception e){
                                responseString = var.toString();
                            }
                            

                           // System.out.println("success:"+sVar);
                        }
                    }
                }                
           }
           else
           {
                   System.out.println("ERROR");
           }
           if (!(response.getResponse() == null)) 
           {
                   //Extract the response
           }
   }  
   public void insertIfTableRFC1213IntoDB(EntityManager em,List list){
       em.getTransaction().begin();
      //Clear table       
      Query query = em.createNativeQuery("DELETE FROM IF_TABLE_RFC1213");
      query.executeUpdate();
      
       IfTableRfc1213 ifTable = null;
      
        Iterator<TableEvent> tEvent = list.iterator();
        while(tEvent.hasNext()) {
           
           ifTable = new IfTableRfc1213();
           
           TableEvent event = (TableEvent) tEvent.next();
           System.out.println("Table event:::error::"+ event.getErrorMessage());
           System.out.println("Table event::reportpdu:::"+ event.getReportPDU());
           System.out.println("Table event:::index::"+ event.getIndex());
          VariableBinding[] vb = event.getColumns();
           //System.out.println("vb:::"+vb.getOid());
           for (int count=0; count< vb.length;count++) {
              if (vb[count] != null && vb[count].getOid() != null)
              {

                System.out.println("Table event::oid:::"+vb[count].getOid());
                System.out.println("Table event::value::"+vb[count].getVariable());               

                String ifVal = vb[count].getOid()+"";              
                 //ifType
                 if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.3")){
                        ifTable.setIfType(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifMtu
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.4")){
                        ifTable.setIfMtu(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifSpeed
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.5")){
                        ifTable.setIfSpeed(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifPhysAddress
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.6")){
                        ifTable.setIfPhysAddress(vb[count].getVariable()+"");
                 }
                 //ifAdminStatus
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.7")){
                        ifTable.setIfAdminStatus(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOperStatus
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.8")){
                        ifTable.setIfOperStatus(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifLastChange
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.9")){
                        ifTable.setIfLastChange(vb[count].getVariable()+"");
                 }
                 //ifInOctets
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.10")){
                        ifTable.setIfInOctets(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifInUcastPkts
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.11")){
                        ifTable.setIfInUcastPkts(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifInNUcastPkts
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.12")){
                        ifTable.setIfInNucastPkts(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifInDiscards
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.13")){
                        ifTable.setIfInDiscards(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifInErrors
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.14")){
                        ifTable.setIfInErrors(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifInUnknownProtos
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.15")){
                        ifTable.setIfInUnknownProtos(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOutOctets
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.16")){
                        ifTable.setIfOutOctets(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOutUcastPkts
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.17")){
                        ifTable.setIfOutUcastPkts(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOutNUcastPkts
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.18")){
                        ifTable.setIfOutNucastPkts(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOutDiscards
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.19")){
                        ifTable.setIfOutDiscards(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOutErrors
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.20")){
                        ifTable.setIfOutErrors(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifOutQLen
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.21")){
                        ifTable.setIfOutQLen(new BigInteger( vb[count].getVariable()+""));
                 }
                 //ifSpecific
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.22")){
                        ifTable.setIfSpecific(vb[count].getVariable()+"");
                 }
                 //ifIndex
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.1")){
                       ifTable.setIfIndex(vb[count].getVariable().toInt());
                 }
                 //ifDescr
                 else if(ifVal.startsWith("1.3.6.1.2.1.2.2.1.2")){
                        ifTable.setIfDescr(vb[count].getVariable()+"");                      
                 }
              }
           }
           em.persist(ifTable);           
        }
        em.getTransaction().commit();        
   }
   public void setTargetVersion(int version){
        switch(version){
            case 1:
                target.setVersion(SnmpConstants.version1);
                break;
           case 2:
                target.setVersion(SnmpConstants.version2c);
                break;
           case 3:
                target.setVersion(SnmpConstants.version3);
                break;    
        }
    }
}
