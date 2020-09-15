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
<<<<<<< HEAD
     let createRes = $('<td>' + '<button class="btnSelect" type="submit" >Create</button>' + '</td>');
=======
     let createRes = $('<td > ' + '<button type="submit" class="btnSelect">Create</button>' + '</td>');
     let editCol = $('<td > ' + '<button type="submit" class="btnSelect1">Edit</button>' + '</td>');
>>>>>>> 0b94f8f2dfccc0dae51bd1a44126a1dc2bc882e3


     let tr = $('<tr></tr>');
     tr.append(id).append(type).append(roomsNumber).append(guestsNumber)
         .append(location).append(city).append(host).append(pricePerNight).append(checkInTime).append(checkOutTime).append(status).append(createRes).append(editCol);
     $('#admin-list-apartments-table').append(tr).append(tr);
 }

 $(document).ready(function() {
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

<<<<<<< HEAD
=======



>>>>>>> 0b94f8f2dfccc0dae51bd1a44126a1dc2bc882e3
     $('#admin-list-apartments-table').on('click', '.btnSelect', function() {
         $('#mod_pretraga').show();
     });


     $('#add_apartment_submit_form').click(function(event) {

         console.log('addd');
         let type = $("#addApartmants input[type='radio']:checked").val();
         let date = $("#datumi");

         let date = ("#datumi");
         let lokacija = $("input[name=Location]").val();
         let lokacijaNiz = lokacija.split(",");
         console.log(lokacijaNiz);

         letAdresaObj = {
             "street": lokacijaNiz[0],
             "number": lokacijaNiz[1],
             "city": lokacijaNiz[2],
             "postalCode": lokacijaNiz[3]
         };

         let LocationObj = {
             "latitude": lokacijaNiz[4],
             "longitude": lokacijaNiz[5],
             "adress": letAdresaObj
         };
         let dataObj = {
             "roomsNumber": $("input[name=numberOfRooms]").val(),
             "guestsNumber": $("input[name=numberOfGuest]").val(),
<<<<<<< HEAD
             "location": LocationObj,
             "datumi": $("input[name=datumi]").val(),
             //  s "comments": $("input[name=comments]").val(),
=======
             "location": $("input[name=location]").val(),
             "city": $("input[name=city]").val(),
>>>>>>> 0b94f8f2dfccc0dae51bd1a44126a1dc2bc882e3
             "pricePerNight": $("input[name=pricePerNight]").val(),
             "checkInTime": $("input[name=checkInTime]").val(),
             "checkOutTime": $("input[name=checkOutTime]").val(),
             "type": type
         };
         console.log(dataObj);
         $.ajax({
             type: 'POST',
             url: 'rest/apartment/addAp',
             data: JSON.stringify(dataObj),
             contentType: 'application/json',
             success: function(response) {
                 console.log(response);
                 alert("Uspesno dodavanje!");

             }
         });

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
<<<<<<< HEAD

             }
         })
     });
=======
>>>>>>> 0b94f8f2dfccc0dae51bd1a44126a1dc2bc882e3

             }
         })
     });


     $('#admin-list-apartments-table').on('click', '.btnSelect1', function() {
        $('#apartment_add').show();
    });
     /*  $('#search_li').click(function() {
          $('#mod_pretraga').show();
      }); */

 });