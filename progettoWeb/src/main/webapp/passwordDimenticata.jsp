<%-- 
    Document   : passwordDimenticata
    Created on : Sep 29, 2017, 11:49:13 AM
    Author     : Veronica Chierzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Dimenticata</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <li>
        <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>
            <li>
                <h1>Inserisci qui la tua mail e ti invieremo un codice di reset</h1>
            </li>
            <li>
                <input name="emailReset" type="text" class="form-control" placeholder="Email" />               
            </li>
            <li>
                <button type="submit">invia mail</button>
            </li>
        </ul>
    </body>
</html>
