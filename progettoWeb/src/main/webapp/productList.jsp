<%@page import="it.unitn.disi.entities.Image"%>
<%@page import="it.unitn.disi.dao.ImageDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />

        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs/dt-1.10.15/fc-3.2.2/fh-3.1.2/r-2.1.1/datatables.min.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Lista Prodotti</title>
    </head>
    <body class="sfondoP">
        <ul id="paginazione">
            <li>
                <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>

            <%ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");%>
            <%String searchQuery = (String) request.getAttribute("searchQuery");%>
            <%ImageDAO imageDAO = (ImageDAO) request.getAttribute("imageDAO");%>
            <%//=products%>

            <li>
                <h1>Risultati ricerca per: <%=searchQuery%>.</h1> <!-- TODO - mettere stringa cercata dall'utente -->
                <ul id="risRicerca">
                    <li id="risRicerca" class="categorie">
                        <jsp:include page="/categoryContainer.jsp"/>
                    </li>
                    <li id="risRicerca" class="prodotti">
                        <ul id="product">
                            <%System.err.println(products.size());%>
                            <%for (Product p : products) {%>
                            <% Image i = imageDAO.getProductImage(p.getId());%>
                            <li id="product">
                                <ul id="products">
                                    <li id="products">
                                        <img class="imgProdotto" src="<%=i.getPath()%>" alt="<%=i.getAlt()%>" class="thumb">
                                    </li>
                                    <li id="products" class="descrizione" >                                    
                                        <h3><%=p.getName()%></h3>
                                        <span class="meta">Product ID: 543J423 </span><br>
                                        <span class="meta"> <%=p.getDescription()%></span>
                                        <span class="price"> <%//p.getShopProduct().getPrice()%></span>
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
    <%--
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
    --%>
</html>
