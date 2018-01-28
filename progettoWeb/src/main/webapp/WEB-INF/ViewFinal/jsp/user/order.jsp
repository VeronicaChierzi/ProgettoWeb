<%-- Scheda dettagliata di un ordine effettuato --%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetOrder%>"/>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Il tuo ordine</title>
    </head>
    <body class="sfondo">

		<ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

			<li class="container">

				<% Order o = (Order) Model.Request.getAttribute(request, Model.Request.orderUser); %>
				<% boolean a = (boolean) Model.Request.getAttribute(request, Model.Request.isSegnalato); %>
				<% if (o == null) { %>
				<div style="margin-left: 10px; font-size: 30px;">Ordine non trovato</div><br/>
				<% } else {%>
				<div>
					<h1>Informazioni Ordine numero <%=o.getId()%></h1>
					<ul>
						<li>
							<% Shop s = o.getShop();
								if (s != null) {
									UserSeller us = s.getUserSeller();
									if (us != null) {%>
							Venduto da 
							<a href="<%=MyPaths.Jsp.allShop%>?id=<%=s.getId()%>"><%=us.getName()%></a>
							<% } %>
							<% }%>
						</li>
						<li>Data di acquisto: <%=new SimpleDateFormat("dd/MM/yyyy").format(o.getDatetimePurchase())%></li>
            <li style="margin-left: 10px;">
				<% if (o.isSpedizione()) { %>
					Spedizione
				<% } else { %>
					Ritiro in Negozio
				<% } %>
			</li>
			
			<% if (o.isSpedizione()) { %>
            <li style="margin-left: 10px;">
				Stato spedizione:
				<% if (o.isConcluso()) { %>
					Spedito
				<% } else { %>
					Non ancora spedito
				<% } %>
			</li>
				<% if (o.isConcluso()) { %>
		            <li style="margin-left: 10px;">Data spedizione: <%=new SimpleDateFormat("dd/MM/yyyy").format(o.getDatetimeConcluso())%></li>
				<% } %>
			<% } else { %>
            <li style="margin-left: 10px;">
				Stato:
				<% if (o.isConcluso()) { %>
					Ritirato
				<% } else { %>
					Non ancora ritirato
				<% } %>
			</li>
				<% if (o.isConcluso()) { %>
		            <li style="margin-left: 10px;">Data ritiro: <%=new SimpleDateFormat("dd/MM/yyyy").format(o.getDatetimeConcluso())%></li>
				<% } %>
			<% } %>

					<li>Prezzo totale ordine: &euro; <%=new DecimalFormat("#.##").format(o.getTotalPrice())%></li>
					</ul>
					<ul>
						<% int num_op = 0; %>
						<% for (OrderProduct op : o.getOrderProducts()) {%>
						<% Product p = op.getProduct();%>
						<li style="margin-top: 20px;">
							<div class="container" style="text-align: center;">
								<div style="text-align: left; font-size: 20px;">Prodotti dell'ordine</div>
								<table>
								<tr id="tabord">
									<td style="width: 30%;">
										<% if (p != null) { %>
										<% Image image = p.getImage(); %>
										<% if (image != null) {%>
										<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
											<img style="margin:20px; width: 60%; height: auto;"class="resizeImg" src="<%=image.getPath()%>" alt="<%=image.getAlt()%>">
										</a>
										<% } else { %>
										Immagine non trovata<br/>
										<% } %>
										<% }%>
									</td>
									<td style="margin-right: 5px;">
										<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=op.getIdProduct()%>">
											<% if (p != null) {%>
											<%=p.getName()%>
											<% } else {%>
											<%=op.getIdProduct()%>
											<% }%>
										</a>
									</td>
									
									<td id="altroes">
										<div id="argg">Prezzo</div>
										<div>&euro; <%=op.getPrice()%></div>
									</td>
									<td id="altroes">
										<div id="argg">Quantità</div>
										<div><%=op.getQuantity()%></div>
									</td>
									<td id="altroes">
										<div id="argg">Totale</div>
										<div>&euro; <%=op.getTotalPrice()%></div>
									</td>
								</tr>
							</table>
								
							</div>
							
						</li>

						<%--
						<li>
								<% ReviewProduct rp = op.getReviewProduct(); %>
								<% if (rp!=null) { %>
										<a href="<%=MyPaths.Jsp.userReviewProduct%>">
												Recensisci Prodotto
										</a>
								<% } else { %>
										Hai già lasciato una recensione per questo prodotto
								<% } %>
						</li>
						--%>
						<% num_op++; %>
						<% } %>
                                                
                                                <% if(!a) { %>
						
						<li id="aprisegnalazione">
							<form action="<%=MyPaths.Servlet.Pubbliche.addUserSegnalazione%>" method="post">
								<input type="hidden" name="idOrder" value="<%=o.getId()%>"/>
								Titolo<br>
								<input style="width: 100%; border-style: hidden; margin-bottom: 5px;" type="text" name="title" value=""/>
								<br><br>
								Testo<br>
								<textarea style="width: 100%; height: 100px; border-style: hidden; margin-bottom: 10px;" name="description" value=""></textarea>
								<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-plus-sign"></span>Apri segnalazione</button>
							</form>
						</li>
                                                <% } %>
					</ul>
				</div>
				<% }%>	
			</li>
		</ul>
	</body>
</html>

