<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<%@include file="./lib/header.jsp" %> 
	<!-- Custom styles for this template -->
   
   
	<title>The Sleek CD Store</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<%@ include file="./lib/navbar.jsp" %>	
	<br><br>
	
	<%if(request.getAttribute("error")!=null){ %>
       	<div class="alert alert-danger" style="margin-top:20px">
       		<%= request.getAttribute("error").toString()%>
       	</div>
    <%} %>
	<div class="container">
	<br><br><br>
	<form action="<%=URLMapper.SIGNUP_URL%>" method="post">
		<div class="row">
			<div class="col-md-6">
				<div class="input-group">
	 				<span class="input-group-addon">Email: </span>
	 				<input type="email" name ="username" id="username" placeholder="Email address">
				</div>
				<br>
				<div class="input-group">
	 				<span class="input-group-addon">Password: </span>
	 				<input type="password" name="password" id="password" placeholder="Passwrod">
				</div>
				
				<br><br>
				<!-- accountinfo -->	
				<div class="row">
					<div class="col-md-4">
						<h4>First name</h4>
					</div>
					<div class="col-md-8">
							<div class="form-group">
								<input type="text" name="firstname" id="firstname" placeholder="Your first name">
							</div>
					</div>
				</div>			
				<div class="row">
					<div class="col-md-4">
						<h4>Last name</h4>
					</div>
					<div class="col-md-8">
							<input type="text"  id="lastname" name="lastname" placeholder="Your last name">
					</div>
				</div>			
				<div class="row">
					<div class="col-md-4">
						<h4>Phone number</h4>
					</div>
					<div class="col-md-8">
							<div class="form-group">
								<input type="text" class="" id="phonenumber" name="phone" placeholder="Your phone number">
							</div>
					</div>
				</div>	
				<!-- accountinfo  end-->
				<br><br>
				<hr>
				<!-- address -->
				<div class="row">
					<div class="col-md-3">
						<p>Street line</p>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							<input type="text" class="" id="street" name="street" placeholder="Street">
						</div>
					</div>
				</div>			
				<div class="row">
					<div class="col-md-6">	
						<div class="row">
							<div class="col-md-3">
								<p>Postcode</p>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<input type="text" class="" id="zip" name="zip" placeholder="Postcode">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">			
						<div class="row">
							<div class="col-md-1">
								<p>City</p>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<input type="text" class="" id="city" name="city" placeholder="City">
								</div>
							</div>
						</div>
					</div>
				</div>					
				<div class="row">
					<div class="col-md-3">
						<p>Province</p>
					</div>
					<div class="col-md-8">
						<select class="" name="province">
						<option value="Ontario">Ontario</option>
						<option value="Quebec">Quebec</option>
						<option value="Alberta">Alberta</option>
						<option value="Saskatchewan">Saskatchewan</option>
						<option value="Vancouver">Vancouver</option>
						<option value="Manitoba">Manitoba</option>
						<option value="Newfoundland">Newfoundland</option>
						<option value="Northarea">North area</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">
						<p>Country</p>
					</div>
					<div class="col-md-8">
						<div class="form-group">
							<input type="text" class="" id="country" name="country" value="Canada">
						</div>
					</div>
				</div>
				<!-- address end -->
				<br><br>			
			</div>
			
		
		</div>
		
			<hr/>		
			<div class="row">
				<div class="col-md-3">
					<button class="btn btn-lg btn-primary btn-danger" type="submit">Creat MY Account</button><br><br>
					<h4>Already have an accout?</h4>
					<a class="btn btn-lg btn-info btn-block" href="<%=URLMapper.LOGIN_URL%>" role="button">Sign in my account</a>
				</div>	
			</div>	
	</form>
    </div> <!-- /container -->	
	<hr/>
<%@include file="./lib/footer.jsp" %>
</body>
</html>