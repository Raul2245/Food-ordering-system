<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.sql.ResultSet"%>
<%@page import = "java.io.*" %>
<%@page import = "forms.*" %>
<%@page import = "javax.servlet.*" %>
<%@page import = "connection.*" %>
<%@page import = "java.sql.*" %>
<%@page import = "java.util.*" %>
<%@page import = "model.*" %>
<%@page import = "BLL.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Lista restaurantelor disponibile în aplicație">
    <title>Pigga</title>
    <!-- font icons -->
    <link rel="stylesheet" href="assets/vendors/themify-icons/css/themify-icons.css">
    <!-- Bootstrap + Pigga main styles -->
	<link rel="stylesheet" href="assets/css/pigga.css">
    <link rel="stylesheet" href="assets/css/restaurants.css">
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="10" id="rests">
    
    <!-- First Navigation -->
    <nav class="navbar nav-first navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="assets/imgs/logo.png">
            </a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link text-primary" href="#home">CONTACT : <span class="pl-2 text-muted">07123 4567</span></a>
                </li>                   
            </ul>
        </div>
    </nav>
    <!-- End of First Navigation --> 
    <!-- Second Navigation -->
    <!-- Second Navigation -->
    <nav class="nav-second navbar custom-navbar navbar-expand-sm navbar-dark bg-dark sticky-top">
        <div class="container">
            <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto"> 
                    <li class="nav-item">
                        <a class="nav-link" href="#about">Despre</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#service">Serviciile noastre</a>
                    </li>
                </ul> 
                <%
                    if (request.getSession().getAttribute("userID") == null) {
                %>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="login.jsp" class="btn btn-primary btn-sm">Logare cont</a>
                    </li>
                </ul>
                <% 		
                    }else {
                %>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="profile-view.jsp" class="btn btn-primary btn-sm">Vizualizare cont</a>
                    </li>
                </ul>
                <% 		
                    } 
               	 	UtilizatorBLL ubll = new UtilizatorBLL();
               	 	if (request.getSession().getAttribute("userID") != null) {
                		if (!ubll.hasOnGoingOrder((int)request.getSession().getAttribute("userID"))) {
                %>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="view-order.jsp" class="btn btn-primary btn-sm">Cos cumparaturi</a>
                    </li>
                </ul>
                 <% 		
                    	}else {
                 %>
                 <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="follow_order.jsp" class="btn btn-primary btn-sm">Urmarire comanda</a>
                    </li>
                </ul>
                <% 		
                    	}
               	 	}
                %>
            </div>
        </div>
    </nav>
    <!-- End Of Second Navigation --> 
    <!-- Page Header -->
    <header class="header">
        <div class="overlay">
            <h1 class="title"> Urmarire comanda</h1>
        </div>  
    </header>
    <!-- End Of Page Header --> 
    <!-- Start lista restaurante -->
   
    <div class="restaurant-list-view">
    	<h1 style = "color: white; text-align: center;"> Status comanda: </h1>
    	<%
    		ComandaBLL cbll = new ComandaBLL();
    		out.println("<p class = restaurant-name style = \"color: white; text-align: center;\">" + cbll.getStatusAsString((int)request.getSession().getAttribute("userID")));
    	%>
    </div>
</body>
</html>
