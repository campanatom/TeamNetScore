package it.unibo.teamnetscore.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.EventoGenerale;
import it.unibo.teamnetscore.model.Squadra;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class EventoGeneraleDAO {

    private SessionFactory sessionFactory;

    public EventoGeneraleDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveEventoGenerale(EventoGenerale eventoGenerale) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(eventoGenerale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public EventoGenerale getEventoGenerale(int id) {
    	Session session = null;
    	EventoGenerale result = null;
        try {
        	session = sessionFactory.openSession();
            result = (EventoGenerale) session.get(EventoGenerale.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
	public List<EventoGenerale> getAllEventiGenerali() {
    	Session session = null;
    	List<EventoGenerale> result = new ArrayList<EventoGenerale>();
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from EventoGenerale").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateEventoGenerale(EventoGenerale eventoGenerale) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(eventoGenerale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEventoGenerale(EventoGenerale eventoGenerale) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(eventoGenerale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@SuppressWarnings("unchecked")
	public List<EventoGenerale> getEventiBySquadra(Squadra squadra) {
		
		List<EventoGenerale> result = new ArrayList<EventoGenerale>();
    	Session session = null;
        try {
        	session = sessionFactory.openSession();
        	Query q = session.createQuery("from evento E where E.squadra_id = ?");
        	q.setString(0, squadra.getId());
            result = q.list();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        
        return result;	
	}
}
