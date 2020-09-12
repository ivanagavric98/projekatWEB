$(document).ready(function() {
    console.log('document profile ready');

    $('#profile_li').click(function() {
        $.ajax({
            type: "get",
            url: "rest/user/loginUsername/username",
            contentType: "application/json",
            success: function(user) {
                console.log(user);
                clearWorkspace();
                $('#user_profile').show();
                $('#user_profile_table-username').val(user.username);
                $('#user_profile_table-password').val(user.password);
                $('#user_profile_table-firstName').val(user.firstName);
                $('#user_profile_table-lastName').val(user.lastName);
                $('#user_profile_table-role').val(user.role);
                if(user.gender === "MALE") {
                    $("#profileMale").prop("checked", true);
                } else {
                    $("#profileFemale").prop("checked", true);
                }
            }
        })
    });

    $('#button-edit-profile').click(function() {
        let username = $('#user_profile_table-username').val();
        let password = $('#user_profile_table-password').val();
        let firstName = $('#user_profile_table-firstName').val();
        let lastName = $('#user_profile_table-lastName').val();
        let genderMale = document.getElementById('profileMale').checked;
        let gender;
        if(genderMale === true) {
            gender = "MALE";
        } else {
            gender = "FEMALE";
        }

        // validacija...

        let dataObj = {
            "firstName" : firstName,
            "lastName" : lastName,
            "username" : username,
            "password" : password,
            "gender" : gender
        }

        $.ajax({
            type: "put",
            url: "rest/user/"+ username,
            data: JSON.stringify(dataObj),
            contentType: "application/json",
            success: function(user) {
                console.log(user);
                alert('Uspesna izmena profila!');
            }
        })
    })

});