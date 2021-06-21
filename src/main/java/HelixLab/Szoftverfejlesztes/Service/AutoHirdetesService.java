
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.AutoHirdetes;
import HelixLab.Szoftverfejlesztes.Repository.AutoHirdetesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AutoHirdetesService {
    private final AutoHirdetesRepository autoHirdetesRepository;

    public AutoHirdetesService(AutoHirdetesRepository autoHirdetesRepository) {
        this.autoHirdetesRepository = autoHirdetesRepository;
    }
    
    public boolean ujAutoHirdetese(AutoHirdetes autoHirdetes){
        return autoHirdetesRepository.ujAutoHirdetese(autoHirdetes);
    }
    
    public JSONArray autohirdetesekKilistazasa(){
        JSONArray autoAdatokLista = autoHirdetesRepository.autohirdetesekKilistazasa();
        try{
            if(autoAdatokLista.length() == 0){
                JSONObject obj = new JSONObject();
                obj.put("result", "A megadott kérésnek megfelelő lista jelenleg üres!");
                autoAdatokLista.put(obj);
                return autoAdatokLista;
            }
            else{
                return autoAdatokLista;
            }
        }
        catch(JSONException ex){
            System.out.println("ex.getMessage()");
            return autoAdatokLista;
        }
    }
    
    public JSONArray autohirdetesekKilistazasaMarkaSzerint(String marka){
        JSONArray autoLista = autoHirdetesRepository.autohirdetesekKilistazasaMarkaSzerint(marka);
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
    
    public JSONArray autohirdetesekArszerintRendezve(){
        JSONArray autoLista = autoHirdetesRepository.autohirdetesekArszerintRendezve();
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
    
    public boolean autohirdetesIdBeallitas(Integer muszakiId, Integer tipusId, Integer elhelyezkedesId, Integer id){
        return autoHirdetesRepository.autoHirdetesIdkBeallitasa(muszakiId, tipusId, elhelyezkedesId, id);
    }
    
    public Integer utolsoBeillesztettAutoHirdetesId(){
        return autoHirdetesRepository.utolsoBeillesztettAutoHirdetesId();
    }
    
    public AutoHirdetes autoHirdetesIdAlapjan(Integer id){
        return autoHirdetesRepository.autoHirdetesIdAlapjan(id);   
    }
    
    public AutoHirdetes autoHirdetesFrissitese(AutoHirdetes autoHirdetes){
        return autoHirdetesRepository.autoHirdetesFrissitese(autoHirdetes);
    }
    
    public boolean autoHirdetesTorlese(Integer id){
        return autoHirdetesRepository.autoHirdetesTorlese(id);
    }
}
