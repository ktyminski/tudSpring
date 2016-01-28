package  ktyminski.spring.service;


import ktyminski.spring.domain.*;

import java.util.List;


public interface MieszkaniaManager {
	
	List<Mieszkania> getAllMieszkania();
	Mieszkania getMieszkaniaById(Mieszkania mieszkania);

	void addMieszkania(Mieszkania mieszkania);
	void editMieszkania(Mieszkania mieszkania);
	void deleteMieszkania(Mieszkania mieszkania);
	void addOddzialToMieszkania(Mieszkania mieszkania, Wynajmujacy wynajmujacy);
	

	
}
