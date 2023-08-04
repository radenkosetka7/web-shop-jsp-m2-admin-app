package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

public class CustomUser extends User{

    private String city;
    private String avatar;
    private String mail;
    private Status status;
    private Role role;

    public CustomUser()
    {
        super();
    }

    public CustomUser(Integer id, String name, String surname, String city, String username, String avatar, String password, String mail, Status status, Role role)
    {
        super(id,name,surname,username,password);
        this.city=city;
        this.avatar=avatar;
        this.mail=mail;
        this.status=status;
        this.role=role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
