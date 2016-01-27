package ktyminski.spring.service;

import java.util.List;

import ktyminski.spring.domain.Mieszkania;
import ktyminski.spring.domain.Wynajmujacy;

public interface WynajmujacyManager {


	    Wynajmujacy pobierzWynajmujacyPoId(Long id);	  
	    Long dodaj(Wynajmujacy wynajmujacy);
	    void edytuj(Wynajmujacy w, String imie, String nazwisko, long pesel);
	    void usun(Wynajmujacy w);

	    
	 

}

