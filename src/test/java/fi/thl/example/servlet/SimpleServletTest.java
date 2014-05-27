package fi.thl.example.servlet;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class SimpleServletTest {

    @Test(expected = NullPointerException.class)
    public void nullActionShouldThrowNullPointerException()
            throws ServletException, IOException {
        SimpleServlet simpleServlet = new SimpleServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        given(req.getParameter("action")).willReturn(null);

        HttpServletResponse res = mock(HttpServletResponse.class);

        // throws npe
        simpleServlet.doGet(req, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void unknownActionShouldThrowIllegalArgumentException()
            throws ServletException, IOException {
        SimpleServlet simpleServlet = new SimpleServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        given(req.getParameter("action"))
                .willReturn("illegal-or-unknown-action");

        HttpServletResponse res = mock(HttpServletResponse.class);

        // throws illegal argument exception
        simpleServlet.doGet(req, res);
    }

}
