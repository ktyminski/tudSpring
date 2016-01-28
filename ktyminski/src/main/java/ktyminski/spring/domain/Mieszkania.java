package ktyminski.spring.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


import ktyminski.spring.domain.Wynajmujacy;
import ktyminski.spring.domain.Mieszkania;

@Entity
@NamedQueries({
	@NamedQuery(name = "mieszkania.wszyscy", query = "Select m from Mieszkania m"),
	
})
public class Mieszkania {

	private Long id;
 	private Wynajmujacy wynajmujacy;
	private String ulica="";
	private String cena="";
	private String opis="";

	 public Mieszkania() {
	}

	public Mieszkania(Wynajmujacy wynajmujacy, String ulica , String cena, String opis)
	{
	this.wynajmujacy = wynajmujacy;
	this.ulica = ulica;
	this.cena = cena;
	this.opis = opis;
	}
	
	public Mieszkania( String ulica , String cena, String opis)
	{
	
	this.ulica = ulica;
	this.cena = cena;
	this.opis = opis;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "id_wynajmujacy", nullable = false)
	
	public Wynajmujacy getWynajmujacy() {
	return wynajmujacy;
	}
	public void setWynajmujacy(Wynajmujacy wynajmujacy) 
	{ 
		this.wynajmujacy=wynajmujacy; 
		}
	
	
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}


	public String getCena() {
		return cena;
	}
	public void setCena(String cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
}


