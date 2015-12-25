<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<%@ page import="com.sleek.CDStore.model.*"%>
<%@ page import="com.sleek.CDStore.function.ModelApplier" %>
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
	
	<br/><br/><br/>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
				<div class="table-responsive">
					<table class="table table-hover table-condensed">
					    <thead>
					      <tr>
					        <th>CD Description</th>
					      </tr>
					    </thead>
					    <tbody>
					    <% 
					    System.out.println("ssssssssss");
    					System.out.println(request.getAttribute("cdIdentify"));
    					int cdid = Integer.parseInt(request.getAttribute("cdIdentify").toString());
    					CDBean c = new CDBean();
    					c= ModelApplier.getProductById(cdid);
    					System.out.println(c.getArtist());
				    	%>
					      <tr>
					        <td>
						        <dl>
								<dt>Title: <%=c.getTitle()%></dt>
								<dt>Category: <%=c.getCategory() %></dt>
								<dt>Price: <%=c.getPrice() %> $</dt>
								<dt>Artist: <%=c.getArtist() %></dt>
								<dt>Description: <%=c.getDescription() %></dt>
								</dl>	
					        </td>
					      </tr>      			      
						</tbody>
					</table>
				</div></div> 				
			</div>			
			<div class="col-md-9">							
				<!-- iframe frameborder="0"  width="100%" height="720"src="http://pipes.yahoo.com/pipes/pipe.info?_id=9bc0a16d4af3bb8df6be931e837f0690"</iframe>-->
			</div>
		</div>
	</div>

<%@include file="./lib/footer.jsp" %>
</body>
</html>