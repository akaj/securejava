package fi.thl.example.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.model.Username;
import fi.thl.example.servlet.RequestParameter;

public class ViewProfileCommand extends BaseCommand {

    public ViewProfileCommand(HttpServletRequest req,
            HttpServletResponse resp) {
        super(req, resp);
    }

    @Override
    public void authenticate() {
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
        //UserDAO.getUser(validatedInput.get(RequestParameter.username));
        return "<html><head></head><body></body></html>";
    }

}
