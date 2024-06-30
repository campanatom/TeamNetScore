package test;

import it.unibo.teamnetscore.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Configura Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Inizia la transazione
            transaction = session.beginTransaction();

          //popolazione del database degli utenti registrati
    		Allenatore a1 = new Allenatore();
    		a1.setNome("Tommaso");;
    		a1.setCognome("Campana");
    		a1.setPassword("pistacchio");
    		a1.setUsername("tomcampana");
    		a1.setRuolo(Ruolo.ALLENATORE);
    		
    		Allenatore a2 = new Allenatore();
    		a2.setNome("Jack");;
    		a2.setCognome("Coacheer");
    		a2.setPassword("pistacchio");
    		a2.setUsername("jack00");
    		a2.setRuolo(Ruolo.ALLENATORE);
    		
    		Calciatore c1 = new Calciatore();
    		c1.setNome("Alessandro");;
    		c1.setCognome("Quaranta");
    		c1.setPassword("pistacchio");
    		c1.setUsername("alequaranta");
    		c1.setRuolo(Ruolo.CALCIATORE);
    		
    		Calciatore c2 = new Calciatore();
    		c2.setNome("Gaia");;
    		c2.setCognome("Verzelli");
    		c2.setPassword("pistacchio");
    		c2.setUsername("gaiaverzelli");
    		c2.setRuolo(Ruolo.CALCIATORE);
    		
    		Calciatore c3 = new Calciatore();
    		c3.setNome("Lorenzo");;
    		c3.setCognome("Insigne");
    		c3.setPassword("pistacchio");
    		c3.setUsername("loreinsigne");
    		c3.setRuolo(Ruolo.CALCIATORE);
    		
    		Calciatore c4 = new Calciatore();
    		c4.setNome("Francesco");;
    		c4.setCognome("Totti");
    		c4.setPassword("pistacchio");
    		c4.setUsername("fratotti");
    		c4.setRuolo(Ruolo.CALCIATORE);
    		
    		List<Calciatore> list1 = new ArrayList<Calciatore>();
    		list1.add(c1);
    		list1.add(c2);
    		
    		List<Calciatore> list2 = new ArrayList<Calciatore>();
    		list2.add(c1);
    		list2.add(c3);
    		list2.add(c4);
    	          
            // Creazione delle squadre
            Squadra squadra1 = new Squadra("001", "SanDonato Calcio", null, "descrizione", a1, list1, Categoria.CALCIO_A_SETTE);
            Squadra squadra2 = new Squadra("002", "prova", null, "descri2", a2, list2, Categoria.CALCIO_A_UNDICI);
            session.save(squadra1);
            session.save(squadra2);

            // Creazione degli eventi
            EventoGenerale eventoGen1 = new EventoComunicazioneGenerale("E1", LocalDateTime.now(), "Allenamento pre-campionato", squadra1);
            EventoGenerale eventoGen2 = new EventoComunicazioneGenerale("E2", LocalDateTime.now(), "Riunione strategica", squadra2);
            session.save(eventoGen1);
            session.save(eventoGen2);

            // Creazione di eventi specifici
            Evento evento1 = new Evento("E3", LocalDateTime.now(), "Partita di campionato", squadra1, LocalDate.of(2024, 7, 1), LocalTime.of(20, 30), "Stadio Olimpico", TipoEvento.PARTITA);
            Evento evento2 = new Evento("E4", LocalDateTime.now(), "Partita amichevole", squadra2, LocalDate.of(2024, 7, 6), LocalTime.of(18, 0), "San Siro", TipoEvento.PARTITA);
            session.save(evento1);
            session.save(evento2);

            /*// Creazione delle presenze
            Presenza presenza1 = new Presenza(calciatore1, evento1);
            Presenza presenza2 = new Presenza(calciatore2, evento1);
            Presenza presenza3 = new Presenza(calciatore1, evento2);
            Presenza presenza4 = new Presenza(calciatore3, evento3);
            session.save(presenza1);
            session.save(presenza2);
            session.save(presenza3);
            session.save(presenza4);
			*/
            
            // Esegui la transazione
            transaction.commit();

            // Stampa i dati popolati per verifica
            @SuppressWarnings("unchecked")
			List<Squadra> squadre = session.createQuery("from Squadra").list();
            System.out.println("Squadre nel database:");
            for (Squadra s : squadre) {
                System.out.println("Nome: " + s.getNomeSquadra());
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
