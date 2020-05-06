$(document).on("click", ".btnUpdate", function(event) { 
	
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIdUpdate').val());
	$("#hostname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#hostlocation").val($(this).closest("tr").find('td:eq(2)').text());
	
	$("#alertSuccess").text().trim() == "Data Retrived"

});

$(document).ready(function () {
	 document.forms['form'].reset();
	 
});

$(document).ready(function() { 

	if($("#alertSuccess").text().trim() == ""){
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide(); 
	
});

$(document).on("click", "#btnSave", function(event) { 
	
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text(""); 
	$("#alertError").hide(); 
	
	var status = validateItemForm(); 
	
	if (status != true)  {  
		$("#alertError").text(status);  
		$("#alertError").show();  
		return; 	
		} 
	
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( { 
		url : "AdminAPI", 
		type : type,  
		data : $("#formItem").serialize(),  
		dataType : "text",  
		complete : function(response, status) 
		{   
			onItemSaveComplete(response.responseText, status);  
		} 
	}); 
	
});

function onItemSaveComplete(response, status) {  
	console.log(response);
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully saved.");  $("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 	
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
	 }
	
	$("#hidItemIDSave").val(""); 
	$("#form")[0].reset(); 
}


$(document).on("click", ".btnRemove", function(event) { 
	console.log( $(this).val());
	$.ajax( { 
		url : "AdminAPI",   
		type : "DELETE",   
		data : "hid=" + $(this).val(),   
		dataType : "text",   
		complete : function(response, status) 
		{   
			onItemDeleteComplete(response.responseText, status);  
		} 
	}); 
	
});

function onItemDeleteComplete(response, status) {  
	
	var resultSet = JSON.parse(response); 
	 
	if (resultSet.status.trim() == "success") {  
		
		$("#alertSuccess").text("Successfully deleted."); 
		$("#alertSuccess").show(); 
		 
		 $("#divItemsGrid").html(resultSet.data); 
	
	} else if (resultSet.status.trim() == "error") 
	
	{  
		$("#alertError").text(resultSet.data); 
		$("#alertError").show(); 
		
	}
	else if (status == "error") {
		
		$("#alertError").text("Error while deletingdeleting.");  
		$("#alertError").show(); 
	}
	 else 
	 {  
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 }
	
}


//validation 

function validateItemForm() {  
	
	if ($("#hostname").val().trim() == 0) {  
		
		return "Please enter hostpital  name";
	} 
	
	if ($("#hostlocation").val().trim() == 0) {  
		 
		return "Please enter location";
	} 
	
	if ($("#adminid").val().trim() == "") {  

	return "Please Enter adminid";
	} 
	
	return true;  
	}
