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
            <form action="ForgotPasswordServlet" method="post">

                <label for="emailReset">Email</label>
                <input type="text" id="emailReset" name="emailReset" class="form-control" placeholder="Email" value="" required>

                <input type="submit" value="Prosegui"></input>
                <br>
            </form>
        </ul>
    </body>
</html>
