package org.example.e_com.business;

import org.example.e_com.model.User;

import java.util.List;

public interface UserBusiness {
    List<User> getAllUser();
    User findById(int id);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
}
