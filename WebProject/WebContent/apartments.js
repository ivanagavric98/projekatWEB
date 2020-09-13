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

      $('#byGuestNumber').on('click', function() {
         let prom="brojSoba"
         $.ajax({
             type: "get",
             url: "rest/apartment/brojSoba/asc",
             contentType: "application/json",
             success: function(apartments) {
                 clearWorkspace();
                  $('#admin-list-apartments').show();
                  $('#admin-list-apartments-table tbody').empty();
                 for(let apartment of apartments) {
                     addNewApartment(apartment);
                 }
             }
         })
     }); 

     /*  $('#search_li').click(function() {
          $('#mod_pretraga').show();
      }); */

 });