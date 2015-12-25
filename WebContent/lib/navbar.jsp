<!-- Fixed navbar -->
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand" href="<%= URLMapper.INDEX_URL %>">The Sleek CD Store</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<%= URLMapper.HOME_URL %>">HOME</a></li>
				<li><a href="<%= URLMapper.ACCOUNT_URL %>">MY ACCOUNT</a></li>            
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">CONTACT <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="./contact.jsp">CONTACT</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Reference CD list</li>
						<li><a href="http://www.hmv.ca/Default.aspx">HMW CD Website</a></li>
					</ul>
				</li>			
			</ul>
			<ul class="nav navbar-nav navbar-right">
	            <li><a><%=new java.util.Date()%></a></li>
	            <li><a href="<%= URLMapper.CART_URL %>">SHOPPING CART <span data-toggle="tooltip" data-placement="bottom" title="I am here!" id="shopping-cart" class="glyphicon glyphicon-gift"></span></a></li>
            </ul>
	    </div><!--/.nav-collapse -->	    
	</div>
</div>
<!-- Fixed navbarFixed navbar ends --> 