<%-- 
    Document   : login
    Created on : Feb 13, 2018, 10:09:23 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/login.css"/>
        <title>Страница входв</title>
    </head>
    <body>
        <div class="container col-md-4">
            <h1>Войдите</h1>
            ${info}
            <form action="controller?command=enter" method="POST">
                <div class="form-group">
                    <label for="user-login">Логин:</label>
                    <input type="text" name="login" class="form-control" id="user-login" aria-describedby="emailHelp" placeholder="Логин">
                </div>
                <div class="form-group">
                    <label for="user-password">Password</label>
                    <input type="password" name="password" class="form-control" id="user-password" placeholder="Пароль">
                </div>
                <button type="submit" class="btn btn-primary">Войти</button>
            </form>
            
        </div>
    </body>
</html>
