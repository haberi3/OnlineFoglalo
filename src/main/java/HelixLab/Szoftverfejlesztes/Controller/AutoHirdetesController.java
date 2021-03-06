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
            System.out.println("K??rem ??rja be milyen m??rka szerint szeretne keresni");
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

                //Ha ez nem sikeres va??ami??rt akkor az eg??szet vissza k??ne rollbackelni, hogy ne legyenek nem ??sszek??t??tt rekordok az adatb??zisban
                if (autoHirdetesService.autohirdetesIdBeallitas(autoMuszakiAdatokId, autoTipusAdatokId, elhelyezkedesId, id)) {
                    System.out.println("A hirdet??s sikeresen feladva");
                } else {
                    System.out.println("A hirdet??shez tartoz?? adatokat nem siker??lt friss??teni");
                }

            } else {
                System.out.println("A hirdet??s felad??sa sikertelen volt");
            }
        } else {
            System.out.println("Minden mez?? kit??lt??se sz??ks??ges");
        }
    }

    @GetMapping(value = "/autohirdetes/autohirdetesIdval")
    public void autoHirdetesIdAlapjan(Integer id) {
        if (autoHirdetesService.autoHirdetesIdAlapjan(id) != null) {
            AutoHirdetes ah = autoHirdetesService.autoHirdetesIdAlapjan(id);
            System.out.println(ah);
        } else {
            System.out.println("Nincs hirdet??s ezzel az id-val!");
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
                System.out.println("A hirdet??s friss??t??se sikeresen megt??rt??nt!");

            } else {
                System.out.println("Hib??s id el??rhet??s??get adott meg ??gy a friss??t??s sikertelen!");
            }
        } else {
            System.out.println("Minden mez?? kit??lt??se sz??ks??ges!");
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
                System.out.println("A hirdet??s sikeresen t??r??lve");
            }
            else{
                System.out.println("Nem tal??lhat?? ezzel az id-val hirdet??s!");
            }
        }
        else{
            System.out.println("Minden mez?? kit??lt??se sz??ks??ges!");
        }
    }
}
