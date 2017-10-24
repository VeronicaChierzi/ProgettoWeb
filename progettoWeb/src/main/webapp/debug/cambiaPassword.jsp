<%-- 
    Document   : cambiaPassword
    Created on : Oct 24, 2017, 2:42:23 PM
    Author     : Luca Degani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambia password</title>
    </head>
    <body>
        <h1>Cambia password</h1>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <form action="../ChangePassword" id="usrform" method="POST">
            <input type="password" name="password" value="password">
            <input type="text" name="id" value="<%=request.getParameter("id")%>" hidden="true">
            <input type="text" name="email" value="<%=request.getParameter("email")%>" hidden="true">
            <input type="submit">
            
            
        </form>

    </body>
</html>
