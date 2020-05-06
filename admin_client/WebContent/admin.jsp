<%@page import="com.AdminRepository"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Heathcare</title>
		<link href="view/css/bootstrap.min.css" rel="stylesheet">
		<script src="component/jquery-3.5.0.min.js" type="text/javascript"></script>
		<script src="component/admin.js" type="text/javascript"></script>
	</head>
	<body>
	<%session.setAttribute("uID",1);   %>
		<div >
		<!-- header  -->
			    <nav class=" navbar navbar-expand-lg navbar-dark bg-dark">
			        <a class="navbar-brand" href="#">HealthCare / Hospitals</a>
			        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			          <span class="navbar-toggler-icon"></span>
			        </button>
			        <div class="collapse navbar-collapse" id="navbarSupportedContent">
			          <ul class="navbar-nav mr-auto">
			            <!-- <li class="nav-item active">
			              <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
			            </li>
			            <li class="nav-item">
			              <a class="nav-link" href="#">Hospitals</a>
			            </li>
			            <li class="nav-item">
			              <a class="nav-link" href="#">Doctors</a>
			            </li>
			            <li class="nav-item">
			              <a class="nav-link" href="#">Patients</a>
			            </li>
			            <li class="nav-item">
			              <a class="nav-link" href="#">Appointements</a>
			            </li>
			            <li class="nav-item">
			              <a class="nav-link" href="#">Payments</a>
			            </li> -->
			          </ul>
			          <form class="form-inline my-2 my-lg-0">
			            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
			          </form>
			        </div>
			    </nav>
			    
			<!-- body -->
			<div class="container">
				<div class="row">
				<div class="col-6">
					<br/>
					<form id="formItem" name="formItem">
					
	 					<h6>Hospital Name :</h6>
	 					<input id="hostname" name="hostname" type="text" class="form-control form-control-sm"> <br> 
	 					
	 					<h6>Location :</h6>
	 					<input id="hostlocation" name="hostlocation" type="text" class="form-control form-control-sm"> <br> 
	 					
	 					<h6>Admin ID :</h6>
	 					<input id="adminid" name="adminid" type="text" value="<%out.print(String.valueOf(session.getAttribute("uID")));%>" class="form-control form-control-sm" readonly="readonly"> <br> 
	 					
	 					<input id="btnSave" name="btnSave" type="button" value="Submit" class="btn btn-primary">
	 					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	 					
					</form>
						
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
						<div id="divItemsGrid">
							 <%
							 AdminRepository itemObj = new AdminRepository();
							 out.print(itemObj.getHospitals());
							 %>
						</div>
				</div>
 			</div>
			</div>
		</div>

	</body>
</html>