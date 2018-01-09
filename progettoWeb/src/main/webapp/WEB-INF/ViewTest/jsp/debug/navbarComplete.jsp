<%@page import="it.unitn.disi.utils.MyPaths"%>

<div style="border:1px solid black;">
	<h2>Public</h2>
	<div style="border:1px solid black;">
		<h3>JSP</h3>
		<h4>All</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp.allCart%>">cart</a></li>
			<li><a href="<%=MyPaths.Jsp.allHome%>">home</a></li>
			<li><a href="<%=MyPaths.Jsp.allProduct%>">product</a></li>
			<li><a href="<%=MyPaths.Jsp.allProductList%>">productList</a></li>
			<li><a href="<%=MyPaths.Jsp.allShop%>">shop</a></li>
		</ul>

		<h4>Anonymous</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp.anonymousForgottenPassword%>">forgottenPassword</a></li>
			<li><a href="<%=MyPaths.Jsp.anonymousLogin%>">login</a></li>
			<li><a href="<%=MyPaths.Jsp.anonymousRegistration%>">register</a></li>
			<li><a href="<%=MyPaths.Jsp.anonymousRegistrationSeller%>">registrationSeller</a></li>
		</ul>

		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp.userChangePassword%>">changePassword</a></li>
			<li><a href="<%=MyPaths.Jsp.userOrder%>">order</a></li>
			<li><a href="<%=MyPaths.Jsp.userOrders%>">orderList</a></li>
			<li><a href="<%=MyPaths.Jsp.userProfile%>">profile</a></li>
			<li><a href="<%=MyPaths.Jsp.userRegistrationSellerLog%>">registrationSellerLog</a></li>
			<li><a href="<%=MyPaths.Jsp.userSegnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Jsp.userSegnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Jsp.userSegnalazioniOpen%>">segnalazioniOpen</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp.sellerMySeller%>">mySeller</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerMyShop%>">myShop</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerOrder%>">order</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerOrdersSeller%>">ordersSeller</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerOrdersShop%>">ordersShop</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerSegnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerSegnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerSegnalazioniOpen%>">segnalazioniOpen</a></li>
			<li><a href="<%=MyPaths.Jsp.sellerSellProduct%>">sellProduct</a></li>
		</ul>

		<h4>Admin</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp.adminSegnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Jsp.adminSegnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Jsp.adminSegnalazioniOpen%>">segnalazioniOpen</a></li>
		</ul>

		<h4>Debug</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp.debugCategoryContainer%>">categoryContainer</a></li>
			<li><a href="<%=MyPaths.Jsp.debugLocationContainer%>">locationContainer</a></li>
			<li><a href="<%=MyPaths.Jsp.debugNavbarComplete%>">navbarComplete</a></li>
		</ul>
	</div>

	<div style="border:1px solid black;">
		<h3>Servlet</h3>
		<h4>Anonymous</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.confirmUser%>">confirmUser</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.forgottenPassword%>">forgottenPassword</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.login%>">login</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.registration%>">registration</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.registrationSeller%>">registrationSeller</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.sellProduct%>">sellProduct</a></li>
		</ul>

		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.addToCart%>">addToCart</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.buyCart%>">buyCart</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.changePassword%>">changePassword</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.logout%>">logout</a></li>
			<li><a href="<%=MyPaths.Servlet.Pubbliche.registrationSellerLog%>">registrationSellerLog</a></li>
		</ul>
	</div>
</div>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

<div style="border:1px solid black;">
	<h2>Private</h2>
	Non dovrebbero essere accessibili al client tramite url dal browser.<br/>
	Accessibili solo dal lato server.<br/>
	<div style="border:1px solid black;">
		<h3>JSP</h3>

		<h4>Utils</h4>
		<ul>
			<li><a href="<%=MyPaths.Jsp._errorPagesErrorDaoException%>">_ErrorDaoException</a></li>
			<li><a href="<%=MyPaths.Jsp._utilsFooter%>">_Footer</a></li>
			<li><a href="<%=MyPaths.Jsp._utilsHeader%>">_Header</a></li>
			<li><a href="<%=MyPaths.Jsp._utilsNavbar%>">_Navbar</a></li>
			<li><a href="<%=MyPaths.Jsp._utilsSearchBar%>">_SearchBar</a></li>
		</ul>
	</div>

	<div style="border:1px solid black;">
		<h3>Servlet</h3>
		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Privatee.userGetOrder%>">getOrder</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.userGetOrders%>">getOrders</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.userGetSegnalazione%>">getSegnalazione</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.userGetSegnalazioni%>">getSegnalazioni</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.userGetSegnalazioniOpen%>">getSegnalazioniOpen</a></li>
		</ul>

		<h4>All</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Privatee.allGetProduct%>">getProduct</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.allGetProductList%>">getProductList</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.allGetShop%>">getShop</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetMyShops%>">getMyShops</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetOrder%>">getOrder</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetOrdersSeller%>">getOrdersSeller</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetOrdersShop%>">getOrdersShop</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazione%>">getSegnalazione</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazioni%>">getSegnalazioni</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazioniOpen%>">getSegnalazioniOpen</a></li>
		</ul>
		
		<h4>Admin</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Privatee.adminGetSegnalazione%>">getSegnalazione</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.adminGetSegnalazioni%>">getSegnalazioni</a></li>
			<li><a href="<%=MyPaths.Servlet.Privatee.adminGetSegnalazioniOpen%>">getSegnalazioniOpen</a></li>
		</ul>

		<%--
		<h4>Init</h4>
		<ul>
			<li><a href="<%=MyPaths.Servlet.Init.loadCategoryContainer%>">getCategoryContainer</a></li>
			<li><a href="<%=MyPaths.Servlet.Init.loadLocationContainer%>">getLocationContainer</a></li>
		</ul>
		--%>
	</div>
</div>