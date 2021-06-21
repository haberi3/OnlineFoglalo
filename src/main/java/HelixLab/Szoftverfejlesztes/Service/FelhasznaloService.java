
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.Felhasznalo;
import HelixLab.Szoftverfejlesztes.Repository.FelhasznaloRepository;
import org.springframework.stereotype.Service;

@Service
public class FelhasznaloService {
     
    private final FelhasznaloRepository felhasznaloRepository;

    public FelhasznaloService(FelhasznaloRepository felhasznaloRepository) {
        this.felhasznaloRepository = felhasznaloRepository;
    }
    
    public String ujFelhasznaloHozzaadas(String felhasznalonev, String jelszo, Integer elerhetosegId){
        return felhasznaloRepository.ujFelhasznaloHozzaadas(felhasznalonev, jelszo, elerhetosegId);
    }
    
    
    public Felhasznalo felhasznaloFrissitese(Felhasznalo felhasznalo){
        return felhasznaloRepository.felhasznaloFrissitese(felhasznalo);
    }
    
    
    public Felhasznalo felhasznaloIdAlapjan(Integer id){
        return felhasznaloRepository.felhasznaloIdAlapjan(id);
    }
     
    public Felhasznalo bejelentkezes(String email, String jelszo){
        return felhasznaloRepository.bejelentkezes(email, jelszo);
    }
    
    public boolean felhasznaloiTorles(Integer id){
        return felhasznaloRepository.felhasznaloiTorles(id);
    }
    
    public boolean adminFelhasznaloTorlese(Integer id){
        return felhasznaloRepository.adminFelhasznaloTorlese(id);
    }
}
