
package HelixLab.Szoftverfejlesztes.Repository;

import HelixLab.Szoftverfejlesztes.Model.Database;
import HelixLab.Szoftverfejlesztes.Model.Felhasznalo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class FelhasznaloRepository {
    
    public String ujFelhasznaloHozzaadas(String felhasznalonev, String jelszo, Integer elerhetosegId){
        try{
            EntityManager em = Database.getDBConn();

            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewUser");

            spq.registerStoredProcedureParameter("felhasznalonevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("elerhetosegek_idIN", Integer.class, ParameterMode.IN);

            spq.setParameter("felhasznalonevIN", felhasznalonev);
            spq.setParameter("jelszoIN", jelszo);
            spq.setParameter("elerhetosegek_idIN", elerhetosegId);

            spq.execute();
            return "A felhasznaló hozzáadása sikeres volt!";
        }
        catch(Exception ex){
            ex.getMessage();
            return "Sajnos nem sikerült a felhasználó hozzáadása!";
        }
    }
    
    public Felhasznalo bejelentkezes(String email, String jelszo){
        Felhasznalo felhasznalo = new Felhasznalo();
        try{
            EntityManager em = Database.getDBConn();
       
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userLogin");

            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoIN", String.class, ParameterMode.IN);

            spq.setParameter("emailIN", email);
            spq.setParameter("jelszoIN", jelszo);
            spq.execute();
            
            List<Object[]> resultlist = spq.getResultList();
            Integer id = Integer.parseInt(resultlist.get(0)[0].toString());
            felhasznalo = em.find(Felhasznalo.class, id);
            return felhasznalo;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return felhasznalo;
        }
    }
  
    public boolean felhasznaloiTorles(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userSelfDelete");

            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("felhasznalo_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public Felhasznalo felhasznaloIdAlapjan(Integer id){
       Felhasznalo uj = new Felhasznalo();
        try{
            EntityManager em = Database.getDBConn();
            uj = em.find(Felhasznalo.class, id);
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
        }
        finally{
            return uj;
        }
    }
    public Felhasznalo felhasznaloFrissitese(Felhasznalo felhasznalo){
        try{
            EntityManager em = Database.getDBConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateUser");
            
            spq.registerStoredProcedureParameter("FelhasznalonevIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("jelszoIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("profilkepIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("FelhasznalonevIN", felhasznalo.getFelhasznalonev());
            spq.setParameter("jelszoIN", felhasznalo.getJelszo());
            spq.setParameter("profilkepIN", felhasznalo.getProfilkep());
            spq.setParameter("felhasznalo_idIN", felhasznalo.getFelhasznaloId());
            spq.execute();
            return felhasznalo;
        }
        catch(Exception ex){
            return felhasznalo;
        }
    }
    
    
    /*
    Nem viszi át az adatbázisba a változtatásokat valamiért
    public Felhasznalo felhasznaloFrissitese(Felhasznalo felhasznalo){
        Felhasznalo felhasznalo2 = felhasznaloIdAlapjan(felhasznalo.getFelhasznaloId());
        System.out.println(felhasznalo2.toString());
        try{
            EntityManager em = Database.getDBConn();
            em.getTransaction().begin();
            felhasznalo2.setElerhetosegekId(felhasznalo.getElerhetosegekId());
            felhasznalo2.setFelhasznalonev(felhasznalo.getFelhasznalonev());
            felhasznalo2.setJelszo(felhasznalo.getJelszo());
            felhasznalo2.setProfilkep(felhasznalo.getProfilkep());
            felhasznalo2.setFelhasznaloStatus(felhasznalo.getFelhasznaloStatus());
            em.getTransaction().commit();
            em.close();
            System.out.println(felhasznalo2.toString());
            return felhasznalo2;
        }
        catch(Exception ex){
            System.out.println("Hibás adatbázis kapcsolat");
            return felhasznalo2;
        }
    }
    */
    
    public boolean adminFelhasznaloTorlese(Integer id){
        try{
            EntityManager em = Database.getDBConn();
        
            StoredProcedureQuery spq = em.createStoredProcedureQuery("userLogDelete");

            spq.registerStoredProcedureParameter("felhasznalo_idIN", Integer.class, ParameterMode.IN);
            spq.setParameter("felhasznalo_idIN", id);
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
