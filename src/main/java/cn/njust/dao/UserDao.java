package cn.njust.dao;


import cn.njust.entity.User;
import cn.njust.utils.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    /**
     * 查找用户,返回用户信息数组
     */
    public static List<User> findAllUser(){
        String sql = "select * from user";
        List<Map<String, Object>> list = null;
        List<User> users=new ArrayList<User>();
        try {
            list = DBUtil.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Map<String, Object> i:list)
        {
            User j=new User();
            j.setId(i.get("user_id").toString());
            j.setPassword(i.get("user_password").toString());
            j.setName(i.get("user_name").toString());
            j.setNumber(i.get("user_number").toString());

        }
        //System.out.println(list.get(0).get("user_state"));
        return users;
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
     * 输入用户实现该用户信息删除
     */
    public static void deleteUser(User user) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("user_id", user.getId());//根据id寻找进而删除
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
        map.put("user_password",user.getPassword() );//更新密码
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("user_id", user.getId());//根据id寻找
        try {
            int count = DBUtil.update("user", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public static void main(String[] args) {
        // 1.测试单条插入 成功
       User a=new User();
       a.setId("0");
       insertSingleUser(a);
        // 2.测试更新 成功
       // updateUser();
        // 3.测试删除 成功
        // deleteUser();


    }
}
