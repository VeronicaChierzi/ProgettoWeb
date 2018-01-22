<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.categories.CategoryContainer"%>
<%@page import="it.unitn.disi.entities.categories.Category"%>
<%@page import="it.unitn.disi.entities.categories.Subcategory" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
	function mostra(id) {
		var elements = document.getElementsByClassName("subcat");
		for (i = 0; i < elements.length; i++) {
			var e = elements.item(i);
			if (e.id !== id) {
				e.style.display = "none";
			}
		}
		var elem = document.getElementById(id);
		if (elem.style.display === "block") {
			elem.style.display = "none";
		} else {
			elem.style.display = "block";
		}
	}
</script>

<% CategoryContainer categoryContainer = (CategoryContainer) Model.Application.getAttribute(config, Model.Application.categoryContainer); %>
<h2>Category</h2>
<div class="resizeCat">
	<ul id="categorie">
		<% if (categoryContainer != null) { %>
			<% for (Category c : categoryContainer.getCategories()) { %>
				<li onclick='mostra("subcat" +<%=c.getId()%>)'>
					<a><%=c.getName()%></a>
					<ul class="subcat" id="subcat<%=c.getId()%>" style="display:none" >
						<% for (Subcategory s : c.getSubcategories()) { %>
							<li>
								<%--<td>id: <%=s.getId()%></td>--%>
								<a href="<%=MyPaths.Jsp.allProductList + "?category=" + s.getId()%>"><%=s.getName()%></a>
                                                                
							</li>
						<% } %>
					</ul>
				</li>
			<% } %>
		<% } %>
	</ul>
</div>