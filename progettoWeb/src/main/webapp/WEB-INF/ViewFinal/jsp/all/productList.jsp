<%-- Lista di prodotti visualizzata dopo una ricerca --%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetProductList%>"/>

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
				<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            </li>
			<% Product[] productList = (Product[]) Model.Request.getAttribute(request, Model.Request.productList); %>
			<% String search = Model.Parameter.get(request, Model.Parameter.textSearch); %>
            <li>
				<%--
                <h1>
					<% if (search != null) {%>
						Risultati ricerca per: "<%=search%>"  <!-- TODO - mettere stringa cercata dall'utente -->
					<% } %>
				</h1>
				--%>
                <ul id="risRicerca">
                    <li id="risRicerca" class="categorie">
                        <jsp:include page="<%=MyPaths.Jsp._utilsCategoryList%>"/>
                    </li>
                    <li id="risRicerca" class="prodotti">
						<% if (productList != null && productList.length > 0) { %>
                        <ul id="product">
							<% for (Product p : productList) {%>
								<% Image i = p.getImage(); %>
								<% ShopProduct sp = p.getShopProduct(); %>

								<li id="product">
									<ul id="products">
										<li id="products">
											<% if (i != null) {%>
												<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
													<img class="imgProdotto" src="<%=i.getPath()%>" alt="<%=i.getAlt()%>" class="thumb">
												</a>
											<% } else { %>
												<span class="meta">Immagine non trovata</span>
											<% } %>
										</li>
										<li id="products" class="descrizione" >
											<h3><a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>"><%=p.getName()%></a></h3>
											<span class="meta">Product ID: <%=p.getId()%></span><br>
											<span class="meta"> <%=p.getDescription()%></span>
											<% if (sp != null) {%>
												<span class="price"> <%=sp.getPrice()%></span>
												<span class="meta">Quantity: <%=sp.getQuantity()%></span>
											<% } else { %>
												<span class="meta">Prodotto non disponibile</span>
											<% } %>
										</li>
										<li id="products" class="carrello">
											<span>
												<a href="javascript:void(0);"><img src="/progettoWeb/img/prova/add-to-cart-btn.png">CARRELLO</a>
											</span>
										</li>
									</ul>
								</li>
                            <% } %>
                        </ul>
						<% } else { %>
							<span class="meta">Non è stato trovato nessun prodotto</span>
						<% } %>
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

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>