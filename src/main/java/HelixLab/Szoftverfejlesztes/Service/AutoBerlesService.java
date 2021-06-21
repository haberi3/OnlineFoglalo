
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.AutoBerles;
import HelixLab.Szoftverfejlesztes.Repository.AutoBerlesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AutoBerlesService {
    private final AutoBerlesRepository autoBerlesRepository;

    public AutoBerlesService(AutoBerlesRepository autoBerlesRepository) {
        this.autoBerlesRepository = autoBerlesRepository;
    }
    
    public boolean ujAutoBerlesHozzaadasa(AutoBerles autoBerles){
        return autoBerlesRepository.ujAutoBerlesHozzaadasa(autoBerles);
    }
    
    public Integer utolsoBeillesztettAutoBerlesId(){
        return autoBerlesRepository.utolsoBeillesztettAutoBerlesId();
    }
    
    public boolean autoBerlesIdkBeallitasa(Integer foglalasId,Integer fizetendo, Integer id){
        return autoBerlesRepository.autoBerlesAdatokBeallitasa(foglalasId, fizetendo, id);
    }
    
    public AutoBerles autoBerlesIdAlapjan(Integer id){
        return autoBerlesRepository.autoBerlesIdAlapjan(id);
    }
    
    public AutoBerles autoBerlesFrissitese(AutoBerles autoBerles){
        return autoBerlesRepository.autoBerlesFrissitese(autoBerles);
    }
    
    public boolean autoBerlesTorlese(Integer id){
        return autoBerlesRepository.autoBerlesTorlese(id);
    }
    
    public JSONArray autoBerlesAdatokKilistazasaIdSzerint(Integer id){
        JSONArray autoLista = autoBerlesRepository.autoBerlesAdatokKilistazasaIdSzerint(id);
        try{
            if(autoLista.length() == 0){
                JSONObject obj = new JSONObject();
                obj.put("result", "A megadott kérésnek megfelelő lista jelenleg üres!");
                autoLista.put(obj);
                return autoLista;
            }
            else{
                return autoLista;
            }
        }
        catch(JSONException ex){
            System.out.println("ex.getMessage()");
            return autoLista;
        }
    }
}
