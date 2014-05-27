package fi.thl.example.model;

public final class User {

    private final Username username;
    private final Address address;
    private final Email email;
    private final Password password;

    public User(Username username, Address address, Email email,
            Password password) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public Username getUsername() {
        return username;
    }

    public Address getAddress() {
        return address;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + "]";
	}
    

}
