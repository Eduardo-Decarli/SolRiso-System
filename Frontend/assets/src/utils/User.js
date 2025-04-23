class user {
    name;
    email;
    password;
    role;

    constructor(name, email, password, role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    constructor(name, password) {
        this.name = name;
        this.password = password;
    }
}