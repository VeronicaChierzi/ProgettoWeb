<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<% User user = Model.Session.getUserSellerLogged(request); %>
<% UserSeller seller = user.getUserSeller();%>
<div>
	<h1>Pagina Seller</h1>
	id: <%=seller.getId()%><br/>
	name: <%=seller.getName()%><br/>
	partita iva: <%=seller.getPartitaIva()%><br/>
	<br/>

	Nome del venditore<br/>
	Partita iva<br/>
	<br/>
	(valutazioni negative date da admin)<br/>
	<br/>
	
	<a href="<%=MyPaths.Jsp.sellerSegnalazioni%>">Lista completa segnalazioni venditore</a>
	<a href="<%=MyPaths.Jsp.sellerSegnalazioniOpen%>">Lista segnalazioni aperte venditore</a>

	Lista di punti vendita (link)
</div>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>