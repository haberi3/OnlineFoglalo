
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.AutoTipusAdatok;
import HelixLab.Szoftverfejlesztes.Model.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class AutoTipusAdatokRepository {
    
    public Boolean ujAutoTipusAdatHozzaadasa(AutoTipusAdatok autoTipusAdatok){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewCarSpecificDetails");
            
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tipusIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ulesek_szamaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ajtok_szamaIN", Short.class, ParameterMode.IN);
            
            spq.setParameter("markaIN", autoTipusAdatok.getMarka());
            spq.setParameter("tipusIN", autoTipusAdatok.getTipus());
            spq.setParameter("ulesek_szamaIN", autoTipusAdatok.getUlesekSzama());
            spq.setParameter("ajtok_szamaIN", autoTipusAdatok.getAjtokSzama());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Integer utolsoBeillesztettAutoTipusId(){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastCarSpecificDetailsId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public AutoTipusAdatok autoTipusAdatokIdAlapjan(Integer id){
       AutoTipusAdatok uj = new AutoTipusAdatok();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(AutoTipusAdatok.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public AutoTipusAdatok autoTipusAdatokFrissitese(AutoTipusAdatok autoTipusAdatok){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCarSpecificDetails");
            
            spq.registerStoredProcedureParameter("markaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tipusIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ulesek_szamaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("ajtok_szamaIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_tipus_adatok_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("markaIN", autoTipusAdatok.getMarka());
            spq.setParameter("tipusIN", autoTipusAdatok.getTipus());
            spq.setParameter("ulesek_szamaIN", autoTipusAdatok.getUlesekSzama());
            spq.setParameter("ajtok_szamaIN", autoTipusAdatok.getAjtokSzama());
            spq.setParameter("auto_tipus_adatok_idIN", autoTipusAdatok.getAutoTipusAdatokId());
            spq.execute();
            return autoTipusAdatok;
        }
        catch(Exception ex){
            return autoTipusAdatok;
        }
    }
    
    public boolean autoTipusAdatokTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCarSpecificDetailsById");

            spq.registerStoredProcedureParameter("auto_tipus_adatok_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("auto_tipus_adatok_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
