<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>

<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%=MyPaths.Private.Servlet.All.getProduct%>"/>
<html>
    <head>
        <%--<jsp:include page="/navbarHead.jsp" />--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prodotto</title>
    </head>
    <body>
        <jsp:include page="<%=MyPaths.Private.Jsp.Utils.navbar%>" />
		<%--<jsp:include page="<%=MyPaths.Private.Jsp.Utils.searchBar%>"/>--%>
		
        <h1>Product</h1>
		<% Product product = Model.Request.getProduct(request);%>
		product: <%=product%><br/>
		<% if (product == null) {%>
		Prodotto non trovato<br/>
		<% } else {%>
		id: <%=product.getId()%><br/>
		name: <%=product.getName()%><br/>
		description: <%=product.getDescription()%><br/>
		<br/>
		<% ShopProduct shopProduct = (ShopProduct) product.getShopProduct();%>
		shopProduct: <%=shopProduct%><br/>
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
		<% }%>
	</ul>
</body>
</html>
