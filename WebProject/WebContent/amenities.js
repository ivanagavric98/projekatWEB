function addNewAmenities(amenitie) {
    let name = $('<td>' + amenitie.name + '</td>');
    let edit = $('<td>' + '<button type="submit" class="btnAmenitiesEdit">Edit</button>' + '</td>');
    let deleteA = $('<td>' + '<button type="submit" class="btnAmenitiesDelete">Delete</button>' + '</td>');

    
    let tr = $('<tr></tr>');
    tr.append(name).append(edit).append(deleteA);
    $('#admin-list-amenities-table').append(tr);
}




$(document).ready(function() {

	$('#add_amenitie_btn').click(function(event){
				event.preventDefault();

		let name = $('input[name="add_amenitie"]').val();
		
		var obj = {
				"name" : name
		}
		
		console.log('ad');
		$.ajax({
            type: 'post',
            url: 'rest/amenities/addAmenities',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            success: function(response) {
            	alert('Succesfully added!');
            	
                clearWorkspace();
                $('#admin-list-amenities').show();

            }
        })
		
		 $.ajax({
	            type: "get",
	            url: "rest/amenities/findAllAmenities",
	            contentType: "application/json",
	            success: function(amenities) {
	                clearWorkspace();
	                $('#sort').hide();
	                $('#filtrate').hide();
	                $('#admin-list-amenities').show();
	                $('#admin-list-amenities-table tbody').empty();
	                for (let amenitie of amenities) {
	                    addNewAmenities(amenitie);
	                }
	            }
	        })
		
		
		
	});
	

	$('#admin-list-amenities-table').on('click', '.btnAmenitiesEdit', function() {
        $.ajax({
            type: 'post',
            url: 'rest/amenities/editAmenities',
            data: JSON.stringify(obj),
            contentType: 'application/json',
            success: function(user) {
                alert('Welcome!');
                activeUserRole = user.role;
                hideFuncByRole(activeUserRole);
                clearWorkspace();
            }
        });
	});

	$('#admin-list-amenities-table').on('click', '.btnAmenitiesDelete', function() {
		   
		let name = $('#').val();
		
		$.ajax({
	            type: 'put',
	            url: 'rest/amenities/delete' + amenitie.name,
	            data: JSON.stringify(obj),
	            contentType: 'application/json',
	            success: function(amenitie) {
	                clearWorkspace();
	            }
	        });	});
	
	
	
    $('#amenities_li').click(function() {
        $.ajax({
            type: "get",
            url: "rest/amenities/findAllAmenities",
            contentType: "application/json",
            success: function(amenities) {
                clearWorkspace();
                $('#sort').hide();
                $('#filtrate').hide();
                $('#admin-list-amenities').show();
                $('#admin-list-amenities-table tbody').empty();
                for (let amenitie of amenities) {
                    addNewAmenities(amenitie);
                }
            }
        })
    });
});