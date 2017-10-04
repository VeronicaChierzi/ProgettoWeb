<%-- 
    Document   : inserisciProdotto
    Created on : Oct 2, 2017, 4:45:20 PM
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
        <h1>Inserisci prodotti</h1>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <form action="InserisciProdottiServlet" id="usrform" method="POST">
            <input type="submit">
        </form>

        <textarea name="data" form="usrform" rows="45" cols="180">Inserisci prodotti</textarea>
    </body>
</html>