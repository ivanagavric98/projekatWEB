 function addNewApartment(apartment) {
     let id = $('<td>' + apartment.id + '</td>');
     let type = $('<td>' + apartment.type + '</td>');
     let roomsNumber = $('<td>' + apartment.roomsNumber + '</td>');
     let guestsNumber = $('<td>' + apartment.guestsNumber + '</td>');
     let location = $('<td>' + apartment.location + '</td>');
     let host = $('<td>' + apartment.host + '</td>');
     let pricePerNight = $('<td>' + apartment.pricePerNight + '</td>');
     let checkInTime = $('<td>' + apartment.checkInTime + '</td>');
     let checkOutTime = $('<td>' + apartment.checkOutTime + '</td>');
     let status = $('<td>' + apartment.status + '</td>');

     let tr = $('<tr></tr>');
     tr.append(id).append(type).append(roomsNumber).append(guestsNumber)
         .append(location).append(host).append(pricePerNight).append(checkInTime).append(checkOutTime).append(status);
     $('#admin-list-apartments-table').append(tr);
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
     //------------------------------------------------------------
     //sortiranje po broju soba
     $('#byRoomNumberAsc').on('click', function() {
         let prom = "brojSoba"
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

     //PRETRAGA APARTMANA PO statusu
     $('#buttonMaxGuests').on('click', function() {
         let maxNumberGuest = $('#maxGuestNumber').val();
         if (maxNumberGuest === "") {
             alert("insert value");
         }

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


     /*  $('#search_li').click(function() {
          $('#mod_pretraga').show();
      }); */

 });