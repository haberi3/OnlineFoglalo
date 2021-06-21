
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.Foglalas;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class FoglalasRepository {
    
    public boolean ujFoglalasHozzaadasa(Foglalas foglalas){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewAppointment");

            spq.registerStoredProcedureParameter("foglalas_kezdeteIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foglalas_vegeIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetesi_modIN", String.class, ParameterMode.IN);
            
            spq.setParameter("foglalas_kezdeteIN", foglalas.getFoglalasKezdete());
            spq.setParameter("foglalas_vegeIN", foglalas.getFoglalasVege());
            spq.setParameter("fizetesi_modIN", foglalas.getFizetesiMod());
            
            spq.execute();
            return true;
            
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Integer utolsoBeillesztettFoglalasId(){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastAppointmentId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
     public Foglalas foglalasIdAlapjan(Integer id){
       Foglalas uj = new Foglalas();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(Foglalas.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public Foglalas foglalasFrissitese(Foglalas foglalas){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateAppointment");
            
            spq.registerStoredProcedureParameter("foglalas_kezdeteIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foglalas_vegeIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("fizetesi_modIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);
            
            
            spq.setParameter("foglalas_kezdeteIN", foglalas.getFoglalasKezdete());
            spq.setParameter("foglalas_vegeIN", foglalas.getFoglalasVege());
            spq.setParameter("fizetesi_modIN", foglalas.getFizetesiMod());
            spq.setParameter("foglalas_idIN", foglalas.getFoglalasId());
            
            spq.execute();
            return foglalas;
        }
        catch(Exception ex){
            return foglalas;
        }
    }
    
    public boolean foglalasTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteAppointment");

            spq.registerStoredProcedureParameter("foglalas_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("foglalas_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
