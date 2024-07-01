<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Allenatore</title>
    <link rel="stylesheet" href="styles/homeallenatore.css">
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
                    <li><a href="calendarioallenatore.jsp">Calendario Eventi</a></li>
                    <li><a href="#squadra">Squadra</a></li>
                    <li><a href="#statistiche">Statistiche</a></li>
                    
                </ul>
            </nav>
        </header>
        <main>
            <div class="user-info">
                <span>Benvenuto, All. ${sessionScope.utente.nome} ${sessionScope.utente.cognome}</span>
            </div>
            <c:choose>
    			<c:when test="${not empty sessionScope.utente.squadra}">
        			<ul>
        			<div class="squadra">
                		<h2>${sessionScope.utente.squadra.nomeSquadra}</h2>
                <p><b>ID Squadra:</b> ${sessionScope.utente.squadra.id}</p>
               		</div>
                <div class="info_squadra">
                    <p><b>Categoria:</b> ${sessionScope.utente.squadra.categoria.value} </p>
                    <p><b>Descrizione:</b> ${sessionScope.utente.squadra.descrizione} </p>
                </div>
                	
        			</ul>
   				</c:when>
   				
    		<c:otherwise>
    		<div class="no-team">
       			 <p>Non hai ancora una squadra!</p>
       			 <form action="creasquadra.jsp" method="get">
    				<button id="create-team" type="submit">Crea Nuova Squadra</button>
				</form> 
			</div>
   			</c:otherwise>
   			</c:choose>
        </main>
    </div>
</body>
</html>
