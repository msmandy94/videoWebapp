package servlet;

import beens.UserBeen;
import beens.UserType;
import beens.VideoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.VideosService;
import services.VideosServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class VideosServlet
 */
@WebServlet("/VideosServlet")
public class VideosServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(VideosServlet.class);
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VideosServiceImpl videosService = VideosServiceImpl.getInstance();
        resp.setContentType("text/html");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID =" + cookie.getValue());
                } else if (cookie.getName().equals("userId")) {
                    System.out.println("userId = " + cookie.getValue());
                } else if (cookie.getName().equals("userType")) {
                    System.out.println("userType = " + cookie.getValue());
                }
            }
        }
        // invalidate the session if exists
        HttpSession session = req.getSession(false);
        UserBeen userBeen = new UserBeen();
        try {
            if (session != null && session.getAttribute("userId") != null && session.getAttribute("userType") != null) {
                String userId = (String) session.getAttribute("userId");
                System.out.println("userId=" + userId);
                String userTypeStr = (String) session.getAttribute("userType");
                if (userTypeStr.equals(UserType.CONTRIBUTOR.name())) {
                    userBeen.setUserType(UserType.CONTRIBUTOR);
                } else if (userTypeStr.equals(UserType.VIEWER.name())) {
                    userBeen.setUserType(UserType.VIEWER);
                }
                userBeen.setUserId(userId);
            }

        } catch (Exception e) {
            logger.error("error while fetching videos", e);
            // todo handle exception

            resp.sendRedirect("login.jsp");
        }

        ArrayList<VideoBean> videosForUser = videosService.getVideosForUser(userBeen);
        // todo: send videos in response.

        resp.sendRedirect("Videos.jsp");

    }
}
