package cn.njust.dao;

import cn.njust.entity.Order;
import cn.njust.entity.Venue;

import java.sql.SQLException;

public class OrderDao extends BaseDao{
    public OrderDao findOrder(Order order)
    {
        try {
            super.connect();
            String sql = "select *from order where user_id=? and order_state=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getUid());
            pstmt.setString(2, order.getState());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Order e = new Order();
                e.setId(rs.getString("venue_id"));
                e.setName(rs.getString("venue_name"));
                e.setPrice(rs.getInt("venue_price"));
                e.setType(rs.getString("venue_type"));
                e.setState(rs.getInt("venue_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }
    public void addVenue(Venue venue) {
        try {
            super.connect();
            String sql = "insert into venue(venue_id,venue_name,venue_type,venue_price,venue_state) values(?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, venue.getId());
            pstmt.setString(2, venue.getName());
            pstmt.setString(3, venue.getType());
            pstmt.setInt(4, venue.getPrice());
            pstmt.setInt(5, venue.getState());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void deleteVenue(Venue venue)
    {
        try {
            super.connect();
            String sql="delete from venue where venue_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,venue.getId());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void updateVenue(Venue venue)
    {
        try {
            super.connect();
            String sql="update venue set venue_state=? and venue_price=? where venue_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,venue.getPrice());
            pstmt.setInt(2,venue.getState());
            pstmt.setString(3,venue.getId());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
}
