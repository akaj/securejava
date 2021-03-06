package fi.thl.example.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.actions.BaseCommand;
import fi.thl.example.actions.EditProfileCommand;
import fi.thl.example.actions.LoginCommand;
import fi.thl.example.actions.ViewProfileCommand;

public class SimpleServlet extends HttpServlet {
    static Map<Action, Class<? extends BaseCommand>> commands;

    static {
        commands = new HashMap<Action, Class<? extends BaseCommand>>();
        commands.put(Action.login, LoginCommand.class);
        commands.put(Action.edit_profile, EditProfileCommand.class);
        commands.put(Action.view_profile, ViewProfileCommand.class);
    }
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		this.doGet(req, resp);
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // throws exception if action not in enum
        Action action = Action.valueOf(
                req.getParameter(RequestParameter.action.toString()));
        BaseCommand command = null;
        try {
            command = commands.get(action)
                    .getConstructor(HttpServletRequest.class,
                            HttpServletResponse.class).newInstance(req, resp);
        } catch (NoSuchMethodException e) {
            // TODO: handle exception
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String content = command.doRequest();

        resp.setContentType("text/html");
        resp.getWriter().write(content);
    }
}
