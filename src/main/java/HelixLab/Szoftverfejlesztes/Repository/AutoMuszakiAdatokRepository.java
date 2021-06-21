
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.AutoMuszakiAdatok;
import HelixLab.Szoftverfejlesztes.Model.Database;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class AutoMuszakiAdatokRepository {
    
    public Boolean ujAutoMuszakiAdatHozzaadasa(AutoMuszakiAdatok autoMuszakiAdatok){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewCarEngineeringDetails");

            spq.registerStoredProcedureParameter("valto_tipusaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("uzemanyagIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("hengerurtartalomIN", Integer.class, ParameterMode.IN);

            spq.setParameter("valto_tipusaIN", autoMuszakiAdatok.getValtoTipusa());
            spq.setParameter("uzemanyagIN", autoMuszakiAdatok.getUzemanyag());
            spq.setParameter("hengerurtartalomIN", autoMuszakiAdatok.getHengerurtartalom());

            spq.execute();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Integer utolsoBeillesztettAutoMuszakiAdatokId(){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("getLastCarEngineerDetailsId");

            List<Object[]> result = spq.getResultList();
            Integer id = Integer.parseInt(result.get(0)[0].toString());
            return id;

        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public AutoMuszakiAdatok autoMuszakiAdatokIdAlapjan(Integer id){
       AutoMuszakiAdatok uj = new AutoMuszakiAdatok();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(AutoMuszakiAdatok.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public AutoMuszakiAdatok autoMuszakiAdatokFrissitese(AutoMuszakiAdatok autoMuszakiAdatok){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateCarEngineeringDetails");
            
            spq.registerStoredProcedureParameter("valto_tipusaIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("uzemanyagIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("hengerurtartalomIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("auto_muszaki_adatok_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("valto_tipusaIN", autoMuszakiAdatok.getValtoTipusa());
            spq.setParameter("uzemanyagIN", autoMuszakiAdatok.getUzemanyag());
            spq.setParameter("hengerurtartalomIN", autoMuszakiAdatok.getHengerurtartalom());
            spq.setParameter("auto_muszaki_adatok_idIN", autoMuszakiAdatok.getAutoMuszakiAdatokId());
            spq.execute();
            return autoMuszakiAdatok;
        }
        catch(Exception ex){
            return autoMuszakiAdatok;
        }
    }
    
    public boolean autoMuszakiAdatokTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteCarEngineeringDetailsById");

            spq.registerStoredProcedureParameter("auto_muszaki_adatok_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("auto_muszaki_adatok_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
