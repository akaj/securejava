package fi.thl.example.model;

import org.mockito.internal.matchers.Equals;

public class Password extends SecureString {
	protected final static String pattern = ".*";
	
	public Password(String s) {
		super(s, pattern);
	}
}
