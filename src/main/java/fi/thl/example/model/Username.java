package fi.thl.example.model;

public class Username extends SecureString {
	protected static final String pattern = "[\\w_\\d]+";
	
	public Username(String uname) {
		super(uname, pattern);
	}
}