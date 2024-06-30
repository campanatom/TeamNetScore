package it.unibo.teamnetscore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.EventoComunicazioneGenerale;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class EventoComunicazioneGeneraleDAO {

    private SessionFactory sessionFactory;

    public EventoComunicazioneGeneraleDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveEventoComunicazioneGenerale(EventoComunicazioneGenerale eventoComunicazioneGenerale) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(eventoComunicazioneGenerale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public EventoComunicazioneGenerale getEventoComunicazioneGenerale(int id) {
    	Session session = null;
    	EventoComunicazioneGenerale result = null;
        try {
        	session = sessionFactory.openSession();
            result = (EventoComunicazioneGenerale) session.get(EventoComunicazioneGenerale.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
	public List<EventoComunicazioneGenerale> getAllEventiComunicazioneGenerale() {
    	Session session = null;
    	List<EventoComunicazioneGenerale> result = new ArrayList<EventoComunicazioneGenerale>();
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from EventoComunicazioneGenerale").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public void updateEventoComunicazioneGenerale(EventoComunicazioneGenerale eventoComunicazioneGenerale) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(eventoComunicazioneGenerale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEventoComunicazioneGenerale(EventoComunicazioneGenerale eventoComunicazioneGenerale) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(eventoComunicazioneGenerale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
