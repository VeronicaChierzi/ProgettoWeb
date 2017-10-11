<%@page import="it.unitn.disi.entities.categories.CategoryContainer"%>
<%@page import="it.unitn.disi.entities.categories.Category"%>
<%@page import="it.unitn.disi.entities.categories.Subcategory" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
        
        
        
        %>
    </head>
    <body class="sfondo">
       <ul id="paginazione">
            <li>
        <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>
            <li>
                <!--
        <h1>CategoryContainer</h1>

		<br/>
		CategoryContainer è una variabile di scope application<br/>
		l'oggetto viene letto solo la prima volta dal database, poi resta in ram<br/>
		<br/>

		chiama servlet<br/>
		<jsp:include page="/CategoryContainerServlet" />
		ricomincia jsp<br/>
		
		<%= session.getAttribute("categoryContainerMessage") %>
		<% session.removeAttribute("categoryContainerMessage"); %>
		<br/><br/>
		
		<% CategoryContainer categoryContainer = (CategoryContainer) application.getAttribute("categoryContainer"); %>
		<%=categoryContainer %><br/>
		<%=categoryContainer.getCategory(1) %><br/>
		<%=categoryContainer.getCategory(1).getName() %><br/>
		-->
                <script>
                    function mostra(id){
                        var elements = document.getElementsByClassName("subcat");
                        for(i=0; i<elements.length; i++){
                            var e = elements.item(i);
                            if(e.id !== id) {
                                e.style.display = "none";
                            }
                        }
                        var elem = document.getElementById(id);
                        if(elem.style.display == "block") {
                            elem.style.display = "none";                            
                        } else {
                            elem.style.display = "block";

                        }
                    }
                </script>

                
                
                <h2>Categorie</h2>
                <div class="resizeCat">
                <ul id="categorie">
		<%
			for (Category c : categoryContainer.getCategories()) {
                           %>
				<li onclick='mostra("subcat"+<%=c.getId()%>)'>
                                    <a><%=c.getName()%></a>
                                <ul class="subcat" id="subcat<%=c.getId()%>" style="display:none" >
                                    
                                    <%
                                for (Subcategory s : c.getSubcategories()) {
					out.print("<li>");
					//out.print("<td>id: " + s.getId() + "</td>");
					out.print("<a>"+s.getName()+"</a>");
					out.print("</li>");
				}
                                out.print("</ul>");
                                out.print("</li>");
			}
		%>
		</ul>
                </div>
                                
                                
                
                
                <!--<br><br><br><br>
                <ul id="categorie">
		<%/*
			for (Category c : categoryContainer.getCategories()) {
				out.print("<li>");
				//out.print("<td>id: " + c.getId() + "</td>");
				out.print(c.getName());
				out.print("<ul id='subcategorie'>");
                                for (Subcategory s : c.getSubcategories()) {
					out.print("<li>");
					//out.print("<td>id: " + s.getId() + "</td>");
					out.print(s.getName());
					out.print("</li>");
				}
                                out.print("</ul>");
                                out.print("</li>");
			}*/
		%>
		</ul>
		<br/><br/><br/>
		<h2>Subcategories</h2>
		<table>
		<%/*
			for (Category c : categoryContainer.getCategories()) {
				out.print("<tr>");
				out.print("<td><h4>" + c.getName() + "</h4></td>");
				out.print("</tr>");
				for (Subcategory s : c.getSubcategories()) {
					out.print("<tr>");
					out.print("<td>id: " + s.getId() + "</td>");
					out.print("<td>name: " + s.getName() + "</td>");
					out.print("</tr>");
				}
			}*/
		%>
		</table>
		
		
		<table>
			<c:forEach items="${applicationScope['attributeNames'].regioni}" var="r">
				<c:out value="r.name"/>
			</c:forEach>
		</table>-->
		
            </li>
        </ul>
		
    </body>
</html>
