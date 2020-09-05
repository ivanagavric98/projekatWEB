$(document).ready(function(){
	let inputElem = $("input[name=fname]");
	console.log('ready');
	
	$("#submit_form").click(function(event){
		console.log('register');

		let firstName = inputElem.val();
		let gender = $("#registration input[type='radio']:checked").val();
		let data = {
			"firstName" : $("input[name=fname]").val(),
			"lastName" : $("input[name=lname]").val(),
			"username" : $("input[name=username]").val(),
			"password" : $("input[name=password]").val(),
			"gender" : gender
		};
		
		$.ajax({
            type: 'POST',
            url: 'rest/registration',
            data: JSON.stringify(data),
            contentType : 'application/json',
            success : function(response){
				console.log(response);
	            alert("Uspesna registracija!");
            }
        });	
		
		event.preventDefault();
	});
	
});


