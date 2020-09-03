/*var firstName = document.forms['vform']['firstName'];
var lastName = document.forms['vform']['lastName'];
var username = document.forms['vform']['username'];
var password = document.forms['vform']['password'];
var password_confirm = document.forms['vform']['password_confirm'];

var firstName_error = document.getElementById('firstName_error');
var lastName_error = document.getElementById('lastName_error');
var username_error = document.getElementById('username_error');
var password_error = document.getElementById('password_error');


firstName.addEventListener('blur', firstNameVerify, true);
lastName.addEventListener('blur', lastNameVerify, true);
username.addEventListener('blur', usernameVerify, true);
password.addEventListener('blur', passwordVerify, true);

function Validate() {
	  if (firstName.value == "") {
	    firstName.style.border = "1px solid red";
	    firstName_error.textContent = "First name is required";
	    firstName.focus();
	    return false;
	  }
  if (lastName.value == "") {
	    lastName.style.border = "1px solid red";
	    lastName_error.textContent = "Last name is required";
	    lastName.focus();
	    return false;
	  }
  if (username.value == "") {
    username.style.border = "1px solid red";
    username_error.textContent = "Username is required";
    username.focus();
    return false;
  }

  if (password.value == "") {
    password.style.border = "1px solid red";
    password_error.textContent = "Password is required";
    password.focus();
    return false;
  }
  if (password.value != password_confirm.value) {
	    password.style.border = "1px solid red";
	    password_confirm.style.border = "1px solid red";
	    password_error.textContent = "The two passwords don't match";
	    return false;
	  }
}
function firstNameVerify() {
	  if (firstName.value != "") {
	   firstName.style.border = "1px solid #5e6e66";
	   firstName_error.innerHTML = "";
	   return true;
	  }
	}
function lastNameVerify() {
	  if (lastName.value != "") {
	   lastName.style.border = "1px solid #5e6e66";
	   lastName_error.innerHTML = "";
	   return true;
	  }
	}
function usernameVerify() {
  if (username.value != "") {
   username.style.border = "1px solid #5e6e66";
   username_error.innerHTML = "";
   return true;
  }
}
function passwordVerify() {
  if (password.value != "") {
  	password.style.border = "1px solid #5e6e66";
  	password_error.innerHTML = "";
  	return true;
  }
  if (password.value === password_confirm.value) {
	  	password.style.border = "1px solid #5e6e66";
	  	password_error.innerHTML = "";
	  	return true;
	  }
}*/
/*
$(function(){
    $("#firstName_error").hide();
    $("#lastName_error").hide();
    $("#username_error").hide();
    $("#password_error").hide();

    error_firstName = false;
    error_username = false;
	error_password = false;
	error_retype_password = false;
	error_email = false;

    $("#firstName_form").focusout(function(){
        check_firstName();
    });

    $("#lastName_form").focusout(function(){
        check_lastName();
    });

    $("#username_form").focusout(function(){
        check_username();
    });

    $("#password_form").focusout(function(){
        check_password();
    });

    $("#password_confirm_form").focusout(function(){
        check_password_confirm();
    });

    function check_firstName(){

        var firstName = $("#firstName_form").val();
        if(firstName = ""){
            $("#firstName_error").html("First Name is required.");
            $("#firstName_error").show();
            error_firstName = true;
        } else{
            $("#firstName_error").hide();
            
        }
    }
    function check_username() {
    	
		var username_length = $("#form_username").val().length;
		
		if(username_length < 5 || username_length > 20) {
			$("#username_error").html("Should be between 5-20 characters");
			$("#username_error").show();
			error_username = true;
		} else {
			$("#username_error").hide();
		}
	
	}
    $("#vform").submit(function() {
		
		error_username = false;
		error_password = false;
		error_retype_password = false;
		error_email = false;
											
		check_username();
		check_password();
	
		
		if(error_username == false && error_password == false && error_retype_password == false && error_email == false) {
			return true;
		} else {
			return false;	
		}

	});


});
*/
jQuery.validator.addMethod("noSpace", function(value, element) { 
    return value == '' || value.trim().length != 0;  
}, "No space please and don't leave it empty");
$.validator.addMethod( "alphanumeric", function( value, element ) {
return this.optional( element ) || /^\w+$/i.test( value );
}, "Letters, numbers, and underscores only please" );

var $registrationForm = $('#registration');
if($registrationForm.length){
  $registrationForm.validate({
      rules:{
          //username is the name of the textbox
          username: {
              required: true,
              //alphanumeric is the custom method, we defined in the above
              alphanumeric: true
          },
          email: {
              required: true,
              customEmail: true
          },
          password: {
              required: true
          },
          confirm: {
              required: true,
              equalTo: '#password'
          },
          fname: {
              required: true,
              noSpace: true
          },
          lname: {
              required: true,
              noSpace: true
          },
          gender: {
              required: true
          }
      },
      messages:{
          username: {
              required: 'Please enter username!'
          },
          password: {
              required: 'Please enter password!'
          },
          confirm: {
              required: 'Please enter confirm password!',
              equalTo: 'Please enter same password!'
          },
          fname: {
              required: 'Please enter first name!'
          },
          lname: {
              required: 'Please enter last name!'
          }
      },
      errorPlacement: function(error, element) 
      {
        if (element.is(":radio")) 
        {
            error.appendTo(element.parents('.gender'));
        }
        else 
        { 
            error.insertAfter( element );
        }
        
       }
  });
}
