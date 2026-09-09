package org.example.e_com.service.impl;

import org.example.e_com.dao.UserDao;
import org.example.e_com.dao.impl.UserDaoImpl;
import org.example.e_com.model.User;
import org.example.e_com.service.UserService;
import org.example.e_com.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userdao;
    public UserServiceImpl(){
        this.userdao = new UserDaoImpl();
    }


    @Override
    public List<User> getAllUser() {
        return userdao.getAllUser();
    }

    @Override
    public User findById(int id) {
        if(id <= 0) return null;
        return userdao.findById(id);
    }


    @Override
    public boolean addUser(User user) {
        if (user == null
                || user.getUsername() == null
                || user.getUsername().trim().isEmpty()
                || user.getPassword() == null
                || user.getPassword().trim().isEmpty()
                || user.getFullname() == null
                || user.getFullname().trim().isEmpty()
                || user.getEmail() == null
                || user.getEmail().trim().isEmpty()
                || user.getPhone() == null
                || user.getPhone().trim().isEmpty()
                || user.getRole() == null
                || user.getRole().trim().isEmpty()) {

            return false;
        }
        //ma hoa mat khau truoc khi luu
        String encodePassword = PasswordUtil.encode(user.getPassword().trim());
        if(encodePassword == null) return false;
        user.setPassword(encodePassword);
        return userdao.insert(user) > 0;
    }

    @Override
    public boolean updateUser(User user) {

        if (user == null
                || user.getUsername() == null
                || user.getUsername().trim().isEmpty()
                || user.getPassword() == null
                || user.getPassword().trim().isEmpty()
                || user.getFullname() == null
                || user.getFullname().trim().isEmpty()
                || user.getEmail() == null
                || user.getEmail().trim().isEmpty()
                || user.getPhone() == null
                || user.getPhone().trim().isEmpty()
                || user.getRole() == null
                || user.getRole().trim().isEmpty()
                || user.getId() <= 0) {

            return false;
        }
        //kiem tra neu mat khau chua duoc ma hoa thi ma hoa roi moi update
        if(!PasswordUtil.isEncoded(user.getPassword())){
            String encodePassword = PasswordUtil.encode(user.getPassword().trim());
            if(encodePassword == null) return false;
            user.setPassword(encodePassword);
        }
        return userdao.update(user) > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        if(id <= 0) return false;
        User user = userdao.findById(id);
        if(user == null) return false;
        return userdao.delete(id) > 0;
    }

    @Override
    public User login(String username, String password) {
        //1 valid input
        if(username == null || username.trim().isEmpty()){

            return null;
        }
        if(password == null || password.trim().isEmpty()){

            return null;
        }
        //tim user theo username
        User user = userdao.findByUsername(username);
        if(user == null){

            return null;
        }
        //kiem tra mat khau da ma hoa
        if(!PasswordUtil.matches(password.trim(), user.getPassword())){
            return null;
        }

        return user;
    }
}
