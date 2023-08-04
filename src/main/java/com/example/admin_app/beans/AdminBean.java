package com.example.admin_app.beans;

import com.example.admin_app.dao.UserDAO;
import com.example.admin_app.dto.Admin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminBean {

    private Boolean loggedIn = false;

    public Admin getUser(String username, String password)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Admin admin= UserDAO.getUserByUsername(username);

        if(admin!=null && bCryptPasswordEncoder.matches(password, admin.getPassword()))
        {
            loggedIn=true;
            return admin;
        }
        else
        {
            return null;
        }
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
