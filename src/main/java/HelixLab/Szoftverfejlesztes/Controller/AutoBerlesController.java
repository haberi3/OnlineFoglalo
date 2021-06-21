
package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.AutoBerles;
import HelixLab.Szoftverfejlesztes.Model.AutoHirdetes;
import HelixLab.Szoftverfejlesztes.Model.Foglalas;
import HelixLab.Szoftverfejlesztes.Service.AutoBerlesService;
import HelixLab.Szoftverfejlesztes.Service.AutoHirdetesService;
import HelixLab.Szoftverfejlesztes.Service.FoglalasService;
import java.time.temporal.ChronoUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoBerlesController {
    private final AutoBerlesService autoBerlesService;
    private final FoglalasService foglalasService;
    private final AutoHirdetesService autoHirdetesService;

    public AutoBerlesController(AutoBerlesService autoBerlesService, FoglalasService foglalasService, AutoHirdetesService autoHirdetesService) {
        this.autoBerlesService = autoBerlesService;
        this.foglalasService = foglalasService;
        this.autoHirdetesService = autoHirdetesService;
    }
    
    @PostMapping(value="autoberles/ujautoberlese")
    public void ujAutoBerlesHozzaadasa(AutoBerles autoBerles, Foglalas foglalas){
        Integer foglalasId;
        Integer id;
        if(foglalas.isNotEmpty() && autoBerles.isNotEmpty()){
            if(autoBerlesService.ujAutoBerlesHozzaadasa(autoBerles) && foglalasService.ujFoglalasHozzaadasa(foglalas)){
                foglalasId = foglalasService.utolsoBeillesztettFoglalasId();
                id = autoBerlesService.utolsoBeillesztettAutoBerlesId();
                AutoBerles ab = autoBerlesService.autoBerlesIdAlapjan(id);
                Integer osszeg = autoBerlesVegosszege(ab.getAutoHirdetesId(), foglalasId);
                if(osszeg <= 0){
                    System.out.println("A végösszeg kiszámítása közben hiba lépett fel!");
                }
                else if(autoBerlesService.autoBerlesIdkBeallitasa(foglalasId, osszeg , id)){
                    System.out.println("A foglalás sikeresen megtörtént!");
                }
                else{
                    System.out.println("A foglalás adatait nem sikerült frissíteni!");
                }
            }
            else{
                System.out.println("A foglalás létrehozása sikertelen volt!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges a foglalás létrehozásához!");
        }
    }
    
    //Ez a függvény ebben a formában nem jó, de nincs ötletem hogy lehetne számolni a megkezdett napokat
    private Integer autoBerlesVegosszege(Integer autoHirdetesId,Integer foglalasId){
        if(autoHirdetesService.autoHirdetesIdAlapjan(autoHirdetesId) != null){
            AutoHirdetes ah = autoHirdetesService.autoHirdetesIdAlapjan(autoHirdetesId);
            if(foglalasService.foglalasIdalapjan(foglalasId) != null){
                Foglalas foglalas = foglalasService.foglalasIdalapjan(foglalasId);
                Long daysBetween = ChronoUnit.DAYS.between(foglalas.getFoglalasKezdete().toInstant(), foglalas.getFoglalasVege().toInstant());
                Integer days = daysBetween.intValue();
                Integer vegosszeg = ah.getKaucio() + (ah.getEgysegar()* days);
                return vegosszeg;
            }
            else{
                return -1;
            }
        }
        else{
            return -2;
        }
    }
    
    @PostMapping(value="/autoberles/autoberlesfrissites")
    public void autoBerlesFrissitese(AutoBerles autoBerles, Foglalas foglalas){
        if(autoBerles.isNotEmpty() && foglalas.isNotEmpty()){
            AutoBerles ab = autoBerlesService.autoBerlesIdAlapjan(autoBerles.getAutoBerlesId());
            if(ab.getAutoHirdetesId() == autoBerles.getAutoHirdetesId() && ab.getFelhasznaloId() == autoBerles.getFelhasznaloId() &&
                    ab.getFoglalasId() == autoBerles.getFoglalasId()){
                foglalas.setFoglalasId(autoBerles.getFoglalasId());
                
                Integer vegosszeg = autoBerlesVegosszege(autoBerles.getAutoHirdetesId(), foglalas.getFoglalasId());
                autoBerles.setFizetendoOsszeg(vegosszeg);
                
                autoBerlesService.autoBerlesFrissitese(autoBerles);
                foglalasService.foglalasFrissitese(foglalas);
                
                System.out.println("A frissítés sikeresen megtörtént!");
            }
            else{
                System.out.println("Az id-k nem lettek megfelelően megadva az azonosításhoz!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value = "/autoberles/autoberlestorlese")
    public void autoBerlesTorlese(Integer id) {
        if (id != null) {
            if(autoBerlesService.autoBerlesIdAlapjan(id) != null){
                AutoBerles ab = autoBerlesService.autoBerlesIdAlapjan(id);
                Foglalas foglalas = foglalasService.foglalasIdalapjan(ab.getFoglalasId());
                if(java.time.Instant.now().until(foglalas.getFoglalasKezdete().toInstant(), ChronoUnit.HOURS) >= 48){
                    foglalasService.foglalasTorlese(ab.getFoglalasId());
                    autoBerlesService.autoBerlesTorlese(id);
                    System.out.println("A hirdetés törlése sikeresen megtörtént!");
                }
                else{
                    System.out.println("A foglalást nem törölheti 48 órával a foglalás megkezdése előtt!");
                }
            }
            else{
                System.out.println("Nem található ezzel az id-val hirdetés!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @GetMapping(value = "/autoberles/autoberlesadatok")
    public void autoBerlesAdatokKilistazasaIdSzerint(Integer id){
        if(id != null){
            System.out.println(autoBerlesService.autoBerlesAdatokKilistazasaIdSzerint(id));
        }
        else{
            System.out.println("Kérem írja be milyen márka szerint szeretne keresni");
        }
    }
}
