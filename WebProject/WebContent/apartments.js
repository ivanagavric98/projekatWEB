 function addNewApartment(apartment) {
     let id = $('<td>' + apartment.id + '</td>');
     let type = $('<td>' + apartment.type + '</td>');
     let roomsNumber = $('<td>' + apartment.roomsNumber + '</td>');
     let guestsNumber = $('<td>' + apartment.guestsNumber + '</td>');
     let location = $('<td>' + apartment.location + '</td>');
     let city = $('<td>' + apartment.city + '</td>');
     let host = $('<td>' + apartment.host + '</td>');
     let pricePerNight = $('<td>' + apartment.pricePerNight + '</td>');
     let checkInTime = $('<td>' + apartment.checkInTime + '</td>');
     let checkOutTime = $('<td>' + apartment.checkOutTime + '</td>');
     let status = $('<td>' + apartment.status + '</td>');
     let createRes = $('<td > ' + '<button type="submit" class="btnSelect">Create</button>' + '</td>');
     let editCol = $('<td > ' + '<button type="submit" class="btnSelect1">Edit</button>' + '</td>');

     let tr = $('<tr></tr>');
     tr.append(id).append(type).append(roomsNumber).append(guestsNumber)
         .append(location).append(city).append(host).append(pricePerNight).append(checkInTime).append(checkOutTime).append(status).append(createRes).append(editCol);
     $('#admin-list-apartments-table').append(tr);
 }

 $(document).ready(function() {
	 
    $('#activeApartmans').click(function() {
        $.ajax({
            type: "get",
            url: "rest/apartment/hostApartment",
            contentType: "application/json",
            success: function(apartments) {
                clearWorkspace();
                $('#admin-list-apartments').show();
                $('#admin-list-apartments-table tbody').empty();
                for (let apartment of apartments) {
                    addNewApartment(apartment);
                }
            }
        })
    });

    $('#inactiveApartmans').click(function() {
        $.ajax({
            type: "get",
            url: "rest/apartment/hostApartmentInactive",
            contentType: "application/json",
            success: function(apartments) {
                clearWorkspace();
                $('#admin-list-apartments').show();
                $('#admin-list-apartments-table tbody').empty();
                for (let apartment of apartments) {
                    addNewApartment(apartment);
                }
            }
        })
    });
	 
	 
     $('#apartments_li').click(function() {
         $.ajax({
             type: "get",
             url: "rest/apartment/findAllApartments",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });

     $('#admin-list-apartments-table').on('click', '.btnSelect', function() {
         $('#mod_pretraga').show();
     });


     $('#add_apartment_submit_form').on('click', function(event) {

         console.log('addd');
         let type = $("#addApartmants input[type='radio']:checked").val();


         let dataObj = {
             "roomsNumber": $("input[name=numberOfRooms]").val(),
             "guestsNumber": $("input[name=numberOfGuest]").val(),
             "location": $("input[name=location]").val(),
             "city": $("input[name=city]").val(),
             "pricePerNight": $("input[name=pricePerNight]").val(),
             "checkInTime": $("input[name=checkInTime]").val(),
             "checkOutTime": $("input[name=checkOutTime]").val(),
             "type": type
         };
            console.log(dataObj);
         $.ajax({
             type: 'post',
             url: 'rest/apartment/addAp',
             data: JSON.stringify(dataObj),
             contentType: 'application/json',
             success: function(response) {
                 console.log(response);
                 alert("Uspesno dodavanje!");

             }
         })

         event.preventDefault();
     });




     //sortiranje po broju soba
     $('#byRoomNumberAsc').on('click', function() {
         $.ajax({
             type: "get",
             url: "rest/apartment/brojSoba/asc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });

     $('#byRoomNumberDesc').on('click', function() {
         let prom = "brojSoba"
         $.ajax({
             type: "get",
             url: "rest/apartment/brojSoba/desc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });
     //----------------------------------------------------------
     //sortiranje po broju gostiju
     $('#byGuestNumberAsc').on('click', function() {
         let prom = "brojSoba"
         $.ajax({
             type: "get",
             url: "rest/apartment/brojGostiju/asc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });

     $('#byGuestNumberDesc').on('click', function() {
         let prom = "brojSoba"
         $.ajax({
             type: "get",
             url: "rest/apartment/brojGostiju/desc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });
     //----------------------------------------------------------
     //sortiranje po cijeni
     $('#byPriceAsc').on('click', function() {
         let prom = "brojSoba"
         $.ajax({
             type: "get",
             url: "rest/apartment/cijena/asc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });

     $('#byPriceDesc').on('click', function() {
         let prom = "brojSoba"
         $.ajax({
             type: "get",
             url: "rest/apartment/cijena/desc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     });
     //------------------------------------------------------
     //PRETRAGA APARTMANA PO tipu
     $('#search-apartment').on('input', function() {
         let type = $('#search-apartment').val();
         if (type === "") {
             type = "prazan_string";
         }
         $.ajax({
             type: "get",
             url: "rest/apartment/" + type + "/filtracija",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }

             }
         })
     });

     //------------------------------------------------------
     //PRETRAGA APARTMANA PO LOKACIJI
     $('#search-apartment').on('input', function() {
         let city = $('#search-apartment').val();
         if (city === "") {
             city = "prazan_string";
         }
         $.ajax({
             type: "get",
             url: "rest/apartment/" + city + "/filtracija1",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }

             }
         })
     });
     //------------------------------------------------------
     //PRETRAGA APARTMANA PO statusu
     $('#search-apartment').on('input', function() {
         let status = $('#search-apartment').val();
         if (status === "") {
             status = "prazan_string";
         }
         $.ajax({
             type: "get",
             url: "rest/apartment/" + status + "/filtracija2",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }

             }
         })
     });

     //filtracija apartmana po max broju gostiju
     $('#buttonMaxGuests').on('click', function() {
         let maxNumberGuest = $('#maxGuestNumber').val();
         if (maxNumberGuest === "") {
             alert("insert value");
         }
         console.log("hejjj")
         $.ajax({
             type: "get",
             url: "rest/apartment/" + maxNumberGuest + "/filtracija3",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }

             }
         })
     });
     //---------------------------------------------------
     //filtracija apartmana po opsegu broja soba
     $('#buttonNumRooms').on('click', function() {
         let minRoomNumber1 = $('#minRoomNumber').val();
         let maxRoomNumber1 = $('#maxRoomNumber').val();
         if (minRoomNumber1 === "" || maxRoomNumber1 === "") {
             alert("insert both value");
         }
         console.log("majaaaaa")
         $.ajax({
             type: "get",
             url: "rest/apartment/" + minRoomNumber1 + "/" + maxRoomNumber1 + "/filtracija4",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }

             }
         })
     });
     //---------------------------------------------------
     //filtracija apartmana po opsegu broja cijene
     $('#buttonPriceB').on('click', function() {
         let minPrice1 = $('#minPrice').val();
         let maxPrice1 = $('#maxPrice').val();
         if (minPrice === "" || maxPrice === "") {
             alert("insert both value");
         }
         $.ajax({
             type: "get",
             url: "rest/apartment/" + minPrice1 + "/" + maxPrice1 + "/filtracija5",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                 $('#admin-list-apartments').show();
                 $('#admin-list-apartments-table tbody').empty();
                 for (let apartment of apartments) {
                     addNewApartment(apartment);
                 }

             }
         })
     });

     /*  $('#search_li').click(function() {
          $('#mod_pretraga').show();
      }); */

 });