<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.carts.Cart"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<div>
	<h1>Pagina di decisione</h1>
	<% if(Model.Messages.consumeBoolean(request, "prodottoAggiuntoAlCarrello")){ %>
		Il Prodotto è stato aggiunto al carrello<br/>
	<% } %>
	<a href="<%=MyPaths.Jsp.allHome%>">continua lo shopping</a><br/>
	<a href="<%=MyPaths.Jsp.allCart%>">vai al carrello</a>
</div>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>