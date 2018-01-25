<%-- Pagina che visualizzera il messagio di avvenuta conferma della registrazione --%>
<%-- Il link nella email manderà alla servlet, che confermerà l'utente e che rimanderà a questa pagina --%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>	
		<jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utente confermato</title>
	</head>
	<body class="sfondo">	

		<ul id="paginazione">
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
			
			<li>	

				<div class="container" style="margin-top: 50px;">


					<div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok-sign"></span> Utente confermato!</div>


					<div style="margin-left: auto; margin-right: auto; display: table">
						<a class="btn btn-primary" href="<%=MyPaths.Jsp.allHome%>">continua lo shopping</a>

					</div>
				</div>

			</li>


		</ul>


	</body>

</html>