package it.unibo.teamnetscore.view;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.teamnetscore.model.Calciatore;
import it.unibo.teamnetscore.model.Evento;
import it.unibo.teamnetscore.model.EventoComunicazioneGenerale;
import it.unibo.teamnetscore.model.EventoGenerale;
import it.unibo.teamnetscore.model.Squadra;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/eventicalciatore")
public class CalendarioCalciatore extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public void init(ServletConfig config) throws ServletException 
	{ 
		super.init(config);
	}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Calciatore calciatore = (Calciatore) request.getSession().getAttribute("utente");
        
        
        String squadraId = request.getParameter("squadraId");
        Squadra selectedteam = null;
	        List<EventoGenerale> eventi = null;
        
        if (squadraId != null && !squadraId.isEmpty()) {
        	
        	for (Squadra s : calciatore.getSquadre())
        		if (s.getId().equals(squadraId))
        			selectedteam = s;
        	
            if (!selectedteam.equals(null))
            	eventi = selectedteam.getEventi();
                
            request.getSession().setAttribute("listaeventi", eventi);
            
            // Converti la lista di eventi in JSON
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("[");

            for (int i = 0; i < eventi.size(); i++) {
             	
                EventoGenerale ev = eventi.get(i);       	
                       
           	                jsonBuilder.append("{")
           	                    .append("\"description\":\"").append(ev.getDescrizione()).append("\"")
           	                    .append(",\"id\":\"").append(ev.getId()).append("\"");
           	                    
           	                if (eventi.get(i) instanceof EventoComunicazioneGenerale) {
           	                	jsonBuilder.append(",\"type\":\"").append("COMUNICAZIONE").append("\"");
           						jsonBuilder.append(",\"date\":\"").append(ev.getDatainserimento().toLocalDate().toString()).append("\"");	
           	                  }
           	                else if (eventi.get(i) instanceof Evento) {	
           	                	Evento evento = (Evento) eventi.get(i);  
           	                    
           	                    jsonBuilder.append(",\"date\":\"").append(evento.getData().toString()).append("\",");

           						jsonBuilder.append("\"time\":\"").append(evento.getOrario().toString()).append("\",");
           	                
           	               
           		                switch(evento.getTipoEvento()) {
           		                
           		                case ALLENAMENTO:
           		                	jsonBuilder.append("\"type\":\"").append("ALLENAMENTO").append("\",");
           		                	break;
           		                case CENA_DI_SQUADRA:
           		                	jsonBuilder.append("\"type\":\"").append("CENA DI SQUADRA").append("\",");
           		                	break;
           		                case PARTITA: 
           		                	jsonBuilder.append("\"type\":\"").append("PARTITA").append("\",");
           		                	break;
           		                }
           		                  
           		                    
           		                 jsonBuilder
           		                 	.append("\"place\":\"").append(evento.getLuogo()).append("\"");
           		               }
           		                
           		                jsonBuilder.append("}");
           		                
           		                if (i < eventi.size() - 1) {
           		                    jsonBuilder.append(",");
           		                }
                    }

                    jsonBuilder.append("]");
                    
                    //System.out.println(jsonBuilder.toString());
                    
                    request.setAttribute("eventi", jsonBuilder.toString());
        }
        
        request.getRequestDispatcher("calendariocalciatore.jsp").forward(request, response);
    
    }
}
