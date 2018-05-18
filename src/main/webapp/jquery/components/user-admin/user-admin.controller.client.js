(function () {

    var userForm;
    var tbody;
    var template;
    var userService = new UserServiceClient()

    $(main);

    function main() { 
        //Retrieved the dom elements needed later in the controller such as the form elements, action icons, and templates.
        //Binds action icons, such as create, update, select, and delete, to respective event handlers

        userForm = $('.userForm');
        tbody = $('tbody');
        template = $('.userRowTemplate');


        //user list
        var promise = fetch('/api/user');
        promise.then(function (response) {
            return response.json();
            }).then(renderUsers)

        //Handle form submit button.
        $('#createUser').click(createUser);

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
        userService
            .findUserById()
            .then(renderUser);
    }

    function selectUser() {
        userService.findUserById(userId)
            .then(function(response) {
                renderUser(response);
            });
    }
    
    function updateUser(user) {
        //Posts user information from the form to the database and triggeres a renderUsers.
        userService
            .updateUser(user)
            .then(findAllUsers);
    }
    
    function renderUser(user) {
        //populates form with a user's information pulled from the server.
        userForm.find('#usernameFld').html(user.username);
        userForm.find('#passwordFld').html(user.password);
        userForm.find('#firstNameFld').html(user.firstName);
        userForm.find('#lastNameFld').html(user.lastName);
        userForm.find('#roleFld').html(user.role);
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
            clone.find('.password')
                .html(user.password);
            clone.find('.first-name')
                .html(user.firstName);
            clone.find('.last-name')
                .html(user.lastName);
            clone.find('.role')
                .html(user.role);
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

        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent()
            .attr('id');

        userService.profileRedirect(userId);
    }

})();
