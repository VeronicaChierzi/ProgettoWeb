<%-- Lista di prodotti visualizzata dopo una ricerca --%>
<%@page import="it.unitn.disi.dao.ShopDAO"%>
<%@page import="it.unitn.disi.utils.UrlUtils"%>
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
        <% CategoryContainer categoryContainer = (CategoryContainer) Model.Application.getAttribute(config, Model.Application.categoryContainer);%>

    </head>
    <body class="sfondoP">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            </li>
			<% ShopDAO shopDAO = (ShopDAO) Model.Request.getAttribute(request, "shopDAO"); %>
			<% Product[] productList = (Product[]) Model.Request.getAttribute(request, Model.Request.productList); %>
			<% String search = Model.Parameter.get(request, Model.Parameter.textSearch); %>
			<% String category = Model.Parameter.get(request, Model.Parameter.category);%>
			<% String titolo = "";
				titolo = search;
				if (category != "" && !Pattern.matches("[a-zA-Z]+", category)) {
					titolo = categoryContainer.getSubcategory(Integer.parseInt(category)).getName();
				}%>

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
							<table id="product">
								<% for (Product p : productList) {%>
								<% Image i = p.getImage(); %>
								<% ShopProduct sp = p.getShopProduct(); %>

								<tr id="product"><td>
										<table id="products">
											<tr id="products"><td>
													<% if (i != null) {%>
													<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>">
														<img class="imgProdotto" src="<%=i.getPath()%>" alt="<%=i.getAlt()%>">
													</a>
													<% } else { %>
													<span>Immagine non trovata</span>
													<% }%>
												</td>
												<td id="products" class="descrizione" >
													<h3 style="margin: 3px"><a href="<%=MyPaths.Jsp.allProduct%>?id=<%=p.getId()%>"><%=StringUtils.formatForWeb(p.getName())%></a></h3>
													<span class="meta" style="padding-bottom: 15px; font-style: italic; font-weight: lighter; margin-top: 0px"><%=categoryContainer.getSubcategory(p.getIdSubcategory()).getName()%>
                                                                                                            - Venduto da <a href="<%=MyPaths.Jsp.allShop + "?id=" + sp.getIdShop()%>"><%=shopDAO.getShop(sp.getIdShop(), true, false).getUserSeller().getName()%></a>
													</span><br><br>
													<span><%=p.getDescription()%></span>
													<% if (sp != null) {%>
													<span class="price"> &euro; <%=sp.getPrice()%></span>
													<span>Quantità disponibile: <%=sp.getQuantity()%></span>
													<% } else { %>
													<span>Prodotto non disponibile</span>
													<% } %>
												</td>
												<!--<li id="products" class="carrello">
													<span>
														<a href="javascript:void(0);"><img src="/progettoWeb/img/prova/add-to-cart-btn.png">CARRELLO</a>
													</span>
												</li>-->
										</table>
									</td></tr>
									<% } %>
							</table>
							<% } else { %>
							<span class="meta">Non è stato trovato nessun prodotto</span>
						<% } %>
                                                
                                                
                                                <%if(UrlUtils.getOffsetFromUrl(request.getQueryString()) != 0) {%>
                                                    <button style="margin-right: 10px; margin-bottom: 20px;" onclick="window.location.href='<%=UrlUtils.cambiaUrl(request.getRequestURI() + "?" + request.getQueryString(), "offset", (UrlUtils.getOffsetFromUrl(request.getQueryString())-10)+"")%>'" class="btn btn-secondary">
														<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>  Pagina precedente</button>
                                                <% }%>
                                                
                                                <%if((int) Model.Request.getAttribute(request, "count") == 10) {%>
                                                    <button style="margin-bottom: 20px;" onclick="window.location.href='<%=UrlUtils.cambiaUrl(request.getRequestURI() + "?" + request.getQueryString(), "offset", (UrlUtils.getOffsetFromUrl(request.getQueryString())+10)+"")%>'" class="btn btn-primary">
														Pagina successiva  <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span></button>
                                                <% }%>
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

