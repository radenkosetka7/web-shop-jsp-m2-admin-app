package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

public class Admin extends User{


    public Admin()
    {
        super();
    }

    public Admin(Integer id, String name, String surname, String username,String password)
    {
        super(id,name,surname,username,password);
    }

}
