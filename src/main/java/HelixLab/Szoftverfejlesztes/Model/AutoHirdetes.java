
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
import org.json.JSONObject;
import org.json.JSONException;


@Entity
@Table(name = "auto_hirdetes")
@XmlRootElement
public class AutoHirdetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auto_hirdetes_id")
    private Integer autoHirdetesId;
    @Basic(optional = false)
    @Column(name = "kaucio")
    private int kaucio;
    @Basic(optional = false)
    @Column(name = "egysegar")
    private int egysegar;
    @Basic(optional = false)
    @Column(name = "klima")
    private short klima;
    @Basic(optional = false)
    @Column(name = "abs")
    private short abs;
    @Basic(optional = false)
    @Lob
    @Column(name = "auto_hirdetes_leiras")
    private String autoHirdetesLeiras;
    @Basic(optional = false)
    @Column(name = "auto_tipus_adatok_id")
    private int autoTipusAdatokId;
    @Basic(optional = false)
    @Column(name = "auto_muszaki_adatok_id")
    private int autoMuszakiAdatokId;
    @Basic(optional = false)
    @Column(name = "elhelyezkedes_id")
    private int elhelyezkedesId;
    @Basic(optional = false)
    @Column(name = "felhasznalo_id")
    private int felhasznaloId;

    public AutoHirdetes() {
    }

    public AutoHirdetes(Integer autoHirdetesId) {
        this.autoHirdetesId = autoHirdetesId;
    }

    public AutoHirdetes(Integer autoHirdetesId, int kaucio, int egysegar, short klima, short abs, String autoHirdetesLeiras, int autoTipusAdatokId, int autoMuszakiAdatokId, int elhelyezkedesId, int felhasznaloId) {
        this.autoHirdetesId = autoHirdetesId;
        this.kaucio = kaucio;
        this.egysegar = egysegar;
        this.klima = klima;
        this.abs = abs;
        this.autoHirdetesLeiras = autoHirdetesLeiras;
        this.autoTipusAdatokId = autoTipusAdatokId;
        this.autoMuszakiAdatokId = autoMuszakiAdatokId;
        this.elhelyezkedesId = elhelyezkedesId;
        this.felhasznaloId = felhasznaloId;
    }

    public Integer getAutoHirdetesId() {
        return autoHirdetesId;
    }

    public void setAutoHirdetesId(Integer autoHirdetesId) {
        this.autoHirdetesId = autoHirdetesId;
    }

    public int getKaucio() {
        return kaucio;
    }

    public void setKaucio(int kaucio) {
        this.kaucio = kaucio;
    }

    public int getEgysegar() {
        return egysegar;
    }

    public void setEgysegar(int egysegar) {
        this.egysegar = egysegar;
    }

    public short getKlima() {
        return klima;
    }

    public void setKlima(short klima) {
        this.klima = klima;
    }

    public short getAbs() {
        return abs;
    }

    public void setAbs(short abs) {
        this.abs = abs;
    }

    public String getAutoHirdetesLeiras() {
        return autoHirdetesLeiras;
    }

    public void setAutoHirdetesLeiras(String autoHirdetesLeiras) {
        this.autoHirdetesLeiras = autoHirdetesLeiras;
    }

    public int getAutoTipusAdatokId() {
        return autoTipusAdatokId;
    }

    public void setAutoTipusAdatokId(int autoTipusAdatokId) {
        this.autoTipusAdatokId = autoTipusAdatokId;
    }

    public int getAutoMuszakiAdatokId() {
        return autoMuszakiAdatokId;
    }

    public void setAutoMuszakiAdatokId(int autoMuszakiAdatokId) {
        this.autoMuszakiAdatokId = autoMuszakiAdatokId;
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
        hash += (autoHirdetesId != null ? autoHirdetesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutoHirdetes)) {
            return false;
        }
        AutoHirdetes other = (AutoHirdetes) object;
        if ((this.autoHirdetesId == null && other.autoHirdetesId != null) || (this.autoHirdetesId != null && !this.autoHirdetesId.equals(other.autoHirdetesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ autoHirdetesId=" + autoHirdetesId + ", kaució=" + this.kaucio + ", egységár=" + this.egysegar + ", felhasználóid = " + this.felhasznaloId + "]";
    }
    
    
    
    public JSONObject toJson(){
        try{
            JSONObject obj = new JSONObject();
            obj.put("autóhirdetésId", this.autoHirdetesId);
            obj.put("kaució", this.kaucio);
            obj.put("egységár", this.egysegar);
            obj.put("klíma", this.klima);
            obj.put("abs", this.abs);
            obj.put("autóHirdetésLeírás", this.autoHirdetesLeiras);
            obj.put("autóTípusadatokId", this.autoTipusAdatokId);
            obj.put("autóMűszakiadatokId", this.autoMuszakiAdatokId);
            obj.put("elhelyezkedésId", this.elhelyezkedesId);
            obj.put("felhasználóId", this.felhasznaloId);
            return obj;
        }
        catch(JSONException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public boolean isNotEmpty() {
        return (this.autoHirdetesLeiras != null && this.egysegar != 0 && this.kaucio != 0);
    }
}
