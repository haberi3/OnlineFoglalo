
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.AutoTipusAdatok;
import HelixLab.Szoftverfejlesztes.Repository.AutoTipusAdatokRepository;
import org.springframework.stereotype.Service;

@Service
public class AutoTipusAdatokService {
    private final AutoTipusAdatokRepository autoTipusAdatokRepository;

    public AutoTipusAdatokService(AutoTipusAdatokRepository autoTipusAdatokRepository) {
        this.autoTipusAdatokRepository = autoTipusAdatokRepository;
    }
    
    public boolean ujAutoTipusAdatHozzaadasa(AutoTipusAdatok autoTipusAdatok){
        return autoTipusAdatokRepository.ujAutoTipusAdatHozzaadasa(autoTipusAdatok);
    }
     public Integer utolsoBeillesztettAutoTipusId(){
        return autoTipusAdatokRepository.utolsoBeillesztettAutoTipusId();
    }
    
    public AutoTipusAdatok autoTipusAdatokIdAlapjan(Integer id){
        return autoTipusAdatokRepository.autoTipusAdatokIdAlapjan(id);
    }
    
    public AutoTipusAdatok autoTipusAdatokFrissitese(AutoTipusAdatok autoTipusAdatok){
        return autoTipusAdatokRepository.autoTipusAdatokFrissitese(autoTipusAdatok);
    }
    
    public boolean autoTipusAdatokTorlese(Integer id){
        if(autoTipusAdatokRepository.autoTipusAdatokIdAlapjan(id) != null){
            return autoTipusAdatokRepository.autoTipusAdatokTorlese(id);
        }
        else{
            return false;
        }
    }
}
