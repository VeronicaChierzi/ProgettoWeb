<%-- Scheda dettagliata di un ordine effettuato --%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.Shop"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.entities.orders.OrderProduct"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetOrder%>"/>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
        <h1>Order</h1>
        <% Order o = (Order) Model.Request.getAttribute(request, Model.Request.orderUser); %>
        <% if (o == null) { %>
        Ordine non trovato<br/>
        <% } else {%>
        <div>
            <h3>Informazioni Ordine</h3>
            <ul>
                <li><a href="<%=MyPaths.Jsp.userOrder%>?id=<%=o.getId()%>">Ordine n°<%=o.getId()%></a></li>
                <li>
                    <% Shop s = o.getShop();
                        if (s != null) {
                            UserSeller us = s.getUserSeller();
                                                                if (us != null) {%>
                    Venduto da 
                    <a href="<%=MyPaths.Jsp.allShop%>?id=<%=s.getId()%>">
                        <%=us.getName()%>
                    </a>
                    <% } %>
                    <% }%>
                </li>
                <li>Data di acquisto: <%=o.getDatetimePurchase()%></li>
                <li>Prezzo totale ordine: <%=o.getTotalPrice()%></li>
                <li>
                    <form action="<%=MyPaths.Servlet.Pubbliche.addUserSegnalazione%>" method="post">
                        <input type="hidden" name="idOrder" value="<%=o.getId()%>"/>
                        Titolo<br>
                        <input style="width: 100%; border-style: hidden; margin-bottom: 10px;" type="text" name="title" value=""/>
                        <br><br>
                        Testo<br>
                        <input style="width: 100%; height: 100px; border-style: hidden; margin-bottom: 10px;" type="text" name="description" value=""/>
                        <button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-plus-sign"></span>  Apri segnalazione</button>
                    </form>
                </li>
            </ul>
            <h4>Prodotti dell'ordine</h4>
            <ul>
                <% int num_op = 0; %>
                <% for (OrderProduct op : o.getOrderProducts()) {%>
                <% Product p = op.getProduct();%>
                <li>
                    <ul>
                        <li>
                            <a href="<%=MyPaths.Jsp.allProduct%>?id=<%=op.getIdProduct()%>">
                                <% if (p != null) {%>
                                <%=p.getName()%>
                                <% } else {%>
                                <%=op.getIdProduct()%>
                                <% } %>
                            </a>
                        </li>
                        <li>
                            <% if (p != null) { %>
                            <% Image image = p.getImage(); %>
                            <% if (image != null) {%>
                            <a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
                                <img src="<%=image.getPath()%>" alt="<%=image.getAlt()%>">
                            </a>
                            <br/>
                            <% } else { %>
                            Immagine non trovata<br/>
                            <% } %>
                            <% }%>
                        </li>
                        <li>Prezzo singolo: <%=op.getPrice()%></li>
                        <li>Quantità: <%=op.getQuantity()%></li>
                        <li>Prezzo totale: <%=op.getTotalPrice()%></li>
                            <%--
                            <li>
                                    <% ReviewProduct rp = op.getReviewProduct(); %>
                                    <% if (rp!=null) { %>
                                            <a href="<%=MyPaths.Jsp.userReviewProduct%>">
                                                    Recensisci Prodotto
                                            </a>
                                    <% } else { %>
                                            Hai già lasciato una recensione per questo prodotto
                                    <% } %>
                            </li>
                            --%>
                    </ul>
                </li>
                <% num_op++; %>
                <% } %>
            </ul>
        </div>
        <br/>
        <% }%>
    </body>
</html>