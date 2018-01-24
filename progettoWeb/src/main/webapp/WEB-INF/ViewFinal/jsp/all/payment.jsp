<%@page import="it.unitn.disi.entities.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="it.unitn.disi.entities.carts.Cart"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allPaymentServlet%>"/>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrello</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <h1 class="paddingTop">Pagamento</h1>
                <% Cart cart = (Cart) Model.Session.getAttribute(request, Model.Session.cart);%>

            </li>
			<li>
                <p class="totale">Totale ordine: &euro; <%=new DecimalFormat("#.##").format(cart.getTotalPrice())%></p>
			</li>
            <li>

                <% User user = (User) Model.Session.getAttribute(request, Model.Session.user); %>
                <% if (user != null) {%>
                <p>Dati carta di credito</p>
                <form method="post" action="<%=MyPaths.Servlet.Pubbliche.buyCart%>">
                    Nome e cognome  
                    <input type="text" name="name" value=""/>
                    <br>
                    <br>
                    MM  <input type="text" name="mm" size="2" value=""/>  YY <input type="text" name="yy" size="2" value=""/><br><br>
                    Codice <input type="text" name="code" size="16" value=""/>  CVC <input type="text" name="cvc" size="3" value=""/>
                    <br>
                    <br>
                    <input class="btn btn-primary" type="submit" value="compra" />
                </form>
					<br>
					<img style="width: 20%; height: auto;"src="/progettoWeb/img/NSec.png">

                <% } else {%>
                <div class="alert alert-warning" role="alert">
                    Effettua il <a href="<%=MyPaths.Jsp.anonymousLogin%>" class="alert-link">login</a> per procedere all'acquisto.
                </div>
                <% }%>

            </li>
        </ul>
    </body>
</html>