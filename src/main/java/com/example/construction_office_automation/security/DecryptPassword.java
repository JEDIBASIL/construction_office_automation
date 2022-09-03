package com.example.construction_office_automation.security;

public class DecryptPassword {

    private String decrypted;

    String DecryptPassword(String password){
        decrypted="";
        char[] passwordArray = password.toCharArray();
        for(char passwordChar:passwordArray){
            passwordChar -= 200;
            decrypted+=passwordChar;
        }
        return decrypted;
    }

}
