
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.Foglalas;
import HelixLab.Szoftverfejlesztes.Repository.FoglalasRepository;
import org.springframework.stereotype.Service;

@Service
public class FoglalasService {
    private final FoglalasRepository foglalasRepository;

    public FoglalasService(FoglalasRepository foglalasRepository) {
        this.foglalasRepository = foglalasRepository;
    }
    
    public boolean ujFoglalasHozzaadasa(Foglalas foglalas){
        if(foglalas.getFoglalasKezdete().before(foglalas.getFoglalasVege())){
            return foglalasRepository.ujFoglalasHozzaadasa(foglalas);
        }
        else{
            System.out.println("A foglalás kezdetének előbb kell lennie, mint a foglalás végének!");
            return false;
        }
    }
    
    public Integer utolsoBeillesztettFoglalasId(){
        return foglalasRepository.utolsoBeillesztettFoglalasId();
    }
    
    public Foglalas foglalasIdalapjan(Integer id){
        return foglalasRepository.foglalasIdAlapjan(id);
    }
    
    public Foglalas foglalasFrissitese(Foglalas foglalas){
        return foglalasRepository.foglalasFrissitese(foglalas);
    }
    
    public boolean foglalasTorlese(Integer id){
        return foglalasRepository.foglalasTorlese(id);
    }
}
