package ktyminski.spring.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ktyminski.spring.domain.Wynajmujacy;
import ktyminski.spring.domain.Mieszkania;
import ktyminski.spring.service.WynajmujacyManager;
import ktyminski.spring.service.MieszkaniaManager;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class MieszkaniaTest {
	
	@Autowired
	MieszkaniaManager mieszkaniaManager;
	
	@Autowired
	WynajmujacyManager wynajmujacyManager;

	private final static String W_IMIE = "Michal";
	private final static String W_NAZWISKO = "Kropiwnicki";
	private final static String W_PESEL = "1434343";
	
	private final static List<String> M_ULICA = Arrays.asList("Malostkowa", "Poznanska", "Krakowska");
	private final static List<String> M_CENA = Arrays.asList("670", "1200", "998");
	private final static List<String> M_OPIS = Arrays.asList("ladne", "brzydkie", "szare");
	
	//private final static double EPSILON = 1e-15;
	
	private final List<Mieszkania> listamieszkania = new ArrayList<Mieszkania>();
	private final List<Wynajmujacy> listawynajmujacy = new ArrayList<Wynajmujacy>();
	
	@Before
	public void getSavedData() {
		
		List<Mieszkania> mieszkania = mieszkaniaManager.getAllMieszkania();
		for(Mieszkania m : mieszkania) {
			listamieszkania.add(m);
		}
		
		List<Wynajmujacy> wynajmujacy = wynajmujacyManager.getAllWynajmujacy();
		for(Wynajmujacy w : wynajmujacy) {
			listawynajmujacy.add(w);
		}
	}
	
	@After
	public void deleteAddedData() {
		
		
		List<Wynajmujacy> allWynajmujacy = wynajmujacyManager.getAllWynajmujacy();
		boolean delete = true;
		
		for (Wynajmujacy w2 : allWynajmujacy) {
			for (Wynajmujacy w : listawynajmujacy) {
				if (w.getId() == w2.getId()) {
					delete = false;
					break;
				}
			}
			if (delete) {
				wynajmujacyManager.deleteWynajmujacy(w2);
			}
			delete = true;
		}
		
		List<Mieszkania> allMieszkania = mieszkaniaManager.getAllMieszkania();
		delete = true;
		
		for (Mieszkania m2 : allMieszkania) {
			for (Mieszkania m : listamieszkania) {
				if (m.getId() == m2.getId()) {
					delete = false;
					break;
				}
			}
			if (delete) {
				mieszkaniaManager.deleteMieszkania(m2);
			}
			delete = true;
		}
	}
	
	@Test
	public void addMieszkaniatest() {

		Mieszkania mieszkania = new Mieszkania(M_ULICA.get(0), M_CENA.get(0), M_OPIS.get(0));
		mieszkaniaManager.addMieszkania(mieszkania);
		
		Mieszkania kontrolerMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);

		assertEquals(M_ULICA.get(0), kontrolerMieszkania.getUlica());
		assertEquals(M_CENA.get(0), kontrolerMieszkania.getCena());
		assertEquals(M_OPIS.get(0), kontrolerMieszkania.getOpis());
	}
	
	

//	@Test
//	public void editMieszkaniatest() {
//
//		Mieszkania mieszkania = new Mieszkania(M_ULICA.get(0), M_CENA.get(0), M_OPIS.get(0));
//		mieszkaniaManager.addMieszkania(mieszkania);
//		
//		Mieszkania kontrolerMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);
//		
//		kontrolerMieszkania.setUlica(M_ULICA.get(1));
//		kontrolerMieszkania.setCena(M_CENA.get(1));
//		kontrolerMieszkania.setOpis(M_OPIS.get(1));
//		mieszkaniaManager.editMieszkania(kontrolerMieszkania);
//		
//		Mieszkania kontrolerEditedMieszkania = mieszkaniaManager.getMieszkaniaById(kontrolerMieszkania);
//		
//		assertEquals(M_ULICA.get(1), kontrolerEditedMieszkania.getUlica());
//		assertEquals(M_CENA.get(1), kontrolerEditedMieszkania.getCena());
//		assertEquals(M_OPIS.get(1), kontrolerEditedMieszkania.getOpis());
//
//		mieszkaniaManager.deleteMieszkania(kontrolerEditedMieszkania);
//		List<Mieszkania> newMieszkania = mieszkaniaManager.getAllMieszkania();
//		
//		assertEquals(savedMieszkania, newMieszkania);
//	}
//	
//	@Test
//	public void deleteMieszkaniatest() {
//		
//		int number = savedMieszkania.size();
//		
//		Mieszkania mieszkania = new Mieszkania(M_ULICA.get(0), M_CENA.get(0), M_OPIS.get(0));
//		mieszkaniaManager.addMieszkania(mieszkania);
//		
//		Mieszkania kontrolerMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);
//		
//		mieszkaniaManager.deleteMieszkania(kontrolerMieszkania);
//		
//		int newNumber = mieszkaniaManager.getAllMieszkania().size();
//		
//		assertEquals(number, newNumber);
//		
//		kontrolerMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);
//		
//		assertNull(kontrolerMieszkania);
//	}
//	
	
}
