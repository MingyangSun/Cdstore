<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<%@include file="./lib/header.jsp" %> 
	<!-- Custom styles for this template -->
    <link href="./lib/login.css" rel="stylesheet">
   
   
	<title>The Sleek CD Store</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<%@ include file="./lib/navbar.jsp" %>
	
	
<!-- 	
	<%if(request.getAttribute("error")!=null){ %>
       	<div class="alert alert-danger">
       		<%= request.getAttribute("error").toString()%>
       	</div>
    <%} %>
    <%if(request.getAttribute("sucess")!=null){ %>
       	<div class="alert alert-success">
       		<%= request.getAttribute("sucess").toString()%>
       	</div>
    <%} %>
-->

	<div class="container">

      <form id="formid" class="form-signin" action="<%=URLMapper.LOGINPAGE_URL %>" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text"  id="name" name="username" class="form-control" value="" placeholder="Email address" autofocus >
        <input type="password" id="password" name="password" class="form-control" placeholder="Password">        
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-warning btn-block" type="submit">Sign In</button>
        <div>Do not have an account? Sign up here please!</div>
        <a class="btn btn-lg btn-info" href="<%=URLMapper.SIGNUP_URL %>" role="button">Sign Up Now</a>
       
      </form>

    </div> <!-- /container -->

	
	
	
	<%@include file="./lib/footer.jsp" %>

</body>
</html>


<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type='text/javascript'>
    $("#formid").submit(function(event) {
      event.preventDefault();
      var $form = $( this ),
          url = $form.attr( 'action' );
      var posting = $.post( url, { username: $('#name').val(), password: $('#password').val() } );

      /* Alerts the results */
      posting.done(function(data) {
    	  if(data==""){window.location.replace("https://localhost:8443/Cdstore/Homepage");}
    	  else { alert(data); }
      });
    });
</script>
