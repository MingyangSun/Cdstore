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
	<br><br><br><br>
	<div class="container">
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
      	<div class="page-header"><h1>Credit Card Authorization Failed</h1></div>       
        <p>We are sorry that your credit card is not valid</p>
        <p><%out.println( request.getAttribute("msg")); %></p>
        <!-- <p>IMPORTANT! ONLY FOR STUDENTS HIMSELF/HERSELF</p> -->
      </div>
    </div> <!-- /container -->  

<%@include file="./lib/footer.jsp" %>
</body>
</html>