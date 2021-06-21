
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.Elhelyezkedes;
import HelixLab.Szoftverfejlesztes.Repository.ElhelyezkedesRepository;
import org.springframework.stereotype.Service;

@Service
public class ElhelyezkedesService {
    private final ElhelyezkedesRepository elhelyezkedesRepository;

    public ElhelyezkedesService(ElhelyezkedesRepository elhelyezkedesRepository) {
        this.elhelyezkedesRepository = elhelyezkedesRepository;
    }
    
    public boolean ujElhelyezkedesHozzaadasa(Elhelyezkedes elhelyezkedes){
        if(elhelyezkedes.getIranyitoszam() < 10000 && elhelyezkedes.getIranyitoszam() > 999){
            return elhelyezkedesRepository.ujElhelyezkedesHozzaadasa(elhelyezkedes);
        }
        else{
            System.out.println("Az irányítószám nem megfelelően lett megadva!");
            return false;
        }
    }
    
    public Integer utolsoBeillesztettElhelyezkedesId(){
        return elhelyezkedesRepository.utolsoBeillesztettElhelyezkedesId();
    }
    
    public Elhelyezkedes elhelyezkedesFrissitese(Elhelyezkedes elhelyezkedes){
        return elhelyezkedesRepository.elhelyezkedesFrissitese(elhelyezkedes);
    }
    
    public Elhelyezkedes elhelyezkedesIdAlapjan(Integer id){
        return elhelyezkedesRepository.elhelyezkedesIdAlapjan(id);
    }
    
    public boolean elhelyezkedesTorlese(Integer id){
        if(elhelyezkedesRepository.elhelyezkedesIdAlapjan(id) != null){
            return elhelyezkedesRepository.elhelyezkedesTorlese(id);
        }
        else{
            return false;
        }
    }
}
