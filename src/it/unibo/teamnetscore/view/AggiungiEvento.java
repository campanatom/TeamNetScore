package it.unibo.teamnetscore.view;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.teamnetscore.model.Allenatore;
import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Evento;
import it.unibo.teamnetscore.model.EventoComunicazioneGenerale;
import it.unibo.teamnetscore.model.EventoGenerale;
import it.unibo.teamnetscore.model.Squadra;
import it.unibo.teamnetscore.model.TipoEvento;
import it.unibo.teamnetscore.model.Utente;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/aggiungievento")
public class AggiungiEvento extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
    	Squadra sq = allenatore.getSquadra();

    	
        String eventType = request.getParameter("event-type");
        String eventDescription = request.getParameter("event-description");
        String eventLocation = request.getParameter("event-location");
        String eventDate = request.getParameter("event-date");
        String eventTime = request.getParameter("event-time");
        
        EventoGenerale ev = null;
        Random random = new Random();
        
        System.out.println("Tipo di Evento: " + eventType);
        //System.out.println("Descrizione: " + eventDescription);
        if (!"Comunicazione".equals(eventType)) {
        	ev = new Evento(String.valueOf(random.nextInt(99999)), LocalDateTime.now(), eventDescription, sq,
        			LocalDate.parse(eventDate), LocalTime.parse(eventTime), eventLocation,
        			TipoEvento.valueOf(eventType.toUpperCase()));
        }
        else {
        	ev = new EventoComunicazioneGenerale(String.valueOf(random.nextInt(99999)), LocalDateTime.now(), 
        			eventDescription, sq);
        }
        
        sq.addEvento(ev);
        allenatore.setSquadra(sq);
        //Aggiornamento
        request.getSession().setAttribute("utente", allenatore);
        
       @SuppressWarnings("unchecked")
	Map<String, Utente> utentiRegistrati = (Map<String, Utente>) this.getServletContext().getAttribute("utentiRegistrati");
        
       //Aggiornamento di tutto il database
       
       /* //Aggiornamento dei calciatori con la lista eventi aggiornata
        for (Utente utente : utentiRegistrati.values()) {
            if (utente instanceof Calciatore) {
            	Calciatore c = (Calciatore) utente;
            	List<Squadra> squadre = c.getSquadre();
            	Iterator<Squadra> iterator = squadre.iterator();
                
                while (iterator.hasNext()) {
                    Squadra s = iterator.next();
                    if (s.getId().equals(sq.getId())) {
                        // Rimuovi la squadra obsoleta
                        iterator.remove();
                        // Aggiungi la nuova squadra
                        c.getSquadre().add(sq);
                        System.out.println("Squadra trovata e aggiornata");
                    }
                }
                
                utentiRegistrati.replace(c.getUsername(), c);
            }
           
           
        }
        
         this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
         */
        
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/calendarioallenatore.jsp");
	     rd.forward(request, response);
	     return;
        
    }
}
