package cn.njust.Service.Customer;

import cn.njust.entity.*;
import cn.njust.dao.*;

import java.util.List;

public class View {
    public List<Equipment> viewEquipment()
    {
        return EquipmentDao.findAllEquipment();
    }
    public List<Venue> viewVenue()
    {
        return null;
    }
    public List<Order> viewOrder(String uid)
    {
        return null;
    }
    public List<Message> viewMessage(String uid)
    {
        return null;
    }

}
