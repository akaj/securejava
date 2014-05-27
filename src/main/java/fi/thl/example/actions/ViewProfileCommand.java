package fi.thl.example.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Preconditions;

import fi.thl.example.model.User;
import fi.thl.example.model.Username;
import fi.thl.example.servlet.RequestParameter;
import fi.thl.example.servlet.UserDAO;

public class ViewProfileCommand extends BaseCommand {

    public ViewProfileCommand(HttpServletRequest req,
            HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void authenticate() {
        Preconditions.checkNotNull(req.getSession().getAttribute("user"));
    }

    @Override
    public void authorize() {
    }

    @Override
    public void doAction() {
    }

    @Override
    public void validateParameters() {
        this.validatedInput.put(RequestParameter.username.toString(),
                new Username(
                        req.getParameter(RequestParameter.username.toString()))
        );
    }

    @Override
    public String renderHtml() {
        User user = UserDAO.getUser((Username) validatedInput
                .get(RequestParameter.username.toString()));
        return "<html><body>" +
                "<h1>" + user.getUsername().html() + "</h1>" +
                "<p>" + user.getEmail().html() + "</p>" +
                "<address>" + user.getAddress().html() + "</address>" +
                "</body></html>";
    }

}
