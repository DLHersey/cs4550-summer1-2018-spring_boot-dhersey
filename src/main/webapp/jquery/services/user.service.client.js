function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.findUserByUsernme;
    this.updateUser = updateUser;
    this.login = login;
    this.register = register;
    this.logout = logout;
    this.url = '/api/user';
    this.loginUrl = '/api/login';
    this.registerUrl = '/api/register';
    this.logoutUrl = '/api/logout';
    this.profileRedirect = profileRedirect;
    var self = this;

    function login(username, password) {
        return fetch(self.loginUrl, {
            method: 'post',
            credentials: "same-origin",
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response) {
            if(response.status == '500') {
                alert('Login Failed: please check you fields and try again.');
                return;
            } else {
                return response.json();
            }
        });
    }

    function logout() {
        return fetch(self.logoutUrl, {
            method: 'post',
            credentials: "same-origin",
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            exit = "../login/login.template.client.html";
            $(location).attr("href", exit);      
        });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            if(response.status == '500') {
                return null;
            } else {
                return response.json();
            }
        });
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }

    function findUserByUsernme(username) {
        return fetch(self.url + '/' + username)
            .then(function(response){
                return response.json();
            });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        });
    }

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function register(user) {
        return fetch(self.registerUrl, {
            method: 'post',
            credentials: "same-origin",
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            if(response.status == '409') {
                return null;
            } else {
                return response.json();
            }
        })
        .then(function(responseJson) {
            if (responseJson === null) {
                alert('Username is already taken.')
            }
            else {
                profileRedirect(responseJson.id);
            }
        });
    }

    function profileRedirect(userId) {
        // redirect to profile page for new user
        profile = "../profile/profile.template.client.html?userId=" + userId;
        $(location).attr("href", profile);        
    }
}