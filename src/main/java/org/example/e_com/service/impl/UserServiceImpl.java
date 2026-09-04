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

    @Override
    public User login(String username, String password) {
        //1 valid input
        if(username == null || username.trim().isEmpty()){
            System.out.println("Tên đăng nhập không được để trống");
            return null;
        }
        if(password == null || password.trim().isEmpty()){
            System.out.println("VUi lòng điền mật khẩu");
            return null;
        }
        //tim user theo username
        User user = userdao.findByUsername(username);
        if(user == null){
            System.out.println("ten dang nhap khong ton tai");
            return null;
        }
        //kiem tra mat khau, tam thoi so sanhs plaintext
        if(!user.getPassword().equalsIgnoreCase(password)) return null;

        return user;
    }
}
 class TestLogin {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // ===== TEST ĐĂNG NHẬP ĐÚNG =====
        System.out.println("=== TEST 1: ĐĂNG NHẬP ĐÚNG ===");
        User user1 = userService.login("huy", "123456");  // user 'huy' tồn tại
        if (user1 != null) {
            System.out.println("✅ Login thành công: " + user1.getFullname());
            System.out.println("   - Role: " + user1.getRole());
            System.out.println("   - Email: " + user1.getEmail());
        } else {
            System.out.println("❌ Login thất bại");
        }

        // ===== TEST SAI USERNAME =====
        System.out.println("\n=== TEST 2: SAI USERNAME ===");
        User user2 = userService.login("nonexist", "123456");
        if (user2 != null) {
            System.out.println("✅ Login thành công: " + user2.getFullname());
        } else {
            System.out.println("❌ Login thất bại (đúng như mong đợi)");
        }

        // ===== TEST SAI PASSWORD =====
        System.out.println("\n=== TEST 3: SAI PASSWORD ===");
        User user3 = userService.login("huy", "wrongpass");
        if (user3 != null) {
            System.out.println("✅ Login thành công: " + user3.getFullname());
        } else {
            System.out.println("❌ Login thất bại (đúng như mong đợi)");
        }

        // ===== TEST ADMIN =====
        System.out.println("\n=== TEST 4: ADMIN LOGIN ===");
        User user4 = userService.login("admin", "123456");
        if (user4 != null) {
            System.out.println("✅ Login thành công: " + user4.getFullname());
            System.out.println("   - Role: " + user4.getRole());
            System.out.println("   - Đây là ADMIN, có quyền quản trị!");
        } else {
            System.out.println("❌ Login thất bại");
        }
    }
}
