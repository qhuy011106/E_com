package org.example.e_com.business;

import org.example.e_com.model.User;

public interface AuthService {
    boolean register(User user);
    User login(String username, String password);
    void logout();
    User getCurrentUser();

}
