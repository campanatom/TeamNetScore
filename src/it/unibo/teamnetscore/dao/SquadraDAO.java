package it.unibo.teamnetscore.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.Squadra;

import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class SquadraDAO {

    private SessionFactory sessionFactory;

    public SquadraDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveSquadra(Squadra squadra) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(squadra);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Squadra getSquadra(String id) {
    	Session session = null;
    	Squadra result = null;
        try {
        	session = sessionFactory.openSession();
            result = (Squadra) session.get(Squadra.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
	public List<Squadra> getAllSquadre() {
    	Session session = null;
    	List<Squadra> result = new ArrayList<Squadra>();
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from Squadra").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateSquadra(Squadra squadra) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(squadra);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteSquadra(Squadra squadra) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(squadra);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
