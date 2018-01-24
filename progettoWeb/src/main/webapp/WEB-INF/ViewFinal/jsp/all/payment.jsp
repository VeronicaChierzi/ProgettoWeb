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
            <li style="margin-bottom: 60px; display: table; margin-left: auto; margin-right: auto;">

                <% User user = (User) Model.Session.getAttribute(request, Model.Session.user); %>
                <% if (user != null) {%>
                
                <form method="post" action="<%=MyPaths.Servlet.Pubbliche.buyCart%>">
					<ul style="padding:10px;" id="campiCarta">
						<li style="font-size: 25px;">
							Dati carta di credito
						</li>
						<li>
							Nome e cognome  <br>
							<input required="true" type="text" name="name" value=""/>
						</li>
						<li id="automobile">
							MM  <input required="true" type="text" name="mm" size="2" value=""/>  YY <input type="text" name="yy" size="2" value=""/>
						</li>
						<li id="automobile">
							Codice <input required="true" type="text" name="code" size="16" value=""/>  CVC <input  required="true" type="text" name="cvc" size="3" value=""/>
						</li>
						<li id="bottone">
							<input style="margin-top: 20px;" class="btn btn-primary" type="submit" value="compra" />
						</li>
					</ul>
					
                </form>
			</li>
			<li>
				<img class="centerImg" style="width: 5%; height: auto;"src="/progettoWeb/img/NSec.png">
			</li>
			<li>
                <% } else {%>
                <div class="alert alert-warning" role="alert">
                    Effettua il <a href="<%=MyPaths.Jsp.anonymousLogin%>" class="alert-link">login</a> per procedere all'acquisto.
                </div>
                <% }%>

            </li>
        </ul>
    </body>
</html>