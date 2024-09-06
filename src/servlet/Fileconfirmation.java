package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Fileselect;

@WebServlet("/Fileconfirmation")
public class Fileconfirmation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        Fileselect dao = new Fileselect();
        BufferedImage BIMG = dao.selectImageById(Integer.parseInt(id));

    
        response.setContentType("image/jpeg");
        OutputStream OS = response.getOutputStream();
        ImageIO.write(BIMG, "jpg", OS);
        OS.flush();
    }
}