<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendario Settimanale</title>
    <link rel="stylesheet" href="styles/calendariocalciatore.css"> 
    <style>
        .hidden {
            display: none;
        }
    </style>

</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
               <a href="login.html">
                <img src="images/logo-viola.jpeg" alt="TeamNetScore Logo">
             </a>
                <h1>TeamNetScore</h1> <!-- Titolo della squadra -->
            </div>
            <nav>
                <ul>
                    <li><a href="#calendario">Calendario Eventi</a></li> <!-- Link al calendario eventi -->
                    <li><a href="#squadra">Squadra</a></li> <!-- Link alla sezione squadra -->
                    <li><a href="#statistiche">Statistiche</a></li> <!-- Link alla sezione statistiche -->
                    <li><a href="#richieste">Richieste</a> </li>
                </ul>
            </nav>
            
        </header>
        
        <div class="user-info">
            <h2>Cal. ${utente.nome} ${utente.cognome}</h2>
        </div>
        
        <form action="eventicalciatore" method="post">
            <div class="select-team" align="right">
                <label for="squadra">Seleziona la Squadra:   </label>
                <select name="squadraId" id="teamSelector" onchange="this.form.submit()">
                    <option value="">-- Seleziona Squadra --</option>
                    <c:forEach var="squadra" items="${utente.squadre}">
                        <option value="${squadra.id}" 
                                <c:if test="${squadra.id == squadraSelezionata.id}">selected</c:if>>${squadra.nomeSquadra}</option>
                    </c:forEach>
                </select>
            </div>
            </form>
            
        
        <main class="calendar-container">
            <div class="calendar-header">
                <button onclick="prevWeek()" class="arrow-button">&lt;</button>
                <h2 id="month-label">Luglio</h2>
                <button onclick="nextWeek()" class="arrow-button">&gt;</button>
            </div>
            <div class="calendar-week" id="calendar-week">
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
            //event.preventDefault();
			/*
            const eventType = document.getElementById('event-type').value;
            const eventLocation = document.getElementById('event-location').value;
            const eventDate = new Date(document.getElementById('event-date').value);
            const eventTime = document.getElementById('event-time').value;
			*/
			
			const eventType = event.type;
			const eventLocation = event.place;
			const eventDate = new Date(event.date);
			const eventTime = event.time;
			const eventDescription = event.description;
            
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
                        '<a href="evento.jsp?id=' + event.id + '">' + eventType + '</a>' +
                    '</p>' +
                    '<p class="event-time">' + eventDescription + '</p>' +
                '</div>';
            }
            else {
	            eventHTML =
	            	'<div class="event">' +
                    '<p class="event-title">' +
                        '<a href="evento.jsp?id=' + event.id + '">' + eventType + '</a>' +
                    '</p>' +
                    '<p class="event-time">' + eventTime + '-' + eventLocation + '</p>' +
                '</div>';
	            
            }
            
            calendarDay.innerHTML += eventHTML;
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
            let eventi = JSON.parse(evjson); 
        	//alert ("Lunghezza: " + eventi.length);
            for (var x = 0; x < eventi.length; x++){
            	addEvent(eventi[x]);
            }
            
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
