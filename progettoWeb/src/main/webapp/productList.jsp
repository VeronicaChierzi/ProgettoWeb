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

            <%ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");%>
            <%String searchQuery = (String) request.getAttribute("searchQuery");%>
            <%//=products%>

            <li>
                <h1>Risultati ricerca per: <%=searchQuery%>.</h1> <!-- TODO - mettere stringa cercata dall'utente -->
                <ul id="risRicerca">
                    <li id="risRicerca" class="categorie">
                        <jsp:include page="/categoryContainer.jsp"/>
                    </li>
                    <li id="risRicerca" class="prodotti">
                        <ul id="product">
                            <%for (Product p : products) {%>
                            <li id="product">
                                <ul id="products">
                                    <li id="products">
                                        <img heigth="200px" width="200px" src="img/bagno/accappatoi/2382014811_1_1_3.jpg" alt="default thumb" class="thumb">
                                    </li>
                                    <li id="products" >                                    
                                        <h3><%=p.getName()%></h3>
                                        <span class="meta">Product ID: 543J423 </span><br>
                                        <span class="meta"> <%=p.getDescription()%></span>
                                        <span class="price"> <%=p.getPriceMin()%></span>
                                    </li>
                                    <li id="products" class="carrello">                                    
                                        <span>
                                            <a href="javascript:void(0);"><img src="img/prova/add-to-cart-btn.png">CARRELLO</a>
                                        </span>
                                    </li>
                                </ul>
                            </li>
                            <%}%>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
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
