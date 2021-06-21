
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.AutoHirdetes;
import HelixLab.Szoftverfejlesztes.Model.Database;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public class AutoHirdetesRepository {
    
    public boolean ujAutoHirdetese(AutoHirdetes autoHirdetes){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewCarAd");
            
            spq.registerStoredProcedureParameter("kaucioIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("egysegarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("klimaIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("absIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_hirdetes_leirasIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_tipus_adatok_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_muszaki_adatok_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("kaucioIN", autoHirdetes.getKaucio());
            spq.setParameter("egysegarIN", autoHirdetes.getEgysegar());
            spq.setParameter("klimaIN", autoHirdetes.getKlima());
            spq.setParameter("absIN", autoHirdetes.getAbs());
            spq.setParameter("auto_hirdetes_leirasIN", autoHirdetes.getAutoHirdetesLeiras());
            spq.setParameter("auto_tipus_adatok_idIN", autoHirdetes.getAutoTipusAdatokId());
            spq.setParameter("auto_muszaki_adatok_idIN", autoHirdetes.getAutoMuszakiAdatokId());
            spq.setParameter("elhelyezkedes_idIN", autoHirdetes.getElhelyezkedesId());
            spq.setParameter("felhasznalo_idIN", autoHirdetes.getFelhasznaloId());
        
            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public JSONArray autohirdetesekKilistazasa(){
        JSONArray autoAdatokLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCarDetails");
            
            List<Object[]> details = spq.getResultList();
            details.stream().map((detail) -> {
                JSONObject obj = new JSONObject();
                obj.put("result",detail);
                return obj;
            }).forEachOrdered((obj) -> {
                autoAdatokLista.put(obj);
            });
            return autoAdatokLista;
        }
        catch(Exception ex){
            return autoAdatokLista;
        }
    }
    
    public JSONArray autohirdetesekKilistazasaMarkaSzerint(String marka){
        JSONArray autoLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCarByBrand");
            
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.setParameter("markaIN", marka);
            spq.execute();
            
            List<Object[]> details = spq.getResultList();
            details.stream().map((detail) -> {
                JSONObject obj = new JSONObject();
                obj.put("result",detail);
                return obj;
            }).forEachOrdered((obj) -> {
                autoLista.put(obj);
            });
            return autoLista;
        }
        catch(Exception ex){
            return autoLista;
        }
    }
    
    public JSONArray autohirdetesekArszerintRendezve(){
        JSONArray autoLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCarOrderByPrice");
        
            List<Object[]> details = spq.getResultList();
            details.stream().map((detail) -> {
                JSONObject obj = new JSONObject();
                obj.put("result",detail);
                return obj;
            }).forEachOrdered((obj) -> {
                autoLista.put(obj);
            });
            return autoLista;
        }
        catch(Exception ex){
            return autoLista;
        }
    }
    
    public boolean autoHirdetesIdkBeallitasa(Integer muszakiId, Integer tipusId, Integer elhelyezkedesId, Integer id){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("carAdIdsUpdate");
            
            spq.registerStoredProcedureParameter("auto_muszaki_adatok_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_tipus_adatok_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_hirdetes_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("auto_muszaki_adatok_idIN", muszakiId);
            spq.setParameter("auto_tipus_adatok_idIN", tipusId);
            spq.setParameter("elhelyezkedes_idIN", elhelyezkedesId);
            spq.setParameter("auto_hirdetes_idIN", id);
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Integer utolsoBeillesztettAutoHirdetesId(){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastCarAdId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public AutoHirdetes autoHirdetesIdAlapjan(Integer id){
       AutoHirdetes uj = new AutoHirdetes();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(AutoHirdetes.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public AutoHirdetes autoHirdetesFrissitese(AutoHirdetes autoHirdetes){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCarAd");
            
            spq.registerStoredProcedureParameter("kaucioIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("egysegarIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("klimaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("absIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_hirdetes_leirasIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_hirdetes_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("kaucioIN", autoHirdetes.getKaucio());
            spq.setParameter("egysegarIN", autoHirdetes.getEgysegar());
            spq.setParameter("klimaIN", autoHirdetes.getKlima());
            spq.setParameter("absIN", autoHirdetes.getAbs());
            spq.setParameter("auto_hirdetes_leirasIN", autoHirdetes.getAutoHirdetesLeiras());
            spq.setParameter("auto_hirdetes_idIN", autoHirdetes.getAutoHirdetesId());
            spq.execute();
            return autoHirdetes;
        }
        catch(Exception ex){
            return autoHirdetes;
        }
    }
    
    public boolean autoHirdetesTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCarAdById");

            spq.registerStoredProcedureParameter("auto_hirdetes_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("auto_hirdetes_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
