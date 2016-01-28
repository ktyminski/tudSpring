package ktyminski.spring.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ktyminski.spring.domain.Mieszkania;
import ktyminski.spring.domain.Wynajmujacy;
import ktyminski.spring.service.WynajmujacyManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class WynajmujacyTest {
	
	@Autowired
	WynajmujacyManager wynajmujacyManager;

	private final static List<String> IMIE = Arrays.asList("janusz", "tadeusz", "mariusz");
	private final static List<String> NAZWISKO = Arrays.asList("kopiczko", "Twardowski", "Nowak");
	private final static List<String> PESEL = Arrays.asList("12", "10", "123");
	
	
	
	private final List<Wynajmujacy> listawynajmujacy = new ArrayList<Wynajmujacy>();

	@Before
	public void getSavedWynajmujacy() {
		
		List<Wynajmujacy> wynajmujacy = wynajmujacyManager.getAllWynajmujacy();
		for(Wynajmujacy w : wynajmujacy) {
			listawynajmujacy.add(w);
		}
	}
	
	@After
	public void deleteAddedWynajmujacytest() {
		
		List<Wynajmujacy> allWynajmujacy = wynajmujacyManager.getAllWynajmujacy();
		boolean delete = true;
		
		for (Wynajmujacy w : listawynajmujacy) {
			for (Wynajmujacy w2 : allWynajmujacy) {
				if (w.getId() == w2.getId()) {
					delete = false;
					break;
				}
			}
			if (delete) {
				wynajmujacyManager.deleteWynajmujacy(w);
			}
			delete = true;
		}
	}
	
	@Test
	public void addWynajmujacytest() {

		Wynajmujacy wynajmujacy = new Wynajmujacy(IMIE.get(0), NAZWISKO.get(0), PESEL.get(0));
		wynajmujacyManager.addWynajmujacy(wynajmujacy);
		
		Wynajmujacy kontrolerWynajmujacy = wynajmujacyManager.getWynajmujacyById(wynajmujacy);

		assertEquals(IMIE.get(0), kontrolerWynajmujacy.getImie());
		assertEquals(NAZWISKO.get(0), kontrolerWynajmujacy.getNazwisko());
		assertEquals(PESEL.get(0), kontrolerWynajmujacy.getPesel());
	}

	@Test
	public void editWynajmujacytest() {

		Wynajmujacy wynajmujacy = new Wynajmujacy(IMIE.get(0), NAZWISKO.get(0), PESEL.get(0));
		wynajmujacyManager.addWynajmujacy(wynajmujacy);
		
		Wynajmujacy kontrolerWynajmujacy = wynajmujacyManager.getWynajmujacyById(wynajmujacy);
		
		kontrolerWynajmujacy.setImie(IMIE.get(1));
		kontrolerWynajmujacy.setNazwisko(NAZWISKO.get(1));
		kontrolerWynajmujacy.setPesel(PESEL.get(1));
		wynajmujacyManager.editWynajmujacy(kontrolerWynajmujacy);
		
		Wynajmujacy kontrolerEditedWynajmujacy = wynajmujacyManager.getWynajmujacyById(kontrolerWynajmujacy);
		
		assertEquals(IMIE.get(1), kontrolerEditedWynajmujacy.getImie());
		assertEquals(NAZWISKO.get(1), kontrolerEditedWynajmujacy.getNazwisko());
		assertEquals(PESEL.get(1), kontrolerEditedWynajmujacy.getPesel());

		wynajmujacyManager.deleteWynajmujacy(kontrolerEditedWynajmujacy);
		List<Wynajmujacy> newWynajmujacy = wynajmujacyManager.getAllWynajmujacy();
		
		assertEquals(listawynajmujacy, newWynajmujacy);
	}
	
	@Test
	public void deleteWynajmujacytest() {
		
		int number = listawynajmujacy.size();
		
		Wynajmujacy wynajmujacy = new Wynajmujacy(IMIE.get(0), NAZWISKO.get(0), PESEL.get(0));
		wynajmujacyManager.addWynajmujacy(wynajmujacy);
		
		Wynajmujacy kontrolerWynajmujacy = wynajmujacyManager.getWynajmujacyById(wynajmujacy);
		
		wynajmujacyManager.deleteWynajmujacy(kontrolerWynajmujacy);
		
		int newNumber = wynajmujacyManager.getAllWynajmujacy().size();
		
		assertEquals(number, newNumber);
	}
	
	
	
	@Test
	public void getByNazwiskotest() {

		int n = wynajmujacyManager.getWynajmujacyByNazwisko(IMIE.get(0)).size();
		
		Wynajmujacy wynajmujacy = new Wynajmujacy(IMIE.get(0), NAZWISKO.get(0), PESEL.get(0));
		wynajmujacyManager.addWynajmujacy(wynajmujacy);
		
		wynajmujacy = new Wynajmujacy(IMIE.get(1), NAZWISKO.get(0), PESEL.get(1));
		wynajmujacyManager.addWynajmujacy(wynajmujacy);
		
		List<Wynajmujacy> porownanie = wynajmujacyManager.getWynajmujacyByNazwisko(wynajmujacy.getNazwisko());
		
		for(Wynajmujacy m : porownanie) {
			assertEquals(NAZWISKO.get(0), m.getNazwisko());
		}
		assertEquals(n+2, porownanie.size());
	}

}
