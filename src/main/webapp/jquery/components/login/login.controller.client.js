(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
    	loginBtn = $('#loginBtn');

    	$('#loginBtn').click(login);
    }

    function login() {
    	//userService
    	//	.login()
    	//	.then()
    }

})();