<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<%@ page import="com.sleek.CDStore.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="./lib/header.jsp" %>     
    <!-- Custom styles for this template -->   
    <title>The Sleek CD Store</title>
</head>
<body>
<%
AccountBean account=(AccountBean)request.getAttribute("account");
AddressBean shipping=(AddressBean)request.getAttribute("shippingAdress");
AddressBean billing=(AddressBean)request.getAttribute("billingAddress");
%>
<form action="<%=URLMapper.ACCOUNT_URL %>" method="post">
	<input name="shippingId" type="hidden" value="<%=shipping.getId() %>"/>
	<input name="billingId" type="hidden" value="<%if(billing!=null)%><%=billing.getId() %>"/>
	<%@ include file="./lib/navbar.jsp" %>	
	<br><br><br>
	<div class="container">	
		<% if(request.getParameter("Sucess")!=null){%>
			<div class="alert alert-success">
       			Update Sucessfully !
       		</div>
		<%} %>	
		<% if(request.getParameter("Error")!=null){%>
			<div class="alert alert-danger">
       			Update failed !
       		</div>
		<%} %>	
		
		
		<div class="container">
		<center><img class="img" src="./pictures/index/hello.jpg" alt="Generic image" style="width: 500px;"></center>
		<br>
		<div class="jumbotron"><center>			
					<div class="row">
						<div class="col-md-4">
							<h4>First name</h4>
						</div>
						<div class="col-md-8">
								<div class="form-group">
									<input type="text" id="firstname" name="firstname" value="<%=account.getFirstname() %>"  placeholder="Please enter your first name">
								</div>
						</div>
					</div>			
					<div class="row">
						<div class="col-md-4">
							<h4>Last name</h4>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text"  id="lastname" name="lastname" value="<%=account.getLastname() %>"  placeholder="Please enter your last name">
							</div>
						</div>
					</div>			
					<div class="row">
						<div class="col-md-4">
							<h4>Phone number</h4>
						</div>
						<div class="col-md-8">
								<div class="form-group">
									<input type="text" name="phone" class="" value="<%=account.getPhone() %>" id="phonenumber"  placeholder="Please enter your phone number">
								</div>
						</div>
					</div></center>	
				</div>	
		</div>
		
		<div class="row">			
			<div class="col-md-6">	
				<!-- Billing address -->
				<img class="img-circle" src="./pictures/index/money.jpg" alt="Generic image" style="width: 140px; height: 140px;">		
					<h1>Your Billing address</h1>	
					<!-- accountinfo  end-->
					<!-- address -->
					<div class="row">
						<div class="col-md-3">
							<p>Street line</p>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text" class="" id="billingstreet" name="billingstreet" value="<%if(billing!=null)%><%=billing.getStreet() %>"  placeholder="Please enter your Street">
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
										<input type="text" class="" id="zip" value="<%if(billing!=null)%><%=billing.getZip() %>" name="billingzip" placeholder="Please enter your postcode">
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
										<input type="text" class="" id="city" value="<%if(billing!=null)%><%=billing.getCity() %>" name="billingcity" placeholder="Please enter your city">
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
							<select class="" name="billingprovince">
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Ontario"))  {%>selected="selected"<%} %> value="Ontario">Ontario</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Quebec"))  {%>selected="selected"<%} %>value="Quebec">Quebec</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Alberta"))  {%>selected="selected"<%} %>value="Alberta">Alberta</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Saskatchewan"))  {%>selected="selected"<%} %>value="Saskatchewan">Saskatchewan</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Vancouver"))  {%>selected="selected"<%} %>value="Vancouver">Vancouver</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Manitoba"))  {%>selected="selected"<%} %>value="Manitoba">Manitoba</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Newfoundland"))  {%>selected="selected"<%} %>value="Newfoundland">Newfoundland</option>
							<option <%if(billing!=null)%><%if(billing.getProvince().equals("Northarea"))  {%>selected="selected"<%} %>value="Northarea">North area</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<p>Country</p>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text"  value="<%if(billing!=null)%><%=billing.getCountry() %>"class="" id="country" name="billingcountry" value="Canada">
							</div>
						</div>
					</div>
					<!-- address end -->
					<br/><br/>
			</div>
			<div class="col-md-6">
				<!-- Mailing address -->
				<img class="img-circle" src="./pictures/index/mail.jpg" alt="Generic image" style="width: 140px; height: 140px;">
					<h1>Your Mailing address</h1>
					<!-- address -->
					<div class="row">
						<div class="col-md-3">
							<p>Street line</p>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text" class="" id="street" name="shippingstreet" value="<%=shipping.getStreet() %>"  placeholder="Please enter your Street">
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
										<input type="text" class="" id="zip" name="shippingzip" value="<%=shipping.getZip() %>"  placeholder="Please enter your postcode">
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
										<input type="text" class="" id="city" name="shippingcity"value="<%=shipping.getCity() %>"  placeholder="Please enter your city">
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
							<select class="" name="shippingprovince">
							<option <%if(shipping.getProvince().equals("Ontario"))  {%>selected="selected"<%} %> value="Ontario">Ontario</option>
							<option <%if(shipping.getProvince().equals("Quebec"))  {%>selected="selected"<%} %>value="Quebec">Quebec</option>
							<option <%if(shipping.getProvince().equals("Alberta"))  {%>selected="selected"<%} %>value="Alberta">Alberta</option>
							<option <%if(shipping.getProvince().equals("Saskatchewan"))  {%>selected="selected"<%} %>value="Saskatchewan">Saskatchewan</option>
							<option <%if(shipping.getProvince().equals("Vancouver"))  {%>selected="selected"<%} %>value="Vancouver">Vancouver</option>
							<option <%if(shipping.getProvince().equals("Manitoba"))  {%>selected="selected"<%} %>value="Manitoba">Manitoba</option>
							<option <%if(shipping.getProvince().equals("Newfoundland"))  {%>selected="selected"<%} %>value="Newfoundland">Newfoundland</option>
							<option <%if(shipping.getProvince().equals("Northarea"))  {%>selected="selected"<%} %>value="Northarea">North area</option>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<p>Country</p>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text"  value="<%=shipping.getCountry() %>"class="" id="country" name="shippingcountry" value="Canada">
							</div>
						</div>
					</div>
					<!-- address end -->
					<br/><br/>
					<hr/>
			</div>
		</div>
	</div>
	<br>
	<hr>
	<button class="btn btn-lg btn-primary btn-block" type="submit">update</button>
	</form>
	<hr/>
	<%@include file="./lib/footer.jsp" %>
</body>
</html>