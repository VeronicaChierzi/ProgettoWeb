<%-- Carrello --%>
<%@page import="java.text.DecimalFormat"%>
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
            <li class="container">
                <h1 class="paddingTop">Carrello</h1>
				<% Cart cart = (Cart) Model.Session.getAttribute(request, Model.Session.cart); %>
                                
                                <% if (Model.Messages.consumeBoolean(request, "buyCartFailed")) { %>
                                <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove-sign"></span> Impossibile effettuare l'acquisto. Contattare l'amministratore del sito.</div>
				<% } %>
				<% if (Model.Messages.consumeBoolean(request, "rimossoProdottoCarrello")) { %>
                                <div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok-sign"></span> Il prodotto è stato rimosso dal carrello</div>
				<% } %>
				<% if (Model.Messages.consumeBoolean(request, "quantitaProdottoCarrelloModificata")) { %>
                                <div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-info-sign"></span> La quantità del prodotto è stata modificata</div>
				<% } %>
                                
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
							<div>
                                                            <table style="border-spacing: 10px; padding-bottom: 20px;"><tr>
                                                                    <td style="padding: 20px; white-space: nowrap; width: 30%;">
                                                                    
										<% if (image != null) {%>
											<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
												<img src="<%=image.getPath()%>"  class="prodotto" alt="<%=image.getAlt()%>">
											</a>
										<% } else { %>
											Immagine non trovata<br/>
										<% } %>
                                                                    </td>
                                                                    <td style="padding: 20px; width: 70%">
                                                                        <ul style="list-style: none;">
                                                                            <li>
                                                                                <a href="<%=MyPaths.Jsp.allProduct%>?id=<%=sp.getIdProduct()%>"><h3>
											<% if(p!=null) { %>
												<%=p.getName()%>
											<% } else { %>
												<%=sp.getIdProduct()%>
											<% } %>
                                                                                    </h3>
										</a>
                                                                            </li>
                                                                            <li>
										Venduto da <a href="<%=MyPaths.Jsp.allShop%>?id=<%=s.getId()%>"><%=us.getName()%></a>
                                                                            </li>
                                                                            <li><h3>&euro; <%=new DecimalFormat("#.##").format(sp.getPrice())%></h3></li>
                                                                            <li style="display: flex">Quantità:
										<form name="form<%=num_ci%>" action="<%=MyPaths.Servlet.Pubbliche.changeProductQuantityCartServlet%>" method="POST">
											<select name="quantity" autocomplete="off" onchange="document.form<%=num_ci%>.submit()" style="padding: 2px; margin-left: 10px">
												<% for (int i = 1; i <= 99 && i<=sp.getQuantity(); i++) {%>
													<option value="<%=i%>" <% if (i == ci.getQuantity()) { %> selected <% }%>><%=i%></option>
												<% } %>
											</select>
											<input type="hidden" name="id_product" value="<%=sp.getIdProduct()%>" />
											<input type="hidden" name="id_shop" value="<%=sp.getIdShop()%>" />
										</form>
									</li>
                                                                        <li style="display: flex;"><h2>Totale: &euro; <%=new DecimalFormat("#.##").format(ci.getTotalPrice())%></h2>
                                                                            <form action="<%=MyPaths.Servlet.Pubbliche.deleteProductCartServlet%>" style="padding-left: 20px; margin-top: 20px;" method="POST">
											<input type="hidden" name="id_product" value="<%=sp.getIdProduct()%>" />
											<input type="hidden" name="id_shop" value="<%=sp.getIdShop()%>" />
                                                                                        <input type="submit" value="Rimuovi dal carrello" class="btn btn-warning" />
										</form>
									</li>
									
                                                                        </ul>
                                                                </td></tr>
                                                            </table>
							</div>
                                                                        
						<% } catch (NullPointerException ex) { %>
							Impossibile trovare l'oggetto<br/><%-- NullPointerException in ShopProduct, Product, Shop, UserSeller o Image --%>
						<% } %>
					<% num_ci++; %>
					<% } %>
                                        
                                        Totale: &euro; <%=new DecimalFormat("#.##").format(cart.getTotalPrice())%><br/><br/>

					<% User user = (User)Model.Session.getAttribute(request, Model.Session.user); %>
					<% if (user!=null){ %>
						<form method="post" action="<%=MyPaths.Servlet.Pubbliche.buyCart%>">
							<input type="submit" value="compra" />
						</form>
					<% } else { %>
                                                <div class="alert alert-warning" role="alert">
                                                    Effettua il <a href="<%=MyPaths.Jsp.anonymousLogin%>" class="alert-link">login</a> per procedere all'acquisto.
                                                </div>
					<% } %>
				<% } %>
            </li>
        </ul>
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>
