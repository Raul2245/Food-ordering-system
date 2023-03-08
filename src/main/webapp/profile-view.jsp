<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="BLL.*" %>
<%@page import="java.sql.ResultSet" %>
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
            <%
				if (request.getSession().getAttribute("angajat") != null){   
            %>
            <a class="navbar-brand" href="angajat.jsp">
                <img src="assets/imgs/logo.png">
            </a>
            <% 
				}else if (request.getSession().getAttribute("curier") != null) {

            %>
            <a class="navbar-brand" href="curier.jsp">
                <img src="assets/imgs/logo.png">
            </a>
            <%
				}else {
            %>
            <a class="navbar-brand" href="index.jsp">
                <img src="assets/imgs/logo.png">
            </a>
            <%} %>
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
                    <li class="nav-item">
                        <a class="nav-link" href="#about">Despre</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#service">Serviciile noastre</a>
                    </li>
                </ul> 
            </div>
        </div>
    </nav>
    <!-- End Of Second Navigation --> 
    <!-- Page Header -->
    <header class="header">
        <div class="overlay">
            <h1 class="title"> Vizualizare profil</h1>
        </div>  
    </header>
    <!-- End Of Page Header --> 
    <!-- Start register -->
    <div class="input-form">
        <div class="form">
            <form action = ProfileViewServlet method = post>
            <%
            	UtilizatorBLL u = new UtilizatorBLL();
            	CurierBLL c = new CurierBLL();
            	AngajatBLL a = new AngajatBLL();
            	ResultSet res = u.getUser((int)request.getSession().getAttribute("userID"));
            	
            	if (request.getSession().getAttribute("curier") != null) {
            	if ((int)request.getSession().getAttribute("curier") == 1)
            		res = c.getUser((int)request.getSession().getAttribute("userID"));
            	}
            	if (request.getSession().getAttribute("angajat") != null) {
            		res = a.getUser((int)request.getSession().getAttribute("userID"));
            	}
            	res.next();
            	if (request.getSession().getAttribute("angajat") == null) {
            %>
                <label class="form-label" for="username"> Nume utilizator:</label><br>
                <input value = "<%=res.getString("numeUtilizator") %>" class="form-input" type="text" name="username"><br>
                <label class="form-label" for="password"> Parola:</label><br>
                <input value = <%=res.getString("parola") %> class="form-input" type="password" name="password">
                <label class="form-label" for="nume">Nume:</label><br>
                <input value = "<%=res.getString("nume") %>" class="form-input" type="text" name="nume">
                <label class="form-label" for="telefon"> Telefon:</label><br>
                <input value = <%=res.getString("telefon") %> class="form-input" type="text" name="telefon">
                <label class="form-label" for="email"> Email:</label><br>
                <input value = <%=res.getString("email") %> class="form-input" type="text" name="email"><br>
                <label class="form-label" for="adress"> Adresa:</label><br>
                <input value = "<%=res.getString("adresa") %>" class="form-input" type="text" name="adresa"><br>
                <%
                	if (request.getSession().getAttribute("curier") != null) {
                	if ((int)request.getSession().getAttribute("curier") == 1) {
                %>
                	<label class="form-label" for="nrInmatriculare"> Numar Inmatriculare:</label><br>
                	<input value = <%=res.getString("numarInmatriculare") %> class="form-input" type="text" name="nrInmatriculare"><br>
                	<label class="form-label" for="tipMasina"> Tip masina:</label><br>
                	<input value = "<%=res.getString("tipMasina") %>" class="form-input" type="text" name="tipMasina"><br>
                <% } 
                }}else {
                %>
                <label class="form-label" for="restaurantID"> ID restaurant: <%= (int)request.getSession().getAttribute("restaurantID-login") %></label><br>
                <input value = <%=res.getString("parola") %> class="form-input" type="text" name="password"><br>
                <% }         
                %>
                
                <input class="btn btn-primary btn-sm" id="update-account-bttn" type = submit value = "Actualizare Cont" name = "update">
                <input class="btn btn-primary btn-sm" id="log-out-bttn" type = submit value = "Log Out" name = "log-out">
          </form>
        </div> 
    </div>
    <!-- End register -->
</body>
</html>
