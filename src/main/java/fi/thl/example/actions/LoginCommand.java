package fi.thl.example.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.model.AuditLog;
import fi.thl.example.model.Password;
import fi.thl.example.model.Status;
import fi.thl.example.model.User;
import fi.thl.example.model.Username;
import fi.thl.example.servlet.Action;
import fi.thl.example.servlet.UserDAO;

public class LoginCommand extends BaseCommand {
    boolean loginOk;
    AuditLog log=new AuditLog();
    public LoginCommand(HttpServletRequest req, HttpServletResponse resp) {
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
        if (this.req.getMethod().equals("POST")) {
            User user = UserDAO.getUser(
                    (Username) this.validatedInput.get("username"));
            
            if (user != null && user.getPassword()
                    .equals(this.validatedInput.get("password"))) {
                req.getSession().setAttribute("user", user);
                loginOk = true;
                log.log(req.getParameter("username"),"",Action.login, user, Status.SUCCESS);
            }
           
        }
    }

    @Override
    public void validateParameters() {
        if (this.req.getMethod().equals("POST")) {
            this.validatedInput.put("username",
                    new Username(req.getParameter("username")));
            this.validatedInput.put("password",
                    new Password(req.getParameter("password")));

        }
    }

    @Override
    public String renderHtml() {
        if (this.req.getMethod().equals("POST")) {
            return "<html><body>" +
                    "Login " + (this.loginOk ? " onnistui." : "epäonnistui.") +
                    "<a href=\"SimpleServlet?action=view_profile&username=" +
                    validatedInput.get("username").url() +
                    "\">Eteenpäin</a>" +
                    "</body></html>";
        } else {
            return "<html><body>" +
                    "Login: " +
                    "<form action=\"SimpleServlet?action=login\" method=\"POST\">" +
                    "KT: <input type=\"text\" name=\"username\" />" +
                    "Salasana: <input name=\"password\" type=\"password\" />" +
                    "<input type=\"submit\" />" +
                    "</form>" +
                    "</body></html>";
        }
    }
}
