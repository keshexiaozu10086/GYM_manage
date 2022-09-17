package cn.njust.dao;

import cn.njust.entity.Equipment;
import cn.njust.entity.User;
import cn.njust.utils.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao extends BaseDao{
    /**
     * 输入用户id实现查询用户,返回用户信息
     */
    public static List<Map<String, Object>> findAllUser(String user_id){
        String sql = "select * from user where user_id = ?";
        Object[] params = { user_id };
        List<Map<String, Object>> list = null;
        try {
            list = DBUtil.executeQuery(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *   输入user,实现该用户信息插入
     */
    public static void insertSingleUser(User user) {

        Map<String, Object> map = new HashMap<>();
        map.put("user_name",user.getName());
        map.put("user_id", user.getId());
        map.put("user_number",user.getNumber() );
        map.put("user_password",user.getPassword());
        try {
            int count = DBUtil.insert("user", map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 输入用户id实现该用户信息删除
     */
    public static void deleteUser(User user) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("user_id", user.getId());
        try {
            int count = DBUtil.delete("user", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输入用户id实现该用户更新，可变更密码
     */
    public static void updateUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_password",user.getPassword() );

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("user_id", user.getId());
        try {
            int count = DBUtil.update("user", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   /* public static void main(String[] args) {
        // 1.测试单条插入 成功
         //	insertSingleUser();
        // 2.测试更新 成功
       // updateUser();
        // 3.测试删除 成功
        // deleteUser();


    }*/
}
