<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.carts.Cart"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<div>
	<h1>Carrello</h1>
	<% Cart cart = (Cart) Model.Session.getAttribute(request, Model.Session.cart); %>
	<% if (cart == null || cart.isEmpty()) { %>
	Il carrello è vuoto
	<% } else { %>
		<% float totalPrice = 0.0f; %>
		<table border="1">
			<% int num_o = 0; %>
			<% for (Order o : cart.getOrders()) {%>
			<tr>
				<td>id order: <%=o.getId()%></td>
				<td>id shop: <%=o.getIdShop()%></td>
				<td>id user: <%=o.getIdUser()%></td>
				<td>datetime purchase: <%=o.getDatetimePurchase()%></td>
				<td>total price order: <%=o.getTotalPrice()%></td>
				<td>order products: <%=o.getOrderProducts()%></td>
			</tr>
			<tr>
				<td>
					<table border="1">
						<% int num_op = 0; %>
						<% for (OrderProduct op : o.getOrderProducts()) {%>
						<tr>
							<td>id order: <%=op.getIdOrder()%></td>
							<td>id product: <%=op.getIdProduct()%></td>
							<td>single price: <%=op.getPrice()%></td>
							<td>
								quantity: <%=op.getQuantity()%>
								<form name="form<%=num_o%>_<%=num_op%>" action="<%=MyPaths.Servlet.Pubbliche.changeProductQuantityCartServlet%>" method="POST">
									<select name="quantity" autocomplete="off" onchange="document.form<%=num_o%>_<%=num_op%>.submit()">
										<% for (int i = 1; i <= 99; i++) {%>
										<option value="<%=i%>" <% if (i == op.getQuantity()) { %> selected <% }%>><%=i%></option>
										<% } %>
									</select>
									<input type="hidden" name="id_product" value="<%=op.getIdProduct()%>" />
									<input type="hidden" name="id_shop" value="<%=o.getIdShop()%>" />
								</form>
							</td>
							<td>total price orderProduct: <%=op.getTotalPrice()%></td>
							<td>
								<form action="<%=MyPaths.Servlet.Pubbliche.deleteProductCartServlet%>" method="POST">
									<input type="hidden" name="id_product" value="<%=op.getIdProduct()%>" />
									<input type="hidden" name="id_shop" value="<%=o.getIdShop()%>" />
									<input type="submit" value="remove" />
								</form>
							</td>
						</tr>
						<% num_op++; %>
						<% } %>
					</table>
				</td>
			</tr>
			<% num_o++; %>
			<% }%>
		</table>
		total price cart: <%=cart.getTotalPrice()%></br>

		<% User user = (User)Model.Session.getAttribute(request, Model.Session.user); %>
		<% if (user!=null){ %>
			<form method="post" action="<%=MyPaths.Servlet.Pubbliche.buyCart%>">
				<input type="submit" value="compra" />
			</form>
		<% } else { %>
		<p>
			Effettua il <a href="<%=MyPaths.Jsp.anonymousLogin%>">login</a> per procedere all'acquisto
		</p>
		<% } %>
	<% } %>
	<% if (Model.Messages.consumeBoolean(request, "buyCartFailed")) { %>
		<p>
			Impossibile effettuare l'acquisto
		</p>
	<% } %>
	<% if (Model.Messages.consumeBoolean(request, "rimossoProdottoCarrello")) { %>
		<p>
			Il prodotto è stato rimosso dal carrello
		</p>
	<% } %>
	<% if (Model.Messages.consumeBoolean(request, "quantitaProdottoCarrelloModificata")) { %>
		<p>
			La quantità del prodotto è stata modificata
		</p>
	<% } %>
</div>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>