package cn.njust.dao;

import cn.njust.entity.User;

import java.sql.SQLException;

public class LoginDao extends BaseDao {
    public User findSingleUser(User user) {
        try {
            super.connect();
            String sql = "select * from user where user_password=? and user_id=? or user_number=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNumber());
            rs = pstmt.executeQuery();
            if (rs.next())               // 如果可以next,代表查找到了这个用户的信息，将结果集中的信息封装到User对象中
            {
                User u = new User();
                u.setId(rs.getString("user_id"));
                u.setName(rs.getString("user_name"));
                u.setNumber(rs.getString("user_number"));
                u.setPassword(rs.getString("user_password"));
               // System.out.println(rs.getString("user_id"));
               // System.out.println(rs.getString("user_name"));        用户信息查询测试
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }

    /*public static void main(String[] args) throws ClassNotFoundException, SQLException {
        User a = new User();
        a.setId("1");
        a.setPassword("2");
        a.setNumber("3");
        a.setName("4");
        LoginDao dao = new LoginDao();
        dao.findUser(a);
    }测试*/
}
