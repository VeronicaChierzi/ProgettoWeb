<%-- 
    Document   : negozio
    Created on : Oct 20, 2017, 2:31:09 PM
    Author     : Veronica Chierzi
--%>
<%@page session="true" %>
<%@page import="it.unitn.disi.entities.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user");%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Negozio</title>
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
                <h1 id="negozio">Questa è la pagina del negozio <%=user.getUserSeller().getName()%>!</h1>
            </li>
        </ul>
    </body>
</html>
