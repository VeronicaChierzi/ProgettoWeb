<%-- Carrello --%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page import="it.unitn.disi.entities.Shop"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.carts.CartItem"%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.carts.Cart"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetCartUpdatedServlet%>"/>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrello</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <h1>Carrello</h1>
				<% Cart cart = (Cart) Model.Session.getAttribute(request, Model.Session.cart); %>
				<% if (cart == null || cart.isEmpty()) { %>
					Il carrello è vuoto<br/>
				<% } else { %>
					<% int num_ci = 0; %>
					<% for (CartItem ci : cart.getCartItems()) {
						try{
							ShopProduct sp = ci.getShopProduct();
							Product p = sp.getProduct();
							Shop s = sp.getShop();
							UserSeller us = s.getUserSeller();
							Image image = p.getImage(); %>
							<div style="border:1px solid black;">
								<ul>
									<li>
										<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=sp.getIdProduct()%>">
											<% if(p!=null) { %>
												<%=p.getName()%>
											<% } else { %>
												<%=sp.getIdProduct()%>
											<% } %>
										</a>
									</li>
									<li>
										<% if (image != null) {%>
											<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
												<img src="<%=image.getPath()%>" alt="<%=image.getAlt()%>">
											</a>
											<br/>
										<% } else { %>
											Immagine non trovata<br/>
										<% } %>
									</li>
									<li>Prezzo singolo: <%=sp.getPrice()%></li>
									<li>Quantità: <%=ci.getQuantity()%></li>
									<li>
										<form name="form<%=num_ci%>" action="<%=MyPaths.Servlet.Pubbliche.changeProductQuantityCartServlet%>" method="POST">
											<select name="quantity" autocomplete="off" onchange="document.form<%=num_ci%>.submit()">
												<% for (int i = 1; i <= 99 && i<=sp.getQuantity(); i++) {%>
													<option value="<%=i%>" <% if (i == ci.getQuantity()) { %> selected <% }%>><%=i%></option>
												<% } %>
											</select>
											<input type="hidden" name="id_product" value="<%=sp.getIdProduct()%>" />
											<input type="hidden" name="id_shop" value="<%=sp.getIdShop()%>" />
										</form>
									</li>
									<li>Prezzo totale: <%=ci.getTotalPrice()%></li>
									<li>
										<form action="<%=MyPaths.Servlet.Pubbliche.deleteProductCartServlet%>" method="POST">
											<input type="hidden" name="id_product" value="<%=sp.getIdProduct()%>" />
											<input type="hidden" name="id_shop" value="<%=sp.getIdShop()%>" />
											<input type="submit" value="remove" />
										</form>
									</li>
									<li>
										Venduto da <a href="<%=MyPaths.Jsp.allShop%>?id=<%=s.getId()%>"><%=us.getName()%></a>
									</li>
								</ul>
							</div>
							<br/>
						<% } catch (NullPointerException ex) { %>
							Impossibile trovare l'oggetto<br/><%-- NullPointerException in ShopProduct, Product, Shop, UserSeller o Image --%>
						<% } %>
					<% num_ci++; %>
					<% } %>

					Prezzo totale carrello: <%=cart.getTotalPrice()%><br/><br/>

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
            </li>
        </ul>
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>