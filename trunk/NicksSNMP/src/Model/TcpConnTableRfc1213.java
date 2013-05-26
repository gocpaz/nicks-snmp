/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
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
@Table(name = "TCP_CONN_TABLE_RFC1213")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TcpConnTableRfc1213.findAll", query = "SELECT t FROM TcpConnTableRfc1213 t"),
    @NamedQuery(name = "TcpConnTableRfc1213.findByTcpConnTableId", query = "SELECT t FROM TcpConnTableRfc1213 t WHERE t.tcpConnTableId = :tcpConnTableId"),
    @NamedQuery(name = "TcpConnTableRfc1213.findByState", query = "SELECT t FROM TcpConnTableRfc1213 t WHERE t.state = :state"),
    @NamedQuery(name = "TcpConnTableRfc1213.findByLocalAddress", query = "SELECT t FROM TcpConnTableRfc1213 t WHERE t.localAddress = :localAddress"),
    @NamedQuery(name = "TcpConnTableRfc1213.findByLocalPort", query = "SELECT t FROM TcpConnTableRfc1213 t WHERE t.localPort = :localPort"),
    @NamedQuery(name = "TcpConnTableRfc1213.findByRemAddress", query = "SELECT t FROM TcpConnTableRfc1213 t WHERE t.remAddress = :remAddress"),
    @NamedQuery(name = "TcpConnTableRfc1213.findByRemPort", query = "SELECT t FROM TcpConnTableRfc1213 t WHERE t.remPort = :remPort"),
    @NamedQuery(name = "TcpConnTableRfc1213.findEstablished", query = "SELECT COUNT(t.state) FROM TcpConnTableRfc1213 t WHERE t.state = 5")
})
public class TcpConnTableRfc1213 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TCP_CONN_TABLE_ID")
    private Integer tcpConnTableId;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "LOCAL_ADDRESS")
    private String localAddress;
    @Column(name = "LOCAL_PORT")
    private Integer localPort;
    @Column(name = "REM_ADDRESS")
    private String remAddress;
    @Column(name = "REM_PORT")
    private Integer remPort;

    public TcpConnTableRfc1213() {
    }

    public TcpConnTableRfc1213(Integer tcpConnTableId) {
        this.tcpConnTableId = tcpConnTableId;
    }

    public Integer getTcpConnTableId() {
        return tcpConnTableId;
    }

    public void setTcpConnTableId(Integer tcpConnTableId) {
        this.tcpConnTableId = tcpConnTableId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }

    public String getRemAddress() {
        return remAddress;
    }

    public void setRemAddress(String remAddress) {
        this.remAddress = remAddress;
    }

    public Integer getRemPort() {
        return remPort;
    }

    public void setRemPort(Integer remPort) {
        this.remPort = remPort;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tcpConnTableId != null ? tcpConnTableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TcpConnTableRfc1213)) {
            return false;
        }
        TcpConnTableRfc1213 other = (TcpConnTableRfc1213) object;
        if ((this.tcpConnTableId == null && other.tcpConnTableId != null) || (this.tcpConnTableId != null && !this.tcpConnTableId.equals(other.tcpConnTableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TcpConnTableRfc1213[ tcpConnTableId=" + tcpConnTableId + " ]";
    }
    
}
