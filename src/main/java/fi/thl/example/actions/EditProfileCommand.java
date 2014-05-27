package fi.thl.example.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.model.Address;
import fi.thl.example.model.Email;
import fi.thl.example.model.User;
import fi.thl.example.model.Username;
import fi.thl.example.servlet.RequestParameter;
import fi.thl.example.servlet.SuspiciousOperation;
import fi.thl.example.servlet.UserDAO;

public class EditProfileCommand extends BaseCommand {
	User user;
	
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
        User user =
                UserDAO.getUser((Username) this.validatedInput.get("username"));
        if (user != null && user.getPassword()
                .equals(this.validatedInput.get("password"))) {
            req.getSession().setAttribute("user", user);
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
        return "<html><head></head><body>Muokataan käyttäjää " + this.user.getUsername().html() +
                "</body></html>";
    }

}
