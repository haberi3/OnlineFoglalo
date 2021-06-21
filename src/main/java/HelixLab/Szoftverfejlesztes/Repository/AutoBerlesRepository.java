package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.AutoBerles;
import HelixLab.Szoftverfejlesztes.Model.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Repository;

@Repository
public class AutoBerlesRepository {

    public boolean ujAutoBerlesHozzaadasa(AutoBerles autoBerles) {
        try {
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewCarRent");

            spq.registerStoredProcedureParameter("megtett_kmIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_karIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetendo_osszegIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_hirdetes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("megtett_kmIN", autoBerles.getMegtettKm());
            spq.setParameter("auto_karIN", autoBerles.getAutoKar());
            spq.setParameter("fizetendo_osszegIN", autoBerles.getFizetendoOsszeg());
            spq.setParameter("auto_hirdetes_idIN", autoBerles.getAutoHirdetesId());
            spq.setParameter("foglalas_idIN", autoBerles.getFoglalasId());
            spq.setParameter("felhasznalo_idIN", autoBerles.getFelhasznaloId());

            spq.execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Integer utolsoBeillesztettAutoBerlesId() {
        try {
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastCarRentId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        } catch (Exception ex) {
            return 0;
        }
    }

    public boolean autoBerlesAdatokBeallitasa(Integer foglalasId, Integer fizetendo, Integer id) {
        try {
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCarRentIdsAndPayment");

            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetendo_osszegIN", Long.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_berles_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("foglalas_idIN", foglalasId);
            spq.setParameter("fizetendo_osszegIN", fizetendo);  
            spq.setParameter("auto_berles_idIN", id);

            spq.execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public AutoBerles autoBerlesIdAlapjan(Integer id){
       AutoBerles uj = new AutoBerles();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(AutoBerles.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public AutoBerles autoBerlesFrissitese(AutoBerles autoBerles){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCarRent");
            
            spq.registerStoredProcedureParameter("megtett_kmIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_karIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetendo_osszegIN", Long.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_berles_idIN", Integer.class, ParameterMode.IN);
            
            
            spq.setParameter("megtett_kmIN", autoBerles.getMegtettKm());
            spq.setParameter("auto_karIN", autoBerles.getAutoKar());
            spq.setParameter("fizetendo_osszegIN", autoBerles.getFizetendoOsszeg());
            spq.setParameter("auto_berles_idIN", autoBerles.getAutoBerlesId());
            
            spq.execute();
            return autoBerles;
        }
        catch(Exception ex){
            return autoBerles;
        }
    }
    
    public boolean autoBerlesTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCarRent");

            spq.registerStoredProcedureParameter("auto_berles_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("auto_berles_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public JSONArray autoBerlesAdatokKilistazasaIdSzerint(Integer id){
        JSONArray autoLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllCarRentDetailsById");
            
            spq.registerStoredProcedureParameter("auto_berles_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("auto_berles_idIN", id);
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
}
