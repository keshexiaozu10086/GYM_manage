package cn.njust.dao;

import cn.njust.entity.Equipment;

import cn.njust.utils.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentDao extends BaseDao {

    /**
     * 输入器材实现查询器材,返回器材信息
     */
    public static List<Map<String, Object>> findAllEquipment(Equipment equipment){
        String sql = "select * from equipment where equipment_id = ?";
        Object[] params = { equipment.getId() };//根据器材id查询
        List<Map<String, Object>> list = null;
        try {
            list = DBUtil.executeQuery(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;//返回器材信息List
    }

    /**
     *   输入equipment,实现器材信息删除
     */
    public static void deleteEquipmentById(Equipment equipment) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("equipment_id", equipment.getId());//根据id寻找进而删除
        try {
            int count = DBUtil.delete("equipment", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 输入equipment实现更新，可变更state,price,number
     */
    public static void updateEquipment(Equipment equipment) {
        Map<String, Object> map = new HashMap<>();
        map.put("equipment_number",equipment.getNumber() );//更新数量
        map.put("equipment_price",equipment.getPrice() );//更新价格
        map.put("equipment_state",equipment.getState() );//更新状态
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("equipment_id", equipment.getId());//根据id寻找
        try {
            int count = DBUtil.update("equipment", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     *   输入Equipment及需要添加的数量,实现器材信息插入
     */
    public static void insertEquipment(int count,Equipment equipment) {

        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("equipment_name",equipment.getName());
            map.put("equipment_id", equipment.getId());
            map.put("equipment_number",equipment.getNumber() );
            map.put("equipment_price",equipment.getPrice() );
            map.put("equipment_state",equipment.getState() );
            map.put("equipment_type",equipment.getType() );
            datas.add(map);
        }
        try {
            DBUtil.insertAll("equipment", datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   /* public Equipment findEquipment(Equipment equipment) {
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
    public static void main(String[] args) {
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
