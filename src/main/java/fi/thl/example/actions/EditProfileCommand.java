package fi.thl.example.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.model.Password;
import fi.thl.example.model.User;
import fi.thl.example.model.Username;
import fi.thl.example.servlet.RequestParameter;
import fi.thl.example.servlet.UserDAO;

public class EditProfileCommand extends BaseCommand {
    boolean loginOk;

    public EditProfileCommand(HttpServletRequest req,
            HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void authenticate() {
        return;
    }

    @Override
    public void authorize() {
        return;
    }

    @Override
    public void doAction() {
        loginOk = false;
        // Note: vulnerable to timing attacks...
        User user =
                UserDAO.getUser((Username) this.validatedInput.get("username"));
        if (user != null && user.getPassword()
                .equals(this.validatedInput.get("password"))) {
            req.getSession().setAttribute("user", user);
            loginOk = true;
        }
    }

    @Override
    public void validateParameters() {
        this.validatedInput.put(RequestParameter.address.toString(),
                new Username(
                        req.getParameter(RequestParameter.address.toString()))
        );
        this.validatedInput.put(RequestParameter.email.toString(), new Password(
                        req.getParameter(RequestParameter.email.toString()))
        );
    }

    @Override
    public String renderHtml() {
        return "<html><head></head><body>Login " +
                (this.loginOk ? " onnistui." : "epäonnistui.") +
                "</body></html>";
    }

}