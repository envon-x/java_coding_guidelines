package jcg.guideline01.jcg.guideline02;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

public class PasswordServlet extends HttpServlet {

    protected void nonCompliant_doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        // Validate input (omitted)
        String username = req.getParameter("username");
        char[] password = req.getParameter("password").toCharArray();
        boolean rememberMe = Boolean.valueOf(req.getParameter("rememberme"));
        LoginService loginService = new LoginServiceImpl();
        if (rememberMe) {
            // Go fishing around in the cookie if we are in search of "rememberme"...
            if (req.getCookies()[0] != null && req.getCookies()[0].getValue() != null) {
                String[] value = req.getCookies()[0].getValue().split(";");
                if (!loginService.isUserValid(value[0], value[1].toCharArray())) {
                    // Set error and return
                } else {
                    // Forward to welcome page...
                }
            } else {
                boolean validated = loginService.isUserValid(username, password);
                if (validated) {
                    // FIXME: BobK.  Violation is right here; storing the credentials in the Cookie
                    Cookie loginCookie =
                        new Cookie("rememberme", username + ";" + new String(password));
                    resp.addCookie(loginCookie);
                    // Forward to welcome page...
                } else {
                    // Set error and return
                }
            }
        } else {
            /*
             * If no remember-me functionality selected, proceed with regular authentication.  If it
             * it fails, set error and return.
             */
        }
        Arrays.fill(password, ' ');
    }

    protected void compliant_doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        // Validate input (omitted)
        String username = req.getParameter("username");
        // FIXME: BobK: NPE waiting to happen right here, no?
        char[] password = req.getParameter("password").toCharArray();
        boolean rememberMe = Boolean.valueOf(req.getParameter("rememberme"));
        LoginService loginService = new LoginServiceImpl();
        boolean validated = false;
        if (rememberMe) {
            // Go fishing around in the cookie if we are in search of "rememberme"...
            if (req.getCookies()[0] != null && req.getCookies()[0].getValue() != null) {
                String[] value = req.getCookies()[0].getValue().split(";");
                if (value.length != 2) {
                    // Set error and return
                }
                if (!loginService.mappingExists(value[0], value[1])) {
                    // (username, random) pair is checked and failed.
                    // Set error and return
                }
            } else {
                validated = loginService.isUserValid(username, password);
                if (!validated) {
                    // Set error and return
                }
            }
            String newRandom = loginService.getRandomString();
            // Reset the random every time
            loginService.mapUserForRememberMe(username, newRandom);
            HttpSession session = req.getSession();
            // TODO: BobK:  Wait, what?  We immediately invalidate any Session that exists?
            session.invalidate();
            session = req.getSession(true);
            // Set session timeout to 15 minutes
            session.setMaxInactiveInterval(60 * 15);
            // Store user attribute and a random attribute in session scope
            session.setAttribute("user", loginService.getUserName());
            Cookie loginCookie = new Cookie("rememberme", username + ";" + newRandom);
            resp.addCookie(loginCookie);
            // Forward to welcome page...
        } else {
            /*
             * If no remember-me functionality selected, proceed with regular authentication.  If it
             * it fails, set error and return.
             */
        }
        Arrays.fill(password, ' ');
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
