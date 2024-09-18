package controller.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.Follow;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesIndexServlet
 */
@WebServlet("/rooms/index")
public class RoomIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        User login_user = (User)request.getSession().getAttribute("login_user");
        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        UserDAO dao = new UserDAO();
        List<User> list = dao.getCountryFromName(login_user.getId());
        List<User> id_list = new ArrayList<User>();
        for (int i=0; list.size() > i; i++) {
            if(i==0){
                id_list.add(list.get(i));
            } else {
                id_list.add(list.get(i));
            }
        }

        List<Follow> follows = null;
        List<Follow> followCheck = new ArrayList<Follow>();
        if(id_list.size() != 0) {
             follows = em.createNamedQuery("checkMyRoom", Follow.class)
                                         .setParameter("follow", login_user)
                                         .setParameter("list", id_list)
                                         .setFirstResult(16 * (page - 1))
                                         .setMaxResults(16)
                                         .getResultList();

            List<Integer> roomSave = new ArrayList<Integer>();
            for (Follow follow : follows) {
                int room =  follow.getRoom().getId();
                if(!roomSave.contains(room)) {
                    followCheck.add(follow);
                    roomSave.add(room);
                }
            }
        }

        List<User> users = em.createNamedQuery("getAllUsers", User.class)
                .setFirstResult(16 * (page - 1))
                .setMaxResults(16)
                .getResultList();

        long rooms_count = (long)em.createNamedQuery("getRoomsCount", Long.class)
                                       .getSingleResult();

        em.close();



        request.setAttribute("user", users);
        request.setAttribute("follows", followCheck);
        request.setAttribute("rooms_count", rooms_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/rooms/index.jsp");
        rd.forward(request, response);
    }
}