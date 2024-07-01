<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Calciatore</title>
    <link rel="stylesheet" href="styles/homecalciatore.css">
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
                    <li><a href="calendariocalciatore.jsp">Calendario Eventi</a></li>
                    <li><a href="#squadra">Squadra</a></li>
                    <li><a href="#statistiche">Statistiche</a></li>
                    <li><a href="#richieste">Richieste</a>
                </ul>
            </nav>
        </header>
        <main>
            <div class="user-info">
                <span>Benvenuto, Cal. ${sessionScope.utente.nome} ${sessionScope.utente.cognome} </span>
            </div>
            <p>
            
            </p>
           
            <c:choose>
    			<c:when test="${not empty sessionScope.utente.squadre}">
        			<ul>
            			<c:forEach var="squadra" items="${sessionScope.utente.squadre}">
            			<div class="squadra">
                		<h2>${squadra.nomeSquadra}</h2>
                <p><b>ID Squadra:</b> ${squadra.id}</p>
                <div class="info_squadra">
                    <p><b>Categoria:</b> ${squadra.categoria.value} </p>
                    <p><b>Descrizione:</b> ${squadra.descrizione} </p>
                </div>
                </div>
            			</c:forEach>
        			</ul>
   				</c:when>
    		<c:otherwise>
       			 <p>               Non fai parte di nessuna squadra.</p>
       			 <p> Chiedi a un Allenatore di invitarti o invia una richiesta! </p>
   			</c:otherwise>
			</c:choose>
            </div>
        </main>
    </div>
</body>
</html>
