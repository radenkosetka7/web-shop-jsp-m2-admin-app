package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

import java.io.Serializable;

public abstract class User implements Serializable {

    private Integer id;
    private String name;
    private String surname;
    private String city;
    private String username;
    private String avatar;
    private String password;
    private String mail;
    private Status status;
    private Role role;

    public User() {
    }

    public User(Integer id, String name, String surname, String city, String username, String avatar, String password, String mail, Status status, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.username = username;
        this.avatar = avatar;
        this.password = password;
        this.mail = mail;
        this.status = status;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
