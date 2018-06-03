<%-- 
    Document   : admin
    Created on : Feb 13, 2018, 2:12:28 PM
    Author     : Melnikov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список всех слов</title>
    </head>
    <body>
        <h1>Список всех слов добавленных пользователем</h1>
        <p>${info}</p>
        <ul>
            <c:forEach  var="word" items="${listWords}">
                <li>${word.word}: ${word.trans} <a href="controller?command=listWords&deleteWordId=${word.id}">Удалить</a>
                    <c:if test="${word.active eq true}">
                        <a href="controller?command=listWords&deactiveWordId=${word.id}">Деактивировать</a>
                    </c:if>
                    <c:if test="${word.active eq false}">
                        <a href="controller?command=listWords&activeWordId=${word.id}">Aктивировать</a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
