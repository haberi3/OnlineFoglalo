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
@Table(name = "lakas_hirdetes")
@XmlRootElement
public class LakasHirdetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lakas_hirdetes_id")
    private Integer lakasHirdetesId;
    @Basic(optional = false)
    @Column(name = "lakas_nev")
    private String lakasNev;
    @Basic(optional = false)
    @Column(name = "alapterulet")
    private int alapterulet;
    @Basic(optional = false)
    @Column(name = "ertekeles")
    private double ertekeles;
    @Basic(optional = false)
    @Column(name = "ferohelyek_szama")
    private short ferohelyekSzama;
    @Basic(optional = false)
    @Column(name = "szobak_szama")
    private short szobakSzama;
    @Basic(optional = false)
    @Column(name = "mikro")
    private short mikro;
    @Basic(optional = false)
    @Column(name = "huto")
    private short huto;
    @Basic(optional = false)
    @Column(name = "ingyen_wifi")
    private short ingyenWifi;
    @Basic(optional = false)
    @Column(name = "minibar")
    private short minibar;
    @Basic(optional = false)
    @Column(name = "ingyen_parkolo")
    private short ingyenParkolo;
    @Basic(optional = false)
    @Column(name = "egysegar")
    private int egysegar;
    @Basic(optional = false)
    @Lob
    @Column(name = "lakas_hirdetes_leiras")
    private String lakasHirdetesLeiras;
    @Basic(optional = false)
    @Column(name = "elhelyezkedes_id")
    private int elhelyezkedesId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private int felhasznaloId;

    public LakasHirdetes() {
    }

    public LakasHirdetes(Integer lakasHirdetesId) {
        this.lakasHirdetesId = lakasHirdetesId;
    }

    public LakasHirdetes(Integer lakasHirdetesId, String lakasNev, int alapterulet, double ertekeles, short ferohelyekSzama, short szobakSzama, short mikro, short huto, short ingyenWifi, short minibar, short ingyenParkolo, int egysegar, String lakasHirdetesLeiras, int elhelyezkedesId, int felhasznaloId) {
        this.lakasHirdetesId = lakasHirdetesId;
        this.lakasNev = lakasNev;
        this.alapterulet = alapterulet;
        this.ertekeles = ertekeles;
        this.ferohelyekSzama = ferohelyekSzama;
        this.szobakSzama = szobakSzama;
        this.mikro = mikro;
        this.huto = huto;
        this.ingyenWifi = ingyenWifi;
        this.minibar = minibar;
        this.ingyenParkolo = ingyenParkolo;
        this.egysegar = egysegar;
        this.lakasHirdetesLeiras = lakasHirdetesLeiras;
        this.elhelyezkedesId = elhelyezkedesId;
        this.felhasznaloId = felhasznaloId;
    }

    public Integer getLakasHirdetesId() {
        return lakasHirdetesId;
    }

    public void setLakasHirdetesId(Integer lakasHirdetesId) {
        this.lakasHirdetesId = lakasHirdetesId;
    }

    public String getLakasNev() {
        return lakasNev;
    }

    public void setLakasNev(String lakasNev) {
        this.lakasNev = lakasNev;
    }

    public int getAlapterulet() {
        return alapterulet;
    }

    public void setAlapterulet(int alapterulet) {
        this.alapterulet = alapterulet;
    }

    public double getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(double ertekeles) {
        this.ertekeles = ertekeles;
    }

    public short getFerohelyekSzama() {
        return ferohelyekSzama;
    }

    public void setFerohelyekSzama(short ferohelyekSzama) {
        this.ferohelyekSzama = ferohelyekSzama;
    }

    public short getSzobakSzama() {
        return szobakSzama;
    }

    public void setSzobakSzama(short szobakSzama) {
        this.szobakSzama = szobakSzama;
    }

    public short getMikro() {
        return mikro;
    }

    public void setMikro(short mikro) {
        this.mikro = mikro;
    }

    public short getHuto() {
        return huto;
    }

    public void setHuto(short huto) {
        this.huto = huto;
    }

    public short getIngyenWifi() {
        return ingyenWifi;
    }

    public void setIngyenWifi(short ingyenWifi) {
        this.ingyenWifi = ingyenWifi;
    }

    public short getMinibar() {
        return minibar;
    }

    public void setMinibar(short minibar) {
        this.minibar = minibar;
    }

    public short getIngyenParkolo() {
        return ingyenParkolo;
    }

    public void setIngyenParkolo(short ingyenParkolo) {
        this.ingyenParkolo = ingyenParkolo;
    }

    public int getEgysegar() {
        return egysegar;
    }

    public void setEgysegar(int egysegar) {
        this.egysegar = egysegar;
    }

    public String getLakasHirdetesLeiras() {
        return lakasHirdetesLeiras;
    }

    public void setLakasHirdetesLeiras(String lakasHirdetesLeiras) {
        this.lakasHirdetesLeiras = lakasHirdetesLeiras;
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
        hash += (lakasHirdetesId != null ? lakasHirdetesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LakasHirdetes)) {
            return false;
        }
        LakasHirdetes other = (LakasHirdetes) object;
        if ((this.lakasHirdetesId == null && other.lakasHirdetesId != null) || (this.lakasHirdetesId != null && !this.lakasHirdetesId.equals(other.lakasHirdetesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.LakasHirdetes[ lakasHirdetesId=" + lakasHirdetesId + " ]";
    }
    
    public boolean isNotEmpty(){
        return (this.lakasHirdetesLeiras != null && this.lakasNev != null && this.alapterulet != 0 && this.egysegar != 0 && this.ferohelyekSzama != 0
                && this.szobakSzama != 0);
    }
}
