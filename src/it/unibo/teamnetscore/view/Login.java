package it.unibo.teamnetscore.view;

import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import it.unibo.teamnetscore.model.Utente;
import jakarta.servlet.annotation.WebServlet;
import it.unibo.teamnetscore.model.Allenatore;
import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Categoria;
import it.unibo.teamnetscore.model.Evento;
import it.unibo.teamnetscore.model.EventoComunicazioneGenerale;
import it.unibo.teamnetscore.model.EventoGenerale;
import it.unibo.teamnetscore.model.Ruolo;
import it.unibo.teamnetscore.model.Squadra;
import it.unibo.teamnetscore.model.TipoEvento;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import javax.servlet.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	@Override
	public void init(ServletConfig config) throws ServletException 
	{ 
		super.init(config);
		
		@SuppressWarnings("unchecked")
		Map<String, Utente> utentiRegistrati = (Map<String, Utente>) this.getServletContext().getAttribute("utentiRegistrati");
		
		if(utentiRegistrati==null) {
        
			utentiRegistrati = new HashMap<String, Utente>();
        
		}
		
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
		
		Allenatore a3 = new Allenatore();
		a3.setNome("Luciano");;
		a3.setCognome("Spalletti");
		a3.setPassword("pistacchio");
		a3.setUsername("spalletti");
		a3.setRuolo(Ruolo.ALLENATORE);
		
		List<Calciatore> list1 = new ArrayList<Calciatore>();
		list1.add(c1);
		list1.add(c2);
		
		List<Calciatore> list2 = new ArrayList<Calciatore>();
		list2.add(c1);
		list2.add(c3);
		list2.add(c4);
	          
        // Creazione delle squadre
        Squadra squadra1 = new Squadra("#0001", "San Donato Calcio", null, "A Bologna nel secondo dopoguerra, con un grande lavoro"
        		+ " di volontariato, nasce la Casa del Popolo Leonildo Corazza per aggregare le persone del quartiere. Le attività"
        		+ " sportive sono da subito organizzate nella zona e nasce la SAN DONATO CALCIO.", a1, list1, Categoria.CALCIO_A_SETTE);
        Squadra squadra2 = new Squadra("#0002", "Mezzana Calcio", null, "Composta da una miscela di giovani entusiasti e veterani dal fiuto per il gol,"
        		+ " sono più che una squadra: sono una famiglia, sono la famiglia del MEZZANA CALCIO.", a2, list2, Categoria.CALCIO_A_UNDICI);
   
        // Creazione degli eventi
        EventoGenerale eventoGen1 = new EventoComunicazioneGenerale("E1", LocalDateTime.now(), "Allenamento cancellato!", squadra1);
        EventoGenerale eventoGen2 = new EventoComunicazioneGenerale("E2", LocalDateTime.now(), "Riunione strategica", squadra2);

        // Creazione di eventi specifici
        EventoGenerale evento1 = new Evento("E3", LocalDateTime.now(), "Partita di campionato", squadra1, LocalDate.of(2024, 7, 10), LocalTime.of(20, 30), "Stadio Olimpico", TipoEvento.PARTITA);
        EventoGenerale evento2 = new Evento("E4", LocalDateTime.now(), "Partita amichevole", squadra2, LocalDate.of(2024, 7, 3), LocalTime.of(18, 0), "San Siro", TipoEvento.PARTITA);
        EventoGenerale evento3 = new Evento("E5", LocalDateTime.now(), "Festeggiamento vittoria", squadra2, LocalDate.of(2024, 7, 2), LocalTime.of(20, 0), "Pizzeria chebontà", TipoEvento.CENA_DI_SQUADRA);
        
		squadra1.addEvento(eventoGen1);
		squadra1.addEvento(evento1);
		
		squadra2.addEvento(eventoGen2);
		squadra2.addEvento(evento2);
		squadra2.addEvento(evento3);
		
		a1.setSquadra(squadra1);
		a2.setSquadra(squadra2);
		
		c1.addSquadra(squadra1);
		c1.addSquadra(squadra2);
		c2.addSquadra(squadra1);
		c3.addSquadra(squadra2);
		c4.addSquadra(squadra2);
		
		utentiRegistrati.put(a1.getUsername(), a1);
		utentiRegistrati.put(a2.getUsername(), a2);
		utentiRegistrati.put(c1.getUsername(), c1);
		utentiRegistrati.put(c2.getUsername(), c2);
		utentiRegistrati.put(c3.getUsername(), c3);
		utentiRegistrati.put(c4.getUsername(), c4);
		utentiRegistrati.put(a3.getUsername(), a3);
		
		//settaggio dell'attributo utentiregistrati della servlet
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);
	}
	
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		 
		 return;
	 }

	 
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		 
		 //recupero parametri dal form
		 String username = req.getParameter("username");
		 String password = req.getParameter("password");
		 
		 @SuppressWarnings("unchecked")
		 Map<String, Utente> utentiRegistrati = (Map<String, Utente>) this.getServletContext().getAttribute("utentiRegistrati");
		
		 HttpSession session = req.getSession();
		 
		 //check credenziali login
		 Utente u = utentiRegistrati.get(username);
	
		 if(u != null)
		 {
			 if(password.equals(u.getPassword())) {
				 
				 //Utente è un attributo di sessione.
		 
				 //indirizzamento alla home
				 if(u.getRuolo()==Ruolo.ALLENATORE) {
				  
					 Allenatore a = (Allenatore) u;
					 session.setAttribute("utente", a);
					 
				  RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeallenatore.jsp");
				  rd.forward(req, res);
				  return;
				 }
				 else if(u.getRuolo()==Ruolo.CALCIATORE) {
					 
					 Calciatore c = (Calciatore) u;
					 session.setAttribute("utente", c);
				   RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homecalciatore.jsp");
				   rd.forward(req, res);
				   return; 
				 }
				 
			 }
			 else {
				 
				 this.getServletContext().setAttribute("errorLogin", true);
				 
			     RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.html");
			     rd.forward(req, res);
			     return;
			 }
			 
			 
		 }
		 else {
		 
			 this.getServletContext().setAttribute("errorLogin", true);
			 
		     RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.html");
		     rd.forward(req, res);
		     return;
		 }
		 
	 }
}
