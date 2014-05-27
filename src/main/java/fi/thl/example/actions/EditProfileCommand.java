package fi.thl.example.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.model.Address;
import fi.thl.example.model.AuditLog;
import fi.thl.example.model.Email;
import fi.thl.example.model.Status;
import fi.thl.example.model.User;
import fi.thl.example.model.Username;
import fi.thl.example.servlet.Action;
import fi.thl.example.servlet.RequestParameter;
import fi.thl.example.servlet.SuspiciousOperation;
import fi.thl.example.servlet.UserDAO;

public class EditProfileCommand extends BaseCommand {
	User user;
	AuditLog log=new AuditLog();
	
    public EditProfileCommand(HttpServletRequest req,
            HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void authenticate() {
        if (this.req.getSession().getAttribute("user") == null) {
        	throw new SuspiciousOperation("Tried to edit user without login");
        }
    }

    @Override
    public void authorize() {
        User u = (User)this.req.getSession().getAttribute("user");
        if (!u.getUsername().rawString().equals(user.getUsername().rawString())) {
        	throw new SuspiciousOperation("Unauthorized access");
        }
    }

    @Override
    public void doAction() {
        // Note: vulnerable to timing attacks...
        if (req.getMethod().equals("POST")) {
        	user = new User(
        			user.getUsername(),
        			(Address)validatedInput.get("address"),
        			(Email)validatedInput.get("email"),
        			user.getPassword()
        			);
        	UserDAO.persist(user);
        	log.log(req.getParameter("username"),"",Action.edit_profile, user, Status.SUCCESS);
        }
    }

    @Override
    public void validateParameters() {
        this.validatedInput.put(RequestParameter.username.toString(),
                new Username(
                        req.getParameter(RequestParameter.username.toString()))
        );
        this.user = UserDAO.getUser((Username)this.validatedInput.get("username"));
        if (this.user == null) {
        	throw new SuspiciousOperation("Tried to edit nonexisting user");
        }
        if (req.getMethod().equals("POST")) {
        	this.validatedInput.put(RequestParameter.address.toString(),
                    new Address(
                            req.getParameter(RequestParameter.address.toString()))
            );
        	this.validatedInput.put(RequestParameter.email.toString(),
                    new Email(
                            req.getParameter(RequestParameter.email.toString()))
            );
        	
        }
    }

    @Override
    public String renderHtml() {
    	  if (this.req.getMethod().equals("POST")) {
              return "<html><body>" +
                      "Edit onnistui." +
                      "<a href=\"SimpleServlet?action=view_profile&username=" +
                      validatedInput.get("username").url() +
                      "\">Eteenpäin</a>" +
                      "</body></html>";
          } else {
              return "<html><body>" +
                      "Muokkaa " +
                      "<form action=\"SimpleServlet?action=edit_profile&username=" + this.user.getUsername().url() + "\" method=\"POST\">" +
                      "Address: <input type=\"text\" name=\"address\" value=\"" + this.user.getAddress().html() + "\" />" +
                      "Email: <input name=\"email\" type=\"text\" value=\"" + this.user.getEmail().html() + "\" />" +
                      "<input type=\"submit\" />" +
                      "</form>" +
                      "</body></html>";
          }
    }

}
