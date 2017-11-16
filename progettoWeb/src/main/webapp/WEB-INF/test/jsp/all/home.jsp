<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<div>
	<h1>Home</h1>

	<form action="<%=MyPaths.Public.Jsp.All.productList%>" method="get">
		<div>
			<input name="<%=Model.Parameter.textSearch%>" type="text" placeholder="Cerca..." />
			<button type="submit">Cerca</button>
		</div>
	</form>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>