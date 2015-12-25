<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<%@ page import="com.sleek.CDStore.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
  <head>
	<%@include file="./lib/header.jsp" %> 
    <!-- Custom styles for this template -->
    <link href="./lib/offcanvas.css" rel="stylesheet">  
    <link href="./lib/carousel.css" rel="stylesheet">
    
    <title>The Sleek CD Store</title>
  </head>

  <body>
	<%@ include file="./lib/navbar.jsp" %>	

    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
      
      
      
      	<!-- for begin choose the categories -->
        <div class="col-xs-12 col-sm-9">
        
	        <div class="container">
		        <h2>All CDs in The Sleek CD Store: </h2>
		    </div>
		    <br>
        
        	<!-- CD table, list all cds -->
			<div class="table-responsive">
				<table class="table table-hover table-condensed table-striped">
				    <thead>
				      <tr>
				        <th>CD Name</th>
				        <th>Category</th>
				        <th>Price</th>
				        <th>Storage</th>
				      </tr>
				    </thead>
				    <tbody>
				    <% 
				    	List<CDBean> productList= new ArrayList<CDBean>();
				    	productList= (ArrayList<CDBean>) request.getAttribute("productList"); 
				    	for(int i=0; i<productList.size();i++){
				    %>
				    	<tr>
				        <td>
					        <dl>
								<dt><a href="<%=URLMapper.DETAIL_URL%>?cdId=<%=productList.get(i).getId()%>"><%=productList.get(i).getTitle() %></a></dt>
								<dd class="sr-only"><%=productList.get(i).getId()%></dd>
							</dl>	
				        </td>
				        <td><%=productList.get(i).getCategory()%></td>
				        <td><%=productList.get(i).getPrice()%></td>
				        <td> lots of </td>
				        <td>
				        	<button type="submit" class="btn btn-success" onclick="addToCart(<%=productList.get(i).getId()%>)">add into cart</button>
				        </td>
				      </tr>			
				    <%	}%>
				      	      			      
					</tbody>
				</table>
			</div>      
        
        <br>
        <div class="container">
	        	<h2>Categories of all CDs in The Sleek CD Store: </h2>
        </div>
        <br>
        
        <!-- catalog service -->
          <div class="row">
          <% List<CategoryBean> cateList= new ArrayList<CategoryBean>();
          	 cateList= (ArrayList<CategoryBean>) request.getAttribute("cateList"); 
			 for(int i=0; i<cateList.size();i++){%>
            <div class="col-6 col-sm-6 col-lg-4">
              <h2><%= cateList.get(i).getCategoryName()%></h2><!-- need category from Dong -->
               <img class="img-circle" src="./pictures/index/cdicon.png" alt="Generic image" style="width: 140px; height: 140px;">
              <!-- category description -->
              <p><a class="btn btn-default" href="<%=URLMapper.HOME_URL %>?cateId=<%=cateList.get(i).getId()%>" role="button">View details &raquo;</a></p>
            </div><!--/span-->
           <%}%>  
          </div><!--/row-->
        </div><!--/span-->
        <!-- end for begin choose the categories -->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <!-- dropdown selection added into the jsp file but haven't done in the database and webservice -->
          <div class="list-group">
          	<li class="dropdown">
            	<a href="#" class="list-group-item active dropdown-toggle" data-toggle="dropdown">Category<b class="caret"></b></a>
            	<ul class="dropdown-menu">
	                <li><a href="<%=URLMapper.HOME_URL %>?cateId=0">View All</a></li>
	                <% for(int i=0; i<cateList.size();i++){%>
	                <li><a href="<%=URLMapper.HOME_URL %>?cateId=<%=cateList.get(i).getId()%>"><%= cateList.get(i).getCategoryName()%></a></li>
	                <% }%>
	            </ul>            	
            </li>
            <li class="dropdown">
            	<a href="#" class="list-group-item active dropdown-toggle" data-toggle="dropdown">Price<b class="caret"></b></a>
            	<ul class="dropdown-menu">
	                <li><a href="#"> < 30$</a></li>
	                <li><a href="#"> 30 ~ 50$</a></li>
	                <li><a href="#"> > 50$</a></li>
	            </ul>            	
            </li>       
          </div>
         <%if((request.getAttribute("isLogin").toString())=="no"){%>
			<form class="form-signin" action="<%=URLMapper.LOGIN_URL %>" method="post">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input type="email"  name="username" class="form-control" value="" placeholder="Email address" autofocus >
				<input type="password" name="password" class="form-control" placeholder="Password">        
				<label class="checkbox">
				  <input type="checkbox" value="remember-me"> Remember me
				</label>
				<button class="btn btn-lg btn-warning btn-block" type="submit">Sign In</button>
				<div>Do not have an account? Sign up here please!</div>
				<br>
				<a class="btn btn-lg btn-info" href="<%=URLMapper.SIGNUP_URL %>" role="button">Sign Up Now</a>				 
			</form>
			 <%}%>
			 <%if((request.getAttribute("isLogin").toString())=="yes"){
				 AccountBean account=(AccountBean)(request.getSession(false).getAttribute("account"));
				 %>
			 <div class="jumbotron">
			 	<h2>Welcome</h2>
			 	<p><%=account.getUsername()%></p>
			 </div>
			 <%} %>
			        
        </div><!--/span-->
      </div><!--/row-->
      <hr>
    </div><!--/.container-->    
	<%@include file="./lib/footer.jsp" %>   
  </body>
  
</html>


