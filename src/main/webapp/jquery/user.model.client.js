function User(username, password, firstName, lastName, role) {
  this.username = username;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  this.role = role;

  this.setUsername = setUsername;
  this.getUsername = getUsername;
  this.setPassword = setPassword;
  this.getPassword = getPassword;
  this.setFirstName = setFirstName;
  this.getFirstName = getFirstName;
  this.setLastName = setLastName;
  this.getLastName = getLastName;
  this.setRole = setRole;
  this.getRole = getRole;

  function setUsername(username) {
    this.username = username;
  }
  function getUsername() {
    return this.username;
  }

  function setPassword(username) {
    this.username = username;
  }
  function getPassword() {
    return this.username;
  }

  function setFirstName(username) {
    this.username = username;
  }
  function getFirstName() {
    return this.username;
  }

  function setLastName(username) {
    this.username = username;
  }
  function getLastName() {
    return this.username;
  }

  function setRole(username) {
    this.username = username;
  }
  function getRole() {
    return this.username;
  }
}
