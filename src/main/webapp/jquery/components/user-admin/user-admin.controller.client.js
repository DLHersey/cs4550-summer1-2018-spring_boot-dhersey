(function () {

    var tbody;
    var template;
    var userService = new UserServiceClient()

    $(main);

    function main() { 
        //Retrieved the dom elements needed later in the controller such as the form elements, action icons, and templates.
        //Binds action icons, such as create, update, select, and delete, to respective event handlers


        tbody = $('tbody');
        template = $('.userRowTemplate');


        //user list
        var promise = fetch('/api/user');
        promise.then(function (response) {
            return response.json();
            }).then(renderUsers)

        //Handle form submit button.
        $('#createUser').click(createUser());

        renderUsers();
        findAllUsers();
    }
    
    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }
    
    function findAllUsers() { 
        userService
            .findAllUsers()
            .then(renderUsers);
    }
    
    function findUserById() { 

    }

    function selectUser() {

    }
    
    function updateUser() {
        //Posts user information from the form to the database and triggeres a renderUsers.

        //renderUsers(users);
    }
    
    function renderUser(user) {
        //populates form with a user's information pulled from the server.
    }
    
    function renderUsers(users) {
        // clear current user list and populates with users list.
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);

            clone.find('.username')
                .html(user.username);
            tbody.append(clone);
        }
    }


    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log('editUser');
        console.log(event);
    }

})();
