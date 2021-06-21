package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.Foglalas;
import HelixLab.Szoftverfejlesztes.Model.LakasBerles;
import HelixLab.Szoftverfejlesztes.Model.LakasHirdetes;
import HelixLab.Szoftverfejlesztes.Service.FoglalasService;
import HelixLab.Szoftverfejlesztes.Service.LakasBerlesService;
import HelixLab.Szoftverfejlesztes.Service.LakasHirdetesService;
import java.time.temporal.ChronoUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LakasBerlesController {

    private final LakasBerlesService lakasBerlesService;
    private final FoglalasService foglalasService;
    private final LakasHirdetesService lakasHirdetesService;

    public LakasBerlesController(LakasBerlesService lakasBerlesService, FoglalasService foglalasService, LakasHirdetesService lakasHirdetesService) {
        this.lakasBerlesService = lakasBerlesService;
        this.foglalasService = foglalasService;
        this.lakasHirdetesService = lakasHirdetesService;
    }

    @PostMapping(value = "/lakasberles/ujlakasberles")
    public void ujLakasBerlesHozzaadasa(LakasBerles lakasBerles, Foglalas foglalas) {
        Integer foglalasId;
        Integer id;
        if (lakasBerles.isNotEmpty() && foglalas.isNotEmpty()) {
            LakasHirdetes lh = lakasHirdetesService.lakasHirdetesIdAlapjan(lakasBerles.getLakasHirdetesId());
            //Esetleg service rétegre átrakható validálás
            if (lakasBerles.getLefoglaltHelyekSzama() <= lh.getFerohelyekSzama()) {
                if (lakasBerlesService.ujLakasBerlesHozzaadasa(lakasBerles) && foglalasService.ujFoglalasHozzaadasa(foglalas)) {
                    foglalasId = foglalasService.utolsoBeillesztettFoglalasId();
                    id = lakasBerlesService.utolsoBeillesztettLakasBerlesId();
                    LakasBerles lb = lakasBerlesService.lakasBerlesIdAlapjan(id);
                    Integer osszeg = lakasBerlesVegosszege(lb.getLakasHirdetesId(), foglalasId, lb.getLefoglaltHelyekSzama());
                    if (osszeg <= 0) {
                        System.out.println("A végösszeg kiszámítása közben hiba lépett fel!");
                    } 
                    else if (lakasBerlesService.lakasBerlesAdatokBeallitasa(foglalasId, osszeg, id)) {
                        System.out.println("A foglalás sikeresen megtörtént!");
                    } 
                    else {
                        System.out.println("A foglalás adatait nem sikerült frissíteni!");
                    }
                } 
                else {
                    System.out.println("A foglalás létrehozása sikertelen volt!");
                }
                
            } else {
                System.out.println("A megadott hirdetésben nincs ennyi férőhely, a maximális lefoglalható férőhelyek száma: " + lh.getFerohelyekSzama());
            }
        }
            else {
                System.out.println("Minden mező kitöltése szükséges a foglalás létrehozásához!");
            }
        }
    
    

    private Integer lakasBerlesVegosszege(Integer lakasHirdetesId, Integer foglalasId, Short foglalatHelyek) {
        if (lakasHirdetesService.lakasHirdetesIdAlapjan(lakasHirdetesId) != null) {
            LakasHirdetes lh = lakasHirdetesService.lakasHirdetesIdAlapjan(lakasHirdetesId);
            if (foglalasService.foglalasIdalapjan(foglalasId) != null) {
                Foglalas foglalas = foglalasService.foglalasIdalapjan(foglalasId);
                Long daysBetween = ChronoUnit.DAYS.between(foglalas.getFoglalasKezdete().toInstant(), foglalas.getFoglalasVege().toInstant());
                Integer days = daysBetween.intValue();
                Integer vegosszeg = lh.getEgysegar() * days * foglalatHelyek;
                return vegosszeg;
            } else {
                return -1;
            }
        } else {
            return -2;
        }
    }
    
    @PostMapping(value="/lakasberles/lakasberlesfrissites")
    public void lakasBerlesFrissitese(LakasBerles lakasBerles, Foglalas foglalas){
        if (lakasBerles.isNotEmpty() && foglalas.isNotEmpty()){
            
            LakasBerles lb = lakasBerlesService.lakasBerlesIdAlapjan(lakasBerles.getLakasBerlesId());
            
            if(lb.getFelhasznaloId() == lakasBerles.getFelhasznaloId() && lb.getFoglalasId() == lakasBerles.getFoglalasId() &&
                    lb.getLakasHirdetesId() == lakasBerles.getLakasHirdetesId()){
                
                LakasHirdetes lh = lakasHirdetesService.lakasHirdetesIdAlapjan(lakasBerles.getLakasHirdetesId());
                Foglalas eredetiFoglalas = foglalasService.foglalasIdalapjan(lb.getFoglalasId());
                if(lakasBerles.getLefoglaltHelyekSzama() <= lh.getFerohelyekSzama() && 
                        java.time.Instant.now().until(eredetiFoglalas.getFoglalasKezdete().toInstant(), ChronoUnit.HOURS) >= 48 &&
                        foglalas.getFoglalasKezdete().before(foglalas.getFoglalasVege())){
                    
                    
                    foglalas.setFoglalasId(lakasBerles.getFoglalasId());
                    
                    Integer vegosszeg = lakasBerlesVegosszege(lakasBerles.getLakasHirdetesId(), foglalas.getFoglalasId(), lakasBerles.getLefoglaltHelyekSzama());
                    lakasBerles.setFizetendoOsszeg(vegosszeg);
                    
                    foglalasService.foglalasFrissitese(foglalas);
                    lakasBerlesService.lakasBerlesFrissitese(lakasBerles);
                    
                    System.out.println("A foglalás sikeresen frissítve!");
                }
                else{
                    System.out.println("A foglalás változtatása nem lehet 48 órán belül vagy több helyet szeretne lefoglalni, mint amennyi elérhető."
                            + " A lefoglalható maximális helyek száma: " + lh.getFerohelyekSzama());
                }
            }
            else{
                System.out.println("Az idk nem megfelelően lettek megadva!"); 
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value = "/lakasberles/lakasberlestorlese")
    public void lakasBerlesTorlese(Integer id) {
        if (id != null) {
            if(lakasBerlesService.lakasBerlesIdAlapjan(id) != null){
                LakasBerles lb = lakasBerlesService.lakasBerlesIdAlapjan(id);
                Foglalas foglalas = foglalasService.foglalasIdalapjan(lb.getFoglalasId());
                if(java.time.Instant.now().until(foglalas.getFoglalasKezdete().toInstant(), ChronoUnit.HOURS) >= 48){
                    foglalasService.foglalasTorlese(lb.getFoglalasId());
                    lakasBerlesService.lakasBerlesTorlese(id);
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
    
    @GetMapping(value = "/lakasberles/lakasberlesIdaltal")
    public void lakasBerleselIdAltaliKilistazasa(Integer id) {
        System.out.println(lakasBerlesService.lakasBerleselIdAltaliKilistazasa(id));
    }
}
