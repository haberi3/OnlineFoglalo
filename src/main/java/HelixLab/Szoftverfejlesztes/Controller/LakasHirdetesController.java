package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.Elhelyezkedes;
import HelixLab.Szoftverfejlesztes.Model.LakasHirdetes;
import HelixLab.Szoftverfejlesztes.Service.ElhelyezkedesService;
import HelixLab.Szoftverfejlesztes.Service.LakasHirdetesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LakasHirdetesController {

    private final LakasHirdetesService lakasHirdetesService;
    private final ElhelyezkedesService elhelyezkedesService;

    public LakasHirdetesController(LakasHirdetesService lakasHirdetesService, ElhelyezkedesService elhelyezkedesService) {
        this.lakasHirdetesService = lakasHirdetesService;
        this.elhelyezkedesService = elhelyezkedesService;
    }

    @PostMapping(value = "/lakashirdetes/ujlakashirdetes")
    public void ujLakasHirdetesHozzaadasa(Elhelyezkedes elhelyezkedes, LakasHirdetes lakasHirdetes) {
        Integer elhelyezkedesId = 0;
        Integer id = 0;
        if(elhelyezkedes.isNotEmpty() && lakasHirdetes.isNotEmpty()){
            if (elhelyezkedesService.ujElhelyezkedesHozzaadasa(elhelyezkedes) && lakasHirdetesService.ujLakasHirdetesHozzaadasa(lakasHirdetes)) {
                elhelyezkedesId = elhelyezkedesService.utolsoBeillesztettElhelyezkedesId();
                id = lakasHirdetesService.utolsoBeillesztettLakasHirdetesId();
                lakasHirdetesService.lakasHirdetesIdkBeallitasa(elhelyezkedesId, id);
                System.out.println("A hirdetés feladás sikeres");
            } 
            else {
                System.out.println("A lakás hirdetés feladása sikertelen volt");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges");
        }
    }
    
    @PostMapping(value="/lakashirdetes/lakashirdetesfrissites")
    public void lakashirdetesFrissitese(Elhelyezkedes elhelyezkedes, LakasHirdetes lakasHirdetes){
        if(elhelyezkedes.isNotEmpty() && lakasHirdetes.isNotEmpty()){
            LakasHirdetes lh = lakasHirdetesService.lakasHirdetesIdAlapjan(lakasHirdetes.getLakasHirdetesId());
            if(lh.getElhelyezkedesId() == elhelyezkedes.getElhelyezkedesId() && lh.getFelhasznaloId() == lakasHirdetes.getFelhasznaloId()){
                elhelyezkedes.setElhelyezkedesId(lakasHirdetes.getElhelyezkedesId());
                
                elhelyezkedesService.elhelyezkedesFrissitese(elhelyezkedes);
                lakasHirdetesService.lakasHirdetesFrissitese(lakasHirdetes);
                
                System.out.println("A hirdetés frissítése sikeresen megtörtént!");
            }
            else{
                System.out.println("Nem megfelelő id elérhetőségek lettek megadva!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value = "/lakashirdetes/lakashirdetestorlese")
    public void lakasHirdetesTorlese(Integer id) {
        if (id != null) {
            if(lakasHirdetesService.lakasHirdetesIdAlapjan(id) != null){
                LakasHirdetes lh = lakasHirdetesService.lakasHirdetesIdAlapjan(id);
                elhelyezkedesService.elhelyezkedesTorlese(lh.getElhelyezkedesId());
                lakasHirdetesService.lakasHirdetesTorlese(id);
                System.out.println("A hirdetés törlése sikeresen megtörtént!");
            }
            else{
                System.out.println("Nem található ezzel az id-val hirdetés!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @GetMapping(value = "/lakashirdetes/osszeslakaslista")
    public void lakasHirdetesAdatokKilistazasa() {
        System.out.println(lakasHirdetesService.lakashirdetesekKilistazasa());
    }
}
