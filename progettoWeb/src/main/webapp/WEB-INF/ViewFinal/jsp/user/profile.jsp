<%-- Scheda del profilo dell'utente con informazioni personali --%>
<%@page session="true" %>
<%@page import="it.unitn.disi.entities.UserAdmin"%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% User user = Model.Session.getUserLogged(request);%>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profilo Utente</title>
    </head>
    <body class="sfondo"> <!--da sistemare con cicli per cambio immagini-->
        <ul id="paginazione">
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <ul id="profilo">
                    <li style="margin-top: 15px;">
                        <% if (user.isAdmin()) {%>
						<img src="/progettoWeb/img/user/userAdmin.png" width="100px" height="100px" /> </br>
						<%} else {%>
						<img src="/progettoWeb/img/user/user.png" width="100px" height="100px"> </br>
						<%}%>
                    </li>
                </ul>
            </li>
			<li>
				<h1 style="text-align:center; font-size: 20px">Ciao, <%=user.getUsername()%>!</h1>
			</li>
            <li style="margin-left: 50px; font-size: 15px;">
				<%--id: <%=user.getId()%><br/>--%>
				<%=user.getFirstName()%> <%=user.getLastName()%><br/>

				<%=user.getEmail()%><br/>
				<%--password: <%=user.getPassword()%><br/>
				
				hash: <%=user.getHash()%><br/>
				--%>
				<%if (user.isVerificato() == false) {%>
				<br>
				<p style="color:#840505">Accedi alla tua mail e clicca sul link che ti è stato inviato al momento della registrazione per verificare l'account!</p>
				<%}%>
				<br/>

				<% if (user.isSeller()) {%>
				<% UserSeller seller = user.getUserSeller();%>
				Sei il proprietario di 
				<a href="<%=MyPaths.Jsp.sellerMySeller%>"><%=seller.getName()%></a><br/>
				<br/>
				<% } else {%>
				<a href="<%=MyPaths.Jsp.userRegistrationSellerLog%>">Registrati come venditore</a><br/>
				<br/>
				<% } %>

				<% if (user.isAdmin()) {%>
				Sei un amministratore<br/>
				<%--
				<% UserAdmin admin = user.getUserAdmin(); %>
				<a href="<%=MyPaths.Jsp.myAdmin%>">Vai alla pagina riservata agli amministratori</a><br/>
				--%>
				<br/>
				<% }%>

				<a href="<%=MyPaths.Jsp.userSegnalazioni%>">Lista delle segnalazioni</a><br/>
				<a href="<%=MyPaths.Jsp.userSegnalazioniOpen%>">Lista delle segnalazioni aperte</a><br/>
				<br/>
			</li>
		</ul>
	</body>
</html>