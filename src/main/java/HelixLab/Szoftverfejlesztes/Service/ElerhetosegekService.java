package HelixLab.Szoftverfejlesztes.Service;

import HelixLab.Szoftverfejlesztes.Model.Elerhetosegek;
import HelixLab.Szoftverfejlesztes.Repository.ElerhetosegekRepository;
import org.springframework.stereotype.Service;

@Service
public class ElerhetosegekService {

    private final ElerhetosegekRepository elerhetosegekRepository;

    public ElerhetosegekService(ElerhetosegekRepository elerhetosegekRepository) {
        this.elerhetosegekRepository = elerhetosegekRepository;
    }

    public boolean ujRegisztraciosElerhetosegHozzaadasa(String email) {
            return elerhetosegekRepository.ujRegisztraciosElerhetosegHozzaadasa(email);
    }

    public Integer elerhetosegIdMeghatarozasaEmailAlapjan(String email) {
        Integer elerhetosegId = elerhetosegekRepository.elerhetosegIdMeghatarozasaEmailAlapjan(email);
        if (elerhetosegId == 0) {
            System.out.println("A keresett email cím nem létezik!");
            return elerhetosegId;
        } else {
            return elerhetosegId;
        }
    }

    public boolean ujEtteremElerhetosegHozzaadasa(String telefon, String email) {
        return elerhetosegekRepository.ujEtteremElerhetosegHozzaadasa(telefon, email);
    }

    public Elerhetosegek elerhetosegekFrissitese(Elerhetosegek elerhetosegek) {
        return elerhetosegekRepository.elerhetosegekFrissitese(elerhetosegek);
    }

    public Elerhetosegek elerhetosegekIdAlapjan(Integer id) {
        return elerhetosegekRepository.elerhetosegekIdAlapjan(id);
    }
    
    public boolean elerhetosegekTorlese(Integer id){
        if(elerhetosegekRepository.elerhetosegekIdAlapjan(id) != null){
            return elerhetosegekRepository.elerhetosegekTorlese(id);
        }
        else{
            return false;
        }
    }
}
