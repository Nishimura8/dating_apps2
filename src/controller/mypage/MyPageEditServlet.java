package controller.mypage;

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
 * Servlet implementation class MyPageEditServlet
 */
@WebServlet("/mypage/edit")
public class MyPageEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
        EntityManager em = DBUtil.createEntityManager();

        User user = em.find(User.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("user", user);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("user_id", user.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/edit.jsp");
        rd.forward(request, response);}
}
