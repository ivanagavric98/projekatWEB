$(document).ready(function(){
	
	var user;           
	$.ajax({
        type : "get",
        url : "rest/currentUser",
        contentType : "application/json",
        success : function(response){
            if(response !== undefined){     
                user = response;
                if(user.role === "ADMINISTRATOR"){
                	$("administratorFunctions").show();
                }
                if(user.role === "HOST"){
                	$("hostFunctions").show();
                }
                if(user.role === "GUEST"){
                	$("guestFunctions").show();
                }
            }
        }
    });	

	
});