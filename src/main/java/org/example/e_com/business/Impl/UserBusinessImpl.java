package org.example.e_com.business.Impl;

import org.example.e_com.business.UserBusiness;
import org.example.e_com.model.User;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.UserServiceImpl;

import java.util.List;

public class UserBusinessImpl implements UserBusiness {
    private final UserService userService;

    public UserBusinessImpl() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public User findById(int id) {
        if(id <= 0) return null;
        return userService.findById(id);
    }

    @Override
    public boolean addUser(User user) {
        if(user == null) return false;
        //username
        if(user.getUsername() == null || user.getUsername().trim().isEmpty()) return false;
        //password
        if(user.getPassword() == null || user.getPassword().trim().isEmpty()) return false;
        //fullname
        if(user.getFullname() == null || user.getFullname().trim().isEmpty()) return false;
        //email
        if(user.getEmail() == null || user.getEmail().trim().isEmpty()) return false;
        //phone
        if(user.getPhone() == null || user.getPhone().trim().isEmpty()) return false;
        //role
        if(user.getRole() == null || user.getRole().trim().isEmpty()){
            user.setRole("CUSTOMER");
        }
        //kiem tra username trung
        for(User item : userService.getAllUser()){
            if(item.getUsername().equalsIgnoreCase(user.getUsername().trim())){
                return false;
            }
        }
        //kiem tra email trung
        for(User item : userService.getAllUser()){
            if(item.getEmail().equalsIgnoreCase(user.getEmail().trim())){
                return false;
            }
        }
        return userService.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        if(user == null || user.getId() <= 0) return false;
        //username
        if(user.getUsername() == null || user.getUsername().trim().isEmpty()) return false;
        //password
        if(user.getPassword() == null || user.getPassword().trim().isEmpty()) return false;
        //fullname
        if(user.getFullname() == null || user.getFullname().trim().isEmpty()) return false;
        //email
        if(user.getEmail() == null || user.getEmail().trim().isEmpty()) return false;
        //phone
        if(user.getPhone() == null || user.getPhone().trim().isEmpty()) return false;
        //role
        if(user.getRole() == null || user.getRole().trim().isEmpty()) return false;
        //user phai ton tai
        User existing =  userService.findById(user.getId());
        if(existing == null) return false;
        // Kiểm tra username trùng User khác
        for (User item : userService.getAllUser()) {

            if (item.getId() != user.getId()
                    && item.getUsername().equalsIgnoreCase(
                    user.getUsername().trim())) {

                return false;
            }
        }

        // Kiểm tra email trùng User khác
        for (User item : userService.getAllUser()) {

            if (item.getId() != user.getId()
                    && item.getEmail().equalsIgnoreCase(
                    user.getEmail().trim())) {

                return false;
            }
        }

        return userService.updateUser(user);


    }

    @Override
    public boolean deleteUser(int id) {
        if (id <= 0) {
            return false;
        }

        // User phải tồn tại
        User user =
                userService.findById(id);

        if (user == null) {
            return false;
        }

        return userService.deleteUser(id);
    }
}
