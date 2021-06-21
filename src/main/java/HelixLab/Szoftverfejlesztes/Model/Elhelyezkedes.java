/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelixLab.Szoftverfejlesztes.Model;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "elhelyezkedes")
@XmlRootElement
public class Elhelyezkedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "elhelyezkedes_id")
    private Integer elhelyezkedesId;
    @Basic(optional = false)
    @Column(name = "Iranyitoszam")
    private short iranyitoszam;
    @Basic(optional = false)
    @Column(name = "Varos")
    private String varos;
    @Basic(optional = false)
    @Column(name = "Utca")
    private String utca;
    @Basic(optional = false)
    @Column(name = "Hazszam")
    private short hazszam;

    public Elhelyezkedes() {
    }

    public Elhelyezkedes(Integer elhelyezkedesId) {
        this.elhelyezkedesId = elhelyezkedesId;
    }

    public Elhelyezkedes(Integer elhelyezkedesId, short iranyitoszam, String varos, String utca, short hazszam) {
        this.elhelyezkedesId = elhelyezkedesId;
        this.iranyitoszam = Objects.requireNonNull(iranyitoszam);
        this.varos = Objects.requireNonNull(varos);
        this.utca = Objects.requireNonNull(utca);
        this.hazszam = Objects.requireNonNull(hazszam);
    }

    public Integer getElhelyezkedesId() {
        return elhelyezkedesId;
    }

    public void setElhelyezkedesId(Integer elhelyezkedesId) {
        this.elhelyezkedesId = elhelyezkedesId;
    }

    public short getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(short iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getUtca() {
        return utca;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public short getHazszam() {
        return hazszam;
    }

    public void setHazszam(short hazszam) {
        this.hazszam = hazszam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elhelyezkedesId != null ? elhelyezkedesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elhelyezkedes)) {
            return false;
        }
        Elhelyezkedes other = (Elhelyezkedes) object;
        if ((this.elhelyezkedesId == null && other.elhelyezkedesId != null) || (this.elhelyezkedesId != null && !this.elhelyezkedesId.equals(other.elhelyezkedesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.Elhelyezkedes[ elhelyezkedesId=" + elhelyezkedesId + " ]";
    }

    public boolean isNotEmpty() {
       return (this.utca != null && this.varos != null && this.iranyitoszam != 0 && this.hazszam != 0);
    }
}
