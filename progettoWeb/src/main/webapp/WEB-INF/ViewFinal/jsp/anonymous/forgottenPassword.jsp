<%-- Pagina di password dimenticata per richiedere l'invio di una nuova password tramite email --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>



<!DOCTYPE html>
<html>
	<head>	
		<jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset password</title>
	</head>
	<body class="sfondo">	
		<ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
            <li class="container">
            <div class="header-content">
                <div class="header-content-inner">
                    <!--form di login-->
                    <img class="centerImg resizeImg" src="/progettoWeb/img/logo/logoFinale.png" height="60" width="60" style="padding: 10px;">
                    <div class="border resizeRegistration" style="padding-left: 25px">
							<form action="<%=MyPaths.Servlet.Pubbliche.forgottenPassword%>" method="post">
								<h1 class="text-center">Richiedi nuova password</h1>
								<% if (Model.Messages.consumeBoolean(request, "forgottenPassword-emailNonValida")) { %>
									<div class="alert alert-danger centerImg" role="alert">
										<span class="glyphicon glyphicon-remove-sign"></span>
										Inserire un indirizzo email registrato
									</div>
								<% }%>
								<br>
								<%--<input style="width: 100%; border-style: hidden; margin-bottom: 10px;" type="text" name="" value=""/>--%>
								<div class="form-group">
									<label for="emailLogin">Email</label>
									<input autofocus type="text" id="emailLogin" name="email" class="form-control" placeholder="Email" value="" required>
								</div>
								<input type="hidden" name="richiediNuovaPassword" value="si"/>
								<div class="text-center">
									<button class="btn btn-default btn-secondary" href="<%=MyPaths.Jsp.allHome%>">Annulla</button>
									<input type="submit" class="btn btn-danger btn-primary" value="Richiedi nuova password"></input>
								</div>
							</form>
                    </div>
                </div>
            </div>
            </li>
			</div>
		</ul>
	</body>
</html>