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
@Table(name = "etterem_hirdetes")
@XmlRootElement
public class EtteremHirdetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "etterem_hirdetes_id")
    private Integer etteremHirdetesId;
    @Basic(optional = false)
    @Column(name = "etterem_nev")
    private String etteremNev;
    @Basic(optional = false)
    @Column(name = "etterem_tipus")
    private String etteremTipus;
    @Basic(optional = false)
    @Column(name = "kiszolgalas_tipus")
    private String kiszolgalasTipus;
    @Basic(optional = false)
    @Column(name = "ertekeles")
    private double ertekeles;
    @Basic(optional = false)
    @Column(name = "menu")
    private String menu;
    @Basic(optional = false)
    @Column(name = "nyitvatartas_kezdete")
    private String nyitvatartasKezdete;
    @Basic(optional = false)
    @Column(name = "nyitvatartas_vege")
    private String nyitvatartasVege;
    @Basic(optional = false)
    @Lob
    @Column(name = "etterem_hirdetes_leiras")
    private String etteremHirdetesLeiras;
    @Basic(optional = false)
    @Column(name = "elerhetosegek_id")
    private int elerhetosegekId;
    @Basic(optional = false)
    @Column(name = "elhelyezkedes_id")
    private int elhelyezkedesId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private int felhasznaloId;

    public EtteremHirdetes() {
    }

    public EtteremHirdetes(Integer etteremHirdetesId) {
        this.etteremHirdetesId = etteremHirdetesId;
    }

    public EtteremHirdetes(Integer etteremHirdetesId, String etteremNev, String etteremTipus, String kiszolgalasTipus, double ertekeles, String menu, String nyitvatartasKezdete, String nyitvatartasVege, String etteremHirdetesLeiras, int elerhetosegekId, int elhelyezkedesId, int felhasznaloId) {
        this.etteremHirdetesId = etteremHirdetesId;
        this.etteremNev = etteremNev;
        this.etteremTipus = etteremTipus;
        this.kiszolgalasTipus = kiszolgalasTipus;
        this.ertekeles = ertekeles;
        this.menu = menu;
        this.nyitvatartasKezdete = nyitvatartasKezdete;
        this.nyitvatartasVege = nyitvatartasVege;
        this.etteremHirdetesLeiras = etteremHirdetesLeiras;
        this.elerhetosegekId = elerhetosegekId;
        this.elhelyezkedesId = elhelyezkedesId;
        this.felhasznaloId = felhasznaloId;
    }

    public Integer getEtteremHirdetesId() {
        return etteremHirdetesId;
    }

    public void setEtteremHirdetesId(Integer etteremHirdetesId) {
        this.etteremHirdetesId = etteremHirdetesId;
    }

    public String getEtteremNev() {
        return etteremNev;
    }

    public void setEtteremNev(String etteremNev) {
        this.etteremNev = etteremNev;
    }

    public String getEtteremTipus() {
        return etteremTipus;
    }

    public void setEtteremTipus(String etteremTipus) {
        this.etteremTipus = etteremTipus;
    }

    public String getKiszolgalasTipus() {
        return kiszolgalasTipus;
    }

    public void setKiszolgalasTipus(String kiszolgalasTipus) {
        this.kiszolgalasTipus = kiszolgalasTipus;
    }

    public double getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(double ertekeles) {
        this.ertekeles = ertekeles;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getNyitvatartasKezdete() {
        return nyitvatartasKezdete;
    }

    public void setNyitvatartasKezdete(String nyitvatartasKezdete) {
        this.nyitvatartasKezdete = nyitvatartasKezdete;
    }

    public String getNyitvatartasVege() {
        return nyitvatartasVege;
    }

    public void setNyitvatartasVege(String nyitvatartasVege) {
        this.nyitvatartasVege = nyitvatartasVege;
    }

    public String getEtteremHirdetesLeiras() {
        return etteremHirdetesLeiras;
    }

    public void setEtteremHirdetesLeiras(String etteremHirdetesLeiras) {
        this.etteremHirdetesLeiras = etteremHirdetesLeiras;
    }

    public int getElerhetosegekId() {
        return elerhetosegekId;
    }

    public void setElerhetosegekId(int elerhetosegekId) {
        this.elerhetosegekId = elerhetosegekId;
    }

    public int getElhelyezkedesId() {
        return elhelyezkedesId;
    }

    public void setElhelyezkedesId(int elhelyezkedesId) {
        this.elhelyezkedesId = elhelyezkedesId;
    }

    public int getFelhasznaloId() {
        return felhasznaloId;
    }

    public void setFelhasznaloId(int felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etteremHirdetesId != null ? etteremHirdetesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtteremHirdetes)) {
            return false;
        }
        EtteremHirdetes other = (EtteremHirdetes) object;
        if ((this.etteremHirdetesId == null && other.etteremHirdetesId != null) || (this.etteremHirdetesId != null && !this.etteremHirdetesId.equals(other.etteremHirdetesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etteremHirdetesId=" + etteremHirdetesId + " elerhetosegId= " + this.elerhetosegekId + " elhelyezkedesId = " + this.elhelyezkedesId
                + " felhasznaloId = " + this.felhasznaloId + "]";
    }

    public boolean isNotEmpty() {
        return (this.etteremHirdetesLeiras != null && this.etteremNev != null && this.etteremTipus != null && this.kiszolgalasTipus != null
                && this.menu != null && this.nyitvatartasKezdete != null && this.nyitvatartasVege != null);
    }
    
}
