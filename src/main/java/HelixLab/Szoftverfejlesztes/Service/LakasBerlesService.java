package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.LakasBerles;
import HelixLab.Szoftverfejlesztes.Repository.LakasBerlesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LakasBerlesService {

    private final LakasBerlesRepository lakasBerlesRepository;

    public LakasBerlesService(LakasBerlesRepository lakasBerlesRepository) {
        this.lakasBerlesRepository = lakasBerlesRepository;
    }

    public Integer utolsoBeillesztettLakasBerlesId() {
        return lakasBerlesRepository.utolsoBeillesztettLakasBerlesId();
    }

    public LakasBerles lakasBerlesIdAlapjan(Integer id) {
         return lakasBerlesRepository.lakasBerlesIdAlapjan(id);
    }

    public boolean ujLakasBerlesHozzaadasa(LakasBerles lakasBerles){
        return lakasBerlesRepository.ujLakasBerlesHozzaadasa(lakasBerles);
    }
    
    public boolean lakasBerlesAdatokBeallitasa(Integer foglalasId, Integer fizetendo, Integer id){
        return lakasBerlesRepository.lakasBerlesAdatokBeallitasa(foglalasId, fizetendo, id);
    }
    
    public LakasBerles lakasBerlesFrissitese(LakasBerles lakasBerles){
        return lakasBerlesRepository.lakasBerlesFrissitese(lakasBerles);
    }
    
    public boolean lakasBerlesTorlese(Integer id){
        return lakasBerlesRepository.lakasBerlesTorlese(id);
    }
    
    public JSONArray lakasBerleselIdAltaliKilistazasa(Integer id){
        JSONArray lakasLista = lakasBerlesRepository.lakasBerlesIdAltaliKilistazasa(id);
        try{
            if(lakasLista.length() == 0){
                JSONObject obj = new JSONObject();
                obj.put("result", "A megadott kérésnek megfelelő lista jelenleg üres!");
                lakasLista.put(obj);
                return lakasLista;
            }
            else{
                return lakasLista;
            }
        }
        catch(JSONException ex){
            System.out.println("ex.getMessage()");
            return lakasLista;
        }
    }
}
