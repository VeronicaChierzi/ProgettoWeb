<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.dao.ImageDAO"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>
<jsp:include page="<%=MyPaths.Private.Servlet.All.getProductList%>"/>

<div>
	<h1>Lista prodotti</h1>

	<%
		Product[] productList = (Product[]) Model.Request.getAttribute(request, Model.Request.productList);
		String search = Model.Parameter.get(request, Model.Parameter.textSearch);
	%>

	<% if (search != null) {%>
	Risultati ricerca per: <%=search%><br/>
	<% } %>

	<% if (productList != null || productList.length == 0) { %>
	<ul>
		<% for (Product p : productList) {%>
		<% Image i = p.getImage(); %>
		<% ShopProduct sp = p.getShopProduct(); %>
		<li>
			<ul>
				<li>
					<% if (i != null) {%>
					<a href="<%=MyPaths.Public.Jsp.All.product%>?id=<%=p.getId()%>">
						<img src="<%=i.getPath()%>" alt="<%=i.getAlt()%>">
					</a>
					<% } else { %>
					Immagine non trovata
					<% }%>
				</li>
				<li>        
					<a href="<%=MyPaths.Public.Jsp.All.product%>?id=<%=p.getId()%>"><%=p.getName()%></a><br/>
					Name: <%=p.getName()%><br/>
					Product ID: <%=p.getId()%><br/>
					Description: <%=p.getDescription()%><br/>
					<% if (sp != null) {%>
					Price: <%=sp.getPrice()%><br/>
					Quantity: <%=sp.getQuantity()%><br/>
					<% } else { %>
					Prodotto non disponibile
					<% } %>
				</li>
			</ul>
		</li>
		<% } %>
	</ul>
	<% } else { %>
	Non è stato trovato nessun prodotto
	<% }%>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>