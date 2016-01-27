package ktyminski.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


import ktyminski.spring.domain.*;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "wynajmujacy.wszystkie", query = "Select w from Wynajmujacy w"),

})
public class Wynajmujacy {
	
	private Long id;
	private String imie = "";
	private String nazwisko = "";
	private Long pesel;

	private List<Mieszkania> mieszkania = new ArrayList<Mieszkania>();


	public Wynajmujacy() {
	}
	
	public Wynajmujacy(Long id, String imie, String nazwisko, Long pesel) {
	this.imie = imie;
	this.nazwisko = nazwisko;
	this.pesel = pesel;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	@OneToMany(mappedBy = "wynajmujacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Mieszkania> getMieszkania() {
	return mieszkania;
	}
	public void setMieszkania(List<Mieszkania> mieszkania) {
	this.mieszkania = mieszkania;
	}

	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}

	
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}


	public Long getPesel() {
		return pesel;
	}
	public void setPesel(Long pesel) {
		this.pesel = pesel;
	}

	



}
