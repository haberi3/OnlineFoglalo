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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Xalax
 */
@Entity
@Table(name = "auto_berles")
@XmlRootElement
public class AutoBerles implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auto_berles_id")
    private Integer autoBerlesId;
    @Basic(optional = false)
    @Column(name = "megtett_km")
    private int megtettKm;
    @Basic(optional = false)
    @Lob
    @Column(name = "auto_kar")
    private String autoKar;
    @Basic(optional = false)
    @Column(name = "fizetendo_osszeg")
    private long fizetendoOsszeg;
    @Basic(optional = false)
    @Column(name = "auto_hirdetes_id")
    private int autoHirdetesId;
    @Basic(optional = false)
    @Column(name = "foglalas_id")
    private int foglalasId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private int felhasznaloId;

    public AutoBerles() {
    }

    public AutoBerles(Integer autoBerlesId) {
        this.autoBerlesId = autoBerlesId;
    }

    public AutoBerles(Integer autoBerlesId, int megtettKm, String autoKar, long fizetendoOsszeg, int autoHirdetesId, int foglalasId, int felhasznaloId) {
        this.autoBerlesId = autoBerlesId;
        this.megtettKm = megtettKm;
        this.autoKar = autoKar;
        this.fizetendoOsszeg = fizetendoOsszeg;
        this.autoHirdetesId = autoHirdetesId;
        this.foglalasId = foglalasId;
        this.felhasznaloId = felhasznaloId;
    }

    public Integer getAutoBerlesId() {
        return autoBerlesId;
    }

    public void setAutoBerlesId(Integer autoBerlesId) {
        this.autoBerlesId = autoBerlesId;
    }

    public int getMegtettKm() {
        return megtettKm;
    }

    public void setMegtettKm(int megtettKm) {
        this.megtettKm = megtettKm;
    }

    public String getAutoKar() {
        return autoKar;
    }

    public void setAutoKar(String autoKar) {
        this.autoKar = autoKar;
    }

    public long getFizetendoOsszeg() {
        return fizetendoOsszeg;
    }

    public void setFizetendoOsszeg(long fizetendoOsszeg) {
        this.fizetendoOsszeg = fizetendoOsszeg;
    }

    public int getAutoHirdetesId() {
        return autoHirdetesId;
    }

    public void setAutoHirdetesId(int autoHirdetesId) {
        this.autoHirdetesId = autoHirdetesId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autoBerlesId != null ? autoBerlesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutoBerles)) {
            return false;
        }
        AutoBerles other = (AutoBerles) object;
        if ((this.autoBerlesId == null && other.autoBerlesId != null) || (this.autoBerlesId != null && !this.autoBerlesId.equals(other.autoBerlesId))) {
            return false;
        }
        return true;
    }
    
    public JSONObject toJson(){
        try{
            JSONObject obj = new JSONObject();
            obj.put("autoBerlesId", this.autoBerlesId);
            obj.put("autoHirdetesId", this.autoHirdetesId);
            obj.put("autoKar", this.autoKar);
            obj.put("felhasznaloId", this.felhasznaloId);
            obj.put("fizetendoOsszeg", this.fizetendoOsszeg);
            obj.put("foglalasId", this.foglalasId);
            obj.put("megtettKm", this.megtettKm);
            return obj;
        }
        catch(JSONException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "HelixLab.Szoftverfejlesztes.Model.AutoBerles[ autoBerlesId=" + autoBerlesId + " ]";
    }
    
    public boolean isNotEmpty() {
        return (this.autoHirdetesId != 0 && this.autoKar != null && this.felhasznaloId != 0 && this.fizetendoOsszeg != 0 && this.foglalasId != 0);
    }
}
