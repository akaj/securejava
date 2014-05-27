package fi.thl.example.model;

public final class Address extends SecureString {
	protected static String pattern = ".*";
	
	public Address(String s) {
		super(s, pattern);
	}
}
