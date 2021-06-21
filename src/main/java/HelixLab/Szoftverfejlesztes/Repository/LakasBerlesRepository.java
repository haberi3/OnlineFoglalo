
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.LakasBerles;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public class LakasBerlesRepository {
    
    public boolean ujLakasBerlesHozzaadasa(LakasBerles lakasBerles){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewFlatRent");

            spq.registerStoredProcedureParameter("lakas_karIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetendo_osszegIN", Long.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_hirdetes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lefoglalt_helyek_szamaIN", Short.class, ParameterMode.IN);

            spq.setParameter("lakas_karIN", lakasBerles.getLakasKar());
            spq.setParameter("fizetendo_osszegIN", lakasBerles.getFizetendoOsszeg());
            spq.setParameter("lakas_hirdetes_idIN", lakasBerles.getLakasHirdetesId());
            spq.setParameter("foglalas_idIN", lakasBerles.getFoglalasId());
            spq.setParameter("felhasznalo_idIN", lakasBerles.getFelhasznaloId());
            spq.setParameter("lefoglalt_helyek_szamaIN", lakasBerles.getLefoglaltHelyekSzama());

            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Integer utolsoBeillesztettLakasBerlesId() {
        try {
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastFlatrentId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        } catch (Exception ex) {
            return 0;
        }
    }
    
    public LakasBerles lakasBerlesIdAlapjan(Integer id){
       LakasBerles uj = new LakasBerles();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(LakasBerles.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public boolean lakasBerlesAdatokBeallitasa(Integer foglalasId, Integer fizetendo, Integer id){
        try {
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateFlatRentIds");

            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetendo_osszegIN", Long.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_berles_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("foglalas_idIN", foglalasId);
            spq.setParameter("fizetendo_osszegIN", fizetendo);  
            spq.setParameter("lakas_berles_idIN", id);

            spq.execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public LakasBerles lakasBerlesFrissitese(LakasBerles lakasBerles){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateFlatRent");
            
            spq.registerStoredProcedureParameter("lakas_karIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetendo_osszegIN", Long.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lefoglalt_helyek_szamaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lakas_berles_idIN", Integer.class, ParameterMode.IN);
            
            
            spq.setParameter("lakas_karIN", lakasBerles.getLakasKar());
            spq.setParameter("fizetendo_osszegIN", lakasBerles.getFizetendoOsszeg());
            spq.setParameter("lefoglalt_helyek_szamaIN", lakasBerles.getLefoglaltHelyekSzama());
            spq.setParameter("lakas_berles_idIN", lakasBerles.getLakasBerlesId());
            
            spq.execute();
            return lakasBerles;
        }
        catch(Exception ex){
            return lakasBerles;
        }
    }
    
    public boolean lakasBerlesTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteFlatRentById");

            spq.registerStoredProcedureParameter("lakas_berles_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("lakas_berles_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public JSONArray lakasBerlesIdAltaliKilistazasa(Integer id){
        JSONArray lakasLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllFlatRentDetailsById");
            spq.registerStoredProcedureParameter("lakas_berles_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("lakas_berles_idIN", id);
            spq.execute();
            
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
