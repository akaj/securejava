package fi.thl.example.servlet;

import java.util.HashMap;
import java.util.Map;

import fi.thl.example.model.Address;
import fi.thl.example.model.Email;
import fi.thl.example.model.Password;
import fi.thl.example.model.User;
import fi.thl.example.model.Username;

public class UserDAO {
	private static Map<String, User> profiles;
	
	static {
		profiles = new HashMap<String, User>();
		persist(
			new User(
				new Username("akaj"),
				new Address("Tilkanmäki <script>alert('hello')</script> 123, 00100 Helsinki"),
				new Email("anssi.kaariainen@thl.fi"),
				new Password("salasana")
		));
		persist(
				new User(
						new Username("admin"),
						new Address("Tilkanmäki 123, 00100 Helsinki"),
						new Email("admin@thl.fi"),
						new Password("admin")
				));
	}
	
	public static synchronized void persist(User p) {
		profiles.put(p.getUsername().rawString(), p);
	}
	
	public static synchronized User getUser(Username username) {
        return profiles.get(username.rawString());
	}
}
