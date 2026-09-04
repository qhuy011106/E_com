package org.example.e_com.dto;

public class UserInfoDTO {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String role;

    // Constructor không tham số
    public UserInfoDTO() {}

    // Constructor có tham số
    public UserInfoDTO(int id, String username, String fullName, String email, String phone, String role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleLabel() {
        return "ROLE_" + role;
    }

    @Override
    public String toString() {
        return String.format(
                "👤 %s (ID: %d)\n" +
                        "   📧 %s\n" +
                        "   📱 %s\n" +
                        "   🔑 %s",
                fullName, id, email, phone, getRoleLabel()
        );
    }
}