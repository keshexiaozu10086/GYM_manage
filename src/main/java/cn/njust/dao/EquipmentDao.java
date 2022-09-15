package cn.njust.dao;

import cn.njust.entity.Equipment;

import java.sql.SQLException;

public class EquipmentDao extends BaseDao {
    public Equipment allEquipment(Equipment equipment)
    {
        try {
            super.connect();
            String sql = "select *from equipment";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                equipment.setId(rs.getString("equipment_id"));
                equipment.setName(rs.getString("equipment_name"));
                equipment.setNumber(rs.getInt("equipment_number"));
                equipment.setPrice(rs.getInt("equipment_price"));
                equipment.setType(rs.getString("equipment_type"));
                equipment.setState(rs.getInt("equipment_state"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return equipment;
    }
    public Equipment findEquipment(Equipment equipment) {
        try {
            super.connect();
            String sql = "select *from equipment where equipment_name=? and equipment_type=? and equipment_state=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, equipment.getName());
            pstmt.setString(2, equipment.getType());
            pstmt.setInt(3, equipment.getState());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Equipment e = new Equipment();
                e.setId(rs.getString("equipment_id"));
                e.setName(rs.getString("equipment_name"));
                e.setNumber(rs.getInt("equipment_number"));
                e.setPrice(rs.getInt("equipment_price"));
                e.setType(rs.getString("equipment_type"));
                e.setState(rs.getInt("equipment_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }

    public void addEquipment(Equipment equipment) {
        try {
            super.connect();
            String sql = "insert into equipment(equipment_id,equipment_name,equipment_type,equipment_number,equipment_price,equipment_state) values(?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, equipment.getId());
            pstmt.setString(2, equipment.getName());
            pstmt.setString(3, equipment.getType());
            pstmt.setInt(4, equipment.getNumber());
            pstmt.setInt(5, equipment.getPrice());
            pstmt.setInt(6, equipment.getState());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void deleteEquipment(Equipment equipment)
    {
        try {
            super.connect();
            String sql="delete from equipment where equipment_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,equipment.getId());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void updateEquipement(Equipment equipment)
    {
        try {
            super.connect();
            String sql="update equipment set equipment_number=? and equipment_state=? and equipment_price=? where equipment_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,equipment.getNumber());
            pstmt.setInt(2,equipment.getPrice());
            pstmt.setInt(3,equipment.getState());
            pstmt.setString(4,equipment.getId());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    /*public static void main(String[] args) {
        Equipment e=new Equipment();
        e.setId("11");
        e.setName("羽毛球拍");
        e.setType("羽毛球");
        e.setNumber(10);
        e.setPrice(3);
        e.setState(0);

        dao.addEquipment(e);
        dao.deleteEquipment(e);
        EquipmentDao dao=new EquipmentDao();
        dao.allEquipment(e);
    }*/
}
