package ktyminski.spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ktyminski.spring.domain.*;

@Component
@Transactional
public class ImpWynajmujacyManager implements WynajmujacyManager{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSessionFactory() {
		return session;
	}
	
	public void setSessionFactory(SessionFactory session) {
		this.session = session;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Wynajmujacy> getAllWynajmujacy() {
		return session.getCurrentSession().getNamedQuery("wynajmujacy.wszyscy").list();
	}
	
	
	public Wynajmujacy getWynajmujacyById(Wynajmujacy wynajmujacy) {
		return (Wynajmujacy) session.getCurrentSession().get(Wynajmujacy.class, wynajmujacy.getId());
	}
	
	
	public void addWynajmujacy(Wynajmujacy wynajmujacy) {
		wynajmujacy.setId(null);
		session.getCurrentSession().persist(wynajmujacy);
	}
	
	
	public void editWynajmujacy(Wynajmujacy wynajmujacy) {
		session.getCurrentSession().update(wynajmujacy);
	}
	
	
	public void deleteWynajmujacy(Wynajmujacy wynajmujacy) {
		session.getCurrentSession().delete(wynajmujacy);
	}

	@Override
	public List<Wynajmujacy> getWynajmujacyByNazwisko(String nazwisko) {
	  return session.getCurrentSession().getNamedQuery("wynajmujacy.poimieniu").setString("nazwisko", nazwisko).list();
	}
}
