package it.unibo.teamnetscore.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.teamnetscore.model.Utente;
import jakarta.servlet.annotation.WebServlet;
import it.unibo.teamnetscore.model.Allenatore;
import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Ruolo;

@WebServlet("/register")
public class Registration extends HttpServlet{
	
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
		
		//settaggio dell'attributo utentiregistrati della servlet
		this.getServletContext().setAttribute("utentiRegistrati", utentiRegistrati);	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
	}
	
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
		
	
	 String nome = req.getParameter("firstname");
	 String cognome = req.getParameter("lastname");
	 String username = req.getParameter("username");
	 String password = req.getParameter("password");
	 String ruolo = req.getParameter("role");
	 
	
	// HttpSession session = req.getSession();
	 
	 @SuppressWarnings("unchecked")
	Map<String, Utente> utentiRegistrati = (Map<String, Utente>) this.getServletContext().getAttribute("utentiRegistrati");
	 
	 if(utentiRegistrati.get(username)!=null) {
		 
		 return;	 
	 }
	 
	 //creo e registro l'utente
	 else {
		 if(ruolo.equals("coach")) {
			 
			 Allenatore a = new Allenatore();
			 a.setNome(nome);
			 a.setCognome(cognome);
			 a.setPassword(password);
			 a.setUsername(username);
			 a.setRuolo(Ruolo.ALLENATORE);
			 
			 utentiRegistrati.put(a.getUsername(), a);
			 
		 }
		 else if(ruolo.equals("player")) {
			 
			 Calciatore c = new Calciatore();
			 c.setNome(nome);
			 c.setCognome(cognome);
			 c.setPassword(password);
			 c.setUsername(username);
			 c.setRuolo(Ruolo.CALCIATORE);
			 
			 utentiRegistrati.put(c.getUsername(), c);
		 }
		 
		 RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.html");
		 rd.forward(req, res);
		 return;
	 }
	}
}
