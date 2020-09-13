function clearWorkspace() {
    $('#user_profile').hide();
    $('#login-form').hide();
    $('#registration-form').hide();
    $('#dodavanjeApartmana-form').hide();
    //$('#admin-list-users').hide();
    //$('#admin-list-apartments').hide();
    $('#mod_pretraga').hide();
}

let activeUserRole = '-1';

$(document).ready(function() {
    console.log('document ready login');

    $('.modal-header span').click(function() {
        $('#mod_pretraga').hide();
    });

    $('#login').submit(login());
    $('#registration').submit(registerNewUser());

    $('#register_form_button').on('click', function() {
        clearWorkspace();
        $('#registration-form').show();
    });

    $('#login-button').on('click', function() {
        clearWorkspace();
        $('#login-form').show();
    });

    $('#logout_li').on('click', function() {

        $.ajax({
            type: 'GET',
            url: 'rest/logout',
            contentType: 'application/json',
            success: function() {
                alert('Goodbay!');
                activeUserRole = undefined;
                clearWorkspace();
                $('#login-form').show();
                $('#logout_li').hide();
                hideFuncByRole(activeUserRole);
            },
            error: function(message) {}
        });
    });


});

function login() {
    return function(event) {
        event.preventDefault();
        console.log('login-submit');

        let password = $('input[name="password"]').val();
        let username = $('input[name="username"]').val();

        if (!username) {
            $('#error_username').text('Please enter valid username!');
            $('#error_password').val();
            $('input[name="username"]').focus();
            return;
        } else if (!password) {
            $('#error_password').text('Please enter valid password!');
            $('#error_username').val();
            $('input[name="password"]').focus();
            return;
        } else {
            var obj = {
                "username": username,
                "password": password
            }

            $.ajax({
                type: 'POST',
                url: 'rest/login',
                data: JSON.stringify(obj),
                contentType: 'application/json',
                success: function(user) {
                    alert('Welcome!');
                    activeUserRole = user.role;
                    hideFuncByRole(activeUserRole);
                    clearWorkspace();
                },
                error: function(message) {
                    $('#error_username').val("");
                    $('#error_password').val("");
                }
            });

        }
    }
}

//function logout() {
//    return function(event) {
//        event.preventDefault();
//        console.log('logout-submit');
//
//        var currentUser = 
//        
//        $.ajax({
//            type: 'POST',
//            url: 'rest/logout',
//            data: JSON.stringify(obj),
//            contentType: 'application/json',
//            success: function(user) {
//                alert('Goodbay!');
//                hideFuncByRole(activeUserRole);
//                clearWorkspace();
//            },
//            error: function(message) {
//                $('#error_username').val("");
//                $('#error_password').val("");
//            }
//        });
//
//
//    }
//}

function hideFuncByRole(role) {
    if (role === "ADMINISTRATOR") {
        $('#profile_li').hide();
        $('#logout_li').hide();
    } else if (role === "HOST") {
        $('#users_li').hide();
    } else {
        $('#users_li').hide();

    }
}


function registerNewUser() {
    return function(event) {
        event.preventDefault();

        let fname = $('input[name=fname]').val();
        let lname = $('input[name=lname]').val();
        let password = $('input[name="password"]').val();
        let username = $('input[name="username"]').val();
        let gender = "";
        if (document.getElementById("female").checked === true) {
            gender = "female";
        } else if (document.getElementById("male").checked === true) {
            gender = "male";
        }

        let passwordConfirm = $('input[name="confirm"]').val();

        if (!username) {
            $(error_username).text('Please enter valid username!');
            cleanErrors("username");
        } else if (!password) {
            $(error_password).text('Please enter valid password!');
            cleanErrors("password");
        } else if (!fname) {
            $(error_fname).text('Please enter valid first name!');
            cleanErrors("fname");
        } else if (!lname) {
            $(error_lname).text('Please enter valid last name!');
            cleanErrors("lname");
        } else if (gender === "") {
            $(error_gender).text('Please enter gender!');
            cleanErrors("password");
        } else if (!passfordConfirm) {
            $(error_confirm).text('Please enter password again!');
            cleanErrors("passwordConfirm");
        } else if (password !== passwordConfirm) {
            $(error_passwordConfirm).text('Passwords do not match!');
            cleanErrors("passwordConfirm");
        } else {
            var obj = {
                "fname": fname,
                "lname": lname,
                "username": username,
                "password": password,
                "gender": gender,
                "role": "GUEST"
            }


        }
    }
}

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