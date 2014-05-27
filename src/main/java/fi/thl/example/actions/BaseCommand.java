package fi.thl.example.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;

import fi.thl.example.model.SecureString;
import fi.thl.example.servlet.SuspiciousOperation;

public abstract class BaseCommand {
	HttpServletRequest req;
	HttpServletResponse resp;
	Map<String, SecureString> validatedInput;
	
	public BaseCommand(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		this.validatedInput = Maps.newHashMap();
	}
	
	public abstract void authenticate();
	public abstract void authorize();
	public abstract void validateParameters();
	public abstract void doAction();
	public abstract String renderHtml();
	
	public String doRequest() {
		try {
		    authenticate();
		    authorize();
		    validateParameters();
	 	    doAction();
	 	    return renderHtml();
		} catch (SuspiciousOperation e) {
			// logger!
			return "<h1>Unhandled error!</h1>";
		}
	}
}
