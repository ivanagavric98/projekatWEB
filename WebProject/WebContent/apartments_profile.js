$(document).ready(function() {
    console.log('document profile ready');

    $('#admin-list-apartments-table').on('click', '.btnSelect1', function() {
        $.ajax({
            type: "get",
            url: "rest/apartment/pretragapoId/1",
            contentType: "application/json",
            success: function(apartment) {
                console.log(apartment);
                clearWorkspace();
                $('#mod_pretraga_apartments').show();
                $('#room_number_id').val(apartment.roomsNumber);
                $('#guest_number_id').val(apartment.guestsNumber);
                $('#location_id').val(apartment.location);
                $('#pricePerNight_id').val(apartment.pricePerNight);
                $('#checkin_time_id').val(apartment.checkInTime);
                $('#checkout_time_id').val(apartment.checkOutTime);
                $('#city_id').val(apartment.city);
                if(apartment.status == "INACTIVE") {
                    $("#statusI").prop("checked", true);
                } else {
                    $("#statusA").prop("checked", true);
                }
                if(apartment.type == "ROOM") {
                    $("#typeRoom").prop("checked", true);
                } else {
                    $("#typeFull").prop("checked", true);
                }
            
            }
        })


        $('#create_res_button_mod1').click(function() {
            let roomsNumber = $('#room_number_id').val();
            let guestsNumber = $('#guest_number_id').val();
            let location = $('#location_id').val();
            let city = $('#city_id').val();
            let pricePerNight = $('#pricePerNight_id').val();
            let checkInTime = $('#checkin_time_id').val();
            let checkOutTimey = $('#checkout_time_id').val();
            let host = "Neki";

            let typeRoom = document.getElementById('typeRoom').checked;
            let type;
            if(typeRoom === true) {
                type = "ROOM";
            } else {
                type = "FULL_APARTMENT";
            }

            let statusIn = document.getElementById('statusA').checked;
            let status;
            if(statusIn === true) {
                status = "INACTIVE";
            } else {
                status = "ACTIVE";
            }
    
            // validacija...
    
            let dataObj = {
                "roomsNumber":roomsNumber,
                "guestsNumber": guestsNumber,
                "location":location,
                "city":city,
                "pricePerNight":pricePerNight,
                "checkInTime": checkInTime,
                "checkOutTime":checkOutTimey,
                "type": type
            }
    
            $.ajax({
                type: "put",
                url: "rest/apartment/1/edit",
                data: JSON.stringify(dataObj),
                contentType: "application/json",
                success: function(apartment) {
                    console.log(apartment);
                    alert('Uspesna izmena apartmana!');
                }
            })
        })
        //---------------------------------------------------------------------------
        // $('#mod_pretraga').show();
      
        // $('#room_number_id').val(user.username);
        // $('#user_profile_table-password').val(user.password);
        // $('#user_profile_table-firstName').val(user.firstName);
        // $('#user_profile_table-lastName').val(user.lastName);
        // $('#user_profile_table-role').val(user.role);
        // if(user.gender === "MALE") {
        //     $("#profileMale").prop("checked", true);
        // } else {
        //     $("#profileFemale").prop("checked", true);
        // }
         })
    });
