function addNewAmenities(amenitie) {
    let id = $('<td>' + amenitie.id + '</td>');
    let name = $('<td>' + amenitie.name + '</td>');


    let tr = $('<tr></tr>');
    tr.append(id).append(name);
    $('#admin-list-amenities-table').append(tr);
}

$(document).ready(function() {

    $('#amenities_li').click(function() {
        $.ajax({
            type: "get",
            url: "rest/amenities/findAllAmenities",
            contentType: "application/json",
            success: function(amenities) {
                clearWorkspace();
                $('#admin-list-amenities').show();
                $('#admin-list-amenities-table tbody').empty();
                for (let amenitie of amenities) {
                    addNewAmenities(amenitie);
                }
            }
        })
    });



});