
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.Elhelyezkedes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ElhelyezkedesRepository {
    public boolean ujElhelyezkedesHozzaadasa(Elhelyezkedes elhelyezkedes){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewLocation");
            
            spq.registerStoredProcedureParameter("IranyitoszamIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("VarosIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("UtcaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("HazszamIN", Short.class, ParameterMode.IN);
            
            spq.setParameter("IranyitoszamIN", elhelyezkedes.getIranyitoszam());
            spq.setParameter("VarosIN", elhelyezkedes.getVaros());
            spq.setParameter("UtcaIN", elhelyezkedes.getUtca());
            spq.setParameter("HazszamIN", elhelyezkedes.getHazszam());
            spq.execute();
            return true;
        }
        catch(Exception ex){
            ex.getMessage();
            return false;
        }
    }
    public Integer utolsoBeillesztettElhelyezkedesId(){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastLocationId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public Elhelyezkedes elhelyezkedesFrissitese(Elhelyezkedes elhelyezkedes){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateLocation");
            
            spq.registerStoredProcedureParameter("IranyitoszamIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("VarosIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("UtcaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("HazszamIN", Short.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("IranyitoszamIN", elhelyezkedes.getIranyitoszam());
            spq.setParameter("VarosIN", elhelyezkedes.getVaros());
            spq.setParameter("UtcaIN", elhelyezkedes.getUtca());
            spq.setParameter("HazszamIN", elhelyezkedes.getHazszam());
            spq.setParameter("elhelyezkedes_idIN", elhelyezkedes.getElhelyezkedesId());
            spq.execute();
            return elhelyezkedes;
        }
        catch(Exception ex){
            return elhelyezkedes;
        }
    }
    
    public Elhelyezkedes elhelyezkedesIdAlapjan(Integer id){
       Elhelyezkedes uj = new Elhelyezkedes();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(Elhelyezkedes.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public boolean elhelyezkedesTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteLocationById");

            spq.registerStoredProcedureParameter("elhelyezkedes_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("elhelyezkedes_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
