/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;


/**
 *
 * @author Nikolaos
 */
public class RFCUtils {
  
    public static String getIfTypesRFC1213(int i )
    {
        String result = null;
        
        switch (i){
            case 1:
                result = "other";
                break;
            case 2:
                result = "regular1822";
                break;
            case 3:
                result = "hdh1822";
                break;
            case 4:
                result = "ddn-x25";
                break;
            case 5:
                result = "rfc877-x25";
                break;
            case 6:
                result = "ethernet-csmacd";
                break;
            case 7:
                result = "iso88023-csmacd";
                break;
            case 8:
                result = "iso88024-tokenBus";
                break;
            case 9:
                result = "iso88025-tokenRing";
                break;
            case 10:
                result = "iso88026-man";
                break;
            case 11:
                result = "starLan";
                break;
            case 12:
                result = "proteon-10Mbit";
                break;
            case 13:
                result = "proteon-80Mbit";
                break;
            case 14:
                result = "hyperchannel";
                break;
            case 15:
                result = "fddi";
                break;
            case 16:
                result = "lapb";
                break;
            case 17:
                result = "sdlc";
                break;
            case 18:
                result = "ds1";
                break;
            case 19:
                result = "e1";
                break;
            case 20:
                result = "basicISDN";
                break;
            case 21:
                result = "primaryISDN";
                break;
            case 22:
                result = "propPointToPointSerial";
                break;
            case 23:
                result = "ppp";
                break;
            case 24:
                result = "softwareLoopback";
                break;
            case 25:
                result = "eon";
                break;     
            case 26:
                result = "ethernet-3Mbit";
                break;
            case 27:
                result = "nsip";
                break;
            case 28:
                result = "slip";
                break;
            case 29:
                result = "ultra";
                break;
            case 30:
                result = "ds3";
                break;
            case 31:
                result = "sip";
                break;
            case 32:
                result = "frame-relay";
                break;                    
                
           case 53:
                result = "propVirtual";
                break;
            case 54:
                result = "propMultiplexor";
                break; 
            default : 
                    result = "CHANGE "+i;
                break;

        }        
        return result;
    }
}
