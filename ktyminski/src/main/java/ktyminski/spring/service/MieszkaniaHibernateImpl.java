package ktyminski.spring.service;



	import org.hibernate.SessionFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;
	import org.springframework.transaction.annotation.Transactional;

import ktyminski.spring.domain.Mieszkania;
import ktyminski.spring.domain.Wynajmujacy;

import java.util.ArrayList;
	import java.util.List;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	@Component
	@Transactional
	public class MieszkaniaHibernateImpl implements MieszkaniaManager {

	    @Autowired
	    private SessionFactory sf;

	    public SessionFactory getSessionFactory() {
	        return sf;
	    }

	    public void setSessionFactory(SessionFactory sf) {
	        this.sf = sf;
	    }

	    public Mieszkania pobierzMieszkaniaPoId(Long id) {
	        return (Mieszkania) sf.getCurrentSession().get(Mieszkania.class, id);
	    }

	 

	    public Long dodaj(Mieszkania mieszkania) {
	        Long id = (Long) sf.getCurrentSession().save(mieszkania);
	        mieszkania.setId(id);
	        Wynajmujacy wynajmujacy = (Wynajmujacy) sf.getCurrentSession().get(Wynajmujacy.class, mieszkania.getWynajmujacy().getId());
	        wynajmujacy.getMieszkania().add(mieszkania);
	        return id;
	    }

	

	    public void edytuj(Mieszkania m, Wynajmujacy wynajmujacy, String ulica, Integer cena, String opis) {
	        m = (Mieszkania) sf.getCurrentSession().get(Mieszkania.class, m.getId());
	        Wynajmujacy w = (Wynajmujacy) sf.getCurrentSession().get(Wynajmujacy.class, m.getWynajmujacy().getId());
	        int i = 0;
	        for(Mieszkania mieszkanie : w.getMieszkania()) {
	            if (mieszkanie == m)
	                break;
	            i++;
	        }
	        m.setWynajmujacy(wynajmujacy);
	        m.setUlica(ulica);
	        m.setCena(cena);
	        m.setOpis(opis);
	        w.getMieszkania().set(i, m);
	        sf.getCurrentSession().update(m);
	    }
	    


	    public void usun(Mieszkania m) {
	        m = (Mieszkania) sf.getCurrentSession().get(Mieszkania.class, m.getId());
	        Wynajmujacy w = (Wynajmujacy) sf.getCurrentSession().get(Wynajmujacy.class,m.getWynajmujacy().getId());
	        w.getMieszkania().remove(m);
	        sf.getCurrentSession().delete(m);
	    }
    

	    @SuppressWarnings("unchecked")
	    public List<Mieszkania> dajWszystkieMieszkania() {
	        return sf.getCurrentSession().getNamedQuery("mieszkania.wszystkie").list();
	    }

	   

	    public List<Mieszkania> wyszukajMieszkaniayWgWynajmujacy(String porownaj){
	        List<Mieszkania> lm = new ArrayList<Mieszkania>();
	        Pattern p = Pattern.compile(".*"+porownaj+".*");
	        Matcher ma;
	        for(Mieszkania m : dajWszystkieMieszkania())
	        {
	            ma = p.matcher(m.getWynajmujacy().getNazwisko());
	            if(ma.matches())
	                lm.add(m);
	        }
	        return lm;
	    }

	    public List<Mieszkania> wyszukajMieszkania(Wynajmujacy w){
	        List<Mieszkania> mie = dajWszystkieMieszkania();
	        List<Mieszkania> m = new ArrayList<Mieszkania>();
	        for (Mieszkania mieszkanie : mie)
	            if(mieszkanie.getWynajmujacy().getId() == w.getId())
	                m.add(mieszkanie);
	        return m;
	    }

	    public void usunZaleznosci(Wynajmujacy w){
	        List<Mieszkania> mieszkania = dajWszystkieMieszkania();
	        for (Mieszkania mieszkanie : mieszkania)
	        {
	            if(mieszkanie.getWynajmujacy().getId() == w.getId())
	                usun(mieszkanie);
	        }
	    }

		
	

		
		
	}

