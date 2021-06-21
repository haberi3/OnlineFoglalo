/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelixLab.Szoftverfejlesztes.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Xalax
 */
@Entity
@Table(name = "auto_muszaki_adatok")
@XmlRootElement
public class AutoMuszakiAdatok implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auto_muszaki_adatok_id")
    private Integer autoMuszakiAdatokId;
    @Basic(optional = false)
    @Column(name = "valto_tipusa")
    private String valtoTipusa;
    @Basic(optional = false)
    @Column(name = "uzemanyag")
    private String uzemanyag;
    @Basic(optional = false)
    @Column(name = "hengerurtartalom")
    private int hengerurtartalom;

    public AutoMuszakiAdatok() {
    }

    public AutoMuszakiAdatok(Integer autoMuszakiAdatokId) {
        this.autoMuszakiAdatokId = autoMuszakiAdatokId;
    }

    public AutoMuszakiAdatok(Integer autoMuszakiAdatokId, String valtoTipusa, String uzemanyag, int hengerurtartalom) {
        this.autoMuszakiAdatokId = autoMuszakiAdatokId;
        this.valtoTipusa = valtoTipusa;
        this.uzemanyag = uzemanyag;
        this.hengerurtartalom = hengerurtartalom;
    }

    public Integer getAutoMuszakiAdatokId() {
        return autoMuszakiAdatokId;
    }

    public void setAutoMuszakiAdatokId(Integer autoMuszakiAdatokId) {
        this.autoMuszakiAdatokId = autoMuszakiAdatokId;
    }

    public String getValtoTipusa() {
        return valtoTipusa;
    }

    public void setValtoTipusa(String valtoTipusa) {
        this.valtoTipusa = valtoTipusa;
    }

    public String getUzemanyag() {
        return uzemanyag;
    }

    public void setUzemanyag(String uzemanyag) {
        this.uzemanyag = uzemanyag;
    }

    public int getHengerurtartalom() {
        return hengerurtartalom;
    }

    public void setHengerurtartalom(int hengerurtartalom) {
        this.hengerurtartalom = hengerurtartalom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoMuszakiAdatokId != null ? autoMuszakiAdatokId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutoMuszakiAdatok)) {
            return false;
        }
        AutoMuszakiAdatok other = (AutoMuszakiAdatok) object;
        if ((this.autoMuszakiAdatokId == null && other.autoMuszakiAdatokId != null) || (this.autoMuszakiAdatokId != null && !this.autoMuszakiAdatokId.equals(other.autoMuszakiAdatokId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.AutoMuszakiAdatok[ autoMuszakiAdatokId=" + autoMuszakiAdatokId + " ]";
    }

    public boolean isNotEmpty() {
        return (this.hengerurtartalom != 0 && this.uzemanyag != null && this.valtoTipusa != null);
    }
    
}
