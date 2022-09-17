package cn.njust.utils;

import cn.njust.utils.DBUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by xj on 2020-04-14.
 */
public class DBTest {

    public static void main(String[] args) {
    	// 1.测试单条插入 成功
  // 	testInsertSingleCourses();
    	// 2.测试更新 成功
  //testUpdateCourse();
    	// 3.测试删除 成功
   // 	testDeleteCourse();
    	// 4.测试批量插入 成功
   	//testBatchInsert(10);

    }

    /**
     * 测试批量插入
     */
    private static void testBatchInsert(int count) {
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
          /*  map.put("order_id", "11"+i);
            map.put("rent_id","50"+i);
            map.put("user_id", "0"+i);
            map.put("order_time", new java.sql.Date(System.currentTimeMillis()));
            map.put("return_time",new java.sql.Date(System.currentTimeMillis()));
            map.put("sum",60);
            map.put("order_state",0);*/
            map.put("user_id","11"+i);
            map.put("user_name","30"+i);
            map.put("user_number","100"+i);
            map.put("user_password","10098"+i);
            datas.add(map);
        }
        try {
            long start = System.currentTimeMillis();
            DBUtil.insertAll("user", datas);
            System.out.println("共耗时" + (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试插入
     */
    private static void testInsertSingleCourses() {
        Map<String, Object> map = new HashMap<>();        
        map.put("user_name","un");
        map.put("user_id", "d");
        map.put("user_number", "12");
        map.put("user_password","13456");
        try {
            int count = DBUtil.insert("user", map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试更新
     */
    private static void testUpdateCourse() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "测试更新");

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("user_id", "1");
        try {
            int count = DBUtil.update("user", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试删除
     */
    private static void testDeleteCourse() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("user_id", "1");
        try {
            int count = DBUtil.delete("user", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方式一
     */
    public static void testQuery1() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("salary", "10000");
        try {
            DBUtil.query("emp_test", whereMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方式二
     */
    public static void testQuery2() {
        String where = "job = ?  AND salary = ? ";
        String[] whereArgs = new String[]{"clerk", "3000"};

        try {
            List<Map<String, Object>> list = DBUtil.query("emp_test", where, whereArgs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方式三
     */
    public static void testQuery3() {
        try {
            List<Map<String, Object>> list = DBUtil.query("emp_test", false, null, null, null, null, null, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * SQL注入问题
     */
    public static void query4() {
        String name = "'1' OR '1'='1'";
        String password = "'1' OR '1'='1'";

        String sql = "SELECT * FROM emp_test WHERE name = " + name + " and password = " + password;
        String where = "name = ?  AND password = ? ";
        String[] whereArgs = new String[]{name, password};

        try {
            DBUtil.query(sql);
            DBUtil.query("emp_test", false, null, where, whereArgs, null, null, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
