package com.example.construction_office_automation.model;

public class Admin {
    private String id;
    private String username;
    private String email;
    private String role;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean validateFields(){
        if(
                getUsername() != null
                        &&  getEmail() != null
                        && getRole() != null
                        && getPassword()!= null

        ) return true;

         return false;

    }
}
