<%-- 
    Document   : inserisciSottocategorie
    Created on : Sep 28, 2017, 5:25:37 PM
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
        <h1>Inserisci sottocategorie</h1>
        <br/>
        <br/>
        <br/>
        <form action="InserisciSottocategorieServlet" id="usrform" method="POST">
            <input type="submit">
        </form>
        <br/>
        <br/>

        <textarea name="data" form="usrform" rows="45" cols="180">Inserisci sottocategorie</textarea>
    </body>
</html>
