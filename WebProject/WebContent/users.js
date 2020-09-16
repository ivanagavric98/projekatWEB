function addNewUser(user) {
    let username = $('<td>'+ user.username +'</td>');
    let firstName = $('<td>'+ user.firstName +'</td>');
    let lastName = $('<td>'+ user.lastName +'</td>');
    let gender = $('<td>'+ user.gender +'</td>');
    let role = $('<td>'+ user.role +'</td>');

    let tr = $('<tr></tr>');
    tr.append(username).append(firstName).append(lastName).append(gender).append(role);
    $('#admin-list-users-table').append(tr);
}

$(document).ready(function() {
    $('#users_li').click(function() {
        $.ajax({
            type: "get",
            url: "rest/users",
            contentType: "application/json",
            success: function(users) {
                clearWorkspace();
                $('#admin-list-users').show();
                $('#admin-list-users-table tbody').empty();
                for(let user of users) {
                    addNewUser(user);
                }
            }
        })
    });

    $('#search-user').on('input', function() {
        let username = $('#search-user').val();
        if(username === "") {
            username = "prazan_string";
        }
        $.ajax({
            type: "get",
            url: "rest/users/"+ username +"/sort",
            contentType: "application/json",
            success: function(users) {
                clearWorkspace();
                $('#sort').hide();
                $('#filtrate').hide();
                $('#admin-list-users').show();
                $('#admin-list-users-table tbody').empty();
                for(let user of users) {
                    addNewUser(user);
                }
            }
        })
    });
  
    $('#search_li').click(function() {
        $('#mod_pretraga').show();
    });

});