package cn.njust.dao;

import cn.njust.entity.Equipment;
import cn.njust.entity.User;
import cn.njust.entity.Venue;

import java.sql.SQLException;

public class VenueDao extends BaseDao{
    public Venue[] allVenue(){
        try {
            Venue[] venues=new Venue[100];
            super.connect();
            String sql = "select *from venue";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Venue venue=new Venue();
                venue.setId(rs.getString("venue_id"));
                venue.setName(rs.getString("venue_name"));
                venue.setType(rs.getString("venue_type"));
                venue.setPrice(rs.getInt("venue_price"));
                venue.setState(rs.getInt("venue_state"));


            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }
    public VenueDao findVenue(Venue venue)
    {
        try {
            super.connect();
            String sql = "select *from venue where venue_name=? and venue_type=? and venue_state=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getType());
            pstmt.setInt(3, venue.getState());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Venue e = new Venue();
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
