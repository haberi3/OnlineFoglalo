
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.EtteremHirdetes;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;

@Repository
public class EtteremHirdetesRepository {
    
    public boolean ujEtteremHirdetesHozzaadasa(EtteremHirdetes etteremHirdetes){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewRestaurantAd");

            spq.registerStoredProcedureParameter("etterem_nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_tipusIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("kiszolgalas_tipusIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ertekelesIN", Double.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("menuIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nyitvatartas_kezdeteIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nyitvatartas_vegeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_hirdetes_leirasIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elerhetosegek_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("etterem_nevIN", etteremHirdetes.getEtteremNev());
            spq.setParameter("etterem_tipusIN", etteremHirdetes.getEtteremTipus());
            spq.setParameter("kiszolgalas_tipusIN", etteremHirdetes.getKiszolgalasTipus());
            spq.setParameter("ertekelesIN", etteremHirdetes.getErtekeles());
            spq.setParameter("menuIN", etteremHirdetes.getMenu());
            spq.setParameter("nyitvatartas_kezdeteIN", etteremHirdetes.getNyitvatartasKezdete());
            spq.setParameter("nyitvatartas_vegeIN", etteremHirdetes.getNyitvatartasVege());
            spq.setParameter("etterem_hirdetes_leirasIN", etteremHirdetes.getEtteremHirdetesLeiras());
            spq.setParameter("elerhetosegek_idIN", etteremHirdetes.getElerhetosegekId());
            spq.setParameter("elhelyezkedes_idIN", etteremHirdetes.getElhelyezkedesId());
            spq.setParameter("felhasznalo_idIN", etteremHirdetes.getFelhasznaloId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    
    }
    
    public Integer utolsoBeillesztettEtteremId(){
       try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastRestaurantAdId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public boolean etteremHirdetesIdkBeallitasa(Integer elerhetosegId, Integer elhelyezkedesId, Integer id){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("restaurantAdIdsUpdate");
            
            spq.registerStoredProcedureParameter("elerhetosegek_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_hirdetes_idIN", Integer.class, ParameterMode.IN);
          
            spq.setParameter("elerhetosegek_idIN", elerhetosegId);
            spq.setParameter("elhelyezkedes_idIN", elhelyezkedesId);
            spq.setParameter("etterem_hirdetes_idIN", id);
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public EtteremHirdetes etteremHirdetesIdAlapjan(Integer id){
       EtteremHirdetes uj = new EtteremHirdetes();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(EtteremHirdetes.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public EtteremHirdetes etteremHirdetesFrissitese(EtteremHirdetes etteremHirdetes){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateRestaurantAd");
            
            spq.registerStoredProcedureParameter("etterem_nevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_tipusIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("kiszolgalas_tipusIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ertekelesIN", Double.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("menuIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nyitvatartas_kezdeteIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nyitvatartas_vegeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_hirdetes_leirasIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("etterem_hirdetes_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("etterem_nevIN", etteremHirdetes.getEtteremNev());
            spq.setParameter("etterem_tipusIN", etteremHirdetes.getEtteremTipus());
            spq.setParameter("kiszolgalas_tipusIN", etteremHirdetes.getKiszolgalasTipus());
            spq.setParameter("ertekelesIN", etteremHirdetes.getErtekeles());
            spq.setParameter("menuIN", etteremHirdetes.getMenu());
            spq.setParameter("nyitvatartas_kezdeteIN", etteremHirdetes.getNyitvatartasKezdete());
            spq.setParameter("nyitvatartas_vegeIN", etteremHirdetes.getNyitvatartasVege());
            spq.setParameter("etterem_hirdetes_leirasIN", etteremHirdetes.getEtteremHirdetesLeiras());
            spq.setParameter("etterem_hirdetes_idIN", etteremHirdetes.getEtteremHirdetesId());
            spq.execute();
            return etteremHirdetes;
        }
        catch(Exception ex){
            return etteremHirdetes;
        }
    }
    
    public boolean etteremHirdetesTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteRestaurantAdById");

            spq.registerStoredProcedureParameter("etterem_hirdetes_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("etterem_hirdetes_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public JSONArray etteremHirdetesekKilistazasa(){
        JSONArray etteremLista = new JSONArray();
        try{
            EntityManager em = Database.getDBConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRestaurant");
            
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
