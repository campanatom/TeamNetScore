package it.unibo.teamnetscore.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import it.unibo.teamnetscore.hibernate.util.HibernateUtil;
import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Evento;
import it.unibo.teamnetscore.model.Presenza;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PresenzaDAO {

    private SessionFactory sessionFactory;

    public PresenzaDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void savePresenza(Presenza presenza) {
        Transaction transaction = null;
        Session session = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(presenza);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Presenza getPresenza(int id) {
    	Session session = null;
    	Presenza result = null;
        try {
        	session = sessionFactory.openSession();
            result = (Presenza) session.get(Presenza.class, id);
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        
        return result;
    }

    @SuppressWarnings("unchecked")
	public List<Presenza> getAllPresenze() {
    	List<Presenza> result = new ArrayList<Presenza>();
    	Session session = null;
        try {
        	session = sessionFactory.openSession();
            result = session.createQuery("from Presenza").list();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        
        return result;
    }

    public void updatePresenza(Presenza presenza) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(presenza);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePresenza(Presenza presenza) {
    	Session session = null;
        Transaction transaction = null;
        try {
        	session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(presenza);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    //Definiti ad-hoc
	@SuppressWarnings("unchecked")
	public List<Presenza> getPresenzeByEvento(Evento evento) {
		List<Presenza> result = new ArrayList<Presenza>();
    	Session session = null;
        try {
        	session = sessionFactory.openSession();
        	Query q = session.createQuery("from Presenza P where P.evento_id = ?");
        	q.setString(0, evento.getId());
            result = q.list();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        
        return result;
		
	}

	public Presenza getPresenzaByIds(Evento evento, Calciatore calciatore) {
		Presenza result = null;
    	Session session = null;
        try {
        	session = sessionFactory.openSession();
        	Query q = session.createQuery("from Presenza P where P.calciatore_id = ? and P.evento_id = ?");
        	q.setString(0, calciatore.getUsername());
        	q.setString(1, evento.getId());
            result =  (Presenza) q.uniqueResult();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
        
        return result;
	}
}
