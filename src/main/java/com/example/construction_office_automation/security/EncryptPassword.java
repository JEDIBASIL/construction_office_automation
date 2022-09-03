package com.example.construction_office_automation.security;

public class EncryptPassword {
    private String encrypted;

    String EncryptPassword(String password){
        encrypted="";
        char[] passwordArray = password.toCharArray();
        for(char passwordChar:passwordArray){
            passwordChar += 200;
           encrypted+=passwordChar;
        }
        return encrypted;
    }
}
