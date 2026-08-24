package org.example.e_com.business.Impl;

import org.example.e_com.business.AuthService;
import org.example.e_com.model.User;
import org.example.e_com.service.UserService;
import org.example.e_com.service.impl.UserServiceImpl;

public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private User currentUser;

    public AuthServiceImpl() {
        this.userService = new UserServiceImpl();
        this.currentUser = null;
    }

    @Override
    public boolean register(User user) {

        if (user == null) return false;

        if (user.getUsername() == null
                || user.getUsername().trim().isEmpty()) {
            return false;
        }

        if (user.getPassword() == null
                || user.getPassword().trim().isEmpty()) {
            return false;
        }

        if (user.getFullname() == null
                || user.getFullname().trim().isEmpty()) {
            return false;
        }

        if (user.getPhone() == null
                || user.getPhone().trim().isEmpty()) {
            return false;
        }

        if (user.getEmail() == null
                || user.getEmail().trim().isEmpty()) {
            return false;
        }

        // Người dùng đăng ký luôn là CUSTOMER
        user.setRole("CUSTOMER");

        return userService.addUser(user);
    }

    @Override
    public User login(String username, String password) {

        if (username == null
                || username.trim().isEmpty()
                || password == null
                || password.trim().isEmpty()) {

            return null;
        }

        for (User user : userService.getAllUser()) {

            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {

                currentUser = user;

                return currentUser;
            }
        }

        return null;
    }

    @Override
    public void logout() {
        currentUser = null;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}