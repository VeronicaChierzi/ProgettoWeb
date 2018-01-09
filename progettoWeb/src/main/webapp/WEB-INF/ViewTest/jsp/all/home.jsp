<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<div>
	<h1>Home</h1>

	<form action="<%=MyPaths.Jsp.allProductList%>" method="get">
		<div>
			<input name="<%=Model.Parameter.textSearch%>" type="text" placeholder="Cerca..." />
			<button type="submit">Cerca</button>
		</div>
	</form>
</div>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>