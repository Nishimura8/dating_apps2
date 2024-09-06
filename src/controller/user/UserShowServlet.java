package controller.user;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/users/show")
public class UserShowServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User login_user = (User)request.getSession().getAttribute("login_user");
        request.setAttribute("_token", request.getSession().getId());
        EntityManager em = DBUtil.createEntityManager();
        User u = em.find(User.class, Integer.parseInt(request.getParameter("id")));
        long follower_count = (long)em.createNamedQuery("getFollowerCount", Long.class)
                .setParameter("follower", u)
                .setParameter("follow", login_user)
                .getSingleResult();
        em.close();
        
        request.setAttribute("user", u);
        request.setAttribute("follower_count", follower_count);  
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/show.jsp");
        rd.forward(request, response);
    }
}
