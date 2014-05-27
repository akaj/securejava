package fi.thl.example.model;

public class Email extends SecureString {
	protected final static String pattern = "[\\w\\.]*@[\\w\\.]*";
	
	public Email(String s) {
		super(s, pattern);
	}
}
