<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sleek.CDStore.config.URLMapper"%>
<%@ page import="com.sleek.CDStore.model.*"%>
<%@ page import="com.sleek.CDStore.function.shoppingCart.*"%>
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
<!-- Show the errors occurs in this page -->
<%if(request.getAttribute("error")!=null){ %>
 	<div class="alert alert-danger" style="margin-top:20px">
 		<%= request.getAttribute("error").toString()%>
 	</div>
<%} 
AccountBean account=(AccountBean)request.getAttribute("account");
AddressBean shipping=(AddressBean)request.getAttribute("shippingAdress");
AddressBean billing=(AddressBean)request.getAttribute("billingAddress");
ShoppingCart sc=(ShoppingCart)request.getAttribute("shoppingCart");
%>
	<div class="container">
	<br/><br/><br/>
		<form action="<%=URLMapper.PAYMENT_URL%>" method="post">
			<input name="shippingId" type="hidden" value="<%=shipping.getId() %>"/>
			<input name="billingId" type="hidden" value="<%if(billing!=null)%><%=billing.getId() %>"/>
			
			
			
			
			   	<div class="container">
     
      <div class="row">
			<div class="col-md-6">
			<h1>Your personal information</h1>	
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
								<input type="text"  id="lastname" name="lastname" value="<%=account.getLastname() %>"  placeholder="Please enter your last name">
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
					</div>	
			
			
			</div>
			<div class="col-md-6">
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
			
			</div>
			</div></div>
			
			<hr/>
			
			<div class="row">
			<div class="col-md-6">
				<h1>Credit Card Information</h1>
			<div class="col-md-4">
							<p>Credit number</p>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text" class="form-control" id="creditcardnumber" name="creditcardnumber" placeholder="Please enter credit card number">
							</div>
						</div>
						<div class="col-md-4">
							<p>Name on card</p>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<input type="text" class="form-control" id="nameoncard" name="nameoncard" placeholder="Please enter the name on your card">
							</div>
						</div>						
						<div class="col-md-4">
							<p>Expire Date</p>
						</div>
						<div class="col-md-2">
							<select class="form-control" name="month">
							<option>month</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							</select>
						</div>
						<div class="col-md-2">
							<select class="form-control" name="year">
							<option>year</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							</select>
						</div>
						
						<div class="col-md-2">
							<p>Security code</p>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<input type="text" class="form-control" id="CID" name="CID" placeholder="Security code on your card back">
							</div>
						</div>						
							<div class="col-md-4 ">
								<p>Card Type</p>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="type">
								<option value="AMEX">American Express</option>	
								<option value="VM">VISA / Master Card</option>													
								</select>
							</div>		
			</div>
			
			<div class="col-md-6">
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
			
			</div>
			
			
			
			
			</div>
			<br><br><hr/>
			
			
<div class="row">
				<div class="col-md-6">				
					<ul>
						<li><h1>Your checkingout information</h1></li>
						<li>Total price 		<h4><%=sc.getTotal() %> $ CDN</h4></li>
						<li>Tax 				<h4><%=sc.getTax() %> $ CDN</h4></li>					
						<li>Shipping 			<h4> 10 $ CDN</h4> </li>
						<li>All your payment 	<h4><%=Double.parseDouble(sc.getTotal())+Double.parseDouble(sc.getTax())+10.0 %> $ CDN</h4> </li>						
					</ul>
					</div> </div>
					<br/><br/>
					<!-- begin the payment -->
					
					<hr/>
					<button class="btn btn-lg btn-danger btn-block" type="submit">Confirm & Pay</button>	
				
			</div>
		</form>		
		
		<br><br><hr/>
<%@include file="./lib/footer.jsp" %>	
</body>
</html>