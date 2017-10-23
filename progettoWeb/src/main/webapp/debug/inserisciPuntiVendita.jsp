<%-- 
    Document   : inserisciPuntiVendita
    Created on : Sep 28, 2017, 4:56:33 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Inserisci Punti Vendita</h1>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <form action="InserisciPuntiVenditaServlet" id="usrform" method="POST">
            <input type="submit">
        </form>

        <textarea name="data" form="usrform" rows="30" cols="50">Inserisci punti vendita</textarea>
    </body>
</html>
