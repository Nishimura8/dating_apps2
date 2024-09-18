package controller.mypage;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.user.UserIndexServlet;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class MyPageShowServlet
 */
@WebServlet("/mypage/show")
public class MyPageShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        User login_user = (User)request.getSession().getAttribute("login_user");
        User u = em.find(User.class, login_user.getId());
        request.setAttribute("login_user", u);

        UserIndexServlet uis = new UserIndexServlet();

        String year = u.getBirth_day().substring(0,4);
        String month = u.getBirth_day().substring(5,7);
        String date = u.getBirth_day().substring(8,10);
        int age = uis.getAge(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(date));
        String.valueOf(age);
        String id = request.getSession().getId();
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("age", age);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/show.jsp");
        rd.forward(request, response);
    }

}
