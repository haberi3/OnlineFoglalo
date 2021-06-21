
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.EtteremFoglalas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public class EtteremFoglalasRepository {
    
    public boolean ujEtteremFoglalasHozzaadasa(EtteremFoglalas etteremFoglalas){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewRestaurantReservation");

            spq.registerStoredProcedureParameter("lefoglalt_helyek_szamaIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_hirdetes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("lefoglalt_helyek_szamaIN", etteremFoglalas.getLefoglaltHelyekSzama());
            spq.setParameter("etterem_hirdetes_idIN", etteremFoglalas.getEtteremHirdetesId());
            spq.setParameter("felhasznalo_idIN", etteremFoglalas.getFelhasznaloId());
            spq.setParameter("foglalas_idIN", etteremFoglalas.getFoglalasId());

            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public EtteremFoglalas etteremFoglalasIdAlapjan(Integer id){
       EtteremFoglalas uj = new EtteremFoglalas();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(EtteremFoglalas.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public Integer utolsoBeillesztettEtteremFoglalasId(){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastRestaurantReservationId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public boolean etteremFoglalasAdatokBeallitasa(Integer foglalasId, Integer id) {
        try {
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateRestaurantReservationIds");

            spq.registerStoredProcedureParameter("foglalas_idIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_foglalas_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("foglalas_idIN", foglalasId);
            spq.setParameter("etterem_foglalas_idIN", id);

            spq.execute();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public EtteremFoglalas etteremFoglalasFrissitese(EtteremFoglalas etteremFoglalas){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateRestaurantReservation");
            
            spq.registerStoredProcedureParameter("lefoglalt_helyek_szamaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_hirdetes_idIN", Integer.class, ParameterMode.IN);
                     
            spq.setParameter("lefoglalt_helyek_szamaIN", etteremFoglalas.getLefoglaltHelyekSzama());
            spq.setParameter("etterem_hirdetes_idIN", etteremFoglalas.getEtteremFoglalasId());
            
            spq.execute();
            return etteremFoglalas;
        }
        catch(Exception ex){
            return etteremFoglalas;
        }
    }
    
    public boolean etteremFoglalasTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteRestaurantReservationById");

            spq.registerStoredProcedureParameter("etterem_foglalas_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("etterem_foglalas_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public JSONArray etteremFoglalasKilistazasaIdAltal(Integer id){
        JSONArray etteremLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getRestaurantReservationDetailsById");
            
            List<Object[]> details = spq.getResultList();
            details.stream().map((detail) -> {
                JSONObject obj = new JSONObject();
                obj.put("result",detail);
                return obj;
            }).forEachOrdered((obj) -> {
                etteremLista.put(obj);
            });
            return etteremLista;
        }
        catch(Exception ex){
            return etteremLista;
        }
    }
}
