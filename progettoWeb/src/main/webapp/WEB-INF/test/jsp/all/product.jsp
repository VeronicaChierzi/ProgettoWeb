<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Image"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>
<jsp:include page="<%=MyPaths.Private.Servlet.All.getProduct%>"/>

<div>
	<h1>Scheda Prodotto</h1>

	<% Product product = (Product) Model.Request.getAttribute(request, Model.Request.product);%>
	<% if (product == null) { %>
	Prodotto non trovato<br/>
	<% } else { %>

	<% Image image = product.getImage(); %>
	<% if (image != null) {%>
	<a href="<%=MyPaths.Public.Jsp.All.product%>?id=<%=product.getId()%>">
		<img src="<%=image.getPath()%>" alt="<%=image.getAlt()%>">
	</a>
	<br/>
	<% } else { %>
	Immagine non trovata<br/>
	<% }%>


	id: <%=product.getId()%><br/>
	name: <%=product.getName()%><br/>
	description: <%=product.getDescription()%><br/>
	<br/>

	<% ShopProduct shopProduct = (ShopProduct) product.getShopProduct();%>
	<% if (shopProduct == null) {%>
	Il prodotto non è disponibile<br/>
	<% } else {%>
	idProduct: <%=shopProduct.getIdProduct()%><br/>
	idShop: <%=shopProduct.getIdShop()%><br/>
	price: <%=shopProduct.getPrice()%><br/>
	quantity: <%=shopProduct.getQuantity()%><br/>
	<form method="post" action="AddToCartServlet">
		<input type="hidden" name="id_product" value="<%=product.getId()%>" />
		<input type="hidden" name="id_shop" value="<%=shopProduct.getIdShop()%>" />
		<input type="hidden" name="current_price" value="<%=shopProduct.getPrice()%>" />
		<select name="quantity" autocomplete="off">
			<% for (int i = 1; i <= shopProduct.getQuantity() && i <= 99; i++) {%>
			<option value="<%=i%>" <% if (i == 1) { %> selected <% }%>><%=i%></option>
			<% } %>
		</select>
		<td><input type="submit" value="add to cart" /></td>
	</form>
	<% } %>
	<br/>
</div>
<% }%>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>