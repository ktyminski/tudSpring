package ktyminski.spring.service;

import java.util.List;

import ktyminski.spring.domain.Mieszkania;
import ktyminski.spring.domain.Wynajmujacy;

public interface MieszkaniaManager {
	

    Mieszkania pobierzMieszkaniaPoId(Long id);
 

    Long dodaj(Mieszkania mieszkania);
 

    void edytuj(Mieszkania m, Wynajmujacy wynajmujacy, String ulica, Integer cena, String opis);
    void usun(Mieszkania m);
    
	 List<Mieszkania> dajWszystkieMieszkania();
	 List<Mieszkania> wyszukajMieszkaniayWgWynajmujacy(String porownaj);
	 List<Mieszkania> wyszukajMieszkania(Wynajmujacy w);


}


