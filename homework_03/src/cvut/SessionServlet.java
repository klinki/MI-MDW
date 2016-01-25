package cvut;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by David on 1. 11. 2014.
 */
public class SessionServlet  extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        HttpSession session = req.getSession(true);

        StateEnum currentState = (StateEnum)session.getAttribute("state");

        if (currentState == null) {
            currentState = StateEnum.NEW;
        }

        session.setAttribute("state", currentState);

        resp.getOutputStream().println("Executed get, reading current state.");
        resp.getOutputStream().println("Current state: " + currentState.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        resp.getOutputStream().println("Executed POST. Doing transition.");

        StateEnum currentState = (StateEnum)req.getSession().getAttribute("state");

        switch (currentState) {
            default:
                currentState = StateEnum.NEW;
                resp.getOutputStream().println("Transition: (init) -> NEW");
                break;
            case NEW:
                currentState = StateEnum.PAYMENT;
                resp.getOutputStream().println("Transition: NEW -> PAYMENT");
                break;
            case PAYMENT:
                currentState = StateEnum.PAYMENT;
                resp.getOutputStream().println("Transition: PAYMENT -> COMPLETED");
                break;
            case COMPLETED:
                resp.getOutputStream().println("Transition: NEW -> PAYMENT");
                req.getSession().invalidate();
                break;
        }

        req.getSession().setAttribute("state", currentState);
        resp.getOutputStream().println("Current state: " + currentState.toString());
    }
}
