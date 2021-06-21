package HelixLab.Szoftverfejlesztes.Controller;

import HelixLab.Szoftverfejlesztes.Model.EtteremFoglalas;
import HelixLab.Szoftverfejlesztes.Model.Foglalas;
import HelixLab.Szoftverfejlesztes.Service.EtteremFoglalasService;
import HelixLab.Szoftverfejlesztes.Service.FoglalasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtteremFoglalasController {

    private final EtteremFoglalasService etteremFoglalasService;
    private final FoglalasService foglalasService;

    public EtteremFoglalasController(EtteremFoglalasService etteremFoglalasService, FoglalasService foglalasService) {
        this.etteremFoglalasService = etteremFoglalasService;
        this.foglalasService = foglalasService;
    }
    
    @PostMapping(value="/etteremfoglalas/ujetteremfoglalasa")
    public void ujEtteremFoglalasHozzaadasa(EtteremFoglalas etteremFoglalas, Foglalas foglalas) {
        Integer foglalasId;
        Integer id;
        if (etteremFoglalas.isNotEmpty() && foglalas.isNotEmpty()) {
            if (etteremFoglalasService.ujEtteremFoglalasHozzaadasa(etteremFoglalas) && foglalasService.ujFoglalasHozzaadasa(foglalas)) {
                foglalasId = foglalasService.utolsoBeillesztettFoglalasId();
                id = etteremFoglalasService.utolsoBeillesztettEtteremFoglalasId();
                if(foglalasId != 0 && id != 0){
                    etteremFoglalasService.etteremFoglalasAdatokBeallitasa(foglalasId, id);
                    System.out.println("A foglalás sikeresen megtörtént");
                }
                else{
                    System.out.println("A megadott hirdetés vagy felhasználó nem létezik");
                }
            }
            else{
                System.out.println("A foglalás létrehozása sikertelen volt!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value="/etteremfoglalas/etteremfoglalasfrissites")
    public void etteremFoglalasFrissitese(EtteremFoglalas etteremFoglalas, Foglalas foglalas){
        if(etteremFoglalas.isNotEmpty() && foglalas.isNotEmpty()){
            EtteremFoglalas ef = etteremFoglalasService.etteremFoglalasIdAlapjan(etteremFoglalas.getEtteremFoglalasId());
            if(ef.getEtteremHirdetesId() == etteremFoglalas.getEtteremHirdetesId() && ef.getFelhasznaloId() == etteremFoglalas.getFelhasznaloId()
                    && ef.getFoglalasId() == etteremFoglalas.getFoglalasId()){
                foglalas.setFoglalasId(etteremFoglalas.getFoglalasId());
                
                foglalasService.foglalasFrissitese(foglalas);
                etteremFoglalasService.etteremFoglalasFrissitese(etteremFoglalas);
                
                System.out.println("A foglalás sikeresen frissítve");
            }
            else{
                System.out.println("Az id-k nem lettek megfelelően megadva az azonosításhoz!");
            }
        }
        else{
            System.out.println("Minden mező kitöltése szükséges!");
        }
    }
    
    @PostMapping(value = "/etteremfoglalas/etteremfoglalastorlese")
    public void etteremFoglalasTorlese(Integer id) {
        if (id != null) {
            if(etteremFoglalasService.etteremFoglalasIdAlapjan(id) != null){
                EtteremFoglalas ef = etteremFoglalasService.etteremFoglalasIdAlapjan(id);
                foglalasService.foglalasTorlese(ef.getFoglalasId());
                etteremFoglalasService.etteremFoglalasTorlese(id);
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
    
    @GetMapping(value = "/etteremfoglalas/etteremfoglalasadatai")
    public void etteremFoglalasAdatokKilistazasa(Integer id) {
        System.out.println(etteremFoglalasService.etteremFoglalasKilistazasaIdAltal(id));
    }
}
