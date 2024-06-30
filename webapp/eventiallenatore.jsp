<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calendario Eventi</title>
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css' rel='stylesheet' />
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js'></script>
    <link rel="stylesheet" type="text/css" href="styles/calendario.css">
     
      <%@ page import="java.util.List" %>
      <%@ page import="java.util.ArrayList" %>
      <%@ page import="it.unibo.teamnetscore.model.EventoGenerale" %>
      <%@ page import="it.unibo.teamnetscore.model.EventoComunicazioneGenerale" %>
      <%@ page import="it.unibo.teamnetscore.model.Evento" %>
      <%@ page import="it.unibo.teamnetscore.model.Allenatore" %>     
     
     <% 
     
     Allenatore allenatore = (Allenatore) request.getSession().getAttribute("utente");
    
	 List<EventoGenerale> eventi = allenatore.getSquadra().getEventi();
             
     request.getSession().setAttribute("listaeventi", eventi);
         
     // Converti la lista di eventi in JSON
     StringBuilder jsonBuilder = new StringBuilder();
     jsonBuilder.append("[");

     for (int i = 0; i < eventi.size(); i++) {
         	
     EventoGenerale ev = eventi.get(i);       	
            
	                jsonBuilder.append("{")
	                    .append("\"title\":\"").append(ev.getDescrizione()).append("\"")
	                    .append(",\"id\":\"").append(ev.getId()).append("\"");
	                    
	                if (eventi.get(i) instanceof EventoComunicazioneGenerale)
	                	jsonBuilder.append(",\"start\":\"").append(ev.getDatainserimento().toString()).append("\"");
	                   
	                else if (eventi.get(i) instanceof Evento) {	
	                	Evento evento = (Evento) eventi.get(i);  
	                    
	                    jsonBuilder.append(",\"start\":\"").append(evento.getData().toString()).append("T")
	                    .append(evento.getOrario().toString()).append("\",");
	                
	               
		                switch(evento.getTipoEvento()) {
		                
		                case ALLENAMENTO:
		                	jsonBuilder.append("\"color\":\"").append("blue").append("\",");
		                case CENA_DI_SQUADRA:
		                	jsonBuilder.append("\"color\":\"").append("green").append("\",");
		                case PARTITA: 
		                	jsonBuilder.append("\"color\":\"").append("red").append("\",");
		                }
		                  
		                    
		                 jsonBuilder
		                 	.append("\"description\":\"").append(evento.getDescrizione()).append("\",")
		                 	.append("\"extendedProps\": {")
		                 	.append("\"place\":\"").append(evento.getLuogo()).append("\"")
		                    .append("}");
		               }
		                
		                jsonBuilder.append("}");
		                
		                if (i < eventi.size() - 1) {
		                    jsonBuilder.append(",");
		                }
         }

         jsonBuilder.append("]");
         
         //System.out.println(jsonBuilder.toString());
         
         request.setAttribute("eventi", jsonBuilder.toString());
         
     
     %>
          
    <script>
    
        // API FullCalendar //
        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');
			eventiJson = '<%=request.getAttribute("eventi")%>';
			//alert(eventiJson);
            
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                displayEventTime: false	,
                locale: 'it',
                events: JSON.parse(eventiJson),
                eventClick: function(info) {
                	const url = `evento.jsp?id=` +info.event.id;
                    window.location.href = url;
                  }
                });
           
                
            calendar.render();
			
        });
    </script>
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
            <a href="login.html">
                <img src="images/logo-viola.jpeg" alt="TeamNetScore Logo">
             </a>
                <h1>TeamNetScore</h1>
           
            </div>
            <nav>
                <ul>
                    <li><a href="#calendario">Calendario Eventi</a></li>
                    <li><a href="#squadra">Squadra</a></li>
                    <li><a href="#statistiche">Statistiche</a></li>
                      
                </ul>
            </nav>
        </header>
        <main>
        <div class="user-info">
            <h2>All. ${utente.nome} ${utente.cognome}</h2>
        </div>
            

            <div class="calendar-container">
        		<div id='calendar'></div>
    		</div>
    		
    		<div class="button-container">
    			<button class="add-event-button">Aggiungi Evento</button>
			</div>
        </main>
    </div>
</body>
</html>
