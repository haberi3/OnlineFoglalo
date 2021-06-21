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
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Xalax
 */
@Entity
@Table(name = "lakas_berles")
@XmlRootElement
public class LakasBerles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lakas_berles_id")
    private Integer lakasBerlesId;
    @Basic(optional = false)
    @Lob
    @Column(name = "lakas_kar")
    private String lakasKar;
    @Basic(optional = false)
    @Column(name = "fizetendo_osszeg")
    private long fizetendoOsszeg;
    @Basic(optional = false)
    @Column(name = "lakas_hirdetes_id")
    private int lakasHirdetesId;
    @Basic(optional = false)
    @Column(name = "foglalas_id")
    private int foglalasId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private int felhasznaloId;
    @Basic(optional = false)
    @Column(name = "lefoglalt_helyek_szama")
    private short lefoglaltHelyekSzama;

    public LakasBerles() {
    }

    public LakasBerles(Integer lakasBerlesId) {
        this.lakasBerlesId = lakasBerlesId;
    }

    public LakasBerles(Integer lakasBerlesId, String lakasKar, long fizetendoOsszeg, int lakasHirdetesId, int foglalasId, int felhasznaloId, short lefoglaltHelyekSzama) {
        this.lakasBerlesId = lakasBerlesId;
        this.lakasKar = lakasKar;
        this.fizetendoOsszeg = fizetendoOsszeg;
        this.lakasHirdetesId = lakasHirdetesId;
        this.foglalasId = foglalasId;
        this.felhasznaloId = felhasznaloId;
        this.lefoglaltHelyekSzama = lefoglaltHelyekSzama;
    }

    public Integer getLakasBerlesId() {
        return lakasBerlesId;
    }

    public void setLakasBerlesId(Integer lakasBerlesId) {
        this.lakasBerlesId = lakasBerlesId;
    }

    public String getLakasKar() {
        return lakasKar;
    }

    public void setLakasKar(String lakasKar) {
        this.lakasKar = lakasKar;
    }

    public long getFizetendoOsszeg() {
        return fizetendoOsszeg;
    }

    public void setFizetendoOsszeg(long fizetendoOsszeg) {
        this.fizetendoOsszeg = fizetendoOsszeg;
    }

    public int getLakasHirdetesId() {
        return lakasHirdetesId;
    }

    public void setLakasHirdetesId(int lakasHirdetesId) {
        this.lakasHirdetesId = lakasHirdetesId;
    }

    public int getFoglalasId() {
        return foglalasId;
    }

    public void setFoglalasId(int foglalasId) {
        this.foglalasId = foglalasId;
    }

    public int getFelhasznaloId() {
        return felhasznaloId;
    }

    public void setFelhasznaloId(int felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    public short getLefoglaltHelyekSzama() {
        return lefoglaltHelyekSzama;
    }

    public void setLefoglaltHelyekSzama(short lefoglaltHelyekSzama) {
        this.lefoglaltHelyekSzama = lefoglaltHelyekSzama;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lakasBerlesId != null ? lakasBerlesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LakasBerles)) {
            return false;
        }
        LakasBerles other = (LakasBerles) object;
        if ((this.lakasBerlesId == null && other.lakasBerlesId != null) || (this.lakasBerlesId != null && !this.lakasBerlesId.equals(other.lakasBerlesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.LakasBerles[ lakasBerlesId=" + lakasBerlesId + " ]";
    }

    public boolean isNotEmpty() {
        return (this.felhasznaloId != 0 && this.lakasHirdetesId != 0 && this.lakasKar != null && this.lefoglaltHelyekSzama != 0);
    }
    
}
