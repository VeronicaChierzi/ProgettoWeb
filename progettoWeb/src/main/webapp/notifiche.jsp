<%-- 
    Document   : notifiche
    Created on : Oct 20, 2017, 2:12:34 PM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifiche</title>
    </head>
    <body>
        <ul id="paginazione">
            <li>
                <jsp:include page="../navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "../barraRicerca.jsp"/>
            </li>
            <li>
                <h1>Pagina delle notifiche</h1>
            </li>   
            <li>
                Numero notifiche: 
            </li>
            
        </ul>
    </body>
</html>
