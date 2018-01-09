<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetOrders%>"/>

<div>
	<h1>Orders</h1>
	<% Order[] orders = (Order[])Model.Request.getAttribute(request, Model.Request.ordersUser); %>
	<% if (orders == null || orders.length==0) { %>
		Non hai ancora effettuato ordini
	<% } else { %>
		<% int num_o = 0; %>
		<% for (Order o : orders) {%>
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
					<% int num_op = 0; %>
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
					<% num_op++; %>
					<% } %>
				</ul>
				<% num_o++; %>
			</div><br/>
		<% } %>
	<% } %>
</div>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>