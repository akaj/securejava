package fi.thl.example.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import fi.thl.example.servlet.SuspiciousOperation;

public abstract class SecureString {
	private Object value;
	protected String pattern;
	
	public SecureString(Object value, String pattern) {
		this.pattern = pattern;
		this.setValue(value);
	}
	
	public void setValue(Object o) {
		this.value = o;
		List<String> errors = this.validate(o);
		if (errors.size() > 0)
			throw new SuspiciousOperation("Value didn't validate");
	}
	
	protected Object getValue() {
		return this.value;
	}
	
	public List<String> validate(Object value) {
		ArrayList<String> errors = new ArrayList<String>();
		if (!this.unsafeStr().matches(pattern)) {
			errors.add("The value didn't match pattern " + pattern);
		}
		return errors;
	}
	
	public String unsafeStr() {
		return this.value.toString();
	}
	
	public String html() {
		return StringEscapeUtils.escapeHtml(this.unsafeStr());
	}
}