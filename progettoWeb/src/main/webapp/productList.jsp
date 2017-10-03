<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Product"%>
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
        <h1>Product list</h1>
		
		<% Product[] products = (Product[]) request.getAttribute("products"); %>
		<%=products %><br/>
		<br/>
		
		<h2>product list</h2>
		<table>
		<% for (Product p : products) { %>
			<tr>
			<td><a href='/progettoWeb/ProductServlet?id=<%=p.getId() %>'><%= p.getName() %></a></td>
			<td>id: <%=p.getId() %></td>
			<td>name: <%=p.getName() %></td>
			<td>description: <%=p.getDescription() %></td>
			<td>id subcategory: <%=p.getIdSubcategory() %></td>
			
			<% ShopProduct sp = (ShopProduct) p.getShopProduct(); %>
			<td>shopProduct: <%=sp %></td>
			<% if(sp!=null) { %>
			<td>idProduct: <%=sp.getIdProduct()%></td>
			<td>idShop: <%=sp.getIdShop()%></td>
			<td>price: <%=sp.getPrice()%></td>
			<td>quantity: <%=sp.getQuantity()%></td>
			<% } else { %>
			<td>Prodotto non disponibile</td>
			<% } %>
			
			</tr>
		<% } %>
		</table>
		
    </body>
</html>
