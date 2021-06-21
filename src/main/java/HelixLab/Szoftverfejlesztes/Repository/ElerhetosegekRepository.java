
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.Elerhetosegek;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ElerhetosegekRepository {
    public boolean ujRegisztraciosElerhetosegHozzaadasa(String email){
        try{
            if(emailSzabade(email)){
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addOnlyEmailToContact");

            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.setParameter("emailIN", email);
            spq.execute();
            return true;
            }
            else{
                return false;
            }
        }
        catch(Exception ex){
            ex.getMessage();
            return false;
        }
    }
            
    private boolean emailSzabade(String email){
        try{
            EntityManager em = Database.getDBConn(); 
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getEmailCount");
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.setParameter("emailIN", email);
            spq.execute();
            List<Object[]> resultlist = spq.getResultList();
            Integer result = Integer.parseInt(resultlist.get(0)[0].toString());
            if(result == 0){
                return true;
            }
            else{
                System.out.println("Az email cím már foglalt");
                return false;
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public Integer elerhetosegIdMeghatarozasaEmailAlapjan(String email){
        Integer emailId = 0;
        try{
            EntityManager em = Database.getDBConn();
  
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getEmailIdByEmailAdress");
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.setParameter("emailIN", email);
            spq.execute();
            List<Object[]> eredmeny = spq.getResultList();
            emailId = Integer.parseInt(eredmeny.get(0)[0].toString());
            return emailId;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return emailId;
        }
    }
    
    public boolean ujEtteremElerhetosegHozzaadasa(String telefon, String email){
        try{
            if(emailSzabade(email)){
                EntityManager em = Database.getDBConn();

                StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewContact");

                spq.registerStoredProcedureParameter("telefonIN", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);

                spq.setParameter("telefonIN", telefon);
                spq.setParameter("emailIN", email);

                spq.execute();
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public Elerhetosegek elerhetosegekFrissitese(Elerhetosegek elerhetosegek){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateContact");
            
            spq.registerStoredProcedureParameter("vezeteknevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("keresztnevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("telefonIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("Elerhetosegek_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("vezeteknevIN", elerhetosegek.getVezeteknev());
            spq.setParameter("keresztnevIN", elerhetosegek.getKeresztnev());
            spq.setParameter("telefonIN", elerhetosegek.getTelefon());
            spq.setParameter("Elerhetosegek_idIN", elerhetosegek.getElerhetosegekid());
            spq.execute();
            return elerhetosegek;
        }
        catch(Exception ex){
            return elerhetosegek;
        }
    }
    
    public Elerhetosegek elerhetosegekIdAlapjan(Integer id){
       Elerhetosegek uj = new Elerhetosegek();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(Elerhetosegek.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    
    public boolean elerhetosegekTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteContactById");

            spq.registerStoredProcedureParameter("Elerhetosegek_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("Elerhetosegek_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
