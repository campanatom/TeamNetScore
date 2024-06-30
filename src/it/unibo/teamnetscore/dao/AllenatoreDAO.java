package it.unibo.teamnetscore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.Allenatore;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AllenatoreDAO {

    private SessionFactory sessionFactory;

    public AllenatoreDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveAllenatore(Allenatore allenatore) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(allenatore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Allenatore getAllenatore(String username) {
    	Session session = null;
    	Allenatore allenatore = null;
        try {
        	session = sessionFactory.openSession();
            allenatore = (Allenatore) session.get(Allenatore.class, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return allenatore;
    }

    @SuppressWarnings("unchecked")
	public List<Allenatore> getAllAllenatori() {
    	List<Allenatore> result = new ArrayList<Allenatore>();
    	Session session = null;
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from Allenatore").list();
        }  catch (Exception e) {
            e.printStackTrace();
        }
           
        return result;
    }

    public void updateAllenatore(Allenatore allenatore) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(allenatore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAllenatore(Allenatore allenatore) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(allenatore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
