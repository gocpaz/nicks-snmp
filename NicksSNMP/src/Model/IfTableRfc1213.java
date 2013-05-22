/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nikolaos
 */
@Entity
@Table(name = "IF_TABLE_RFC1213")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IfTableRfc1213.findAll", query = "SELECT i FROM IfTableRfc1213 i"),
    @NamedQuery(name = "IfTableRfc1213.findByIfTableId", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifTableId = :ifTableId"),
    @NamedQuery(name = "IfTableRfc1213.findByIfIndex", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifIndex = :ifIndex"),
    @NamedQuery(name = "IfTableRfc1213.findByIfInOctets", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifInOctets = :ifInOctets"),
    @NamedQuery(name = "IfTableRfc1213.findByIfInUcastPkts", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifInUcastPkts = :ifInUcastPkts"),
    @NamedQuery(name = "IfTableRfc1213.findByIfInNucastPkts", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifInNucastPkts = :ifInNucastPkts"),
    @NamedQuery(name = "IfTableRfc1213.findByIfInDiscards", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifInDiscards = :ifInDiscards"),
    @NamedQuery(name = "IfTableRfc1213.findByIfInErrors", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifInErrors = :ifInErrors"),
    @NamedQuery(name = "IfTableRfc1213.findByIfInUnknownProtos", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifInUnknownProtos = :ifInUnknownProtos"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOutOctets", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOutOctets = :ifOutOctets"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOutUcastPkts", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOutUcastPkts = :ifOutUcastPkts"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOutNucastPkts", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOutNucastPkts = :ifOutNucastPkts"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOutDiscards", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOutDiscards = :ifOutDiscards"),
    @NamedQuery(name = "IfTableRfc1213.findByIfDescr", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifDescr = :ifDescr"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOutErrors", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOutErrors = :ifOutErrors"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOutQLen", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOutQLen = :ifOutQLen"),
    @NamedQuery(name = "IfTableRfc1213.findByIfSpecific", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifSpecific = :ifSpecific"),
    @NamedQuery(name = "IfTableRfc1213.findByIfType", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifType = :ifType"),
    @NamedQuery(name = "IfTableRfc1213.findByIfMtu", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifMtu = :ifMtu"),
    @NamedQuery(name = "IfTableRfc1213.findByIfSpeed", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifSpeed = :ifSpeed"),
    @NamedQuery(name = "IfTableRfc1213.findByIfPhysAddress", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifPhysAddress = :ifPhysAddress"),
    @NamedQuery(name = "IfTableRfc1213.findByIfAdminStatus", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifAdminStatus = :ifAdminStatus"),
    @NamedQuery(name = "IfTableRfc1213.findByIfOperStatus", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifOperStatus = :ifOperStatus"),
    @NamedQuery(name = "IfTableRfc1213.findByIfLastChange", query = "SELECT i FROM IfTableRfc1213 i WHERE i.ifLastChange = :ifLastChange")})
public class IfTableRfc1213 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IF_TABLE_ID")
    private Integer ifTableId;
    @Column(name = "IF_INDEX")
    private Integer ifIndex;
    @Column(name = "IF_IN_OCTETS")
    private BigInteger ifInOctets;
    @Column(name = "IF_IN_UCAST_PKTS")
    private BigInteger ifInUcastPkts;
    @Column(name = "IF_IN_NUCAST_PKTS")
    private BigInteger ifInNucastPkts;
    @Column(name = "IF_IN_DISCARDS")
    private BigInteger ifInDiscards;
    @Column(name = "IF_IN_ERRORS")
    private BigInteger ifInErrors;
    @Column(name = "IF_IN_UNKNOWN_PROTOS")
    private BigInteger ifInUnknownProtos;
    @Column(name = "IF_OUT_OCTETS")
    private BigInteger ifOutOctets;
    @Column(name = "IF_OUT_UCAST_PKTS")
    private BigInteger ifOutUcastPkts;
    @Column(name = "IF_OUT_NUCAST_PKTS")
    private BigInteger ifOutNucastPkts;
    @Column(name = "IF_OUT_DISCARDS")
    private BigInteger ifOutDiscards;
    @Column(name = "IF_DESCR")
    private String ifDescr;
    @Column(name = "IF_OUT_ERRORS")
    private BigInteger ifOutErrors;
    @Column(name = "IF_OUT_Q_LEN")
    private BigInteger ifOutQLen;
    @Column(name = "IF_SPECIFIC")
    private String ifSpecific;
    @Column(name = "IF_TYPE")
    private BigInteger ifType;
    @Column(name = "IF_MTU")
    private BigInteger ifMtu;
    @Column(name = "IF_SPEED")
    private BigInteger ifSpeed;
    @Column(name = "IF_PHYS_ADDRESS")
    private String ifPhysAddress;
    @Column(name = "IF_ADMIN_STATUS")
    private BigInteger ifAdminStatus;
    @Column(name = "IF_OPER_STATUS")
    private BigInteger ifOperStatus;
    @Column(name = "IF_LAST_CHANGE")
    private String ifLastChange;

    public IfTableRfc1213() {
    }

    public IfTableRfc1213(Integer ifTableId) {
        this.ifTableId = ifTableId;
    }

    public Integer getIfTableId() {
        return ifTableId;
    }

    public void setIfTableId(Integer ifTableId) {
        this.ifTableId = ifTableId;
    }

    public Integer getIfIndex() {
        return ifIndex;
    }

    public void setIfIndex(Integer ifIndex) {
        this.ifIndex = ifIndex;
    }

    public BigInteger getIfInOctets() {
        return ifInOctets;
    }

    public void setIfInOctets(BigInteger ifInOctets) {
        this.ifInOctets = ifInOctets;
    }

    public BigInteger getIfInUcastPkts() {
        return ifInUcastPkts;
    }

    public void setIfInUcastPkts(BigInteger ifInUcastPkts) {
        this.ifInUcastPkts = ifInUcastPkts;
    }

    public BigInteger getIfInNucastPkts() {
        return ifInNucastPkts;
    }

    public void setIfInNucastPkts(BigInteger ifInNucastPkts) {
        this.ifInNucastPkts = ifInNucastPkts;
    }

    public BigInteger getIfInDiscards() {
        return ifInDiscards;
    }

    public void setIfInDiscards(BigInteger ifInDiscards) {
        this.ifInDiscards = ifInDiscards;
    }

    public BigInteger getIfInErrors() {
        return ifInErrors;
    }

    public void setIfInErrors(BigInteger ifInErrors) {
        this.ifInErrors = ifInErrors;
    }

    public BigInteger getIfInUnknownProtos() {
        return ifInUnknownProtos;
    }

    public void setIfInUnknownProtos(BigInteger ifInUnknownProtos) {
        this.ifInUnknownProtos = ifInUnknownProtos;
    }

    public BigInteger getIfOutOctets() {
        return ifOutOctets;
    }

    public void setIfOutOctets(BigInteger ifOutOctets) {
        this.ifOutOctets = ifOutOctets;
    }

    public BigInteger getIfOutUcastPkts() {
        return ifOutUcastPkts;
    }

    public void setIfOutUcastPkts(BigInteger ifOutUcastPkts) {
        this.ifOutUcastPkts = ifOutUcastPkts;
    }

    public BigInteger getIfOutNucastPkts() {
        return ifOutNucastPkts;
    }

    public void setIfOutNucastPkts(BigInteger ifOutNucastPkts) {
        this.ifOutNucastPkts = ifOutNucastPkts;
    }

    public BigInteger getIfOutDiscards() {
        return ifOutDiscards;
    }

    public void setIfOutDiscards(BigInteger ifOutDiscards) {
        this.ifOutDiscards = ifOutDiscards;
    }

    public String getIfDescr() {
        return ifDescr;
    }

    public void setIfDescr(String ifDescr) {
        this.ifDescr = ifDescr;
    }

    public BigInteger getIfOutErrors() {
        return ifOutErrors;
    }

    public void setIfOutErrors(BigInteger ifOutErrors) {
        this.ifOutErrors = ifOutErrors;
    }

    public BigInteger getIfOutQLen() {
        return ifOutQLen;
    }

    public void setIfOutQLen(BigInteger ifOutQLen) {
        this.ifOutQLen = ifOutQLen;
    }

    public String getIfSpecific() {
        return ifSpecific;
    }

    public void setIfSpecific(String ifSpecific) {
        this.ifSpecific = ifSpecific;
    }

    public BigInteger getIfType() {
        return ifType;
    }

    public void setIfType(BigInteger ifType) {
        this.ifType = ifType;
    }

    public BigInteger getIfMtu() {
        return ifMtu;
    }

    public void setIfMtu(BigInteger ifMtu) {
        this.ifMtu = ifMtu;
    }

    public BigInteger getIfSpeed() {
        return ifSpeed;
    }

    public void setIfSpeed(BigInteger ifSpeed) {
        this.ifSpeed = ifSpeed;
    }

    public String getIfPhysAddress() {
        return ifPhysAddress;
    }

    public void setIfPhysAddress(String ifPhysAddress) {
        this.ifPhysAddress = ifPhysAddress;
    }

    public BigInteger getIfAdminStatus() {
        return ifAdminStatus;
    }

    public void setIfAdminStatus(BigInteger ifAdminStatus) {
        this.ifAdminStatus = ifAdminStatus;
    }

    public BigInteger getIfOperStatus() {
        return ifOperStatus;
    }

    public void setIfOperStatus(BigInteger ifOperStatus) {
        this.ifOperStatus = ifOperStatus;
    }

    public String getIfLastChange() {
        return ifLastChange;
    }

    public void setIfLastChange(String ifLastChange) {
        this.ifLastChange = ifLastChange;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ifTableId != null ? ifTableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IfTableRfc1213)) {
            return false;
        }
        IfTableRfc1213 other = (IfTableRfc1213) object;
        if ((this.ifTableId == null && other.ifTableId != null) || (this.ifTableId != null && !this.ifTableId.equals(other.ifTableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.IfTableRfc1213[ ifTableId=" + ifTableId + " ]";
    }
    
}
