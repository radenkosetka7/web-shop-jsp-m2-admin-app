package com.example.admin_app.dto.enums;

public enum Type {
    STRING, INT, DOUBLE;

    public static Type getKey(int value) {
        if (value == 0) {
            return STRING;
        } else if (value == 1) {
            return INT;
        } else if (value == 2) {
            return DOUBLE;
        }
        else {
            throw new IllegalArgumentException("An error occured");
        }
    }

    public static Integer getValue(Type type) {
        if (type.equals(Type.STRING)) {
            return 0;
        }else if (type.equals(Type.INT)) {
            return 1;
        } else if (type.equals(Type.DOUBLE)) {
            return 2;
        }
        else {
            throw new IllegalArgumentException("An error occured");
        }
    }
}
