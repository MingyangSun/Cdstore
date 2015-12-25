<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<%@include file="./lib/header.jsp" %>     
    <!-- Custom styles for this template -->   
    <title>The Sleek CD Store</title>
</head>
<body>
	<%@ include file="./lib/navbar.jsp" %>	
	<br><br><br>
	<div class="container">
		<div class="row">			
			<!-- Mailing address -->
			<div class="col-md-6">		
				<%@ include file="./lib/accountinfo.jsp" %>	
				<h2>Billing Address <small> </small></h2>
				<br>
				<%@ include file="./lib/address.jsp" %>		
				<hr>
				<h2>Mailing Address <small> </small></h2>
				<br>
				<%@ include file="./lib/address.jsp" %>	
			</div>
			<!-- mailing address -->
			<div class="col-md-6">
			<!-- Payment information -->
				<ul>
					<li><h2>Your checkingout information</h2></li>
					<li>Total price <h4>request CAD</h4></li>
					<li>Tax <h4> request CAD</h4></li>					
					<li>Shipping <h4> request CAD</h4> </li>
					<li>All your payment <h4> request CAD</h4> </li>
				</ul>
			</div>
		</div>
	</div>
	<br><br><br>
	<div class="container">		
		<!-- Payment menthods -->	
		<div class="jumbotron">
			<!-- <div class="page-header"><h3>Choose your payment method</h3></div> -->			
			<div class="row">
				<div class="col-md-6">					
					<div class="col-md-4">
						<p>Credit number</p>
					</div>
					<div class="col-md-8">
						<form class="form" role="form">
							<div class="form-group">
								<label class="sr-only" for="exampleInputEmail2">Credit number</label>
								<input type="email" class="form-control" id="exampleInputEmail2" placeholder="Please enter credit card number">
							</div>
						</form>
					</div>
				</div>		
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-4">
							<p>Expire Date</p>
						</div>
						<div class="col-md-2">
							<select class="form-control">
							<option>month</option>
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							</select>
						</div>
						<div class="col-md-2">
							<select class="form-control">
							<option>year</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
							<option>19</option>
							<option>20</option>
							<option>21</option>
							<option>22</option>
							<option>23</option>
							</select>
						</div>
						
						<div class="col-md-2">
							<p>Security code</p>
						</div>
						<div class="col-md-2">
							<form class="form" role="form">
								<div class="form-group">
									<label class="sr-only" for="exampleInputEmail2">Security code</label>
									<input type="email" class="form-control" id="exampleInputEmail2" placeholder="Security code on your card back">
								</div>
							</form>
						</div>
						
					</div>
				</div>		
			</div>	
			<div class="row">	
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-4">
							<p>Name on card</p>
						</div>
						<div class="col-md-8">
							<form class="form" role="form">
								<div class="form-group">
									<label class="sr-only" for="exampleInputEmail2">Name on card</label>
									<input type="email" class="form-control" id="exampleInputEmail2" placeholder="Please enter the name on your card">
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<p>Card Type</p>
						</div>
						<div class="col-md-4">
							<select class="form-control">
							<option>American Express</option>	
							<option>VISA / Master Card</option>													
							</select>
						</div>
					</div>
				</div>	
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-4">
							<p>Confirmation Email</p>
						</div>
						<div class="col-md-8">
							<form class="form" role="form">
								<div class="form-group">
									<label class="sr-only" for="exampleInputEmail2">Confirmation Email</label>
									<input type="email" class="form-control" id="exampleInputEmail2" placeholder="Email address to receive purchase confirmation">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>					
		</div>
	</div>	
	
	
	
	
	
	
	<%@include file="./lib/footer.jsp" %>
</body>
</html>