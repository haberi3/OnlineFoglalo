
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.AutoMuszakiAdatok;
import HelixLab.Szoftverfejlesztes.Repository.AutoMuszakiAdatokRepository;
import org.springframework.stereotype.Service;

@Service
public class AutoMuszakiAdatokService {
    private final AutoMuszakiAdatokRepository autoMuszakiAdatokRepository;

    public AutoMuszakiAdatokService(AutoMuszakiAdatokRepository autoMuszakiAdatokRepository) {
        this.autoMuszakiAdatokRepository = autoMuszakiAdatokRepository;
    }
    
    public boolean ujAutoMuszakiAdatHozzaadasa(AutoMuszakiAdatok autoMuszakiAdatok){
        return autoMuszakiAdatokRepository.ujAutoMuszakiAdatHozzaadasa(autoMuszakiAdatok);
    }
    
    public Integer utolsoBeillesztettAutoMuszakiAdatokId(){
        return autoMuszakiAdatokRepository.utolsoBeillesztettAutoMuszakiAdatokId();
    }
    
    public AutoMuszakiAdatok autoMuszakiAdatokIdAlapjan(Integer id){
        return autoMuszakiAdatokRepository.autoMuszakiAdatokIdAlapjan(id);
    }
    
    public AutoMuszakiAdatok autoMuszakiAdatokFrissitese(AutoMuszakiAdatok autoMuszakiAdatok){
        return autoMuszakiAdatokRepository.autoMuszakiAdatokFrissitese(autoMuszakiAdatok);
    }
    
    public boolean autoMuszakiAdatokTorlese(Integer id){
        if(autoMuszakiAdatokRepository.autoMuszakiAdatokIdAlapjan(id) != null){
            return autoMuszakiAdatokRepository.autoMuszakiAdatokTorlese(id);
        }
        else{
            return false;
        }
    }
}
