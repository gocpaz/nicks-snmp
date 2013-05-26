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
import org.snmp4j.smi.GenericAddress;
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

    PDU requestPDU = null;
	Snmp snmp = null;
	CommunityTarget target = null;
	String responseString = null;
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws IOException {
////         try {
////            Snmp snmp4j =  new Snmp(new DefaultUdpTransportMapping());
////            snmp4j.listen();
////            Address add = new UdpAddress("83.212.238.231"+"/"+"161");
////            CommunityTarget target = new CommunityTarget();
////            target.setAddress(add);
////            target.setTimeout(500);            
////            target.setRetries(3);
////            target.setCommunity(new OctetString("public"));
////            target.setVersion(SnmpConstants.version1);
////            
////        //-- TABULAR DATA    
////        //----------------------------------------
////            ScopedPDU pdu = new ScopedPDU();
////        
////         String []strOIDs = {"1.3.6.1.2.1.3.1"};
////         OID oids = new OID("1.3.6.1.2.1.3.1");
////         List<OID> list = new ArrayList();
////         list.add(oids);
////         final OID[] oidArray = new OID[list.size()];
////         int i = 0;
////         for (OID oid : list) {
////            oidArray[i++] = oid;
////         }            
////             PDU request = new PDU();            
////             
////        TableUtils tableUtils = new TableUtils(snmp4j, new DefaultPDUFactory());
////        InternalTableListener listener = new InternalTableListener();
////        tableUtils.getTable(target, oidArray, listener, null, null, null);             
////            
////        System.out.println("rows::"+listener.getRows());
////         List<TableEvent> tableEvents = tableUtils.getTable(target, 
////            parseStrToOID(strOIDs), null, null);
////         Iterator<TableEvent> tEvent = tableEvents.iterator();
////         while(tEvent.hasNext()) {
////            TableEvent event = (TableEvent) tEvent.next();
////            System.out.println("Table event:::error::"+ event.getErrorMessage());
////            System.out.println("Table event::reportpdu:::"+ event.getReportPDU());
////            System.out.println("Table event:::index::"+ event.getIndex());
////            VariableBinding[] vb = event.getColumns();
////            //System.out.println("vb:::"+vb.getOid());
////            for (int count=0; count< vb.length;count++) {
////               if (vb[count].getOid() != null)
////               System.out.println("Table event::oid:::"+vb[count].getOid());
////               System.out.println("Table event::value::"+vb[count].getVariable());               
////            
////            }
////         }
////         //-- PRIMITIVE DATA
////            //-----------------------------------------------------------------
////         
////        request.setType(PDU.GET);
////        OID oid= new OID(".1.3.6.1.2.1.1.1.0");
////        request.add(new VariableBinding(oid));
////
////        PDU responsePDU=null;
////        ResponseEvent responseEvent;
////        responseEvent = snmp4j.send(request, target);
////
////        if (responseEvent != null)
////        {
////            responsePDU = responseEvent.getResponse();
////            if ( responsePDU != null)
////            {
////
////                Vector <VariableBinding> tmpv = (Vector <VariableBinding>) responsePDU.getVariableBindings();
////                if(tmpv != null)
////                {
////                    for(int k=0; k <tmpv.size();k++)
////                    {
////                        VariableBinding vb = (VariableBinding) tmpv.get(k);
////                        String output = null;
////                        if ( vb.isException())
////                        {
////
////                            String errorstring = vb.getVariable().getSyntaxString();
////                            System.out.println("Error:"+errorstring);
////                        }
////                        else
////                        {
////                            String sOid = vb.getOid().toString();
////                            Variable var = vb.getVariable();
////                            OctetString oct = new OctetString((OctetString)var);
////                            String sVar =  oct.toString();
////
////                            System.out.println("success:"+sVar);
////                        }
////                }
////            }
////        }
////        }
////        } catch (IOException e) {
////             
////            e.printStackTrace();
////        }      
//        //for(int i = 0; i < 10; i++)
//		  {
//		    NicksSNMP obj = new NicksSNMP("83.212.238.193","public",1,1000);
//			String arrGet = obj.snmpGet(".1.3.6.1.2.1.1.1.0");
//			System.out.println("\nGET RESPONSE");
//			System.out.println(arrGet);
//			
//			String arrGetNext = obj.snmpGetNext(".1.3.6.1.2.1.1.1.0");
//			System.out.println("\nGETNEXT RESPONSE");
//			System.out.println(arrGetNext);
//			
//			String[] arr = { "1.3.6.1.2.1.2.2.1.1", "1.3.6.1.2.1.2.2.1.5" };
//			String arrGetTable = obj.snmpGetTable(arr);
//			System.out.println("\nGETTABLE RESPONSE");
//			System.out.println(arrGetTable);
//			
//		  }
//        
//        
//    }
    
    protected static OID[] parseStrToOID(String[] strOIDs) {
      OID[] oids = new OID[strOIDs.length];
      for (int i = 0; i < strOIDs.length; i++) {
          oids[i] = new OID(strOIDs[i]);
      }
      return oids;
  }
    public NicksSNMP(String IP, String commString, int version, long timeout) 
	{
		
		// Create an instance of the Snmp class, CommunityTarget class 
		try 
		{
			snmp = new Snmp(new DefaultUdpTransportMapping());
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
			snmp.listen();
	
			// Send the PDU constructed, to the target
			response = snmp.send(requestPDU, target);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
			
		// Retrieve the response details and put into an array called "responseArray"
		PDU responsePdu = response.getResponse(); 
		if(responsePdu.getErrorStatus() == 0)
		{
			responseString = responsePdu.toString();
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
	public String snmpGetTable(String[] oid) 
	{
		
		// Invoke the listen() method on the Snmp object
		try 
		{
			snmp.listen();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		      
		// Create a TableUtils 
		TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory());
				
		// Set the lower/upper bounds for the table operation
		OID lowerIndex = null;
		OID upperIndex = null;
		
		// Create an array of the OID's that need to be checked
		OID[] arr = new OID[oid.length];
		for (int i=0; i<oid.length; i++)
			arr[i] = new OID(oid[i]);
		
		// Transfer output to a data structure
		List list = utils.getTable(target, arr, lowerIndex, upperIndex);
				
		// Dump the response into an array called "responseArray"
		
		return responseString;
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
