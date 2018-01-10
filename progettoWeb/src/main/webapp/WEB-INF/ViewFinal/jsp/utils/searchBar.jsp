<%-- 
    Document   : barraRicerca
    Created on : Oct 4, 2017, 2:15:14 PM
    Author     : Veronica Chierzi
--%>

<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-transparent navbar-static-top navbar-fixed-top">
    <div class="wrap">
        <div class="search">
            <div class="borderBarra">
                <div class="bgBarraRicerca ">
                    <form action="<%=MyPaths.Jsp.allProductList%>" method="post">
                        <div class="centerImg">
                            <ul id="barra">
                                <li id="barraL">
                                    <div class="dropdown">
                                        <button class="dropbtn">Categorie</button>
                                        <div class="dropdown-content">
                                            <a href="#">Link 1</a>
                                            <a href="#">Link 2</a>
                                            <a href="#">Link 3</a>
                                        </div>
                                    </div>
                                </li>
                                <li id ="barraC">
                                    <div class="input-group centroCentro">
                                        <input id="testoRicerca" type="text" class="form-control" placeholder="Cerca..." />
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="submit">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                            </button>
                                        </span>
                                    </div>
                                </li>
                                <li id ="barraR">
                                    <div class="dropdown">
                                        <button class="dropbtn">Indirizzo di spedizione</button>
                                        <div class="dropdown-content">
                                            <a href="#">Link 1</a>
                                            <a href="#">Link 2</a>
                                            <a href="#">Link 3</a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</nav>

<script src="/progettoWeb/js/autocompletamento.js"></script>
