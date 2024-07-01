<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendario Settimanale</title>
    <link rel="stylesheet" href="styles/calendarioallenatore.css"> 
    <style>
        .hidden {
            display: none;
        }
    </style>
    
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
         
         request.setAttribute("eventi", jsonBuilder.toString());
         
     %>
    
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
                    <li><a href="#calendario">Calendario Eventi</a></li> <!-- Link al calendario eventi -->
                    <li><a href="#squadra">Squadra</a></li> <!-- Link alla sezione squadra -->
                    <li><a href="#statistiche">Statistiche</a></li> <!-- Link alla sezione statistiche -->
                </ul>
            </nav>
        </header>
        
        <div class="user-info">
            <h2>All. ${utente.nome} ${utente.cognome}</h2>
        </div>
        
        <main class="calendar-container">
            <div class="calendar-header">
                <button onclick="prevWeek()" class="arrow-button">&lt;</button>
                <h2 id="month-label">Luglio</h2>
                <button onclick="nextWeek()" class="arrow-button">&gt;</button>
            </div>
            <div class="calendar-week" id="calendar-week">
            </div>
            <button onclick="showEventForm()" class="add-event-button">Aggiungi Evento</button>
        </main>
    </div>


    <div id="event-form" class="modal">
        <div class="modal-content">
            <span class="close-button" onclick="closeEventForm()">&times;</span>
            <h2>Aggiungi Evento</h2>
            
            <form id="new-event-form" action="aggiungievento" method="post">
                <label for="event-type">Tipo di Evento:</label>
                <select id="event-type" name="event-type" required>
                    <option value="Allenamento">Allenamento</option>
                    <option value="Partita">Partita</option>
                    <option value="Cena_di_squadra">Cena di squadra</option>
                    <option value="Comunicazione">Comunicazione</option>
                </select>
                
                <label for="event-description">Descrizione:</label>
                <input type="text" id="event-description" name="event-description" required>
                
				<div id="event-details">
                <label for="event-location">Luogo:</label>
                <input type="text" id="event-location" name="event-location" required>

                <label for="event-date">Data:</label>
                <input type="date" id="event-date" name="event-date" required>

                <label for="event-time">Ora:</label>
                <input type="time" id="event-time" name="event-time" required>
				</div>
                <button type="submit">Aggiungi</button>
            </form>
       		
       		<script>
        document.getElementById('event-type').addEventListener('change', function() {
            var eventType = this.value;
            var eventDetails = document.getElementById('event-details');

            if (eventType == 'Comunicazione') {
                eventDetails.classList.add('hidden');
                // Disable required attributes when hidden
                document.getElementById('event-location').required = false;
                document.getElementById('event-date').required = false;
                document.getElementById('event-time').required = false;
            } else {
                eventDetails.classList.remove('hidden');
                // Re-enable required attributes when visible
                document.getElementById('event-location').required = true;
                document.getElementById('event-date').required = true;
                document.getElementById('event-time').required = true;
            }
        });
    </script>
          
        </div>
    </div>

    <script>
        const weekDays = ['L', 'M', 'M', 'G', 'V', 'S', 'D'];
        let currentDate = new Date();
    
        function getWeekStart(date) {
            const day = date.getDay();
            const diff = date.getDate() - day + (day === 0 ? -6 : 1); // Adjust when day is Sunday
            return new Date(date.setDate(diff));
        }
        
        function isSameWeek(date1, date2) {
            // Convert dates to ISO format to avoid timezone issues
            const d1 = new Date(date1);
            const d2 = new Date(date2);

            // Get the start of the week (Monday) for both dates
            const startOfWeek1 = new Date(d1.setDate(d1.getDate() - d1.getDay() + (d1.getDay() === 0 ? -6 : 1)));
            const startOfWeek2 = new Date(d2.setDate(d2.getDate() - d2.getDay() + (d2.getDay() === 0 ? -6 : 1)));

            // Compare the start of the week for both dates
            return startOfWeek1.toDateString() === startOfWeek2.toDateString();
        }
	
        function addEvent(event) {
           
			var eventType = event.type;
			var eventLocation = event.place;
			var eventDate = new Date(event.date);
			var eventTime = event.time;
			var eventDescription = event.description;
            
            const weekStart = getWeekStart(new Date(currentDate));
            const dayIndex = (eventDate.getDay() + 6) % 7;
            
            if (!isSameWeek(eventDate, currentDate))
            	return;
            
            const calendarDay = document.querySelectorAll('.calendar-day')[dayIndex];
            var eventHTML;
            
            if (eventType == "COMUNICAZIONE") {
            	eventHTML =
            		'<div class="event">' +
                    '<p class="event-title">' +
                        '<a href="evento.jsp?id=' + event.id + '">' + event.type + '</a>' +
                    '</p>' +
                    '<p class="event-time">' + eventDescription + '</p>' +
                    '<button class="delete-event-button" onclick="eliminaEvento(' + event.id + ')">Elimina Evento</button>' +
                '</div>';
            }
            else {
	            eventHTML =
	            	'<div class="event">' +
                    '<p class="event-title">' +
                        '<a href="evento.jsp?id=' + event.id + '">' + event.type + '</a>' +
                    '</p>' +
                    '<p class="event-time">' + eventTime + '-' + eventLocation + '</p>' +
                    '<button class="delete-event-button" onclick="eliminaEvento(' + event.id + ')">Elimina Evento</button>' +
                '</div>';
	            
            }
            
            calendarDay.innerHTML += eventHTML;
            closeEventForm();
        }
        
        function updateCalendar() {
            const weekStart = getWeekStart(new Date(currentDate));
            const monthLabel = document.getElementById('month-label');
            const calendarWeek = document.getElementById('calendar-week');
            calendarWeek.innerHTML = '';

            monthLabel.innerText = weekStart.toLocaleString('it-IT', { month: 'long' });

            for (let i = 0; i < 7; i++) {
                var dayDate = new Date(weekStart);
                dayDate.setDate(weekStart.getDate() + i);
                //alert(weekStart.getDate())
                
                const dayElement = document.createElement('div');
                dayElement.className = 'calendar-day';

                let eventHTML = '';

                dayElement.innerHTML = `
                    <header>` + weekDays[i] + `<span class="date-number">` + dayDate.getDate() + `</span></header>` +
                    eventHTML;
                calendarWeek.appendChild(dayElement);

            }
            
            var evjson = '<%=request.getAttribute("eventi")%>';
            //alert(evjson)
            let eventi = JSON.parse(evjson); 
        	//alert ("Lunghezza: " + eventi.length);
            for (var x = 0; x < eventi.length; x++){
            	addEvent(eventi[x]);
            }
            
        }

        function showEventForm() {
            document.getElementById('event-form').style.display = 'block';
        }

        function closeEventForm() {
            document.getElementById('event-form').style.display = 'none';
        }

       

        function deleteEvent(button) {
            const eventElement = button.parentElement;
            eventElement.remove();
        }

        function prevWeek() {
            currentDate.setDate(currentDate.getDate() - 7);
            updateCalendar();
        }

        function nextWeek() {
            currentDate.setDate(currentDate.getDate() + 7);
            updateCalendar();
        }
        
        updateCalendar(); // Initial call to display the current week
         
    </script>
</body>
</html>
