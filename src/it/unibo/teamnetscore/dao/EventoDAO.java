package it.unibo.teamnetscore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.Evento;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private SessionFactory sessionFactory;

    public EventoDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveEvento(Evento evento) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Evento getEvento(int id) {
    	Session session = null;
    	Evento result = null;
        try {
        	session = sessionFactory.openSession();
            result =  (Evento) session.get(Evento.class, id);
        }  catch (Exception e) {
        e.printStackTrace();
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
	public List<Evento> getAllEventi() {
    	List<Evento> result = new ArrayList<Evento>();
    	Session session = null;
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from Evento").list();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateEvento(Evento evento) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteEvento(Evento evento) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
