<%-- 
    Document   : barraRicerca
    Created on : Oct 4, 2017, 2:15:14 PM
    Author     : Veronica Chierzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<div class="wrap">
    <div class="search">
        <div class="borderBarra">
            <div class="bgBarraRicerca ">
                <form action="/progettoWeb/ProductListServlet" method="post">
                    <div class="centerImg">
                        <ul id="barra">
                            <li id="barra">Categorie</li>
                            <li id ="barra">
                                <div class="input-group">
                                    <input id="testoRicerca" type="text" class="form-control" placeholder="Cerca..." />
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit">
                                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                        </button>
                                    </span>
                                </div>
                            </li>
                            <li id ="barra">Opzioni</li>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/progettoWeb/js/autocompletamento.js"></script>
