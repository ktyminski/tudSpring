package ktyminski.spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ktyminski.spring.domain.*;


@Component
@Transactional
public class ImpMieszkanieManager implements MieszkaniaManager{
	
	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSessionFactory() {
		return session;
	}
	
	public void setSessionFactory(SessionFactory session) {
		this.session = session;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Mieszkania> getAllMieszkania() {
		return session.getCurrentSession().getNamedQuery("mieszkania.wszyscy").list();
	}
	
	
	public Mieszkania getMieszkaniaById(Mieszkania mieszkania) {
		return (Mieszkania) session.getCurrentSession().get(Mieszkania.class, mieszkania.getId());
	}
	
	
	public List<Mieszkania> getOddzialOfMieszkania(Wynajmujacy wynajmujacy) {
		Wynajmujacy w = (Wynajmujacy) session.getCurrentSession().get(Wynajmujacy.class, wynajmujacy.getId());
		return w.getMieszkania();
	}
	
	
	public void addMieszkania(Mieszkania mieszkania) {
		mieszkania.setId(null);
		session.getCurrentSession().persist(mieszkania);
	}
	
	
	public void editMieszkania(Mieszkania mieszkania) {
		session.getCurrentSession().update(mieszkania);
	}
	
	
	public void deleteMieszkania(Mieszkania mieszkania) {
		session.getCurrentSession().delete(mieszkania);
	}
	
	
	public void addOddzialToMieszkania(Mieszkania mieszkania, Wynajmujacy wynajmujacy) {
		Wynajmujacy w = (Wynajmujacy) session.getCurrentSession().get(Wynajmujacy.class, wynajmujacy.getId());
		w.getMieszkania().add(mieszkania);
	}

	
//	public void deleteOddzialFromMieszkania(Mieszkania mieszkania, Oddzial oddzial) {
//		Oddzial o = (Oddzial) session.getCurrentSession().get(Oddzial.class, oddzial.getId());
//		o.getPracownicy().delete(mieszkania);
//	}

}
