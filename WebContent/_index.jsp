<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">	
<head>

	<%@include file="./lib/header.jsp" %> 
	<!-- Custom styles for this template -->
	<link href="./lib/carousel.css" rel="stylesheet">  
    <title>The Sleek CD Store</title>
    
    
</head>

<body>

	<%@ include file="./lib/navbar.jsp" %>	
	<br><br><br><br>

  <div class="container">
	<div id="mycarousel" class="carousel slide" data-ride="carousel" data-interval="2000">
	  <!-- Indicators -->
	  <ol class="carousel-indicators">
	    <li data-target="#mycarousel" data-slide-to="0" class="active"></li>
	    <li data-target="#mycarousel" data-slide-to="1"></li>
	    <li data-target="#mycarousel" data-slide-to="2"></li>
	    <li data-target="#mycarousel" data-slide-to="3"></li>
	  </ol>
	  <!-- Wrapper for slides -->
	  <div class="carousel-inner">
	    <div class="item active">
	    	<a href="./Homepage?cateId=0" ><img src="./pictures/index/all.jpg"  alt="ALL"></a>
	    	<div class="carousel-caption"><h3>Browse all New CD 2014</h3></div>
	   </div>
	   <div class="item">
	      <a href="Homepage?cateId=1" ><img src="./pictures/index/jazz.jpg" alt="ROCK"></a>
	          <div class="carousel-caption"><h3>ROCK</h3></div>
	   </div>
	   <div class="item">
	      <a href="Homepage?cateId=2" ><img src="./pictures/index/pop.jpg" alt="POP"></a>
	      <div class="carousel-caption"> <h3>POP</h3></div>
	    </div>
	    <div class="item">
	      <a href="Homepage?cateId=3" ><img src="./pictures/index/country.jpg" alt="COUNTRY"></a>
	      <div class="carousel-caption"><h3>COUNTRY</h3></div>
	    </div>
	  </div>
	</div> <!-- Carousel -->
  </div>
   
   	<div class="container">
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      	<div class="page-header"><h1>Welcome to The Sleek CD Store</h1></div>       
        <p>We hope you have a pleasant shopping experience!</p>
      </div>
    </div> <!-- /container -->  

	<%@include file="./lib/footer.jsp" %>

</body>




</html>	