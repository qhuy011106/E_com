package org.example.e_com.util;

import org.example.e_com.dao.UserDao;
import org.example.e_com.dao.impl.UserDaoImpl;
import org.example.e_com.model.User;

import java.util.List;

public class PasswordMigration {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        List<User>  users = userDao.getAllUser();
        int update = 0;
        for(User user : users){
            String password = user.getPassword();
                    if(!PasswordUtil.isEncoded(password)){
                        user.setPassword(PasswordUtil.encode(password));
                        if(userDao.update(user) > 0){
                            update++;
                            System.out.println("cap nhat thanh cong");
                        }else{
                            System.out.println("khong the cap nhat mat khau cho user so " + user.getId());
                        }
                    }
        }
        System.out.println("cap nhat thanh cong cho " + update + " user");

    }
}
