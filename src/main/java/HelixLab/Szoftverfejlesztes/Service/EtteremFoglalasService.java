package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.EtteremFoglalas;
import HelixLab.Szoftverfejlesztes.Repository.EtteremFoglalasRepository;
import HelixLab.Szoftverfejlesztes.Repository.EtteremHirdetesRepository;
import HelixLab.Szoftverfejlesztes.Repository.FelhasznaloRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class EtteremFoglalasService {

    private final EtteremFoglalasRepository etteremFoglalasRepository;
    private final EtteremHirdetesRepository etteremHirdetesRepository;
    private final FelhasznaloRepository felhasznaloRepository;

    public EtteremFoglalasService(EtteremFoglalasRepository etteremFoglalasRepository, EtteremHirdetesRepository etteremHirdetesRepository, FelhasznaloRepository felhasznaloRepository) {
        this.etteremFoglalasRepository = etteremFoglalasRepository;
        this.etteremHirdetesRepository = etteremHirdetesRepository;
        this.felhasznaloRepository = felhasznaloRepository;
    }
    
    public boolean ujEtteremFoglalasHozzaadasa(EtteremFoglalas etteremFoglalas) {
        return etteremFoglalasRepository.ujEtteremFoglalasHozzaadasa(etteremFoglalas);
    }

    public Integer utolsoBeillesztettEtteremFoglalasId() {
        return etteremFoglalasRepository.utolsoBeillesztettEtteremFoglalasId();
    }
    
    public boolean etteremFoglalasAdatokBeallitasa(Integer foglalasId, Integer id){
        return etteremFoglalasRepository.etteremFoglalasAdatokBeallitasa(foglalasId, id);
    }
    
    public EtteremFoglalas etteremFoglalasFrissitese(EtteremFoglalas etteremFoglalas){
        return etteremFoglalasRepository.etteremFoglalasFrissitese(etteremFoglalas);
    }
    
    public EtteremFoglalas etteremFoglalasIdAlapjan(Integer id){
        return etteremFoglalasRepository.etteremFoglalasIdAlapjan(id);
    }
    
    public boolean etteremFoglalasTorlese(Integer id){
        return etteremFoglalasRepository.etteremFoglalasTorlese(id);
    }
    
    public JSONArray etteremFoglalasKilistazasaIdAltal(Integer id){
        JSONArray etteremLista = etteremFoglalasRepository.etteremFoglalasKilistazasaIdAltal(id);
        try{
            if(etteremLista.length() == 0){
                JSONObject obj = new JSONObject();
                obj.put("result", "A megadott kérésnek megfelelő lista jelenleg üres!");
                etteremLista.put(obj);
                return etteremLista;
            }
            else{
                return etteremLista;
            }
        }
        catch(JSONException ex){
            System.out.println("ex.getMessage()");
            return etteremLista;
        }
    }
}
