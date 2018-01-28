<%-- Lista dei venditori che vendono il prodotto --%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="it.unitn.disi.entities.ReviewProduct"%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="it.unitn.disi.utils.StringUtils"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Product"%>
<%@page import="it.unitn.disi.entities.ShopProduct"%>
<%@page import="it.unitn.disi.entities.Image"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetShopsProduct%>"/>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prodotto</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <div class="container">	
                    <% ShopProduct[] shopsProduct = (ShopProduct[]) Model.Request.getAttribute(request, "shopsProduct");%>

                    <% if (shopsProduct == null) { %>
	                    Nessun venditore trovato per questo prodotto<br/>
                    <% } else {%>
					<% Product product = shopsProduct[0].getProduct(); %>
                    <h1 class="paddingLeftTop"><%=StringUtils.formatForWeb(product.getName())%></h1>
                    <% Image image = product.getImage(); %>
                    <table style="border-spacing: 10px;">
						<tr>
                            <% if (image != null) {%>
                            <td style="white-space: nowrap; width: auto;">
                                <a href="<%=MyPaths.Jsp.allProduct%>?id=<%=product.getId()%>">
                                    <img src="<%=image.getPath()%>" class="prodotto" alt="<%=image.getAlt()%>">
                                </a>
                            </td>
	                        <br/>
                            <% } else { %>
                            Immagine non trovata<br/>
                            <% }%>

						<td style="padding: 20px; width: 70%">
                            
							<table style="border-collapse: separate; border-spacing: 10px;">
								<tr>
									<th style="text-align: center;">Venditore</th>
									<th style="text-align: center;">Prezzo</th>
									<th style="text-align: center;">Quantità</th>
									<th style="text-align: center;">Spedizione</th>
								</tr>
							<% for(ShopProduct sp : shopsProduct){ %>
							<tr>
								<td>
									<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=product.getId()%>&id_shop=<%=sp.getIdShop()%>">
									Acquista da <%=sp.getShop().getUserSeller().getName()%> di <%=sp.getShop().getComune().getName()%>
									</a>
								</td>
								<td>&euro; <%=new DecimalFormat("#.##").format(sp.getPrice())%></td>
								<td style="text-align: center;"><%=sp.getQuantity()%></td>
								<td style="text-align: center;">
									<% if (sp.getShop().isRitiroInNegozio()) { %>
										Ritiro in negozio o spedizione online
									<% } else { %>
										Solo spedizione online
									<% } %>
								</td>
								<%--
								<td>
									<p style="margin: 3px">
										<a href="<%=MyPaths.Jsp.allProduct%>?id=<%=product.getId()%>&id_shop=<%=sp.getIdShop()%>">Acquista da <%=sp.getShop().getUserSeller().getName()%></a>
									</p>
								</td>
								--%>
							</tr>
							<% } %>
							</table>
                        </td>
                        </tr>
					</table>
                    <br/>
                    <% } %>
                </div>
            </li>
        </ul>
    </body>
</html>