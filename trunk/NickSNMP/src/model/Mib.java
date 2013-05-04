/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nikos
 */
@Entity
@Table(name = "MIB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mib.findAll", query = "SELECT m FROM Mib m"),
    @NamedQuery(name = "Mib.findByMibId", query = "SELECT m FROM Mib m WHERE m.mibId = :mibId"),
    @NamedQuery(name = "Mib.findByMibName", query = "SELECT m FROM Mib m WHERE m.mibName = :mibName")})
public class Mib implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MIB_ID")
    private Integer mibId;
    @Basic(optional = false)
    @Column(name = "MIB_NAME")
    private String mibName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerMib")
    private List<Oid> oidList;

    public Mib() {
    }

    public Mib(Integer mibId) {
        this.mibId = mibId;
    }

    public Mib(Integer mibId, String mibName) {
        this.mibId = mibId;
        this.mibName = mibName;
    }

    public Integer getMibId() {
        return mibId;
    }

    public void setMibId(Integer mibId) {
        this.mibId = mibId;
    }

    public String getMibName() {
        return mibName;
    }

    public void setMibName(String mibName) {
        this.mibName = mibName;
    }

    @XmlTransient
    public List<Oid> getOidList() {
        return oidList;
    }

    public void setOidList(List<Oid> oidList) {
        this.oidList = oidList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mibId != null ? mibId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mib)) {
            return false;
        }
        Mib other = (Mib) object;
        if ((this.mibId == null && other.mibId != null) || (this.mibId != null && !this.mibId.equals(other.mibId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mibName;
    }
    
}
