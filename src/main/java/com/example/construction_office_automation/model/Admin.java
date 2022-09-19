package com.example.construction_office_automation.model;

public class Admin {
    private String id;
    private String username;
    private String email;
    private String role;
    private String status;

    private String password;

    public Admin(){}
    public Admin(String username,String email,String id,String role,String status){
        this.username = username;
        this.email = email;
        this.id = id;
        this.role = role;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean validateFields(){
        if(
                getId() != null
                && getUsername() != null
                        &&  getEmail() != null
                        && getRole() != null
                        && getPassword()!= null

        ) return true;

         return false;

    }
}
