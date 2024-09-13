package controller.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class EmployeesIndexServlet
 */
@WebServlet("/index.html")
public class UserIndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<User> users = em.createNamedQuery("getAllUsers", User.class)
                                     .setFirstResult(16 * (page - 1))
                                     .setMaxResults(16)
                                     .getResultList();

        long users_count = (long)em.createNamedQuery("getUsersCount", Long.class)
                                       .getSingleResult();

        em.close();
        String  b = users.get(1).getBirth_day();
        String u= users.get(1).getName();


        List<String> manAgeList = new ArrayList<String>();
        List<String> womanAgeList = new ArrayList<String>();
        for(int i=0; i<users.size(); i++){
            int year = Integer.valueOf(users.get(i).getBirth_day().substring(0,4));
            int month = Integer.valueOf(users.get(i).getBirth_day().substring(5,7));
            int date = Integer.valueOf(users.get(i).getBirth_day().substring(8,10));
            int age = getAge(year, month, date);
            if(users.get(i).getGender() == 0){
                manAgeList.add(String.valueOf(age));
            } else {
                womanAgeList.add(String.valueOf(age));
            }

        }

        request.setAttribute("manAgeList", manAgeList);
        request.setAttribute("womanAgeList", womanAgeList);
        request.setAttribute("users", users);
        request.setAttribute("users_count", users_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/index.jsp");
        rd.forward(request, response);
    }

    public int getAge(int year, int month, int day) {

        // 計算対象の誕生日
        LocalDate birthday = LocalDate.of(year, month, day);

        // 現在の年月日
        LocalDate today = LocalDate.now();

        long duration = ChronoUnit.YEARS.between(birthday, today);

        return (int)duration;
    }
}