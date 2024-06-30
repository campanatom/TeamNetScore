<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>TNS: Registrazione</title>
    <link rel="stylesheet" type="text/css" href="styles/register.css">
</head>
<body>

    <div class="register-container">
        
        <h1>Registrazione</h1>
       
        <form class="register-form" action="register" method="post">
            <input type="text" name="firstname" placeholder="Nome" required>
            <input type="text" name="lastname" placeholder="Cognome" required>
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            
            <div class="role-selection">
                <label>
                    <input type="radio" name="role" value="coach" required>
                    Allenatore
                </label>
                <label>
                    <input type="radio" name="role" value="player" required>
                    Calciatore
                </label>
            </div>
            
            <button type="submit">Registrati</button>
        </form>
        <div class="register-message">
            Sei già registrato? <a href="login.html">Accedi qui!</a>
        </div>
    </div>
    
</body>
</html>
