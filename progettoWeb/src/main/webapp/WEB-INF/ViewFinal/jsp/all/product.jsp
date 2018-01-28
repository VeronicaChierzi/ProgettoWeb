<%-- Scheda del prodotto --%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="it.unitn.disi.entities.ReviewProduct"%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="it.unitn.disi.utils.StringUtils"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetProduct%>"/>


<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prodotto</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <div class="container">	
                    <% Product product = (Product) Model.Request.getAttribute(request, Model.Request.product);%>
                    <% ShopProduct shopProduct = (ShopProduct) product.getShopProduct();%>

                    <% if (product == null) { %>
                    Prodotto non trovato<br/>
                    <% } else {%>
                    <h1 class="paddingLeftTop"><%=StringUtils.formatForWeb(product.getName())%></h1>
                    <% Image image = product.getImage(); %>
                    <table style="border-spacing: 10px;">
						<tr>
                            <% if (image != null) {%>
                            <td style="white-space: nowrap; width: auto;">
                                <a href="<%=MyPaths.Jsp.allProduct%>?id=<%=product.getId()%>">
                                    <img src="<%=image.getPath()%>" class="prodotto" alt="<%=image.getAlt()%>">
                                </a>
                            </td>
                        <br/>
                        <td style="padding: 20px; width: 70%">
                            <% } else { %>
                            Immagine non trovata<br/>
                            <% }%>
                            <h3><%=StringUtils.formatForWeb(product.getName())%></h3>
                            <h5>Cod. <%=product.getId()%></h5>
                            <h4>Descrizione dell'oggetto:</h4>
                            <p class="descrizioneProdotto"><%=product.getDescription()%></p>

                            <% if (shopProduct == null) {%>
                            Il prodotto non è disponibile<br/>
                            <% } else {%>
                            Venduto e spedito da: <a href="<%=MyPaths.Jsp.allShop + "?id=" + shopProduct.getIdShop()%>">
                                <%=shopProduct.getShop().getUserSeller().getName()%></a><br/>
                            <h2>&euro; <%=new DecimalFormat("#.##").format(shopProduct.getPrice())%></h2>
                            Quantità disponibile: <%=shopProduct.getQuantity()%><br/>
                            <div class="addToCart" style="display: table-caption;">
                                <form method="post" action="<%=MyPaths.Servlet.Pubbliche.addToCart%>">
                                    <input type="hidden" name="id_product" value="<%=product.getId()%>" />
                                    <input type="hidden" name="id_shop" value="<%=shopProduct.getIdShop()%>" />
                                    <%--<input type="hidden" name="current_price" value="<%=shopProduct.getPrice()%>" />--%>
                                    <select name="quantity" autocomplete="off" style="padding: 2px; margin-right: 10px">
                                        <% for (int i = 1; i <= shopProduct.getQuantity() && i <= 99; i++) {%>
                                        <option value="<%=i%>" <% if (i == 1) { %> selected <% }%>><%=i%></option>
                                        <% } %>
                                    </select>
                                    <button id="aggCarr" class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-shopping-cart"></span>  Aggiungi al carrello</input>
                                </form>
                            </div>
                            <% } %>
                        </td>
                        </tr>
					</table>
                    <br/>
					<a href="<%=MyPaths.Jsp.allShopsProduct%>?id=<%=product.getId()%>">Altri venditori</a>
					<br/>
                    <% } %>

                    <br/>
                    <% ReviewProduct[] rps = (ReviewProduct[]) Model.Request.getAttribute(request, Model.Request.productReviews); %>
                    <% for (ReviewProduct r : rps) {%>
					<div style="border-bottom-color: black; border-bottom-width: 1px; border-bottom-style: solid; margin-bottom: 10px; padding-bottom: 10px;">
						<span style="font-size: 20px; font-style: italic; font-weight: 700;"><%=r.getTitle()%></span><br>
						<span style="font-size: 20px;"><%=r.getText()%></span><br>
						<span class="price"style="font-size: 20px; margin-bottom: 0px;">Voto: <%=r.getRate()%></span><br>
						<span style="font-size: 15px;"><%=new SimpleDateFormat("dd/MM/yyyy").format(r.getDatetime())%></span><br>
					</div>

                    <% } %>

                    <% if (Model.Messages.consumeBoolean(request, "recensioneFallita")) { %>
                    <br><div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove-sign"></span> Impossibile aggiungere la recensione. Controlla di non averla già fatta.</div>
                    <% } %>
                    <% if (Model.Messages.consumeBoolean(request, "recensioneAggiunta")) { %>
                    <br><div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok-sign"></span> Recensione pubblicata con successo!</div>
                    <% } %>


                    <% Object a = Model.Request.getAttribute(request, Model.Request.orderId);
						int orderId = -1;
						if (a != null) {
							orderId = (int) a;
						} %>
                    <% if (orderId != -1) { //mostrare possibilità di aggiungere prodotti %>
                    <form method="post" action="<%=MyPaths.Servlet.Pubbliche.addReview%>">
                        <input type="hidden" name="id_product" value="<%=product.getId()%>" />
                        <input type="hidden" name="id_order" value="<%=orderId%>"/>
                        Titolo<br>
                        <input style="width: 100%; border-style: hidden; margin-bottom: 10px;" type="text" name="title" value=""/>
                        <br><br>
                        Testo<br>
                        <textarea style="width: 100%; height: 100px; border-style: hidden; margin-bottom: 10px;" name="text" value=""></textarea>
						
						<div style="margin-bottom: 10px;margin-left: auto; margin-right: auto; display: table;">	
							<div class="stars" style="margin-bottom: 20px; margin-left: auto; margin-right: auto;">
								<input type="radio" name="rate" class="star-1" id="star-1" value="1" />
								<label class="star-1" for="star-1">1</label>
								<input type="radio" name="rate" class="star-2" id="star-2" value="2"/>
								<label class="star-2" for="star-2">2</label>
								<input type="radio" name="rate" class="star-3" id="star-3" value="3"/>
								<label class="star-3" for="star-3">3</label>
								<input type="radio" name="rate" class="star-4" id="star-4" value="4"/>
								<label class="star-4" for="star-4">4</label>
								<input type="radio" name="rate" class="star-5" id="star-5" value="5"/>
								<label class="star-5" for="star-5">5</label>
								<span></span>
							</div>            




							<%--<select name="rate" autocomplete="off" style="padding: 2px; margin-right: 10px;">
								<% for (int i = 1; i <= 5; i++) {%>
								<option value="<%=i%>" <% if (i == 5) { %> selected <% }%>><%=i%></option>
								<% } %>
							</select>--%>
							<button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-plus-sign"></span>  Aggiungi recensione</button>
						</div>

                    </form>

                    <% }%>
                </div>
            </li>
        </ul>

    </body>
</html>