<%-- 
    Document   : addWord
    Created on : Feb 13, 2018, 10:09:47 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/addword.css"/>
        <title>Редактирование слова</title>
    </head>
    <body>
        <div class="container col-md-6">
            <h1>Изменить слово!</h1>
            ${info}
            <hr>

            <form action="controller?command=doChangeWord" method="POST">
                <div class="form-group">
                    <input type="hidden" name="id"  value="${editWord.id}">
                    <label for="user-word">Слово</label>
                    <input type="text" name="word" class="form-control" id="user-word" value="${editWord.word}">

                </div>
                <div class="form-group">
                    <label for="user-trans">Перевод</label>
                    <input type="text" name="trans" class="form-control" id="user-trans"  value="${editWord.trans}">
                </div>
                <div class="form-group">
                    <label for="user-phrases">Фразы</label>
                    <textarea  class="form-control" name="phrases" id="user-phrases">${editWord.phrases}</textarea>
                </div>

                <button type="submit" class="btn btn-primary">Изменить</button>
            </form>
        </div>
    </body>
</html>
