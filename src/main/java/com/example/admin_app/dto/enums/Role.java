package com.example.admin_app.dto.enums;

public enum Role {

    ADMIN,SUPPORT,CUSTOM_USER;


    public static Role getKey(int value) {
        if (value == 0) {
            return ADMIN;
        } if (value == 1) {
            return SUPPORT;
        } if (value == 2) {
            return CUSTOM_USER;
        }
        else {
            throw new IllegalArgumentException("An error occured");
        }
    }

    public static Integer getValue(Role role) {
        if (role.equals(Role.ADMIN)) {
            return 0;
        }else if (role.equals(Role.SUPPORT)) {
            return 1;
        } else if (role.equals(Role.CUSTOM_USER)) {
            return 2;
        }
        else {
            throw new IllegalArgumentException("An error occured");
        }
    }
}
