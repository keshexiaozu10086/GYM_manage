package cn.njust.dao;

import cn.njust.entity.User;

public class UserDao extends BaseDao{
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
        return row;
    }
}
