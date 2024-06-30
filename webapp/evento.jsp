<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Evento</title>
    <link rel="stylesheet" href="styles/evento.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <div class="logo">
                
                <span><strong>TeamNetScore </strong></span>
            </div>
            <nav class="navbar">
                <button onclick="window.history.back();">Torna al Calendario</button>
            </nav>
        </header>
        
        <%@ page import="java.util.List" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="it.unibo.teamnetscore.model.EventoGenerale" %>
        <%@ page import="it.unibo.teamnetscore.model.Evento" %>
        
<% 
	List<EventoGenerale> evs = (ArrayList<EventoGenerale>) request.getSession().getAttribute("listaeventi"); 
	String id = (String) request.getParameter("id");
	
	EventoGenerale eventoselezionato = null;

	for (EventoGenerale e: evs){
		
		e.getId();
		
		if (e.getId().equals(id)) {
			eventoselezionato = e;
		}
		
	}
	
	//log ("ID TROVATO: " + eventoselezionato.getId());
%>

        <main>
        
        <h2>Dettagli Evento</h2>
            <div class="event-details">
                <p><strong>Titolo:</strong> <%=eventoselezionato.getDescrizione() %></p>
                <p><strong>ID:</strong> <%=eventoselezionato.getId() %></p>
        
    			<% if (eventoselezionato instanceof Evento) {
    				Evento e = (Evento) eventoselezionato; %>
        			<p><strong>Ora:</strong> <%=e.getData().toString() %> </p>
               		<p><strong>Luogo:</strong> <%=e.getLuogo() %> </p>
                	<p><strong>Tipologia:</strong> <%=e.getOrario().toString() %> </p>
                	<br> <h3>Presenze (non implementato)</h3>
                <ul>
                    <li>Calciatore 1</li>
                    <li>Calciatore 2</li>
                    <li>Calciatore 3</li>
                </ul>
        			
   				<% }
    			else { %>
    			<p><strong>Data Inserimento:</strong> <%=eventoselezionato.getDatainserimento().toString() %> </p>
   				<%} %>
            </div>
        </main>
    </div>
</body>
</html>