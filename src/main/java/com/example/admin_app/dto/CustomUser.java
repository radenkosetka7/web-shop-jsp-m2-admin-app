package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

public class CustomUser extends User{

    private String city;
    private String mail;
    private Status status;

    public CustomUser()
    {
        super();
    }

    public CustomUser(Integer id, String name, String surname, String city, String username,String password, String mail, Status status)
    {
        super(id,name,surname,username,password);
        this.city=city;
        this.mail=mail;
        this.status=status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
