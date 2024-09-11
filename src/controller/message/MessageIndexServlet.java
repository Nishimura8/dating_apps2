package controller.message;

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

import models.Message;
import models.Room;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsIndexServlet
 */
@WebServlet("/messages/index")
public class MessageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        Room room = em.find(Room.class, Integer.parseInt(request.getParameter("id")));
        List<Message> messages   = em.createNamedQuery("getMessagesAllRooms", Message.class)
                                  .setParameter("room", room)
                                  .getResultList();

        long messages_count = (long)em.createNamedQuery("getMessagesCount", Long.class)
                                     .getSingleResult();

        List<String> posted_day = em.createNamedQuery("getPostedDay", String.class)
                .setParameter("room", room)
                .getResultList();

        List<String> posted_day2 = new ArrayList<String>();
        for(int i=0; i<posted_day.size(); i++){
            posted_day2.add(posted_day.get(i).substring(4,6)+ "月" + posted_day.get(i).substring(6,8)+"日");
        }

        em.close();
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("room", room);
        request.setAttribute("day", posted_day);
        request.setAttribute("day2", posted_day2);
        request.setAttribute("message", messages);
        request.setAttribute("messages_count", messages_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/message/index.jsp");
        rd.forward(request, response);

        request.setAttribute("_token", request.getSession().getId());

    }

}