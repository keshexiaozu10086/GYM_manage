package cn.njust.Service.Customer;

import java.util.Objects;
import java.util.Random;

public class Register {
    public int registerNew(String name,String number,String password1,String password2)
    {
        //返回 1 代表两次密码不一致

        //检验两次密码是否一致
        if(!password1.equals(password2))
            return 1;
        do {
                //随机生成五位数字作为用户ID
                String str = "0123456789";
                Random random = new Random();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < 5; i++) {
                    int num = random.nextInt(10);
                    stringBuffer.append(str.charAt(num));
                }
                String uid = stringBuffer.toString();
            }
        while(false);//查找数据库检验是否有重复ID


        //将数据插入数据库
        return 1;


    }
}
