(function() {
    $(init);

    var $username;
    var $firstName;
    var $lastName;
    var $updateBtn;
    var $logoutBtn;
    var url;
    var displayUserId;
    var userService = new UserServiceClient();


    function init() {
        $username = $("#username");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $updateBtn = $("#updateBtn")
            .click(updateUser);
        $logoutBtn = $("#logoutBtn")
            .click(logout);


        url = window.location.href;
        displayUserId =  $(location).attr('search').substring(8,);

        console.log(displayUserId);
        findUserById(displayUserId);
    }

    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };

        userService
            .updateUser(displayUserId, user)
            .then(success);
    }

    function success(response) {
        if(response === null) {
            alert('unable to update')
        } else {
            alert('success');
        }
    }

    function logout() {
        userService
            .logout();
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }
    
    function renderUser(user) {
        console.log(user);
        $username.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }
})()