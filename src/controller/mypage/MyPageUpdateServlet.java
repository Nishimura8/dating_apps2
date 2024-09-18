package controller.mypage;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class MyPageUpdateServlet
 */
@WebServlet("/mypage/update")
@MultipartConfig()
public class MyPageUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            User login_user = (User)request.getSession().getAttribute("login_user");
            User u = em.find(User.class, login_user.getId());
            Part part = request.getPart("file");
            String name = this.getFileName(part);
            String path = getServletContext().getRealPath("/uploaded") + "/" + name;

            part.write(path);

            u.setImage("/uploaded/" + name);
            u.setContent(request.getParameter("file"));
            u.setContent(request.getParameter("content"));
            u.setPrefecture(request.getParameter("prefecture"));

            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "プロフィールを更新しました。");

            request.getSession().removeAttribute("user_id");

            response.sendRedirect(request.getContextPath() + "/users/index");
        }
    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }

}
