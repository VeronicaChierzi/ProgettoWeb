<%-- Pagina di login --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
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
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
            <li>
            <div class="header-content">
                <div class="header-content-inner">
                    <!--form di login-->
                    <img class="centerImg resizeImg" src="/progettoWeb/img/logo/logoFinale.png" height="60" width="60" style="padding: 10px;">
                    <div class="border resizeRegistration" style="padding-left: 25px">
						<form action="<%=MyPaths.Servlet.Pubbliche.login%>" method="post">
                            <h1 class="text-center">Login</h1>
                            <% if (Model.Messages.consumeBoolean(request, Model.Messages.loginFailed)) { %>
                                <div class="alert alert-danger centerImg" role="alert">
                                    <span class="glyphicon glyphicon-remove-sign"></span> Login fallito: username o password errati
                                </div>
							
                            <% }%>
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
                                <a href="<%=MyPaths.Jsp.anonymousForgottenPassword%>">Password dimenticata?</a>
                            </div>
                            <br>
                            <div class="text-center">
                                <button class="btn btn-default btn-secondary" href="<%=MyPaths.Jsp.allHome%>">Annulla</button>
                                <input type="submit" class="btn btn-danger btn-primary" value="Accedi"></input>
                            </div>
                            <br>
                            <!--Per dare il messaggio di allerta email o password sbagliata-->
                            <!--<div class="alert alert-danger" role="alert">
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                <span class="sr-only"></span>Username o password errati.
                            </div>-->
                        </form>
						
                    </div>
                </div>
            </div>
            </li>
        </ul>
		<br/>
		<br/>
	</body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>
