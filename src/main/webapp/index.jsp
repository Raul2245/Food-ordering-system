<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "java.io.*" %>
<%@page import = "forms.LoginServlet" %>
<%@page import = "BLL.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Start your development with Pigga landing page.">
    <meta name="author" content="Devcrud">
    <title>Pigga</title>
    <!-- font icons -->
    <link rel="stylesheet" href="assets/vendors/themify-icons/css/themify-icons.css">
    <!-- Bootstrap + Pigga main styles -->
	<link rel="stylesheet" href="assets/css/pigga.css">
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="40" id="home">
    
    <!-- First Navigation -->
    <nav class="navbar nav-first navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="assets/imgs/logo.png" >
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
            <img src="assets/imgs/logo.png" alt="Download free bootstrap 4 landing page, free boootstrap 4 templates, Download free bootstrap 4.1 landing page, free boootstrap 4.1.1 templates, Pigga Landing page" class="logo">
            <h1 class="subtitle">Bine ati venit!</h1>
            <h1 class="title">Rapid &amp; Gustos</h1> 
            <a class="btn btn-primary mt-3" href=RestaurantsServlet>Comanda</a> 
        </div>      
    </header>
    <!-- End Of Page Header --> 
    <!-- About Section -->
    <section id="about">
        <div class="container">
            <div class="row align-items-center">                
                <div class="col-md-6">
                    <h6 class="section-subtitle">Cluj-Napoca</h6>
                    <h3 class="section-title">Despre compania noastra</h3>
                    <p >Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                        Curabitur sed purus quis purus bibendum semper et et mi. Ut rhoncus justo ex, ac hendrerit ante tristique in. 
                        Duis porttitor, tortor ac ultrices rhoncus, dui augue volutpat odio, non pretium magna augue rutrum lectus. 
                        Integer in odio nec urna vulputate accumsan in eu mauris. Curabitur convallis ex lorem, et feugiat ligula porttitor vel. 
                        Integer mi enim, vehicula eget sodales vel, mattis a turpis. 
                        Proin porttitor semper urna, in fringilla dolor placerat at. Vestibulum a nunc sit amet nisl aliquet bibendum. 
                        Quisque dictum sodales felis vel euismod.</p>
                    <a href="restaurant-register.jsp" class="btn btn-primary btn-sm w-md mt-4">Inregistreaza restaurant</a>                 
                    <a href="angajat-register.jsp" class="btn btn-primary btn-sm w-md mt-4">Inregistrare cont angajat</a>  
                    <a href="angajat-login.jsp" class="btn btn-primary btn-sm w-md mt-4">Logare cont angajat</a> 
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="col">
                            <img src="assets/imgs/scooter1.png" class="w-100 rounded shadow">
                        </div>
                    </div>                  
                </div>
            </div>
            
            
        </div>
    </section>
    <!-- End OF About Section -->
    <!-- Services section -->
    <section id="service" class="pattern-style-4 has-overlay">
        <div class="container raise-2">
            <h3 class="section-title mb-6 pb-3 text-center">Servicii oferite</h3>
            <p> In ac ullamcorper ipsum. Vestibulum et volutpat enim. Curabitur dolor lacus, mattis ac massa sollicitudin, porttitor vehicula tellus. 
                Phasellus gravida purus vel gravida congue. Nam fermentum ante ut neque aliquam, at scelerisque diam dictum. Proin a vehicula nisi. 
                Phasellus sed est magna. Etiam eget elit sodales, sodales lacus porttitor, interdum orci. Cras varius diam eu velit tempor facilisis. 
                Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Etiam faucibus nisi nec mi tristique vestibulum.</p> 
                <a href="curier-login.jsp" class="btn btn-primary btn-sm w-md mt-4">Vreau sa devin curier</a>
                <a href="curier-login-form.jsp" class="btn btn-primary btn-sm w-md mt-4">Logare curier</a> 
                <a href="register.jsp" class="btn btn-primary btn-sm w-md mt-4">Creeaza cont</a>    
        </div>
    </section>
    <!-- End of Services Section -->
    

   

    <!-- core  -->
    <script src="assets/vendors/jquery/jquery-3.4.1.js"></script>
    <script src="assets/vendors/bootstrap/bootstrap.bundle.js"></script>

    <!-- bootstrap affix -->
    <script src="assets/vendors/bootstrap/bootstrap.affix.js"></script>

    <!-- Pigga js -->
    <script src="assets/js/pigga.js"></script>

</body>
</html>
