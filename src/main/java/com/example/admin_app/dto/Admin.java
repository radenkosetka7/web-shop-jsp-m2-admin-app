package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

public class Admin extends User{

    private Boolean loggedIn = false;

    public Admin()
    {
        super();
    }

    public Admin(Integer id, String name, String surname, String city, String username, String avatar, String password, String mail, Status status, Role role)
    {
        super(id,name,surname,city,username,avatar,password,mail,status,role);
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
