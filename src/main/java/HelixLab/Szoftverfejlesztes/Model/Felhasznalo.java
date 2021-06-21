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
@Table(name = "felhasznalo")
@XmlRootElement
public class Felhasznalo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private Integer felhasznaloId;
    @Basic(optional = false)
    @Column(name = "felhasznalonev")
    private String felhasznalonev;
    @Basic(optional = false)
    @Column(name = "jelszo")
    private String jelszo;
    @Basic(optional = false)
    @Column(name = "profilkep")
    private String profilkep;
    @Basic(optional = false)
    @Column(name = "elerhetosegek_id")
    private int elerhetosegekId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_status")
    private int felhasznaloStatus;

    public Felhasznalo() {
    }

    public Felhasznalo(Integer felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    public Felhasznalo(Integer felhasznaloId, String felhasznalonev, String jelszo, String profilkep, int elerhetosegekId, int felhasznaloStatus) {
        this.felhasznaloId = felhasznaloId;
        this.felhasznalonev = felhasznalonev;
        this.jelszo = jelszo;
        this.profilkep = profilkep;
        this.elerhetosegekId = elerhetosegekId;
        this.felhasznaloStatus = felhasznaloStatus;
    }

    public Integer getFelhasznaloId() {
        return felhasznaloId;
    }

    public void setFelhasznaloId(Integer felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getProfilkep() {
        return profilkep;
    }

    public void setProfilkep(String profilkep) {
        this.profilkep = profilkep;
    }

    public int getElerhetosegekId() {
        return elerhetosegekId;
    }

    public void setElerhetosegekId(int elerhetosegekId) {
        this.elerhetosegekId = elerhetosegekId;
    }

    public int getFelhasznaloStatus() {
        return felhasznaloStatus;
    }

    public void setFelhasznaloStatus(int felhasznaloStatus) {
        this.felhasznaloStatus = felhasznaloStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (felhasznaloId != null ? felhasznaloId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Felhasznalo)) {
            return false;
        }
        Felhasznalo other = (Felhasznalo) object;
        if ((this.felhasznaloId == null && other.felhasznaloId != null) || (this.felhasznaloId != null && !this.felhasznaloId.equals(other.felhasznaloId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ felhasznaloId=" + felhasznaloId + " felhasználónév= " + this.felhasznalonev + " jelszó= " + this.jelszo + " profilkép = " + this.profilkep
                + " elérhetőségekId = " + this.elerhetosegekId + " státusz= " + this.felhasznaloStatus + "]";
    }

    public boolean isNotEmpty() {
        return (this.felhasznalonev != null && this.jelszo != null && this.elerhetosegekId != 0);
    }
    
}
