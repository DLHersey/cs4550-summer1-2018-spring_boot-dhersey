(function () {
    var usernameFld, passwordFld, verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() { 

    	$registerBtn = $('#registerBtn');

    	$('#registerBtn').click(register);

    }

    function register() {

    	usernameFld = $('#usernameFld').val();
    	passwordFld = $('#passwordFld').val();
    	verifyPasswordFld = $('#verifyPasswordFld').val();


    	console.log('register user');
    	if(passwordFld != verifyPasswordFld) {
    		alert('Passwords do not match')
    	}
    	else {

	        var user = {
	            username: usernameFld,
	            password: passwordFld,
	            firstName: 'Last Name',
	            lastName: 'First Name',
	            role: 'Student'
	        };
	        
    		userService
    			.register(user);
    			/*.then(function(response) {
    				if (response === null) {
			      		alert('Username is already taken.')
					}
    				else {
    					userService
    						.profileRedirect(response.id);
		      		}
				});*/
    	}
    }

})();
