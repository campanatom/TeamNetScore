package it.unibo.teamnetscore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.Calciatore;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CalciatoreDAO {

    private SessionFactory sessionFactory;

    public CalciatoreDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveCalciatore(Calciatore calciatore) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(calciatore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Calciatore getCalciatore(String username) {
    	Session session = null;
    	Calciatore calciatore = null;
        try {
        	session = sessionFactory.openSession();
            calciatore =  (Calciatore) session.get(Calciatore.class, username);
        }  catch (Exception e) {
        	e.printStackTrace();
        }
        
        return calciatore;
    }

    @SuppressWarnings("unchecked")
	public List<Calciatore> getAllCalciatori() {
    	Session session = null;
    	List<Calciatore> result = new ArrayList<Calciatore>();
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from Calciatore").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public void updateCalciatore(Calciatore calciatore) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(calciatore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCalciatore(Calciatore calciatore) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(calciatore);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
