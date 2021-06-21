
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.LakasHirdetes;
import HelixLab.Szoftverfejlesztes.Repository.LakasHirdetesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LakasHirdetesService {
    private final LakasHirdetesRepository lakasHirdetesRepository;

    public LakasHirdetesService(LakasHirdetesRepository lakasHirdetesRepository) {
        this.lakasHirdetesRepository = lakasHirdetesRepository;
    }
    
    public boolean ujLakasHirdetesHozzaadasa(LakasHirdetes lakasHirdetes){
        return lakasHirdetesRepository.ujLakasHirdetesHozzaadasa(lakasHirdetes);
    }
    
    public Integer utolsoBeillesztettLakasHirdetesId(){
        return lakasHirdetesRepository.utolsoBeillesztettLakasHirdetesId();
    }
    
    public boolean lakasHirdetesIdkBeallitasa(Integer elhelyezkedesId, Integer id){
        return lakasHirdetesRepository.lakasHirdetesIdkBeallitasa(elhelyezkedesId, id);
    }
    
    public LakasHirdetes lakasHirdetesIdAlapjan(Integer id) {
        return lakasHirdetesRepository.lakasHirdetesIdAlapjan(id);
    }
    
    public LakasHirdetes lakasHirdetesFrissitese(LakasHirdetes lakasHirdetes){
        return lakasHirdetesRepository.lakasHirdetesFrissitese(lakasHirdetes);
    }
    
    public boolean lakasHirdetesTorlese(Integer id){
        return lakasHirdetesRepository.lakasHirdetesTorlese(id);
    }
    
    public JSONArray lakashirdetesekKilistazasa(){
        JSONArray lakasLista = lakasHirdetesRepository.lakashirdetesekKilistazasa();
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
