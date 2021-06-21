
package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.Elerhetosegek;
import HelixLab.Szoftverfejlesztes.Model.Felhasznalo;
import HelixLab.Szoftverfejlesztes.Service.ElerhetosegekService;
import HelixLab.Szoftverfejlesztes.Service.FelhasznaloService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FelhasznaloController {
    
    public final FelhasznaloService felhasznaloService;
    public final ElerhetosegekService elerhetosegekService;

    public FelhasznaloController(FelhasznaloService felhasznaloService, ElerhetosegekService elerhetosegekService) {
        this.felhasznaloService = felhasznaloService;
        this.elerhetosegekService = elerhetosegekService;
    }
    
    @PostMapping(value = "/felhasznalo/regisztracio")
    public void regisztracio(String email, String felhasznalonev, String jelszo){
        if(email != null && felhasznalonev != null && jelszo != null){
            if(elerhetosegekService.ujRegisztraciosElerhetosegHozzaadasa(email)){
                Integer elerhetosegId = elerhetosegekService.elerhetosegIdMeghatarozasaEmailAlapjan(email);
                felhasznaloService.ujFelhasznaloHozzaadas(felhasznalonev, jelszo, elerhetosegId);
            }
            else{
                System.out.println("A regisztáció sikertelen volt!");
            }
        }else{
            System.out.println("Minden mező kitöltése szükséges");
        }
    }
    
    
    @PostMapping(value="/felhasznalo/frissites")
    public void felhasznaloFrissitese(Felhasznalo felhasznalo, Elerhetosegek elerhetosegek){
        if(felhasznalo.isNotEmpty() && elerhetosegek.isNotEmpty()){
            if(elerhetosegekService.elerhetosegekIdAlapjan(felhasznalo.getElerhetosegekId()) != null){
                elerhetosegek.setElerhetosegekid(felhasznalo.getElerhetosegekId());
                felhasznaloService.felhasznaloFrissitese(felhasznalo);
                elerhetosegekService.elerhetosegekFrissitese(elerhetosegek);
                System.out.println("Az adatok frissítése sikeresen megtörtént!");
            }
            else{
                System.out.println("A megadott elérhetoseg nem létezik");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges");
        }
    }
    
    @PostMapping(value="/felhasznalo/bejelentkezes")
    public Felhasznalo bejelentkezes(String email, String jelszo){
        if(email != null && jelszo != null){
            if(felhasznaloService.bejelentkezes(email, jelszo).getFelhasznaloId() != null){
                System.out.println("Sikeres bejelentkezés!");
                return felhasznaloService.bejelentkezes(email, jelszo);
            }
            else{
                System.out.println("Az email cím vagy a jelszó rosszul lett maegadva!");
                return null;
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
            return null;
        }
    }
    
    @PostMapping(value="/felhasznalo/felhasznaloilogtorles")
    public void felhasznaloiTorles(Integer id){
        if(id != null){
            if(felhasznaloService.felhasznaloIdAlapjan(id) != null){
                felhasznaloService.felhasznaloiTorles(id);
                System.out.println("Sikeres törlés!");
            }
            else{
                System.out.println("Sikertelen törlés, mert ezzel az idval nem létezik felhasználó!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value="/felhasznalo/adminfelhasznaloilogtorles")
    public void adminfelhasznaloTorlese(Integer id){
        if(id != null){
            if(felhasznaloService.felhasznaloIdAlapjan(id) != null){
                felhasznaloService.adminFelhasznaloTorlese(id);
                System.out.println("Sikeres törlés!");
            }
            else{
                System.out.println("Sikertelen törlés, mert ezzel az idval nem létezik felhasználó!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
}
