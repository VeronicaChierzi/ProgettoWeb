<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina decisione</title>
    </head>
	<body class="sfondo">
        <ul id="paginazione">
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li id="messico" style="margin-top: 20px">
				<% if (Model.Messages.consumeBoolean(request, "prodottoAggiuntoAlCarrello")) { %>
				<div style="text-align: center">Il Prodotto è stato aggiunto al carrello</div> <br> 
				<% }%>
				<ul style="padding: 0px; display: inline-flex;" id="pulsantiscelta">
					<li>
						<a class="btn btn-secondary" href="<%=MyPaths.Jsp.allHome%>">continua lo shopping</a><br/>

					</li>
					<li>
						<a class="btn btn-primary" href="<%=MyPaths.Jsp.allCart%>">vai al carrello</a>

					</li>
				</ul>
            </li>
        </ul>
    </body>
</html>
