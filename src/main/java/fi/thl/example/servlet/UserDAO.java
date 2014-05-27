package fi.thl.example.servlet;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private static List<Profile> profiles;
	
	static {
		profiles = new ArrayList<Profile>();
	}
	
	public void addProfile(Profile p) {
		profiles.add(p);
	}
}
