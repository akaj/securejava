package fi.thl.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.thl.example.model.User;
import fi.thl.example.model.Username;


public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // throws exception if action not in enum
        Action action = Action.valueOf(req.getParameter(
                        RequestParameter.ACTION.toString().toLowerCase())
        );

        resp.setContentType("text/html");
        User bo = UserDAO.getUser(new Username("akaj"));
        resp.getWriter().write("<html><head></head><body>" +
                        bo.getAddress().html() +
                        "</body></html>"
        );
    }
}
