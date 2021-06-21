
package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.Elerhetosegek;
import HelixLab.Szoftverfejlesztes.Model.Elhelyezkedes;
import HelixLab.Szoftverfejlesztes.Model.EtteremHirdetes;
import HelixLab.Szoftverfejlesztes.Service.ElerhetosegekService;
import HelixLab.Szoftverfejlesztes.Service.ElhelyezkedesService;
import HelixLab.Szoftverfejlesztes.Service.EtteremHirdetesService;
import HelixLab.Szoftverfejlesztes.Service.FelhasznaloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtteremHirdetesController {
    private final EtteremHirdetesService etteremHirdetesService;
    private final ElhelyezkedesService elhelyezkedesService;
    private final ElerhetosegekService elerhetosegekService;
    private final FelhasznaloService felhasznaloService;

    public EtteremHirdetesController(EtteremHirdetesService etteremHirdetesService, ElhelyezkedesService elhelyezkedesService, ElerhetosegekService elerhetosegekService, FelhasznaloService felhasznaloService) {
        this.etteremHirdetesService = etteremHirdetesService;
        this.elhelyezkedesService = elhelyezkedesService;
        this.elerhetosegekService = elerhetosegekService;
        this.felhasznaloService = felhasznaloService;
    }
    
    @PostMapping(value="/etteremhirdetes/etteremhirdetese")
    public void ujEtteremHirdetesHozzaadasa(EtteremHirdetes etteremHirdetes, Elhelyezkedes elhelyezkedes,String telefon, String email){
        Integer elhelyezkedesId;
        Integer elerhetosegId;
        Integer id;
        if(etteremHirdetes.isNotEmpty() && elhelyezkedes.isNotEmpty() && telefon != null && email != null){
            if(elhelyezkedesService.ujElhelyezkedesHozzaadasa(elhelyezkedes) && elerhetosegekService.ujEtteremElerhetosegHozzaadasa(telefon, email)
                    && etteremHirdetesService.ujEtteremHirdetesHozzaadasa(etteremHirdetes)){
                elhelyezkedesId = elhelyezkedesService.utolsoBeillesztettElhelyezkedesId();
                elerhetosegId = elerhetosegekService.elerhetosegIdMeghatarozasaEmailAlapjan(email);
                id = etteremHirdetesService.utolsoBeillesztettEtteremId();
                if(etteremHirdetesService.etteremHirdetesIdkBeallitasa(elerhetosegId, elhelyezkedesId, id)){
                    System.out.println("A hirdetés sikeresen feladva");
                }
                else{
                    System.out.println("A hirdetés adatok frissítése sikertelen volt!");
                }
            }
            else{
                System.out.println("A hirdetés feladása sikertelen volt!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value="/etteremhirdetes/etteremhirdetesfrissites")
    public void etteremhirdetesFrissitese(EtteremHirdetes etteremHirdetes, Elhelyezkedes elhelyezkedes, Elerhetosegek elerhetosegek){
        if(etteremHirdetes.isNotEmpty() && elhelyezkedes.isNotEmpty() && elerhetosegek.isNotEmpty()){
            EtteremHirdetes eh = etteremHirdetesService.etteremHirdetesIdAlapjan(etteremHirdetes.getEtteremHirdetesId());
            if(eh.getElhelyezkedesId() == etteremHirdetes.getElhelyezkedesId() && eh.getElerhetosegekId() == etteremHirdetes.getElerhetosegekId() &&
                    eh.getFelhasznaloId() == etteremHirdetes.getFelhasznaloId()){
                
                elhelyezkedes.setElhelyezkedesId(etteremHirdetes.getElhelyezkedesId());
                elerhetosegek.setElerhetosegekid(etteremHirdetes.getElerhetosegekId());
                
                elhelyezkedesService.elhelyezkedesFrissitese(elhelyezkedes);
                elerhetosegekService.elerhetosegekFrissitese(elerhetosegek);
                etteremHirdetesService.etteremHirdetesFrissitese(etteremHirdetes);
                
                System.out.println("A hirdetés frissítése sikeresen megtörtént!");
            }
            else{
                System.out.println("Nem megfelelő idk kerültek megadásra, ezért a frissítés sikertelen!");
            }
        }
        else{
        System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value = "/etteremhirdetes/etteremhirdetestorlese")
    public void etteremHirdetesTorlese(Integer id) {
        if (id != null) {
            if(etteremHirdetesService.etteremHirdetesIdAlapjan(id) != null){
                EtteremHirdetes eh = etteremHirdetesService.etteremHirdetesIdAlapjan(id);
                elerhetosegekService.elerhetosegekTorlese(eh.getElerhetosegekId());
                elhelyezkedesService.elhelyezkedesTorlese(eh.getElhelyezkedesId());
                etteremHirdetesService.etteremHirdetesTorlese(id);
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
    
    @GetMapping(value = "/etteremhirdetes/osszesetteremlista")
    public void etteremHirdetesAdatokKilistazasa() {
        System.out.println(etteremHirdetesService.etteremHirdetesekKilistazasa());
    }
}
