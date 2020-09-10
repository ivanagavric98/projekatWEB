$(document).ready(function(){

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
		
		let fname = $('input[name=fname]').val();
		let lname = $('input[name=lname]').val();
		let password = $('input[name="password"]').val();
		let username = $('input[name="username"]').val();
		let gender = "";
		if(document.getElementById("female").checked === true){
			gender="female";
		}else if(document.getElementById("male").checked === true){
			gender="male";
		}
		
		let passwordConfirm = $('input[name="confirm"]').val();
		
		if(!username){
			$('#error_username').text('Please enter valid username!');
			cleanErrors("username");
		}else if(!password){
			$(error_password).text('Please enter valid password!');
			cleanErrors("password");
		}else if(!fname){
			$(error_fname).text('Please enter valid first name!');
			cleanErrors("fname");
		}else if(!lname){
			$(error_lname).text('Please enter valid last name!');
			cleanErrors("lname");
		}else if(gender === ""){
			$(error_gender).text('Please enter gender!');
			cleanErrors("password");
		}else if(!passfordConfirm){
			$(error_confirm).text('Please enter password again!');
			cleanErrors("passwordConfirm");
		}else if(password !== passwordConfirm){
			$(error_passwordConfirm).text('Passwords do not match!');
			cleanErrors("passwordConfirm");
		}else{
            var obj = {
            		"fname" : fname,
            		"lname" : lname,
            		"username" : username,
                    "password" : password,
                    "gender" : gender,
                    "role" : "GUEST"
                 }
		}
		
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


function cleanErrors(name){
    if(name === "username"){
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    }else if(name === "fname"){
        $(error_username).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    }else if(name === "lname"){
        $(error_username).val("");
        $(error_fname).val("");
        $(error_gender).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    }else if(name === "gender"){
        $(error_username).val("");
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    }else if(name === "password"){
        $(error_username).val("");
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_confirm).val("");
    }else if(name === "confirm"){
        $(error_username).val("");
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_password).val("");
    }
}

