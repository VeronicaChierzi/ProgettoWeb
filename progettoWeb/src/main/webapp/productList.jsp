<%@page import="it.unitn.disi.entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Prodotti</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <li>
        <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>
            <li>
        <h1>Product list</h1>
		
		<% Product[] products = (Product[]) request.getAttribute("products"); %>
		<%=products %><br/>
		<br/>
		
		<h2>product list</h2>
		<table>
		<%
			for (Product p : products) {
				out.print("<tr>");
				out.print("<td><a href='/progettoWeb/ProductServlet?id=1'>" + p.getName() + "</a></td>");
				out.print("<td>id: " + p.getId() + "</td>");
				out.print("<td>name: " + p.getName() + "</td>");
				out.print("<td>description: " + p.getDescription()+ "</td>");
				out.print("<td>id subcategory: " + p.getIdSubcategory()+ "</td>");
				out.print("<td>price min: " + p.getPriceMin()+ "</td>");
				out.print("</tr>");
			}
		%>
		</table>
            </li>
        </ul>
    </body>
</html>
