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
	public void addMieszkania() {

		Mieszkania mieszkania = new Mieszkania(M_ULICA.get(0), M_CENA.get(0), M_OPIS.get(0));
		mieszkaniaManager.addMieszkania(mieszkania);
		
		Mieszkania foundMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);

		assertEquals(M_ULICA.get(0), foundMieszkania.getUlica());
		assertEquals(M_CENA.get(0), foundMieszkania.getCena());
		assertEquals(M_OPIS.get(0), foundMieszkania.getOpis());
	}

//	@Test
//	public void editMieszkania() {
//
//		Mieszkania mieszkania = new Mieszkania(M_ULICA.get(0), M_CENA.get(0), M_OPIS.get(0));
//		mieszkaniaManager.addMieszkania(mieszkania);
//		
//		Mieszkania foundMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);
//		
//		foundMieszkania.setUlica(M_ULICA.get(1));
//		foundMieszkania.setCena(M_CENA.get(1));
//		foundMieszkania.setOpis(M_OPIS.get(1));
//		mieszkaniaManager.editMieszkania(foundMieszkania);
//		
//		Mieszkania foundEditedMieszkania = mieszkaniaManager.getMieszkaniaById(foundMieszkania);
//		
//		assertEquals(M_ULICA.get(1), foundEditedMieszkania.getUlica());
//		assertEquals(M_CENA.get(1), foundEditedMieszkania.getCena());
//		assertEquals(M_OPIS.get(1), foundEditedMieszkania.getOpis());
//
//		mieszkaniaManager.deleteMieszkania(foundEditedMieszkania);
//		List<Mieszkania> newMieszkania = mieszkaniaManager.getAllMieszkania();
//		
//		assertEquals(savedMieszkania, newMieszkania);
//	}
//	
//	@Test
//	public void deleteMieszkania() {
//		
//		int number = savedMieszkania.size();
//		
//		Mieszkania mieszkania = new Mieszkania(M_ULICA.get(0), M_CENA.get(0), M_OPIS.get(0));
//		mieszkaniaManager.addMieszkania(mieszkania);
//		
//		Mieszkania foundMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);
//		
//		mieszkaniaManager.deleteMieszkania(foundMieszkania);
//		
//		int newNumber = mieszkaniaManager.getAllMieszkania().size();
//		
//		assertEquals(number, newNumber);
//		
//		foundMieszkania = mieszkaniaManager.getMieszkaniaById(mieszkania);
//		
//		assertNull(foundMieszkania);
//	}
//	
	/*
	@Test
	public void addMieszkaniaToWynajmujacy() {
		
		Wynajmujacy wynajmujacy = new Wynajmujacy(ODDZIAL_MIASTO, ODDZIAL_ULICA, ODDZIAL_NR);
		wynajmujacyManager.addWynajmujacy(wynajmujacy);
		
		int n = mieszkaniaManager.get(wynajmujacy).size();
		for (int i=0; i<3; i++) {
			Ingredient ing = new Ingredient(ING_NAMES.get(i), ING_KINDS.get(i));
			ingManager.addIngredientToCake(ing, cake);
		}
		
		List<Ingredient> ingsOfCake = ingManager.getIngredientOfCake(cake);
		
		int i = 0;
		for (Ingredient ing : ingsOfCake) {
			assertEquals(ING_NAMES.get(i), ing.getName());
			assertEquals(ING_KINDS.get(i), ing.getKind());
			i++;
		}
		
		assertEquals(n+3, ingManager.getIngredientOfCake(cake).size());
	}
	
	@Test
	public void removeIngFromCakeCheck() {
		
		Cake cake = new Cake(CAKE_NAME, CAKE_PRICE, CAKE_WEIGHT);
		cakeManager.addCake(cake);
		
		Ingredient ing = new Ingredient(ING_NAMES.get(0), ING_KINDS.get(0));
		
		for (int i=0; i<3; i++) {
			ing = new Ingredient(ING_NAMES.get(i), ING_KINDS.get(i));
			ingManager.addIngredientToCake(ing, cake);
		}
		
		int n = ingManager.getIngredientOfCake(cake).size();
		
		ingManager.removeIngredientFromCake(ing, cake);
		
		assertEquals(n-1, ingManager.getIngredientOfCake(cake).size());
	}
	*/
}
