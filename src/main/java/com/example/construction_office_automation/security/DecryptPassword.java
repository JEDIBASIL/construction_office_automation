package com.example.construction_office_automation.security;

public class DecryptPassword {

    private String decrypted;

    public String DecryptPassword(String password){
        decrypted="";
        if(password != null){
            char[] passwordArray = password.toCharArray();
            for(char passwordChar:passwordArray){
                passwordChar -= 200;
                decrypted+=passwordChar;
            }
            return decrypted;
        }
        return null;
    }

}
