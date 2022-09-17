package cn.njust.Service.Customer;
import cn.njust.entity.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Rent {
    public Equipment[] searchEquipment(){
        return null;
    }
    public Venue[] searchVenue(){
        return null;
    }
    public Order equipmentOrder(String uid,String type,int number)
    {
        //订单号命名：日期+三位随机数
        //获得当前日期
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyMMdd");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(dateFormat.format(date));
        //生成三位随机数
        do {
            //随机生成五位数字作为用户ID
            String str = "0123456789";
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int num = random.nextInt(10);
                stringBuffer.append(str.charAt(num));
            }
            String oid = stringBuffer.toString();
        }
        while(false);//查找数据库检验是否有重复订单号

        System.out.println(dateFormat.format(date).toString());
        return null;
    }
    public Order venueOrder(String type,int time)
    {
        return null;
    }

    public static void main(String[] args){
        Rent t=new Rent();
        //t.equipmentOrder("0","0",0);
    }
}
