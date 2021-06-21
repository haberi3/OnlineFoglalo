package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.AutoHirdetes;
import HelixLab.Szoftverfejlesztes.Model.AutoMuszakiAdatok;
import HelixLab.Szoftverfejlesztes.Model.AutoTipusAdatok;
import HelixLab.Szoftverfejlesztes.Model.Elhelyezkedes;
import HelixLab.Szoftverfejlesztes.Service.AutoHirdetesService;
import HelixLab.Szoftverfejlesztes.Service.AutoMuszakiAdatokService;
import HelixLab.Szoftverfejlesztes.Service.AutoTipusAdatokService;
import HelixLab.Szoftverfejlesztes.Service.ElhelyezkedesService;
import HelixLab.Szoftverfejlesztes.Service.FelhasznaloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoHirdetesController {

    private final AutoHirdetesService autoHirdetesService;
    private final AutoTipusAdatokService autoTipusAdatokService;
    private final AutoMuszakiAdatokService autoMuszakiAdatokSerivce;
    private final ElhelyezkedesService elhelyezkedesService;
    private final FelhasznaloService felhasznaloService;

    public AutoHirdetesController(AutoHirdetesService autoHirdetesService, AutoTipusAdatokService autoTipusAdatokService, AutoMuszakiAdatokService autoMuszakiAdatokSerivce, ElhelyezkedesService elhelyezkedesService, FelhasznaloService felhasznaloService) {
        this.autoHirdetesService = autoHirdetesService;
        this.autoTipusAdatokService = autoTipusAdatokService;
        this.autoMuszakiAdatokSerivce = autoMuszakiAdatokSerivce;
        this.elhelyezkedesService = elhelyezkedesService;
        this.felhasznaloService = felhasznaloService;
    }

    @GetMapping(value = "/autohirdetes/osszesautolista")
    public void autoHirdetesAdatokKilistazasa() {
        System.out.println(autoHirdetesService.autohirdetesekKilistazasa());
    }
    
    @GetMapping(value = "/autohirdetes/osszesautomarkaszerint")
    public void autoHirdetesAdatokKilistazasaMarkaSzerint(String marka) {
        if(marka != null){
            System.out.println(autoHirdetesService.autohirdetesekKilistazasaMarkaSzerint(marka));
        }
        else{
            System.out.println("Kérem írja be milyen márka szerint szeretne keresni");
        }
    }
    
    @GetMapping(value = "/autohirdetes/osszesautoarszerintnovekvo")
    public void autohirdetesekArszerintRendezve() {
        System.out.println(autoHirdetesService.autohirdetesekArszerintRendezve());
    }

    @PostMapping(value = "/autohirdetes/ujautohirdetes")
    public void ujAutoHirdetese(AutoMuszakiAdatok autoMuszakiAdatok, AutoTipusAdatok autoTipusAdatok, Elhelyezkedes elhelyezkedes, AutoHirdetes autoHirdetes) {
        Integer autoMuszakiAdatokId = 0;
        Integer autoTipusAdatokId = 0;
        Integer elhelyezkedesId = 0;
        Integer id = 0;
        if (autoMuszakiAdatok.isNotEmpty() && autoTipusAdatok.isNotEmpty() && elhelyezkedes.isNotEmpty() && autoHirdetes.isNotEmpty()) {
            if (autoMuszakiAdatokSerivce.ujAutoMuszakiAdatHozzaadasa(autoMuszakiAdatok) && autoTipusAdatokService.ujAutoTipusAdatHozzaadasa(autoTipusAdatok)
                    && elhelyezkedesService.ujElhelyezkedesHozzaadasa(elhelyezkedes) && autoHirdetesService.ujAutoHirdetese(autoHirdetes)) {
                autoMuszakiAdatokId = autoMuszakiAdatokSerivce.utolsoBeillesztettAutoMuszakiAdatokId();
                autoTipusAdatokId = autoTipusAdatokService.utolsoBeillesztettAutoTipusId();
                elhelyezkedesId = elhelyezkedesService.utolsoBeillesztettElhelyezkedesId();
                id = autoHirdetesService.utolsoBeillesztettAutoHirdetesId();

                //Ha ez nem sikeres vaéamiért akkor az egészet vissza kéne rollbackelni, hogy ne legyenek nem összekötött rekordok az adatbázisban
                if (autoHirdetesService.autohirdetesIdBeallitas(autoMuszakiAdatokId, autoTipusAdatokId, elhelyezkedesId, id)) {
                    System.out.println("A hirdetés sikeresen feladva");
                } else {
                    System.out.println("A hirdetéshez tartozó adatokat nem sikerült frissíteni");
                }

            } else {
                System.out.println("A hirdetés feladása sikertelen volt");
            }
        } else {
            System.out.println("Minden mező kitöltése szükséges");
        }
    }

    @GetMapping(value = "/autohirdetes/autohirdetesIdval")
    public void autoHirdetesIdAlapjan(Integer id) {
        if (autoHirdetesService.autoHirdetesIdAlapjan(id) != null) {
            AutoHirdetes ah = autoHirdetesService.autoHirdetesIdAlapjan(id);
            System.out.println(ah);
        } else {
            System.out.println("Nincs hirdetés ezzel az id-val!");
        }
    }

    @PostMapping(value = "/autohirdetes/autohirdetesfrissites")
    public void autohirdetesFrissitese(AutoHirdetes autoHirdetes, Elhelyezkedes elhelyezkedes, AutoMuszakiAdatok autoMuszakiAdatok, AutoTipusAdatok autoTipusAdatok) {
        if (autoHirdetes.isNotEmpty() && elhelyezkedes.isNotEmpty() && autoMuszakiAdatok.isNotEmpty() && autoTipusAdatok.isNotEmpty()) {

            AutoHirdetes ah = autoHirdetesService.autoHirdetesIdAlapjan(autoHirdetes.getAutoHirdetesId());

            if (ah.getElhelyezkedesId() == autoHirdetes.getElhelyezkedesId() && ah.getAutoTipusAdatokId() == autoHirdetes.getAutoTipusAdatokId()
                    && ah.getAutoMuszakiAdatokId() == autoHirdetes.getAutoMuszakiAdatokId() && ah.getFelhasznaloId() == autoHirdetes.getFelhasznaloId()) {

                elhelyezkedes.setElhelyezkedesId(autoHirdetes.getElhelyezkedesId());
                autoTipusAdatok.setAutoTipusAdatokId(autoHirdetes.getAutoTipusAdatokId());
                autoMuszakiAdatok.setAutoMuszakiAdatokId(autoHirdetes.getAutoMuszakiAdatokId());

                elhelyezkedesService.elhelyezkedesFrissitese(elhelyezkedes);
                autoTipusAdatokService.autoTipusAdatokFrissitese(autoTipusAdatok);
                autoMuszakiAdatokSerivce.autoMuszakiAdatokFrissitese(autoMuszakiAdatok);
                autoHirdetesService.autoHirdetesFrissitese(autoHirdetes);
                System.out.println("A hirdetés frissítése sikeresen megtörtént!");

            } else {
                System.out.println("Hibás id elérhetőséget adott meg így a frissítés sikertelen!");
            }
        } else {
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }

    @PostMapping(value = "/autohirdetes/autohirdetestorlese")
    public void autoHirdetesTorlese(Integer id) {
        if (id != null) {
            if (autoHirdetesService.autoHirdetesIdAlapjan(id) != null) {
                AutoHirdetes ah = autoHirdetesService.autoHirdetesIdAlapjan(id);
                elhelyezkedesService.elhelyezkedesTorlese(ah.getElhelyezkedesId());
                autoTipusAdatokService.autoTipusAdatokTorlese(ah.getAutoTipusAdatokId());
                autoMuszakiAdatokSerivce.autoMuszakiAdatokTorlese(ah.getAutoMuszakiAdatokId());
                autoHirdetesService.autoHirdetesTorlese(id);
                System.out.println("A hirdetés sikeresen törölve");
            }
            else{
                System.out.println("Nem található ezzel az id-val hirdetés!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
}
