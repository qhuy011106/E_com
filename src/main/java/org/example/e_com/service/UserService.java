package org.example.e_com.service;

import org.example.e_com.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User findById(int id);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
