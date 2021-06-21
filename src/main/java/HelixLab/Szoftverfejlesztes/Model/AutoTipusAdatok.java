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
@Table(name = "auto_tipus_adatok")
@XmlRootElement
public class AutoTipusAdatok implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auto_tipus_adatok_id")
    private Integer autoTipusAdatokId;
    @Basic(optional = false)
    @Column(name = "marka")
    private String marka;
    @Basic(optional = false)
    @Column(name = "tipus")
    private String tipus;
    @Basic(optional = false)
    @Column(name = "ulesek_szama")
    private short ulesekSzama;
    @Basic(optional = false)
    @Column(name = "ajtok_szama")
    private short ajtokSzama;

    public AutoTipusAdatok() {
    }

    public AutoTipusAdatok(Integer autoTipusAdatokId) {
        this.autoTipusAdatokId = autoTipusAdatokId;
    }

    public AutoTipusAdatok(Integer autoTipusAdatokId, String marka, String tipus, short ulesekSzama, short ajtokSzama) {
        this.autoTipusAdatokId = autoTipusAdatokId;
        this.marka = marka;
        this.tipus = tipus;
        this.ulesekSzama = ulesekSzama;
        this.ajtokSzama = ajtokSzama;
    }

    public Integer getAutoTipusAdatokId() {
        return autoTipusAdatokId;
    }

    public void setAutoTipusAdatokId(Integer autoTipusAdatokId) {
        this.autoTipusAdatokId = autoTipusAdatokId;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public short getUlesekSzama() {
        return ulesekSzama;
    }

    public void setUlesekSzama(short ulesekSzama) {
        this.ulesekSzama = ulesekSzama;
    }

    public short getAjtokSzama() {
        return ajtokSzama;
    }

    public void setAjtokSzama(short ajtokSzama) {
        this.ajtokSzama = ajtokSzama;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoTipusAdatokId != null ? autoTipusAdatokId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutoTipusAdatok)) {
            return false;
        }
        AutoTipusAdatok other = (AutoTipusAdatok) object;
        if ((this.autoTipusAdatokId == null && other.autoTipusAdatokId != null) || (this.autoTipusAdatokId != null && !this.autoTipusAdatokId.equals(other.autoTipusAdatokId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.AutoTipusAdatok[ autoTipusAdatokId=" + autoTipusAdatokId + " ]";
    }
    
    public boolean isNotEmpty(){
        return (this.ajtokSzama != 0 && this.marka != null && this.tipus != null && this.ulesekSzama != 0);
    }
}
