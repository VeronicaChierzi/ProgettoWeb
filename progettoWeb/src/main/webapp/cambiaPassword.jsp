<%-- 
    Document   : cambiaPassword
    Created on : Oct 24, 2017, 2:42:23 PM
    Author     : Luca Degani
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
                <h1>Inserisci la nuova password</h1>
            </li>

            <% String action = ((request.getParameter("id") == null || request.getParameter("email") == null) ? "index.jsp" : "ChangePassword");%>
            <form action="<%=action%>" id="usrform" method="POST">
                <li>
                    <input type="password" name="password" value="password" class="form-control">
                    <input type="text" name="id" value="<%=request.getParameter("id")%>" hidden="true">
                    <input type="text" name="email" value="<%=request.getParameter("email")%>" hidden="true">
                </li>
                <li>
                    <button type="submit">Cambia password</button>
                </li>


            </form>




        </ul>
    </body>
</html>
