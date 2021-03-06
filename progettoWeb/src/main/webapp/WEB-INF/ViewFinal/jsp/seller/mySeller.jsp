<%-- Scheda del proprio account da venditore --%>
<%@page import="it.unitn.disi.entities.Shop"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetMySeller%>"/>
<% Shop[] b = (Shop[]) Model.Request.getAttribute(request, "shops");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>I miei punti vendita</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <div class="container">	
                    <h1 class="paddingTop">I miei punti vendita</h1>

                    <% if (Model.Messages.consumeBoolean(request, "orarioNonCambiatoErroreDB")) { %>
                    <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove-sign"></span> Impossibile cambiare l'orario del tuo shop. Contatta l'amministratore del sito.</div>
                    <% } %>
                    <% if (Model.Messages.consumeBoolean(request, "orarioCambiatoCorrettamente")) { %>
                    <div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok-sign"></span> Orario cambiato correttamente!</div>
                    <% } %>
                    <% if (Model.Messages.consumeBoolean(request, "orarioNonCambiatoPermessi")) { %>
                    <div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-info-sign"></span> L'orario non è stato cambiato perchè non hai i permessi per farlo.</div>
                    <% } %>
                    <% if (Model.Messages.consumeBoolean(request, "orarioNonCambiatoNonLoggato")) { %>
                    <div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-info-sign"></span> L'orario non è stato cambiato perchè non sei loggato.</div>
                    <% } %>

                    <table id="negozietto">
                        <%for (Shop a : b) {%>
                        <tr>
                            <td>
                                <p><%=a.getAddress()%>, <%=a.getComune().getName()%><br/>
                                <div style="margin-right: 10px;">
                                    <form name="form_negozio_spedizione_<%=a.getId()%>" action="<%=MyPaths.Servlet.Pubbliche.changeShopRitiroInNegozioServlet%>" method="POST">
                                        <select name="ritiro_in_negozio" autocomplete="off" onchange="document.form_negozio_spedizione_<%=a.getId()%>.submit()" style="padding: 2px; margin-left: 10px">
                                            <option value="true" <% if (a.isRitiroInNegozio()) { %> selected <% }%>>Ritiro in negozio e spedizione online</option>
                                            <option value="false" <% if (!a.isRitiroInNegozio()) { %> selected <% }%>>Solo spedizione online</option>
                                        </select>
                                        <input type="hidden" name="id_shop" value="<%=a.getId()%>" />
                                    </form>

                                </div>
                                </p>
                            </td>
                        <form action="<%=MyPaths.Servlet.Pubbliche.changeShopParams%>" method="post">
                            <td>
                            <label for="ora">Orario</label>
                                <input type="hidden" name="idShop" value="<%=a.getId()%>" />
                                <textarea id="ora" style="width: 100%; height: auto;" rows="4" cols="40" name="orarioShop" type="text"><%= (a.getOrario() != null ? a.getOrario() : "")%></textarea>
                            </td>
                            <td>
                                <button style="margin-left:20px;" type="submit" class="btn btn-primary">Salva</button>
                            </td>
                        </form>
                        </tr>

                        <% }%>
                    </table>
                </div>
            </li>
        </ul>

    </body>
</html>

