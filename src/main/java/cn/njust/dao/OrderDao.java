package cn.njust.dao;

import cn.njust.entity.Order;
import cn.njust.entity.Venue;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OrderDao extends BaseDao{
    public Order[] allOrder(Order order)
    {
        try {
            super.connect();
            String sql="select *from 'order'";
            pstmt = conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                order.setOid(rs.getString("order_id"));
                order.setUid(rs.getString("user_id"));
                order.setRid(rs.getString("rent_id"));
                order.setOrdertime(rs.getString("order_time"));
                order.setReturntime(rs.getString("return_time"));
                order.setSum(rs.getInt("sum"));
                order.setState(rs.getInt("order_state"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }
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
                e.setOrdertime(rs.getString("order_time"));
                e.setReturntime(rs.getString("return_time"));
                e.setSum(rs.getInt("sum"));
                e.setState(rs.getInt("order_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }
    public void addOrder(Order order) {
        try {
            super.connect();
           /* Date = new Date(System.currentTimeMillis());//获得系统时间.
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String nowTime = sdf.format(date);*/
            String sql = "insert into 'order'(order_id,rent_id,user_id,order_time,return_time,sum,order_state) values(?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, order.getOid());
            pstmt.setString(2, order.getRid());
            pstmt.setString(3, order.getUid());
            pstmt.setString(4,order.getOrdertime());
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
        o.setReturntime(nowTime);
        o.setSum(156);
        o.setOrdertime(nowTime);
        o.setRid("1");
        o.setUid("2");
        o.setState(3);
        o.setOid("12");
        OrderDao dao=new OrderDao();
        dao.addOrder(o);
    }

}
