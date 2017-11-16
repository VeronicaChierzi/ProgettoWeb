<%-- Pagina di debug (mapping da rimuovere prima della consegna) --%>
<%-- Permette di visualizzare la lista delle categorie --%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.categories.CategoryContainer"%>
<%@page import="it.unitn.disi.entities.categories.Category"%>
<%@page import="it.unitn.disi.entities.categories.Subcategory" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<div>
	<div>
		<% CategoryContainer categoryContainer = (CategoryContainer) Model.Application.getAttribute(config, Model.Application.categoryContainer); %>
		<% if (categoryContainer != null) {%>
		CategoryContainer caricato: <%=categoryContainer%><br/>
		<% } else { %>
		CategoryContainer non caricato<br/>
		<% } %>
	</div>

	<div>
		<h2>Categories</h2>
		<ul>
			<% if (categoryContainer != null) { %>
			<% for (Category c : categoryContainer.getCategories()) {%>
			<li>
				<%=c.getId()%>&emsp;&emsp;
				<%=c.getName()%>&emsp;&emsp;
				<%=c.getSubcategories()%>&emsp;&emsp;
				<% for (Subcategory s : c.getSubcategories()) {%>
				<ul>
					<li>
						<%=s.getId()%>&emsp;&emsp;
						<%=s.getName()%><%--&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;--%>
						<%--<%=s.getCategory()%>&emsp;&emsp;
						<%=s.getIdCategory()%>&emsp;&emsp;--%>
					</li>
				</ul>
				<% } %>
			</li>
			<% } %>
			<% }%>
		</ul>
	</div>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>