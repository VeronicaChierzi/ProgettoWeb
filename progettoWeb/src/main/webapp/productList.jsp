<%@page import="java.util.ArrayList"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />

        <link rel="stylesheet" type="text/css" href="css/searchresultstyle.css"/>        
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/r-2.1.1/datatables.min.css"/>
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

                <% ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");%>
                <% String searchQuery = (String) request.getAttribute("searchQuery");%>
                <%=products%><br/>
                <br/>

                <h2>product list</h2>
                <table id="usersTable" class="table">
                    <thead><th>Image</th><th>ID</th><th>Name</th><th>Description</th><th>ID Subcat</th><th>Price min</th></thead>
                        <%
                            for (Product p : products) {
                                out.print("<tr>");
                                out.print("<td><a href='/progettoWeb/ProductServlet?id=" + p.getId() + "'>" + p.getName() + "</a></td>");
                                out.print("<td>" + p.getId() + "</td>");
                                out.print("<td>" + p.getName() + "</td>");
                                out.print("<td>" + p.getDescription() + "</td>");
                                out.print("<td>" + p.getIdSubcategory() + "</td>");
                                out.print("<td>" + p.getPriceMin() + "</td>");
                                out.print("</tr>");
                            }
                        %>
                </table>
            </li>

            <li><h1>Risultati ricerca per: <%=searchQuery%>.</h1> <!-- TODO - mettere stringa cercata dall'utente -->

                <ul id="products" class="list clearfix">

                    <%
                        /*for (Product p : products) {
                            out.print("<section class=\"left\">"
                                    + "<img heigth=\"200px\" width=\"200px\" src=\"");
                            out.print("img/bagno/accappatoi/2382014811_1_1_3.jpg"); //TODO - Sostituire con query al db per prendere path e alt immagine
                            out.print("\" alt=\"");
                            out.print("sostituire con alt");
                            out.print("\" class=\"thumb\">"
                                    + "</section>"
                                    + "<section>");
                            out.print("<h3>" + p.getName() + "</h3>");
                            out.print("<span class=\"meta\">" + p.getDescription() + "</span>");
                            out.print("<span class=\"price\">" + p.getPriceMin() + "</span>");
                            out.print("</section>");
                            out.print("<td>" + p.getIdSubcategory() + "</td>");
                            out.print("</tr>");
                        }*/
                    %>
                    <%
                            for (Product p : products) {%>
                    <li class="clearfix">
                        <section class="left">
                            <img heigth="200px" width="200px" src="img/bagno/accappatoi/2382014811_1_1_3.jpg" alt="default thumb" class="thumb">
                        </section>

                        <section>
                            <h3><%=p.getName()%></h3>
                            <span class="meta">Product ID: 543J423</span>
                            <span class="meta"><%=p.getDescription()%></span>
                            <span class="price"><%=p.getPriceMin()%></span>
                        </section>
                        <section class="right">
                            <span class="darkview">
                                <a href="javascript:void(0);"><img src="img/prova/add-to-cart-btn.png" alt="Add to Cart"></a>
                            </span>
                        </section>
                    </li>
                    <%}
                    %>
                    <li class="clearfix">
                        <section class="left">
                            <img heigth="200px" width="200px" src="img/bagno/accappatoi/8440014433_1_1_3.jpg" alt="default thumb" class="thumb">
                        </section>

                        <section>
                            <h3>Product Name</h3>
                            <span class="meta">Product ID: 543J423</span>

                            <span class="price">$45.00</span>
                        </section>

                        <section class="right">
                            <span class="darkview">
                                <a href="javascript:void(0);"><img src="img/prova/add-to-cart-btn.png" alt="Add to Cart"></a>
                            </span>
                        </section>
                    </li>

                </ul>

            </li>
        </ul>


        <script type="text/javascript" src="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/r-2.1.1/datatables.min.js"></script>
        
        <script>
            $(document).ready(function () {
                $("#usersTable").dataTable();
            });
        </script>
    </body>
</html>
