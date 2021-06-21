/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.EtteremHirdetes;
import HelixLab.Szoftverfejlesztes.Repository.EtteremHirdetesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class EtteremHirdetesService {
    private final EtteremHirdetesRepository etteremHirdetesRepository;

    public EtteremHirdetesService(EtteremHirdetesRepository etteremHirdetesRepository) {
        this.etteremHirdetesRepository = etteremHirdetesRepository;
    }
    
    public boolean ujEtteremHirdetesHozzaadasa(EtteremHirdetes etteremHirdetes){
        return etteremHirdetesRepository.ujEtteremHirdetesHozzaadasa(etteremHirdetes);
    }
    
    public Integer utolsoBeillesztettEtteremId(){
        return etteremHirdetesRepository.utolsoBeillesztettEtteremId();
    }
    
    public boolean etteremHirdetesIdkBeallitasa(Integer elerhetosegId, Integer elhelyezkedesId, Integer id){
        return etteremHirdetesRepository.etteremHirdetesIdkBeallitasa(elerhetosegId, elhelyezkedesId, id);
    }
    
    public EtteremHirdetes etteremHirdetesFrissitese(EtteremHirdetes etteremHirdetes){
        return etteremHirdetesRepository.etteremHirdetesFrissitese(etteremHirdetes);
    }
    
    public EtteremHirdetes etteremHirdetesIdAlapjan(Integer id){
        return etteremHirdetesRepository.etteremHirdetesIdAlapjan(id);
    }
    
    public boolean etteremHirdetesTorlese(Integer id){
        return etteremHirdetesRepository.etteremHirdetesTorlese(id);
    }
    
    public JSONArray etteremHirdetesekKilistazasa(){
        JSONArray etteremLista = etteremHirdetesRepository.etteremHirdetesekKilistazasa();
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
