<%-- 
    Document   : passwordDimenticata
    Created on : Oct 24, 2017, 2:50:59 PM
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
        <h1>Password dimenticata!</h1>
        <br><br>
        <form action="../ForgotPasswordServlet" method="post">

            <label for="emailReset">Email</label>
            <input type="text" id="emailReset" name="emailReset" class="form-control" placeholder="Email" value="" required>

            <input type="submit" value="Accedi"></input>
            <br>
        </form>

    </body>
</html>
