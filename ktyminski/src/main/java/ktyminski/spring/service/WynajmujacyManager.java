package ktyminski.spring.service;

import java.util.List;

import ktyminski.spring.domain.*;

public interface WynajmujacyManager {

	List<Wynajmujacy> getAllWynajmujacy();
	Wynajmujacy getWynajmujacyById(Wynajmujacy wynajmujacy);
	
	void addWynajmujacy(Wynajmujacy wynajmujacy);
	void editWynajmujacy(Wynajmujacy wynajmujacy);
	void deleteWynajmujacy(Wynajmujacy wynajmujacy);
	List<Wynajmujacy> getWynajmujacyByNazwisko(String nazwisko);
}
