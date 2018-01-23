<%-- 
    Document   : barraRicerca
    Created on : Oct 4, 2017, 2:15:14 PM
    Author     : Veronica Chierzi
--%>

<%@page import="it.unitn.disi.utils.UrlUtils"%>
<%@page import="it.unitn.disi.entities.categories.Subcategory"%>
<%@page import="it.unitn.disi.entities.categories.Category"%>
<%@page import="it.unitn.disi.entities.categories.CategoryContainer"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% CategoryContainer categoryContainer = (CategoryContainer) Model.Application.getAttribute(config, Model.Application.categoryContainer);%>

<nav class="navbar navbar-transparent navbar-static-top navbar-fixed-top">
    <div class="wrap">
        <div class="search">
            <div class="borderBarra">
                <div class="bgBarraRicerca ">
                        <div class="centerImg">
                            <ul id="barra">
                                <li id="barraL">
                                    <div class="dropdown">
                                        <button class="dropbtn">Categorie</button>
                                        <div class="dropdown-content"><!-- TODO: FARE UNA CLASSE -->      
                                            <table border="1">
                                                <tr>
                                                    <% if (categoryContainer != null) { %>
                                                    <% for (Category c : categoryContainer.getCategories()) {%>
                                                    <td style="vertical-align: initial">
                                                        <table>
                                                            <thead>
                                                                <tr>
                                                                    <td><p style="align-content: left; font-weight: lighter; color: grey; font-style: italic; font-size: 9dp; margin: 5px;"><%=c.getName()%></p></td>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <% for (Subcategory s : c.getSubcategories()) {%>
                                                                <tr>
                                                                    <td><a href="<%=MyPaths.Jsp.allProductList + "?category=" + s.getId()%>"><%=s.getName()%></a></td>
                                                                </tr>
                                                                <% } %>
                                                            </tbody>
                                                        </table>
                                                    </td>

                                                    <% } %>
                                                    <% }%>   
                                                </tr>
                                            </table>

                                        </div>
                                    </div>
                                </li>
                                <li id ="barraC">

                                    <form action="<%=MyPaths.Jsp.allProductList%>" method="get">
                                        <div class="input-group centroCentro">

                                            <input id="testoRicerca" name="<%=Model.Parameter.textSearch%>" type="text" class="form-control" placeholder="Cerca..." />
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" type="submit">
                                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                                </button>
                                            </span>
                                        </div>

                                    </form>

                                </li>
                                <% if(request.getRequestURI().contains("ProductList")) {
                                    %>
                                
                                <li id ="barraR">
                                    <div class="dropdown">
                                        <button class="dropbtn">Ordina per</button>
                                        <div class="dropdown-content">
                                            <a href="<%=UrlUtils.cambiaUrl(request.getRequestURI() + "?" + request.getQueryString(), "sort", "price")%>">Prezzo crescente</a>
                                            <a href="<%=UrlUtils.cambiaUrl(request.getRequestURI() + "?" + request.getQueryString(), "sort", "review")%>">Stelle</a>
                                        </div>
                                    </div>
                                </li>
                                
                                <% } %>
                            </ul>
                        </div>
                </div>
            </div>
        </div>
    </div>
</nav>

<script src="/progettoWeb/js/autocompletamento.js"></script>
