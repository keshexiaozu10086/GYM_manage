package cn.njust.dao;

import cn.njust.entity.Order;
import cn.njust.entity.User;
import cn.njust.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao extends BaseDao{

    /**
     *   无需输入，直接查看所有订单信息
     */
    public static List<Order> findAllOrder(){
        String sql = "select * from order";
        List<Map<String, Object>> list = null;
        List<Order> orders=new ArrayList<Order>();
        try {
            list = DBUtil.query(sql);
            for(Map<String, Object> i:list)
            {
                Order j=new Order();
                j.setOid(i.get("order_id").toString());
                j.setUid(i.get("user_id").toString());
                j.setRid(i.get("rent_id").toString());
                j.setReturnTime(i.get("return_time").toString());
                j.setOrderTime(i.get("order_time").toString());
                j.setSum(Integer.parseInt(i.get("sum").toString()));
                j.setState(Integer.parseInt(i.get("order_state").toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        //System.out.println(list.get(0).get("order_state"));
        return orders;//返回订单信息
    }

    /**
     *   输入user_id,根据user_id查看每个用户所有订单的信息
     */
    public static List<Order> findAllOrderByUserid(User user) {
        String sql = "select * from order where user_id=?";
        List<Map<String, Object>> list = null;
        Object[] param={user.getId()};
        List<Order> orders = new ArrayList<Order>();
        try {
            list = DBUtil.executeQuery(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Map<String, Object> i : list) {
            Order j = new Order();
            j.setOid(i.get("order_id").toString());
            j.setUid(i.get("user_id").toString());
            j.setRid(i.get("rent_id").toString());
            j.setReturnTime(i.get("return_time").toString());
            j.setOrderTime(i.get("order_time").toString());
            j.setSum(Integer.parseInt(i.get("sum").toString()));
            j.setState(Integer.parseInt(i.get("order_state").toString()));
        }
        return orders;}

        /**
         *   输入order,根据order_id查看每个用户所有订单的信息
         */

    public Order findOrderByOrderId(Order order)
    {
        try {
            super.connect();
            String sql = "select *from 'order' where order_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOid());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Order e = new Order();
                e.setOid(rs.getString("order_id"));
                e.setUid(rs.getString("user_id"));
                e.setRid(rs.getString("rent_id"));
                e.setOrderTime(rs.getString("order_time"));
                e.setReturnTime(rs.getString("return_time"));
                e.setSum(rs.getInt("sum"));
                e.setState(rs.getInt("order_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return order;
    }



    /**
     *   输入order,根据订单id实现订单信息删除
     */
    public static void deleteOrderById(Order order) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("order_id", order.getOid());//根据订单id寻找进而删除
        try {
            int count = DBUtil.delete("order", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 输入order实现更新，可变更state
     */
    public static void updateOrder(Order order) {
        Map<String, Object> map = new HashMap<>();
        map.put("order_state",order.getState() );//更新状态
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("order_id", order.getOid());//根据订单id寻找
        try {
            int count = DBUtil.update("order", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *   输入Order,实现订单信息插入
     */
    public static void insertOrder(Order order){
            Map<String, Object> map = new HashMap<>();
            map.put("user_id",order.getUid());
            map.put("order_id", order.getOid());
            map.put("rent_id",order.getRid() );
            map.put("order_time",order.getOrderTime() );
            map.put("order_state",order.getState() );
            map.put("return_time",order.getReturnTime());
        try {
            int count = DBUtil.insert("order", map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


  /*
    public Order findOrder(Order order)
    {
        try {
            super.connect();
            String sql = "select *from 'order' where user_id=? and order_state=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getUid());
            pstmt.setInt(2, order.getState());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Order e = new Order();
                e.setOid(rs.getString("order_id"));
                e.setUid(rs.getString("user_id"));
                e.setRid(rs.getString("rent_id"));
                e.setOrderTime(rs.getString("order_time"));
                e.setReturnTime(rs.getString("return_time"));
                e.setSum(rs.getInt("sum"));
                e.setState(rs.getInt("order_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return order;
    }
    public void addOrder(Order order) {
        try {
            super.connect();
            Date = new Date(System.currentTimeMillis());//获得系统时间.
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String nowTime = sdf.format(date);
            String sql = "insert into 'order'(order_id,rent_id,user_id,order_time,return_time,sum,order_state) values(?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, order.getOid());
            pstmt.setString(2, order.getRid());
            pstmt.setString(3, order.getUid());
            pstmt.setString(4,order.getOrderTime());
            pstmt.setString(5,order.getReturntime());
            pstmt.setInt(6,order.getSum());
            pstmt.setInt(7,order.getState());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void deleteOrder(Order order)
    {
        try {
            super.connect();
            String sql="delete from 'order' where order_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,order.getOid());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void updateOrder(Order order)
    {
        try {
            super.connect();
            String sql="update 'order' set order_state=? where order_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,order.getState());
            pstmt.setString(2,order.getOid());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
   public static void main(String[] args) {
        Order o=new Order();
        Date date = new Date(System.currentTimeMillis());//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
        String nowTime = sdf.format(date);
        o.setReturnTime(nowTime);
        o.setSum(156);
        o.setOrderTime(nowTime);
        o.setRid("1");
        o.setUid("2");
        o.setState(3);
        o.setOid("12");
        OrderDao dao=new OrderDao();
        dao.addOrder(o);
    }
*/
}
