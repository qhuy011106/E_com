package org.example.e_com.dao;

import org.example.e_com.model.User;

import java.util.List;

import org.example.e_com.model.User;

import java.util.List;
public interface UserDao {


        List<User> getAllUser();

        User findById(int id);


        User findByUsername(String username);


        int insert(User user);


        int update(User user);


        int delete(int id);

}
