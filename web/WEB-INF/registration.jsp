<%-- 
    Document   : registration
    Created on : Feb 13, 2018, 10:22:57 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/registration.css"/>
        <title>Регистрация</title>
    </head>
    <body>
        <div class="container col-md-4">
            <h1>Регистрация</h1>
            ${info}
            <form action="controller?command=newUser" method="POST">
                <div class="form-group">
                    <label for="formGroupExampleInput">Имя:</label>
                    <input type="text" name="username" value="${username}" class="form-control" id="user-name" placeholder="Имя">
                </div>
                <div class="form-group">
                    <label for="formGroupExampleInput2">Логин:</label>
                    <input type="text" name="login" value="${login}" class="form-control" id="user-login" placeholder="Логин">
                </div>
                <div class="form-group">
                    <label for="formGroupExampleInput2">Пароль:</label>
                    <input type="password" name="password1" class="form-control" id="user-password1" placeholder="Пароль">
                </div>
                <div class="form-group">
                    <label for="formGroupExampleInput2">Повторите пароль:</label>
                    <input type="password" name="password2" class="form-control" id="user-password2" placeholder="Пароль">
                </div>
                <button type="submit" class="btn btn-primary">Зарегистрировать</button>
            </form>
        </div>

    </body>
</html>
