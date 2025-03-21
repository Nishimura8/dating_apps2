package controller.message;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import models.User;
import models.validators.MessageValidator;
import utils.DBUtil;

/**vlet implementation class ReportsCreateServlet
 */
@WebServlet("/message/create")
public class MessageCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Message m = new Message();
            Room r = em.find(Room.class, Integer.parseInt(request.getParameter("room_id")));

            m.setUser((User)request.getSession().getAttribute("login_user"));
            m.setRoom(r);
            m.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(currentTime);

            Calendar cl = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String str = sdf.format(cl.getTime());
            m.setPostedDay(str);

            Date dateObj = new Date();
            SimpleDateFormat format = new SimpleDateFormat( "HH:mm" );
            // 日時情報を指定フォーマットの文字列で取得
            String time = format.format( dateObj );
            m.setPostedTime(time);

            String weekDay = getDayOfTheWeekShort();
            m.setPostedWeek(weekDay);

            List<String> errors = MessageValidator.validate(m);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("message", m);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/rooms/index.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "メッセージを送信しました。");

                response.sendRedirect(request.getContextPath() + "/messages/index?id="+ r.getId());
            }
        }
    }

    /**
     * 現在の曜日を返します。
     * ※曜日は省略します。
     * @return  現在の曜日
     */
    public static String getDayOfTheWeekShort() {
        Calendar cal = Calendar.getInstance();
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY: return "日";
            case Calendar.MONDAY: return "月";
            case Calendar.TUESDAY: return "火";
            case Calendar.WEDNESDAY: return "水";
            case Calendar.THURSDAY: return "木";
            case Calendar.FRIDAY: return "金";
            case Calendar.SATURDAY: return "土";
        }
        throw new IllegalStateException();
    }

}
