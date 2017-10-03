<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
		<jsp:include page="/navbar.jsp" />
        <h1>Product</h1>
		
		<% Product product = (Product) request.getAttribute("product"); %>
		product: <%=product %><br/>
		<% if(product!=null) { %>
		id: <%=product.getId()%><br/>
		name: <%=product.getName()%><br/>
		description: <%=product.getDescription()%><br/>
		<br/>
			<% ShopProduct shopProduct = (ShopProduct) product.getShopProduct(); %>
			shopProduct: <%=shopProduct %><br/>
			<% if(shopProduct!=null) { %>
			idProduct: <%=shopProduct.getIdProduct()%><br/>
			idShop: <%=shopProduct.getIdShop()%><br/>
			price: <%=shopProduct.getPrice()%><br/>
			quantity: <%=shopProduct.getQuantity()%><br/>
			<form method="get" action="AddToCartServlet">
				<input type="hidden" name="id_product" value="<%=product.getId() %>" />
				<input type="hidden" name="id_shop" value="<%=shopProduct.getIdShop() %>" />
				<%--<input type="hidden" name="price" value="<%=shopProduct.getPrice()%>" />--%>
				<td><input type="submit" value="add to cart" /></td>
			</form>
			<% } else { %>
			il prodotto non è disponibile<br/>
			<% } %>
			<br/>
		<% } %>
		
		product è una variabile di scope request<br/>
		ogni volta che si ricarica la pagina, viene interrogato il database e si crea un nuovo oggetto<br/>
		bisogna accedere a questa pagina dalla servlet (o richiamare la servlet dalla jsp?)<br/>
		<br/>
		servlet:<br/>
		<a href="/progettoWeb/ProductServlet?id=1">ProductServlet?id=1</a><br/>
		<a href="/progettoWeb/ProductServlet?id=2">ProductServlet?id=2</a><br/>
		<a href="/progettoWeb/ProductServlet?id=3">ProductServlet?id=3</a><br/>
		<br/>
		jsp:<br/>
		<a href="/progettoWeb/product.jsp?id=1">product.jsp?id=1</a><br/>
		<a href="/progettoWeb/product.jsp?id=2">product.jsp?id=2</a><br/>
		<a href="/progettoWeb/product.jsp?id=3">product.jsp?id=3</a><br/>
		<br/>
		
    </body>
</html>
