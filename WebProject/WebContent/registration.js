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
          username: {
              required: true,
              alphanumeric: true
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

$(document).ready(function(){
	let inputElem = $("input[name=fname]");
	
	let forma = $("form");
	forma.submit(function(event){

		let firstName = inputElem.val();
		
		let data = {
			"firstName" : $("input[name=fname]").val(),
			"lastName" : $("input[name=lname]").val(),
			"username" : $("input[name=username]").val(),
			"password" : $("input[name=password]").val()
		};
		
		$.post("/WebProject/",
				JSON.stringify(data),
				function(data, status) {
					console.log(data);
					console.log(status);
				}
		);
		
		event.preventDefault();
	});
	
});


