<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accedi</title>
    </head>
    <body class="sfondo">
        
        <ul id="paginazione">
            <li>
        <jsp:include page="/navbar.jsp" />
            </li>
            
            <li>
            <div class="header-content">
                <div class="header-content-inner">
                    <!--form di login-->
                    <img class="centerImg resizeImg" src="/progettoWeb/img/logo/logoFinale.png" height="60" width="60" style="padding: 10px;">
                    <div class="border resizeRegistration">

                        <form action="LoginServlet" method="post">
                            <h1 class="text-center">Login</h1>
                            <br>
                            <div class="form-group">
                                <label for="emailLogin">Username (email)</label>
                                <input type="text" id="emailLogin" name="username" class="form-control" placeholder="Email" value="" required>
                            </div>
                            <div class="form-group">
                                <label for="passwordLogin">Password </label>
                                <input type="password" id="passwordLogin" name="password" class="form-control" placeholder="Password" value="" required>
                            </div>
                            <br>
                            <div class="text-center">
                                <a href="passwordDimenticata.jsp">Password dimenticata?</a>
                            </div>
                            <br>
                            <div class="text-center">
                                <input type="submit" class="btn btn-default btn-secondary" value="Registrati"></input>
                                <input type="submit" class="btn btn-danger btn-primary" value="Accedi"></input>
                            </div>
                            <br>
                            <!--Per dare il messaggio di allerta email o password sbagliata-->
                            <!--<div class="alert alert-danger" role="alert">
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only"></span>Username o password errati.
                            </div>-->
                        </form>
                        <%
            if (session.getAttribute("loginFallito") != null) {
                out.print(session.getAttribute("loginFallito"));
                session.removeAttribute("loginFallito");
            }
        %>
                    </div>
                </div>
            </div>
            </li>
        </ul>




    <!--<h1>Hello World!</h1>

    <h1>Login</h1>
    <div>
        <form method="post" action="LoginServlet">
            <input type="hidden" name="op" value="login" />
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" value="" /></td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Log In" /></td>
                </tr>
            </table>
        </form>
        <%/*
            if (session.getAttribute("loginFallito") != null) {
                out.print(session.getAttribute("loginFallito"));
                session.removeAttribute("loginFallito");
            }*/
        %>
    </div>-->

</body>
</html>
