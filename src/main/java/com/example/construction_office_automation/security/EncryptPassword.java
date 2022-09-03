package com.example.construction_office_automation.security;

public class EncryptPassword {
    private String encrypted;

    public String EncryptPassword(String password){
        encrypted="";
        if(password != null){
            char[] passwordArray = password.toCharArray();
            for(char passwordChar:passwordArray){
                passwordChar += 200;
                encrypted+=passwordChar;
            }
            return encrypted;
        }
        return null;
    }
}
