
package HelixLab.Szoftverfejlesztes.Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelixLab_Szoftverfejlesztes_jar_0.0.1-SNAPSHOTPU");
    public static EntityManager getDBConn(){
        EntityManager em = emf.createEntityManager();
        return em;
    }
}
