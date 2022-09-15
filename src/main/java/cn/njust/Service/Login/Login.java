package cn.njust.Service.Login;


import cn.njust.entity.*;

import cn.njust.dao.*;

public class Login {
    public int login(User user)
    {
        LoginDao dao=new LoginDao();
        User userCorrect = dao.findUser(user);
        if(user.getPassword()==userCorrect.getPassword())return 0;//正确登录
        return 1;
    }
}
