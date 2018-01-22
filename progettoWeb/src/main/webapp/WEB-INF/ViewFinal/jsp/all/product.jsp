<%-- Scheda del prodotto --%>
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
                    <table style="padding: 20px; border-spacing: 10px;"><tr>
                            <% if (image != null) {%>
                            <td style="padding: 20px; white-space: nowrap; width: auto;">
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
                            Venduto e spedito da: <%=shopProduct.getShop().getUserSeller().getName()%><br/>
                            <h2>&euro; <%=new DecimalFormat("#.##").format(shopProduct.getPrice())%></h2>
                            Quantità disponibile: <%=shopProduct.getQuantity()%><br/>
                            <div class="addToCart">
                                <form method="post" action="<%=MyPaths.Servlet.Pubbliche.addToCart%>">
                                    <input type="hidden" name="id_product" value="<%=product.getId()%>" />
                                    <input type="hidden" name="id_shop" value="<%=shopProduct.getIdShop()%>" />
                                    <%--<input type="hidden" name="current_price" value="<%=shopProduct.getPrice()%>" />--%>
                                    <select name="quantity" autocomplete="off" style="padding: 2px; margin-right: 10px">
                                        <% for (int i = 1; i <= shopProduct.getQuantity() && i <= 99; i++) {%>
                                        <option value="<%=i%>" <% if (i == 1) { %> selected <% }%>><%=i%></option>
                                        <% } %>
                                    </select>
                                    <button type="submit"><span class="glyphicon glyphicon-shopping-cart"></span>  Aggiungi al carrello</input>
                                </form>
                            </div>
                            <% } %>
                        </td>
                        </tr></table>
                    <br/>
                    <% } %>

                    <br/>
                    <% ReviewProduct[] rps = (ReviewProduct[]) Model.Request.getAttribute(request, Model.Request.productReviews); %>
                    <% for (ReviewProduct r : rps) {%>

                    <span class="meta"><%=r.getTitle()%></span><br>
                    <span class="meta"><%=r.getText()%></span><br>
                    <span class="meta"><%=r.getRate()%></span><br>
                    <span class="meta"><%=r.getDatetime()%></span><br>


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
                        <input type="text" name="title" value=""/>
                        <br><br>
                        Testo<br>
                        <input type="text" name="text" value=""/>
                        <select name="rate" autocomplete="off" style="padding: 2px; margin-right: 10px">
                            <% for (int i = 1; i <= 5; i++) {%>
                            <option value="<%=i%>" <% if (i == 5) { %> selected <% }%>><%=i%></option>
                            <% } %>
                        </select>
                        <button type="submit"><span class="glyphicon glyphicon-plus-sign"></span>  Aggiungi recensione</button>
                    </form>

                    <% }%>
                </div>
            </li>
        </ul>

    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>
