jQuery.validator.addMethod("noSpace", function(value, element) { 
    return value == '' || value.trim().length != 0;  
}, "No space please and don't leave it empty");
$.validator.addMethod( "alphanumeric", function( value, element ) {
return this.optional( element ) || /^\w+$/i.test( value );
}, "Letters, numbers, and underscores only please" );

var $loginForm = $('#login');
if($loginForm.length){
  $loginForm.validate({
      rules:{
          username: {
              required: true,
              alphanumeric: true
          },
          password: {
              required: true
          }
      },
      messages:{
          username: {
              required: 'Please enter username!'
          },
          password: {
              required: 'Please enter password!'
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
