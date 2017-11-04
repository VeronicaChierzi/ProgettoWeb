<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.carts.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrello</title>
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
                <h1>Carrello</h1>
                <% Cart cart = (Cart) session.getAttribute("cart"); %>
                <% if (cart == null) { %>
                Il carrello è vuoto
                <% } else { %>
                <% float totalPrice = 0.0f; %>
                <table border="1">
                    <% for (Order o : cart.getOrders()) {%>
                    <tr>
                        <td>id order: <%=o.getId()%></td>
                        <td>id shop: <%=o.getIdShop()%></td>
                        <td>id user: <%=o.getIdUser()%></td>
                        <td>datetime purchase: <%=o.getDatetimePurchase()%></td>
                        <td>total price order: <%=o.getTotalPrice()%></td>
                        <td>order products: <%=o.getOrderProducts()%></td>
                    </tr>
                    <tr>
                        <td>
                            <table border="1">
                                <% for (OrderProduct op : o.getOrderProducts()) {%>
                                <tr>
                                    <td>id order: <%=op.getIdOrder()%></td>
                                    <td>id product: <%=op.getIdProduct()%></td>
                                    <td>single price: <%=op.getPrice()%></td>
                                    <td>quantity: <%=op.getQuantity()%></td>
                                    <td>total price orderProduct: <%=op.getTotalPrice()%></td>
                                </tr>
                                <% } %>						
                            </table>
                        </td>
                    </tr>
                    <% }%>
                </table>
                total price cart: <%=cart.getTotalPrice()%>
                <form method="post" action="BuyServlet">
                    <td><input type="submit" value="compra" /></td>
                </form>
                <% }%>
            </li>
        </ul>

    </body>
</html>
