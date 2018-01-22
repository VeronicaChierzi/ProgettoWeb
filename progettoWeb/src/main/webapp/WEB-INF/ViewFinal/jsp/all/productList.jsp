<%-- Lista di prodotti visualizzata dopo una ricerca --%>
<%@page import="it.unitn.disi.entities.categories.CategoryContainer"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="it.unitn.disi.utils.StringUtils"%>
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
        <% CategoryContainer categoryContainer = (CategoryContainer) Model.Application.getAttribute(config, Model.Application.categoryContainer); %>

    </head>
    <body class="sfondoP">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            </li>
			<% Product[] productList = (Product[]) Model.Request.getAttribute(request, Model.Request.productList); %>
			<% String search = Model.Parameter.get(request, Model.Parameter.textSearch); %>
			<% String category = Model.Parameter.get(request, Model.Parameter.category);%>
                        <% String titolo = "";
                           titolo = search;
                           if(category != "" && !Pattern.matches("[a-zA-Z]+", category)) {
                               titolo = categoryContainer.getSubcategory(Integer.parseInt(category)).getName();
                           } %>
                        
            <li>
				<%--
                <h1>
					<% if (search != null) {%>
						Risultati ricerca per: "<%=search%>"  <!-- TODO - mettere stringa cercata dall'utente -->
					<% } 
				</h1>
				--%>
                                <h1 class="paddingLeftTop">Hai cercato: <%=titolo%></h1>
                <ul id="risRicerca">
                    <div class="container">	
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
                                                                                    <h3 style="margin: 3px"><a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>"><%=StringUtils.formatForWeb(p.getName())%></a></h3>
                                                                                    <span class="meta" style="padding-bottom: 15px; font-style: italic; font-weight: lighter; margin-top: 0px"><%=categoryContainer.getSubcategory(p.getIdSubcategory()).getName()%><br>
                                                                                    </span><br>
                                                                                        <span class="meta"> <%=p.getDescription()%></span>
											<% if (sp != null) {%>
                                                                                        <span class="price"> &euro; <%=sp.getPrice()%></span>
												<span class="meta">Quantità disponibile: <%=sp.getQuantity()%></span>
											<% } else { %>
												<span class="meta">Prodotto non disponibile</span>
											<% } %>
										</li>
										<!--<li id="products" class="carrello">
											<span>
												<a href="javascript:void(0);"><img src="/progettoWeb/img/prova/add-to-cart-btn.png">CARRELLO</a>
											</span>
										</li>-->
									</ul>
								</li>
                            <% } %>
                        </ul>
						<% } else { %>
							<span class="meta">Non è stato trovato nessun prodotto</span>
						<% } %>
                    </li>
                    </div>
                </ul>
            </li>
        </ul>
    </ul>
</li>
</ul>

</body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>