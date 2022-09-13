package cn.njust.dao;

import cn.njust.entity.User;

import java.sql.SQLException;

public class LoginDao extends BaseDao{
    public User findUser(User user)
    {
        try {
            super.connect();
            String sql = "select * from user where user_id=? and user_password=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,user.getId());
                pstmt.setString(2, user.getPassword());
                rs=pstmt.executeQuery();
                if(rs.next())               // 如果可以next,代表查找到了这个用户的信息，将结果集中的信息封装到User对象中
                {
                    User u=new User();
                    u.setId(rs.getString("user_id"));
                    u.setName(rs.getString("user_name"));
                    u.setNumber(rs.getString("user_number"));
                    u.setPassword(rs.getString("user_password"));
                    return u;
                }
        }
             catch (SQLException e) {
                e.printStackTrace();
            }
        finally{
            super.closeAll();
        }
        return null;
    }
}
