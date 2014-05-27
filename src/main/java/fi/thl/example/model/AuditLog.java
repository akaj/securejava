package fi.thl.example.model;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fi.thl.example.servlet.Action;

public class AuditLog {
	 private final Logger log = LoggerFactory.getLogger(getClass());
     public void log(Date date,String parameter,String encoded,Action action,String modelValue,User user){
    	 log.debug(":PARAMETER="+parameter,":ENCODED="+encoded,":ACTION="+action,":USER="+user);
    	 
     }
     
}
