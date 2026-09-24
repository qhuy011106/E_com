package org.example.e_com.util;

import org.example.e_com.model.User;

public class AuthUtil {
    private static User currentUser = null;
    public static void setCurrentUser(User user){
        currentUser = user;
    }
    public static User getCurrentUser(){
        return currentUser;
    }
    public static boolean isLoggedIn(){
        return currentUser != null;
    }
    //kiem tra quyen
    public static boolean isAdmin(){
        if(currentUser == null) return false;
        if(currentUser.getRole() == null) return false;
        return "ADMIN".equalsIgnoreCase(currentUser.getRole().trim());
    }
    public static boolean isCustomer(){
        if(currentUser == null) return false;
        if(currentUser.getRole() == null) return false;
        return "CUSTOMER".equalsIgnoreCase(currentUser.getRole().trim());

    }
    //logout
    public static void logout(){
        if(currentUser != null) currentUser = null;
    }

    public static boolean requireLogin(){
        if(!isLoggedIn()) return false;
        return true;
    }
    public static boolean requireAdmin(){
        if(!isLoggedIn()) return false;
        if(!isAdmin()) return false;
        return true;
    }
    public static boolean requireCustomer(){
        if(!isLoggedIn()) return false;
        if(!isCustomer()) return false;
        return true;
    }

}
