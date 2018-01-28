<%-- Scheda del negozio (pubblica, visibile da tutti) --%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.entities.Shop"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetShop%>"/>
<% Shop s = (Shop) Model.Request.getAttribute(request, "shop");%>
<% User u = (User) Model.Request.getAttribute(request, "user");%>
<% int neg = (int) Model.Request.getAttribute(request, "negative");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Il negozio</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
			<div class="container">
				<li>
					<h1 class="paddingTop"><%= s.getUserSeller().getName()%></h1>
					<h5>di <%= u.getLastName()%> <%= u.getFirstName()%></h5>
					<span ><%= s.getAddress()%>, <%= s.getComune().getName()%></span><br>
					<% if(s.isRitiroInNegozio()) {%>
						<span>Accetta sia ritiro in negozio che spedizioni online</span><br>
					<% } else { %>
						<span>Vende soltanto tramite spedizioni online</span><br>
					<% } %>
					
					<span style="margin-bottom: 10px">Questo negozio ha avuto <%=neg%> valutazioni negative.</span>

				</li>
				<li style="margin-left: auto; margin-right: auto; display: table;">
					<ul>

						<li style="margin-bottom: 20px; margin-top: 30px; ">
							<div style="margin-left: auto; margin-right: auto; display: table;">	
								<img style="    width: 60%;
									 margin-right: auto;
									 height: auto;
									 margin-left: auto;
									 display: table;" src="https://maps.googleapis.com/maps/api/streetview?size=600x300&location=<%=s.getLatitude()%>,<%=s.getLongitude()%>&heading=150&pitch=-0.76&key=AIzaSyAdFPcsuR2JaXI_HSDZ0NBJeiA2EuH13tQ" alt="foto del negozio"/>
							</div>
						</li>

						<li style="margin-top: 20px; width: 100%; margin-bottom: 30px;" >
							<div style="margin-left: auto; margin-right: auto; display: table;">	
								<iframe style="width: 100%; height: auto"

										frameborder="0" style="border:0"
										src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBfUs-GBHcPOMgY39DYXfuqWo0b3vNTNzo
										&q=<%= s.getAddress()%>, <%= s.getComune().getName()%>" allowfullscreen>
								</iframe>

							</div>

						</li>
					</ul>
				</li>	
			</div>
        </ul>

	</div>

</ul>

</body>
</html>