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
    	$usernameFld = $('#usernameFld').val();
    	$passwordFld = $('#passwordFld').val();
    	
    	userService
    		.login($usernameFld, $passwordFld)
    		.then(function(responseJson){
    			userService.profileRedirect(responseJson.id);
    		});
    }

})();
