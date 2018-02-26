<%-- 
    Document   : memoWords
    Created on : Feb 13, 2018, 10:10:09 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/memoWords.css"/>
        <title>Запоминаем слова</title>
    </head>
    <body>
<div class="container col-md-4">
<div class="alert alert-primary" role="alert">
  ${info}
</div>
            <form action="controller?command=addNewWord" method="POST">
                <button type="button" class="btn btn-primary" onclick="showTrans()">Перевод</button>
                <button  type="button" class="btn btn-primary"><a href="controller?command=memoWords">Следующее слово</a></button>
                <button type="button" class="btn btn-primary"><a href="controller?command=deleteWord&id=${word.id}">Выучил</a></button>
                <button type="button" class="btn btn-primary"><a href="controller?command=editWord&id=${word.id}">Изменить слово</a></button>
                <button type="button" class="btn btn-primary"><a href="controller?command=logout">Выход</a></button>
                <hr>
                <div class="form-group">
                    <input type="text" name="word" class="form-control" id="user-word" placeholder="слово" value="${word.word}">
                </div>
                <div id="trans">
                    <div class="form-group">
                        <input type="text" name="trans" class="form-control" id="user-trans" placeholder="перевод слова" value="${word.trans}">
                    </div>
                    <div class="form-group">
                        <label for="user-phrases">Фразы</label>
                        <textarea  class="form-control" name="phrases" id="user-phrases">${word.phrases}</textarea>
                    </div>
                </div>
                
                
            </form>
                <script src="js/memoWords.js"></script>
        </div>
        
    </body>
</html>
