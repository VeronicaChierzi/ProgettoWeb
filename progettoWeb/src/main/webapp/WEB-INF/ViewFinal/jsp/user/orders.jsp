<%-- Lista degli ordini effettuati --%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.Shop"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetOrders%>"/>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>I tuoi Ordini</title>
    </head>
    <body class="sfondo">
		<ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            </li>
			<li style="margin-left: 10px; margin-top: 30px; margin-bottom: 30px;">
				<h1>I tuoi ordini</h1>
			</li>

			<% Order[] orders = (Order[]) Model.Request.getAttribute(request, Model.Request.ordersUser); %>
			<% if (orders == null || orders.length == 0) { %>
			<li style="margin-left: 10px; font-size: 30px;">
				Non hai ancora effettuato ordini<br/></li>
				<% } else { %>
				<% int num_o = 0; %>
				<% for (Order o : orders) {%>
			<li style="margin-left: 10px; font-size: 30px;">
				<a href="<%=MyPaths.Jsp.userOrder%>?id=<%=o.getId()%>">Ordine n°<%=o.getId()%></a>
			</li>
			<li style="margin-left: 10px;">
				<% Shop s = o.getShop();
					if (s != null) {
						UserSeller us = s.getUserSeller();
						if (us != null) {%>
				Venduto da 
				<a href="<%=MyPaths.Jsp.allShop%>?id=<%=s.getId()%>">
					<%=us.getName()%>
				</a>
				<% } %>
				<% }%>
			</li>
			<li style="margin-left: 10px;">Data di acquisto: <%=o.getDatetimePurchase()%></li>
			<li style="margin-left: 10px;">Totale: <%=o.getTotalPrice()%></li>
			<li style="border-bottom-width: 1px;
				border-bottom-color: black;
				border-bottom-style: solid;
				margin-top: 5px;">
				<table style="border-spacing: 10px 30px;">
					<thead>
						<tr>
							<td>
								Prodotto
							</td>
							<td>
								Prezzo
							</td>
							<td>
								Quantità
							</td>
							<td>
								Totale
							</td>
						</tr>
					</thead>
					<% int num_op = 0; %>
					<% for (OrderProduct op : o.getOrderProducts()) {%>
					<% Product p = op.getProduct();%>


					<tr style="text-align: center"><td>
							<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=op.getIdProduct()%>">
								<% if (p != null) {%>
								<%=p.getName()%>
								<% } else {%>
								<%=op.getIdProduct()%>
								<% }%>
							</a>
						</td>

						<%--<li>
							<% if(p!=null){ %>
								<% Image image = p.getImage(); %>
								<% if (image != null) {%>
									<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
										<img src="<%=image.getPath()%>" alt="<%=image.getAlt()%>">
									</a>
									<br/>
								<% } else { %>
									Immagine non trovata<br/>
								<% } %>
							<% } %>
						</li>--%>
						<td><%=op.getPrice()%></td>
						<td><%=op.getQuantity()%></td>
						<td><%=op.getTotalPrice()%></td>
					</tr>


					<% num_op++; %>
					<% } %>
				</table>
			</li>
			<% num_o++; %>

			<% } %>
			<% }%>

		</li>
	</ul>





	<%--<% Order[] orders = (Order[])Model.Request.getAttribute(request, Model.Request.ordersUser); %>
	<% if (orders == null || orders.length==0) { %>
		Non hai ancora effettuato ordini<br/>
	<% } else { %>
		<% int num_o = 0; %>
		<% for (Order o : orders) {%>
			<div>
				<h3>Informazioni Ordine</h3>
				<ul>
					<li><a href="<%=MyPaths.Jsp.userOrder%>?id=<%=o.getId()%>">Ordine n°<%=o.getId()%></a></li>
					<li>
						<% Shop s = o.getShop();
						if (s!=null) {
							UserSeller us = s.getUserSeller();
							if (us!=null) { %>
								Venduto da 
								<a href="<%=MyPaths.Jsp.allShop%>?id=<%=s.getId()%>">
									<%=us.getName()%>
								</a>
							<% } %>
						<% } %>
					</li>
					<li>Data di acquisto: <%=o.getDatetimePurchase()%></li>
					<li>Prezzo totale ordine: <%=o.getTotalPrice()%></li>
				</ul>
				<h4>Prodotti dell'ordine</h4>
				<ul>
					<% int num_op = 0; %>
					<% for (OrderProduct op : o.getOrderProducts()) {%>
					<% Product p = op.getProduct(); %>
					<li>
						<ul>
							<li>
								<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=op.getIdProduct()%>">
									<% if(p!=null) { %>
										<%=p.getName()%>
									<% } else { %>
										<%=op.getIdProduct()%>
									<% } %>
								</a>
							</li>
							<li>
								<% if(p!=null){ %>
									<% Image image = p.getImage(); %>
									<% if (image != null) {%>
										<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
											<img src="<%=image.getPath()%>" alt="<%=image.getAlt()%>">
										</a>
										<br/>
									<% } else { %>
										Immagine non trovata<br/>
									<% } %>
								<% } %>
							</li>
							<li>Prezzo singolo: <%=op.getPrice()%></li>
							<li>Quantità: <%=op.getQuantity()%></li>
							<li>Prezzo totale: <%=op.getTotalPrice()%></li>
						</ul>
					</li>
					<% num_op++; %>
					<% } %>
				</ul>
				<% num_o++; %>
			</div><br/>
		<% } %>
	<% } %>--%>
</body>
</html>
