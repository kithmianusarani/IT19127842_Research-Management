$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

	// Form validation-------------------
	/*var status = validateResearchForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
		$("#alertError").show(); 
 		return; 
 	}*/
	// If valid-------------------------
 	var type = ($("#hidresearchIDSave").val() == "") ? "POST" : "PUT"; 
 	
 	$.ajax( 
 	{ 
 			url : "researchAPI", 
 			type : type, 
 			data : $("#formresearch").serialize(), 
 			dataType : "text", 
 			complete : function(response, status) 
 			{ 
 				onresearchSaveComplete(response.responseText, status); 
 			} 
 	}); 
 });
 
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event) 
	{ 
 	$("#hidresearchIDSave").val($(this).closest("tr").find('#hidIDUpdate').val());
 	$("#researchName").val($(this).closest("tr").find('td:eq(0)').text()); 
 	$("#researchContactNo").val($(this).closest("tr").find('td:eq(1)').text()); 
 	$("#researchEmail").val($(this).closest("tr").find('td:eq(2)').text()); 

});


$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
			 url : "researchAPI", 
 			 type : "DELETE", 
 			 data : "researchID=" + $(this).data("researchid"),
			 dataType : "text", 
 			 complete : function(response, status) 
 			{ 
 					onresearchDeleteComplete(response.responseText, status); 
 			} 
 	}); 
});

function onresearchDeleteComplete(response, status)
	{ 
		if (status == "success") 
 		{ 
 			var resultSet = JSON.parse(response); 
 			if (resultSet.status.trim() == "success") 
 			{ 
 				$("#alertSuccess").text("Successfully deleted."); 
 				$("#alertSuccess").show(); 
 				$("#divresearchGrid").html(resultSet.data); 
 			} else if (resultSet.status.trim() == "error") 
 			{ 
 				$("#alertError").text(resultSet.data); 
 				$("#alertError").show(); 
 			} 
 		} else if (status == "error") 
 		{ 
 				$("#alertError").text("Error while deleting."); 
 				$("#alertError").show(); 
 		} else
 		{ 
 				$("#alertError").text("Unknown error while deleting.."); 
 				$("#alertError").show(); 
		} 
}


function onresearchSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 	{ 
 		$("#alertSuccess").text("Successfully saved."); 
 		$("#alertSuccess").show(); 
 		$("#divresearchGrid").html(resultSet.data); 
 	} else if (resultSet.status.trim() == "error") 
 	{ 
 		$("#alertError").text(resultSet.data); 
 		$("#alertError").show(); 
 	} 
 } else if (status == "error") 
 { 
 		$("#alertError").text("Error while saving."); 
 		$("#alertError").show(); 
 } else
 { 
 		$("#alertError").text("Unknown error while saving.."); 
 		$("#alertError").show(); 
 } 
 		$("#hidresearchIDSave").val(""); 
 		$("#formresearch")[0].reset(); 
}
 

// CLIENT-MODEL================================================================
function validateResearchForm() 
{ 

	// NAME
	if ($("#researchName").val().trim() == "") 
 	{ 
 		return "Insert researcher Name."; 
 	} 

	// CONTACTNO-------------------------------
	if ($("#researchContactNo").val().trim() == "") 
 	{ 
 		return "Insert researcher Contact Number."; 
 	} 

 
	// EMAIL------------------------
	
	if ($("#researchEmail").val().trim() == "") 
	{ 
		 return "Insert researcher Email."; 
 	} 

	return true; 
	
}
