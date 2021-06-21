package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.LakasHirdetes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public class LakasHirdetesRepository {

    public boolean ujLakasHirdetesHozzaadasa(LakasHirdetes lakasHirdetes) {
        try {
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewFlatAd");

            spq.registerStoredProcedureParameter("lakas_nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("alapteruletIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ertekelesIN", Double.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ferohelyek_szamaIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("szobak_szamaIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("mikroIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("hutoIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ingyen_wifiIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("minibarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ingyen_parkoloIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("egysegarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_hirdetes_leirasIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("lakas_nevIN", lakasHirdetes.getLakasNev());
            spq.setParameter("alapteruletIN", lakasHirdetes.getAlapterulet());
            spq.setParameter("ertekelesIN", lakasHirdetes.getErtekeles());
            spq.setParameter("ferohelyek_szamaIN", lakasHirdetes.getFerohelyekSzama());
            spq.setParameter("szobak_szamaIN", lakasHirdetes.getSzobakSzama());
            spq.setParameter("mikroIN", lakasHirdetes.getMikro());
            spq.setParameter("hutoIN", lakasHirdetes.getHuto());
            spq.setParameter("ingyen_wifiIN", lakasHirdetes.getIngyenWifi());
            spq.setParameter("minibarIN", lakasHirdetes.getMinibar());
            spq.setParameter("ingyen_parkoloIN", lakasHirdetes.getIngyenParkolo());
            spq.setParameter("egysegarIN", lakasHirdetes.getEgysegar());
            spq.setParameter("lakas_hirdetes_leirasIN", lakasHirdetes.getLakasHirdetesLeiras());
            spq.setParameter("elhelyezkedes_idIN", lakasHirdetes.getElhelyezkedesId());
            spq.setParameter("felhasznalo_idIN", lakasHirdetes.getFelhasznaloId());

            spq.execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Integer utolsoBeillesztettLakasHirdetesId() {
        try {
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastFlatAdId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        } catch (Exception ex) {
            return 0;
        }
    }

    public boolean lakasHirdetesIdkBeallitasa(Integer elhelyezkedesId, Integer id) {
        try {
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateFlatAdIds");

            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_hirdetes_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("elhelyezkedes_idIN", elhelyezkedesId);
            spq.setParameter("lakas_hirdetes_idIN", id);

            spq.execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public LakasHirdetes lakasHirdetesIdAlapjan(Integer id) throws NullPointerException {
        LakasHirdetes uj = new LakasHirdetes();
        try {
            EntityManager em = Database.getDBConn();
            uj = em.find(LakasHirdetes.class, id);
        } catch (Exception ex) {
            System.out.println("Hibás adatbázis kapcsolat");
        } finally {
            return uj;
        }
    }

    public LakasHirdetes lakasHirdetesFrissitese(LakasHirdetes lakasHirdetes) {
        try {
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateFlatAd");

            spq.registerStoredProcedureParameter("lakas_nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ertekelesIN", Double.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ferohelyek_szamaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("mikroIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("hutoIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ingyen_wifiIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("minibarIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ingyen_parkoloIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("egysegarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_hirdetes_leirasIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_hirdetes_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("lakas_nevIN", lakasHirdetes.getLakasNev());
            spq.setParameter("ertekelesIN", lakasHirdetes.getErtekeles());
            spq.setParameter("ferohelyek_szamaIN", lakasHirdetes.getFerohelyekSzama());
            spq.setParameter("mikroIN", lakasHirdetes.getMikro());
            spq.setParameter("hutoIN", lakasHirdetes.getHuto());
            spq.setParameter("ingyen_wifiIN", lakasHirdetes.getIngyenWifi());
            spq.setParameter("minibarIN", lakasHirdetes.getMinibar());
            spq.setParameter("ingyen_parkoloIN", lakasHirdetes.getIngyenParkolo());
            spq.setParameter("egysegarIN", lakasHirdetes.getEgysegar());
            spq.setParameter("lakas_hirdetes_leirasIN", lakasHirdetes.getLakasHirdetesLeiras());
            spq.setParameter("lakas_hirdetes_idIN", lakasHirdetes.getLakasHirdetesId());
            spq.execute();
            return lakasHirdetes;
        } catch (Exception ex) {
            return lakasHirdetes;
        }
    }
    
    public boolean lakasHirdetesTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteFlatAdById");

            spq.registerStoredProcedureParameter("lakas_hirdetes_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("lakas_hirdetes_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public JSONArray lakashirdetesekKilistazasa(){
        JSONArray lakasLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllFlat");
            
            List<Object[]> details = spq.getResultList();
            details.stream().map((detail) -> {
                JSONObject obj = new JSONObject();
                obj.put("result",detail);
                return obj;
            }).forEachOrdered((obj) -> {
                lakasLista.put(obj);
            });
            return lakasLista;
        }
        catch(Exception ex){
            return lakasLista;
        }
    }
}
