<%-- Pagina di password dimenticata per richiedere l'invio di una nuova password tramite email --%>
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
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
			
			<div class="container">
				<li>
				Inserisci la mail per il reset della password
			</li>
			<li>
				<input style="width: 100%; border-style: hidden; margin-bottom: 10px;" type="text" name="title" value=""/>
			</li>
				
				
			</div>
			
			
		</ul>
	
	
	</body>
	
</html>