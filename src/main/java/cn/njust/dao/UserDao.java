package cn.njust.dao;

import cn.njust.entity.Equipment;
import cn.njust.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDao extends BaseDao{
    public User allUser(User user){
        try {
            super.connect();
            String sql = "select *from user";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setNumber(rs.getString("user_number"));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return user;
    }
    public int insert(User user){
        int row=0;
        try {
            super.connect();
            String sql="insert into user(user_id,user_name,user_number,user_password) values(?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getNumber());
            pstmt.setString(4, user.getPassword());
            row=pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
        return row;//
    }
    public void delete(User user){
        try {
            super.connect();
            String sql="delete from user where user_id=? or user_number=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getNumber());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }

   /*public static void main(String[] args) throws ClassNotFoundException, SQLException {
        User a=new User();
        a.setId("1");
        a.setPassword("2");
        a.setNumber("3");
        a.setName("4");
        UserDao dao=new UserDao();
        dao.insert(a);
    }*/

}
