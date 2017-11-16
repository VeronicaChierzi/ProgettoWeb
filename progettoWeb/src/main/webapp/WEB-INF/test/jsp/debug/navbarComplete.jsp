<%@page import="it.unitn.disi.utils.MyPaths"%>

<div style="border:1px solid black;">
	<h2>Public</h2>
	<div style="border:1px solid black;">
		<h3>JSP</h3>
		<h4>All</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Jsp.All.cart%>">cart</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.All.home%>">home</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.All.product%>">product</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.All.productList%>">productList</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.All.shop%>">shop</a></li>
		</ul>

		<h4>Anonymous</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Jsp.Anonymous.login%>">login</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Anonymous.registration%>">register</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Anonymous.registrationSeller%>">registrationSeller</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Anonymous.forgottenPassword%>">forgottenPassword</a></li>
		</ul>

		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Jsp.User.changePassword%>">changePassword</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.order%>">order</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.orders%>">orderList</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.profile%>">profile</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.segnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.segnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.segnalazioniOpen%>">segnalazioniOpen</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.User.registrationSellerLog%>">registrationSellerLog</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.order%>">order</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.ordersSeller%>">ordersSeller</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.ordersShop%>">ordersShop</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.segnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.segnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.segnalazioniOpen%>">segnalazioniOpen</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.sellProduct%>">sellProduct</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.mySeller%>">mySeller</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Seller.myShop%>">myShop</a></li>
		</ul>

		<h4>Admin</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Jsp.Admin.segnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Admin.segnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Admin.segnalazioniOpen%>">segnalazioniOpen</a></li>
		</ul>

		<h4>Debug</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Jsp.Debug.categoryContainer%>">categoryContainer</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Debug.locationContainer%>">locationContainer</a></li>
			<li><a href="<%=MyPaths.Public.Jsp.Debug.navbarComplete%>">navbarComplete</a></li>
		</ul>
	</div>

	<div style="border:1px solid black;">
		<h3>Servlet</h3>
		<h4>Anonymous</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Servlet.Anonymous.confirmUser%>">confirmUser</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.Anonymous.forgottenPassword%>">forgottenPassword</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.Anonymous.login%>">login</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.Anonymous.registration%>">registration</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.Anonymous.registrationSeller%>">registrationSeller</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Servlet.Seller.sellProduct%>">sellProduct</a></li>
		</ul>

		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Public.Servlet.User.addToCart%>">addToCart</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.User.buyCart%>">buyCart</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.User.changePassword%>">changePassword</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.User.logout%>">logout</a></li>
			<li><a href="<%=MyPaths.Public.Servlet.User.registrationSellerLog%>">registrationSellerLog</a></li>
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
	<div style="border:1px solid black;">
		<h3>JSP</h3>
		<h4>All</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Jsp.All.cart%>">cart</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.All.home%>">home</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.All.product%>">product</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.All.productList%>">productList</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.All.shop%>">shop</a></li>
		</ul>

		<h4>Anonymous</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Jsp.Anonymous.login%>">login</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Anonymous.registration%>">registration</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Anonymous.registrationSeller%>">registrationSeller</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Anonymous.forgottenPassword%>">forgottenPassword</a></li>
		</ul>

		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Jsp.User.changePassword%>">changePassword</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.order%>">order</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.orders%>">orderList</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.profile%>">profile</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.segnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.segnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.segnalazioniOpen%>">segnalazioniOpen</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.User.registrationSellerLog%>">registrationSellerLog</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.order%>">order</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.ordersSeller%>">ordersSeller</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.ordersShop%>">ordersShop</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.segnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.segnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.segnalazioniOpen%>">segnalazioniOpen</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.sellProduct%>">sellProduct</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.mySeller%>">mySeller</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Seller.myShop%>">myShop</a></li>
		</ul>

		<h4>Admin</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Jsp.Admin.segnalazione%>">segnalazione</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Admin.segnalazioni%>">segnalazioni</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Admin.segnalazioniOpen%>">segnalazioniOpen</a></li>
		</ul>

		<h4>Debug</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Jsp.Debug.categoryContainer%>">categoryContainer</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Debug.locationContainer%>">locationContainer</a></li>
			<li><a href="<%=MyPaths.Private.Jsp.Debug.navbarComplete%>">navbarComplete</a></li>
		</ul>
	</div>

	<div style="border:1px solid black;">
		<h3>Servlet</h3>
		<h4>User</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Servlet.User.getOrder%>">getOrder</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.User.getOrders%>">getOrders</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.User.getSegnalazione%>">getSegnalazione</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.User.getSegnalazioni%>">getSegnalazioni</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.User.getSegnalazioniOpen%>">getSegnalazioniOpen</a></li>
		</ul>

		<h4>All</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Servlet.All.getProduct%>">getProduct</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.All.getProductList%>">getProductList</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.All.getShop%>">getShop</a></li>
		</ul>

		<h4>Seller</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getMyShops%>">getMyShops</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getOrder%>">getOrder</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getOrdersSeller%>">getOrdersSeller</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getOrdersShop%>">getOrdersShop</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getSegnalazione%>">getSegnalazione</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getSegnalazioni%>">getSegnalazioni</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Seller.getSegnalazioniOpen%>">getSegnalazioniOpen</a></li>
		</ul>
		
		<h4>Admin</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Servlet.Admin.getSegnalazione%>">getSegnalazione</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Admin.getSegnalazioni%>">getSegnalazioni</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Admin.getSegnalazioniOpen%>">getSegnalazioniOpen</a></li>
		</ul>

		<h4>Init</h4>
		<ul>
			<li><a href="<%=MyPaths.Private.Servlet.Init.loadCategoryContainer%>">getCategoryContainer</a></li>
			<li><a href="<%=MyPaths.Private.Servlet.Init.loadLocationContainer%>">getLocationContainer</a></li>
		</ul>
	</div>
</div>