package dao;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class Fileselect {
    private String url = "jdbc:mysql://localhost/dating_apps?useSSL=false&useUnicode=true&characterEncoding=utf8";
    private String user = "repuser";
    private String passwd = "reppass";

    public BufferedImage selectImageById(int ID){

        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passwd);

            
            String sql = "SELECT * FROM user WHERE id = ?;";

            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();

            
            while(rs.next()){
                InputStream is = rs.getBinaryStream("image");
                BufferedInputStream bis = new BufferedInputStream(is);
                return ImageIO.read(bis);
            }
            }catch
                (IOException | SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return null;
            }

    }