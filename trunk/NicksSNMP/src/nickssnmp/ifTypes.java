/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

/**
 *
 * @author Nikolaos
 */
public enum ifTypes {
    other(1),         
    regular1822(2),
    hdh1822(3),
    ddnx25(4),
    rfc877x25(5),
    ethernetcsmacd(6),
    iso88023csmacd(7),
    iso88024tokenBus(8),
    iso88025tokenRing(9),
    iso88026man(10),
    starLan(11),
    proteon10Mbit(12),
    proteon80Mbit(13),
    hyperchannel(14),
    fddi(15),
    lapb(16),
    sdlc(17),
    ds1(18),          
    e1(19),            
    basicISDN(20),
    primaryISDN(21),   
    propPointToPointSerial(22),
    ppp(23),
    softwareLoopback(24),
    eon(25),            
    ethernet3Mbit(26),
    nsip(27),           
    slip(28),           
    ultra(29),          
    ds3(30),            
    sip(31),            
    framerelay(32);
                
    private int value;
    
    private ifTypes(int value){
        this.value = value;
    }    
}
