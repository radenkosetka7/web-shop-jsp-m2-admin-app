package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;

import java.io.Serializable;

public class User implements Serializable {

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
}
