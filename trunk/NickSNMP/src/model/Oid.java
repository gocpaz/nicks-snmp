/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nikos
 */
@Entity
@Table(name = "OID")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oid.findAll", query = "SELECT o FROM Oid o"),
    @NamedQuery(name = "Oid.findByOidId", query = "SELECT o FROM Oid o WHERE o.oidId = :oidId"),
    @NamedQuery(name = "Oid.findByOidName", query = "SELECT o FROM Oid o WHERE o.oidName = :oidName"),
    @NamedQuery(name = "Oid.findByOidIndex", query = "SELECT o FROM Oid o WHERE o.oidIndex = :oidIndex"),
    @NamedQuery(name = "Oid.findByOwner", query = "SELECT o FROM Oid o WHERE o.ownerMib = :ownerMib")})
public class Oid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OID_ID")
    private Integer oidId;
    @Basic(optional = false)
    @Column(name = "OID_NAME")
    private String oidName;
    @Basic(optional = false)
    @Column(name = "OID_INDEX")
    private String oidIndex;
    @JoinColumn(name = "OWNER_MIB", referencedColumnName = "MIB_ID")
    @ManyToOne(optional = false)
    private Mib ownerMib;

    public Oid() {
    }

    public Oid(Integer oidId) {
        this.oidId = oidId;
    }

    public Oid(Integer oidId, String oidName, String oidIndex) {
        this.oidId = oidId;
        this.oidName = oidName;
        this.oidIndex = oidIndex;
    }

    public Integer getOidId() {
        return oidId;
    }

    public void setOidId(Integer oidId) {
        this.oidId = oidId;
    }

    public String getOidName() {
        return oidName;
    }

    public void setOidName(String oidName) {
        this.oidName = oidName;
    }

    public String getOidIndex() {
        return oidIndex;
    }

    public void setOidIndex(String oidIndex) {
        this.oidIndex = oidIndex;
    }

    public Mib getOwnerMib() {
        return ownerMib;
    }

    public void setOwnerMib(Mib ownerMib) {
        this.ownerMib = ownerMib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oidId != null ? oidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oid)) {
            return false;
        }
        Oid other = (Oid) object;
        if ((this.oidId == null && other.oidId != null) || (this.oidId != null && !this.oidId.equals(other.oidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return oidName;
    }
    
}
