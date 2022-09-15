package cn.njust.Service.Customer;

import cn.njust.dao.EquipmentDao;
import cn.njust.entity.Equipment;

public class Repair {
    public void repair(Equipment em)
    {
        EquipmentDao dao =new EquipmentDao();
        Equipment eq = dao.findEquipment(em);
        eq.setState(2);//2代表停租

    }

}
