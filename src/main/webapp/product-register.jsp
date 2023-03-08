<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
            <a class="navbar-brand" href="angajat.jsp">
                <img src="assets/imgs/logo.png">
            </a>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link text-primary" href="">CONTACT : <span class="pl-2 text-muted">07123 4567</span></a>
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
                </ul> 
            </div>
        </div>
    </nav>
    <!-- End Of Second Navigation --> 
    <!-- Page Header -->
    <header class="header">
        <div class="overlay">
            <h1 class="title"> Inregistrare produs</h1>
        </div>  
    </header>
    <!-- End Of Page Header --> 
    <!-- Start register -->
    <div class="input-form">
        <div class="form">
            <form action = ProductUploadServlet method = post enctype="multipart/form-data">
                <label class="form-label" for="product-name">Nume produs:</label><br>
                <input class="form-input" type="text" id="fname" name="nume"><br>
                <label class="form-label" for="pret">Pret:</label><br>
                <input class="form-input" type="text" id="lname" name="pret"><br>
                <label class="form-label" for="pret">Descriere:</label><br>
                <textarea class="form-input" name="descriere" rows = "4" columns = "50"></textarea><br>
                <label class="form-label" for="logo">Logo:</label><br>
                <input class="form-input" type="file" name="logo"><br>
                <input class="btn btn-primary btn-sm" type="submit" name="inregistrare" id = login-bttn value=Inregistrare>
          </form>
        </div> 
    </div>
    <!-- End register -->
</body>
</html>
