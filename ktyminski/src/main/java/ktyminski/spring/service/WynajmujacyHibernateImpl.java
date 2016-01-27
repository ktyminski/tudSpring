package ktyminski.spring.service;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import ktyminski.spring.domain.Wynajmujacy;


import java.util.List;


@Component
@Transactional
public class WynajmujacyHibernateImpl implements WynajmujacyManager {

    @Autowired
    private SessionFactory sf;

    public SessionFactory getSessionFactory() {
        return sf;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

   
    public Wynajmujacy pobierzWynajmujacyPoId(Long id) {
        return (Wynajmujacy) sf.getCurrentSession().get(Wynajmujacy.class, id);
    }

    

    public Long dodaj(Wynajmujacy wynajmujacy) {
        Long id = (Long) sf.getCurrentSession().save(wynajmujacy);
        wynajmujacy.setId(id);
        return id;
    }

  
    
public void edytuj(Wynajmujacy w, String imie, String nazwisko, long pesel) {
        w = (Wynajmujacy) sf.getCurrentSession().get(Wynajmujacy.class, w.getId());
        w.setImie(imie);
        w.setNazwisko(nazwisko);
        w.setPesel(pesel);
        sf.getCurrentSession().update(w);
    }

    

    public void usun(Wynajmujacy w) {
        w = (Wynajmujacy) sf.getCurrentSession().get(Wynajmujacy.class, w.getId());
        sf.getCurrentSession().delete(w);
    }

   
    @SuppressWarnings("unchecked")
    public List<Wynajmujacy> dajWszystkieWynajmujacy() {
        return sf.getCurrentSession().getNamedQuery("wynajmujacy.wszystkie").list();
    }


	
}
