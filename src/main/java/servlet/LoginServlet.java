package servlet;

import beens.ActionType;
import beens.UserBeen;
import beens.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.UserAuditServiceImpl;
import services.UserCredentialsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String user = request.getParameter("userId");
        if (user == null || user.isEmpty()) {
            failureResponse(request, response, "please enter username");
            return;
        }
        String pwd = request.getParameter("pwd");
        if (pwd == null || pwd.isEmpty()) {
            failureResponse(request, response, "please enter password");
            return;
        }
        UserBeen credentials = new UserBeen(user, pwd);
        try {
            UserBeen validUser = UserCredentialsServiceImpl.getInstance().validateUserCred(credentials);
            if (validUser != null && validUser.getUserType() != null) {
                UserAuditServiceImpl userAuditService = UserAuditServiceImpl.getInstance();

                userAuditService.saveAuditActionAsync(user, ActionType.LOGGED_IN.name());
                HttpSession session = request.getSession();
                session.setAttribute("userId", user);
                session.setAttribute("userType", validUser.getUserType().name());
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("userId", user);
                Cookie userType = new Cookie("userType", validUser.getUserType().name());
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                response.addCookie(userType);
                request.setAttribute("key1", "request attribute-LoginServlet.java");
                response.setHeader("header1","resp header1-LoginServlet.java");
                response.sendRedirect("Home.jsp");
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either user name or password is wrong.</font>");
                rd.include(request, response);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            failureResponse(request, response, e.getMessage());
        }


    }

    private void failureResponse(HttpServletRequest request, HttpServletResponse response, String userMessage) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
        PrintWriter out = response.getWriter();
        out.println("<font color=red>" + userMessage + " </font>");
        rd.include(request, response);
    }

}
