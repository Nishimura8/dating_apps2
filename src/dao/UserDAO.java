package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import util.DatabaseManager;

public class UserDAO {
    // データベース接続と結果取得のための変数
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<User> getCountryFromName(int id) {
        // メソッドの結果として返すリスト
        List<User> results = new ArrayList<User>();

        try {
            // 1,2. ドライバを読み込み、DBに接続
            Connection con = DatabaseManager.getConnection();

            // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            String sql = "select f2.follow_id from follow  as f1"
                    + " inner join follow f2  on f1.follow_id = f2.follower_id "
                    + "and f2.follow_id = f1.follower_id  where f1.follow_id = ?";
            pstmt = con.prepareStatement(sql);


            // 4, 5. Select文の実行と結果を格納／代入
            pstmt.setString(1, String.valueOf(id));
            rs = pstmt.executeQuery();

            // 6. 結果を表示する
            int i;
            while (rs.next()) {
                // 1件ずつCountryオブジェクトを生成して結果を詰める
                i = rs.getInt("f2.follow_id");
                User user = new User();
                user.setId(rs.getInt("f2.follow_id"));

                // リストに追加
                results.add(user);

                System.out.print(i);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if( rs != null ){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( pstmt != null ){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseManager.close();
        }
        return results;
    }
}