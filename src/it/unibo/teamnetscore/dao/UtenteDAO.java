package it.unibo.teamnetscore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.Utente;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    private SessionFactory sessionFactory;

    public UtenteDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveUtente(Utente utente) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Utente getUtente(String username) {
    	Session session = null;
    	Utente result = null;
        try {
        	session = sessionFactory.openSession();
            result = (Utente) session.get(Utente.class, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
	public List<Utente> getAllUtenti() {
    	Session session = null;
    	List<Utente> result = new ArrayList<Utente>();
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from Utente").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateUtente(Utente utente) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUtente(Utente utente) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
