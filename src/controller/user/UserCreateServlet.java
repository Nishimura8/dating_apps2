package controller.user;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/users/create")
@MultipartConfig(maxFileSize = 1048576)


public class UserCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            User u = new User();

            u.setName(request.getParameter("name"));

            u.setContent(request.getParameter("content"));

            Part part = request.getPart("image");
            String name = this.getFileName(part);
            String path = getServletContext().getRealPath("/uploaded") + "/" + name;
            System.out.println("★★★★★★★★★★★★ path = " + path + " ★★★★★★★★★★★★");
            part.write(path);
            u.setImage("/uploaded/" + name);

            u.setAge(Integer.parseInt(request.getParameter("age")));

            u.setPrefecture(request.getParameter("prefecture"));

            u.setGender(Integer.parseInt(request.getParameter("gender")));

            u.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("pepper")
                            )
                    );

            List<String> errors = UserValidator.validate(u, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("user", u);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(u);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath() + "/users/");
            }
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
