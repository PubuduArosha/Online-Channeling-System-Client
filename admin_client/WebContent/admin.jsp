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
		<div class="container">
			<div class="row">
				<div class="col-6">
					<h1>Hospital</h1>
					
					<form id="formItem" name="formItem">
					
	 					Hospital Name:
	 					<input id="hostname" name="hostname" type="text" class="form-control form-control-sm"> <br> 
	 					
	 					Location:
	 					<input id="hostlocation" name="hostlocation" type="text" class="form-control form-control-sm"> <br> 
	 					
	 					Admin ID:
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

	</body>
</html>