$(document).ready(function() {


    $('#addApartmant_li').on('click', function() {
        clearWorkspace();
        $('#dodavanjeApartmana-form').show();
    });




    console.log('ready');

    $("#submit_form").click(function(event) {
        console.log('register');

        let gender = $("#registration input[type='radio']:checked").val();
        let fname = $('input[name=fname]').val();
        let lname = $('input[name=lname]').val();
        let password = $('input[name="password"]').val();
        let username = $('input[name="username"]').val();
        let passwordConfirm = $('input[name="confirm"]').val();

        if (!username) {
            document.getElementById("error_fname").innerText("morate popuniiti polja");
        }

        // 	$('#error_username').text('Please enter valid username!');
        //     cleanErrors("username");
        //     return;
        // }else if(!password){
        // 	$(error_password).text('Please enter valid password!');
        //     cleanErrors("password");
        //     return;
        // }else if(!fname){
        // 	$(error_fname).text('Please enter valid first name!');
        //     cleanErrors("fname");
        //     return;
        // }else if(!lname){
        // 	$(error_lname).text('Please enter valid last name!');
        //     cleanErrors("lname");
        //     return;
        // }else if(gender === ""){
        // 	$(error_gender).text('Please enter gender!');
        //     cleanErrors("password");
        //     return;
        // }else if(!passfordConfirm){
        // 	$(error_confirm).text('Please enter password again!');
        //     cleanErrors("passwordConfirm");
        //     return;
        // }else if(password !== passwordConfirm){
        // 	$(error_passwordConfirm).text('Passwords do not match!');
        //     cleanErrors("passwordConfirm");
        //     return;
        // }else{

        // }

        let dataObj = {
            "firstName": $("input[name=fname]").val(),
            "lastName": $("input[name=lname]").val(),
            "username": $("input[name=username-register]").val(),
            "password": $("input[name=password-register]").val(),
            "gender": gender
        };

        $.ajax({
            type: 'POST',
            url: 'rest/registration',
            data: JSON.stringify(dataObj),
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
                alert("Uspesna registracija!");
            }
        });

        event.preventDefault();
    });

});


function cleanErrors(name) {
    if (name === "username") {
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    } else if (name === "fname") {
        $(error_username).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    } else if (name === "lname") {
        $(error_username).val("");
        $(error_fname).val("");
        $(error_gender).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    } else if (name === "gender") {
        $(error_username).val("");
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_password).val("");
        $(error_confirm).val("");
    } else if (name === "password") {
        $(error_username).val("");
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_confirm).val("");
    } else if (name === "confirm") {
        $(error_username).val("");
        $(error_fname).val("");
        $(error_lname).val("");
        $(error_gender).val("");
        $(error_password).val("");
    }
}