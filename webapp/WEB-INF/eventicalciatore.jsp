<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calendario Eventi</title>
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css' rel='stylesheet' />
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js'></script>
    <link rel="stylesheet" type="text/css" href="styles/calendario.css">
          
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
			
            document.getElementById("teamSelector").addEventListener("change", function() {
                calendar.refetchEvents();
            });
        });
    </script>
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
                <img src="images/logo-viola.jpeg" alt="TeamNetScore Logo">
                <h1>TeamNetScore</h1>
            </div>
            <nav>
                <ul>
                    <li><a href="#calendario">Calendario Eventi</a></li>
                    <li><a href="#squadra">Squadra</a></li>
                    <li><a href="#statistiche">Statistiche</a></li>
                    <li><a href="#richieste">Richieste</a>
                </ul>
            </nav>
        </header>
        <main>
        <div class="user-info">
            <h2>Cal. ${utente.nome} ${utente.cognome}</h2>
            <form action="eventicalciatore" method="post">
            <div class="select-team">
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
        </div>
            

            <div class="calendar-container">
        		<div id='calendar'></div>
    		</div>
        </main>
    </div>
</body>
</html>
