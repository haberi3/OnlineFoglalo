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
@Table(name = "elerhetosegek")
@XmlRootElement
public class Elerhetosegek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Elerhetosegek_id")
    private Integer elerhetosegekid;
    @Basic(optional = false)
    @Column(name = "vezeteknev")
    private String vezeteknev;
    @Basic(optional = false)
    @Column(name = "keresztnev")
    private String keresztnev;
    @Basic(optional = false)
    @Column(name = "telefon")
    private String telefon;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    public Elerhetosegek() {
    }

    public Elerhetosegek(Integer elerhetosegekid) {
        this.elerhetosegekid = elerhetosegekid;
    }

    public Elerhetosegek(Integer elerhetosegekid, String vezeteknev, String keresztnev, String telefon, String email) {
        this.elerhetosegekid = elerhetosegekid;
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
        this.telefon = telefon;
        this.email = email;
    }

    public Integer getElerhetosegekid() {
        return elerhetosegekid;
    }

    public void setElerhetosegekid(Integer elerhetosegekid) {
        this.elerhetosegekid = elerhetosegekid;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }

    public void setKeresztnev(String keresztnev) {
        this.keresztnev = keresztnev;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elerhetosegekid != null ? elerhetosegekid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elerhetosegek)) {
            return false;
        }
        Elerhetosegek other = (Elerhetosegek) object;
        if ((this.elerhetosegekid == null && other.elerhetosegekid != null) || (this.elerhetosegekid != null && !this.elerhetosegekid.equals(other.elerhetosegekid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "elerhetosegekid=" + elerhetosegekid + " vezetéknév= " + this.vezeteknev + " keresztnév= " + this.keresztnev + " telefon= " + 
                this.telefon + " email= " + this.email + "]";
    }

    public boolean isNotEmpty() {
        return (this.email != null && this.keresztnev != null && this.telefon != null && this.vezeteknev != null);
    }
    
}
