<%-- Scheda del prodotto --%>
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
				<% } else { %>
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
					<% } %>
                                        <h3><%=StringUtils.formatForWeb(product.getName())%></h3>
					<h5>Cod. <%=product.getId()%></h5>
                                        <h4>Descrizione dell'oggetto:</h4>
                                        <p class="descrizioneProdotto"><%=product.getDescription()%></p>
					
					<% if (shopProduct == null) {%>
						Il prodotto non è disponibile<br/>
					<% } else { %>
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
                            </div>
            </li>
        </ul>
        
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>