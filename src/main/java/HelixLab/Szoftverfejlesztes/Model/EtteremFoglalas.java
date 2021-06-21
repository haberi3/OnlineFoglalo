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
@Table(name = "etterem_foglalas")
@XmlRootElement
public class EtteremFoglalas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "etterem_foglalas_id")
    private Integer etteremFoglalasId;
    @Basic(optional = false)
    @Column(name = "lefoglalt_helyek_szama")
    private short lefoglaltHelyekSzama;
    @Basic(optional = false)
    @Column(name = "etterem_hirdetes_id")
    private int etteremHirdetesId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private int felhasznaloId;
    @Basic(optional = false)
    @Column(name = "foglalas_id")
    private int foglalasId;

    public EtteremFoglalas() {
    }

    public EtteremFoglalas(Integer etteremFoglalasId) {
        this.etteremFoglalasId = etteremFoglalasId;
    }

    public EtteremFoglalas(Integer etteremFoglalasId, short lefoglaltHelyekSzama, int etteremHirdetesId, int felhasznaloId, int foglalasId) {
        this.etteremFoglalasId = etteremFoglalasId;
        this.lefoglaltHelyekSzama = lefoglaltHelyekSzama;
        this.etteremHirdetesId = etteremHirdetesId;
        this.felhasznaloId = felhasznaloId;
        this.foglalasId = foglalasId;
    }

    public Integer getEtteremFoglalasId() {
        return etteremFoglalasId;
    }

    public void setEtteremFoglalasId(Integer etteremFoglalasId) {
        this.etteremFoglalasId = etteremFoglalasId;
    }

    public short getLefoglaltHelyekSzama() {
        return lefoglaltHelyekSzama;
    }

    public void setLefoglaltHelyekSzama(short lefoglaltHelyekSzama) {
        this.lefoglaltHelyekSzama = lefoglaltHelyekSzama;
    }

    public int getEtteremHirdetesId() {
        return etteremHirdetesId;
    }

    public void setEtteremHirdetesId(int etteremHirdetesId) {
        this.etteremHirdetesId = etteremHirdetesId;
    }

    public int getFelhasznaloId() {
        return felhasznaloId;
    }

    public void setFelhasznaloId(int felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    public int getFoglalasId() {
        return foglalasId;
    }

    public void setFoglalasId(int foglalasId) {
        this.foglalasId = foglalasId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etteremFoglalasId != null ? etteremFoglalasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtteremFoglalas)) {
            return false;
        }
        EtteremFoglalas other = (EtteremFoglalas) object;
        if ((this.etteremFoglalasId == null && other.etteremFoglalasId != null) || (this.etteremFoglalasId != null && !this.etteremFoglalasId.equals(other.etteremFoglalasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.EtteremFoglalas[ etteremFoglalasId=" + etteremFoglalasId + " ]";
    }

    public boolean isNotEmpty() {
        return (this.etteremHirdetesId != 0 && this.felhasznaloId != 0 && this.foglalasId != 0 && this.lefoglaltHelyekSzama != 0);
    }
    
}
