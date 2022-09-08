package com.example.construction_office_automation.utils;

import com.example.construction_office_automation.model.Admin;

public class Session extends Admin {
    public boolean isLoggedIn(){
        if(getUsername() != null
        && getPassword() != null) return true;

        return false;
    }
}
