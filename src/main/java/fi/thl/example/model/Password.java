package fi.thl.example.model;

public class Password extends SecureString {
	protected final static String pattern = ".*";
	
	public Password(String s) {
		super(s, pattern);
	}
}
