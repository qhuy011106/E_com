package org.example.e_com.service.impl;

import org.example.e_com.dao.UserDao;
import org.example.e_com.dao.impl.UserDaoImpl;
import org.example.e_com.model.User;
import org.example.e_com.service.UserService;

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
        return userdao.update(user) > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        if(id <= 0) return false;
        User user = userdao.findById(id);
        if(user == null) return false;
        return userdao.delete(id) > 0;
    }
}
