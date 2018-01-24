<%-- Home del sito --%>
<%@page import="it.unitn.disi.entities.categories.Subcategory"%>
<%@page import="it.unitn.disi.entities.categories.Category"%>
<%@page import="it.unitn.disi.entities.categories.CategoryContainer"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>

<% CategoryContainer categoryContainer = (CategoryContainer) Model.Application.getAttribute(config, Model.Application.categoryContainer);%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>

    <body class="sfondoHome"> <!--da sistemare con cicli per cambio immagini-->
        <ul id="paginazione">
            <li>
                <jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
            <li>
                <div class="centerImg resizeImgHome">
                    <img class="centerImg resizeImgHome" src="/progettoWeb/img/logo/logoFinale.png">
                </div>
                <br/>
            </li>
            <li>
                <form action="<%=MyPaths.Jsp.allProductList%>" method="get">
                    <div class="input-group resizeSearch containerA">
                        <input id="testoRicerca" name="<%=Model.Parameter.textSearch%>" type="text" class="form-control" placeholder="Cerca..." />
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            </button>
                        </span>
                    </div>
                    <br>
					<div id="mi">
					<span id="sono">
                    <span id="divertita">
						<div id="sparire" style="margin-top: auto; margin-bottom: auto;">Con almeno:</div> 
						  <div class="stars" style="margin-top: auto; margin-bottom: auto; margin-left: 10px; margin-right: 10px;">
							 
								<input type="radio" name="rate" class="star-1" id="star-1" value="1" />
								<label class="star-1" for="star-1">1</label>
								<input type="radio" name="rate" class="star-2" id="star-2" value="2"/>
								<label class="star-2" for="star-2">2</label>
								<input type="radio" name="rate" class="star-3" id="star-3" value="3"/>
								<label class="star-3" for="star-3">3</label>
								<input type="radio" name="rate" class="star-4" id="star-4" value="4"/>
								<label class="star-4" for="star-4">4</label>
								<input type="radio" name="rate" class="star-5" id="star-5" value="5"/>
								<label class="star-5" for="star-5">5</label>
								<span></span>
							</div>        
						  
						  
                          <%--<select name="rate" autocomplete="off" style="padding: 2px; margin-right: 10px; margin-left: 10px">
                          <option value="0" selected>Qualsiasi</option>
                          <% for (int i = 1; i <= 5; i++) {%>
                          <option value="<%=i%>"><%=i%></option>
                        <% }%>
                        </select>--%>
                    </span>
                    <br>
                    <span id="divertita">
                        <div id="sparire">nella categoria </div>
                        <select name="categoria" autocomplete="off" style="padding: 2px; margin-right: 10px; margin-left: 10px">
                            <option value="all" selected>Tutte le categorie</option>
                            <% if (categoryContainer != null) {%>
                            <% for (Category c : categoryContainer.getCategories()) {%>
                            <optgroup label="<%= c.getName()%>">
                            <% for (Subcategory s : c.getSubcategories()) {%>
                            <option value="<%=s.getId()%>"><%=s.getName()%></option>
                            <% }%>
                            </optgroup>
                            <% }%>
                            <% }%>
                        </select>
                    </span>
					</span>
					</div>
                </form>
            </li>
        </ul>

        <script src="/progettoWeb/js/autocompletamento.js"></script>
    </body>
</html>

