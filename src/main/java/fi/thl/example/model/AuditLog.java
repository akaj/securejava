package fi.thl.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.thl.example.servlet.Action;

public class AuditLog {
	 
	 private final Logger log = LoggerFactory.getLogger(getClass());
     public void log(String parameter,String encoded,Action action,User user,Status status){
    	 String msg = "PARAMETER="+parameter + ":ENCODED="+encoded + ":ACTION="+action + ":USER="+user + ":STATUS"+status;
    	 System.out.println(msg);
    	 log.debug("PARAMETER="+parameter,":ENCODED="+encoded,":ACTION="+action,":USER="+user,":STATUS"+status);	 
     }
     
}
