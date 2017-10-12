<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
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
        <h1>Order list</h1>

		<% Order[] orders = (Order[]) request.getAttribute("orders");%>
		<%=orders%><br/>
		<br/>

		<h2>product list</h2>
		<% for (Order o : orders) { %>
		<table>
			<% for (OrderProduct op : o.getOrderProducts()) {%>			
			<tr>
				<td>id order : <%=op.getIdOrder()%></td>
				<td>id product: <a href='/progettoWeb/ProductServlet?id=<%=op.getIdProduct()%>'><%=op.getIdProduct()%></a></td>
				<td>price: <%=op.getPrice()%></td>
				<td>quantity: <%=op.getQuantity()%></td>
				<td>total price: <%=op.getPrice() * op.getQuantity()%></td>
			</tr>
			<% } %>
		</table>
		<% }%>

    </body>
</html>
