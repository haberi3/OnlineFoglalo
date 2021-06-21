/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelixLab.Szoftverfejlesztes.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Xalax
 */
@Entity
@Table(name = "foglalas")
@XmlRootElement
public class Foglalas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foglalas_id")
    private Integer foglalasId;
    @Basic(optional = false)
    @Column(name = "foglalas_kezdete")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date foglalasKezdete;
    @Basic(optional = false)
    @Column(name = "foglalas_vege")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date foglalasVege;
    @Basic(optional = false)
    @Column(name = "fizetesi_mod")
    private String fizetesiMod;

    public Foglalas() {
    }

    public Foglalas(Integer foglalasId) {
        this.foglalasId = foglalasId;
    }

    public Foglalas(Integer foglalasId, Date foglalasKezdete, Date foglalasVege, String fizetesiMod) {
        this.foglalasId = foglalasId;
        this.foglalasKezdete = foglalasKezdete;
        this.foglalasVege = foglalasVege;
        this.fizetesiMod = fizetesiMod;
    }

    public Integer getFoglalasId() {
        return foglalasId;
    }

    public void setFoglalasId(Integer foglalasId) {
        this.foglalasId = foglalasId;
    }

    public Date getFoglalasKezdete() {
        return foglalasKezdete;
    }

    public void setFoglalasKezdete(Date foglalasKezdete) {
        this.foglalasKezdete = foglalasKezdete;
    }

    public Date getFoglalasVege() {
        return foglalasVege;
    }

    public void setFoglalasVege(Date foglalasVege) {
        this.foglalasVege = foglalasVege;
    }

    public String getFizetesiMod() {
        return fizetesiMod;
    }

    public void setFizetesiMod(String fizetesiMod) {
        this.fizetesiMod = fizetesiMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foglalasId != null ? foglalasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foglalas)) {
            return false;
        }
        Foglalas other = (Foglalas) object;
        if ((this.foglalasId == null && other.foglalasId != null) || (this.foglalasId != null && !this.foglalasId.equals(other.foglalasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.Foglalas[ foglalasId=" + foglalasId + " ]";
    }

    public boolean isNotEmpty() {
        return (this.fizetesiMod != null && this.foglalasKezdete != null && this.foglalasVege != null);
    }
    
}
