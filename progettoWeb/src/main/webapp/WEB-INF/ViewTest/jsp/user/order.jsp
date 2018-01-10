<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetOrder%>"/>

<div>
	<h1>Order</h1>
	<% Order o = (Order)Model.Request.getAttribute(request, Model.Request.orderUser); %>
	<% if (o == null) { %>
		Ordine non trovato<br/>
	<% } else { %>
		<div style="border:1px solid black;">
			<h3>Informazioni Ordine</h3>
			<ul>
				<li>id order: <%=o.getId()%></li>
				<li>id shop: <%=o.getIdShop()%></li>
				<li>id user: <%=o.getIdUser()%></li>
				<li>datetime purchase: <%=o.getDatetimePurchase()%></li>
				<li>total price order: <%=o.getTotalPrice()%></li>
				<li>order products: <%=o.getOrderProducts()%></li>
			</ul>
			<h4>Prodotti dell'ordine</h4>
			<ul>
				<% for (OrderProduct op : o.getOrderProducts()) {%>
				<li>
					<ul>
						<li>id order: <%=op.getIdOrder()%></li>
						<li>id product: <%=op.getIdProduct()%></li>
						<li>single price: <%=op.getPrice()%></li>
						<li>quantity: <%=op.getQuantity()%></li>
						<li>total price orderProduct: <%=op.getTotalPrice()%></li>
					</ul>
				</li>
				<% } %>
			</ul>
		</div><br/>
	<% } %>
</div>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>