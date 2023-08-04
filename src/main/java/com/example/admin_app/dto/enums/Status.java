package com.example.admin_app.dto.enums;

public enum Status {
    REQUESTED, ACTIVE, BLOCKED;

    public static Status getKey(int value) {
        if (value == 0) {
            return REQUESTED;
        } if (value == 1) {
            return ACTIVE;
        } if (value == 2) {
            return BLOCKED;
        }
        else {
            throw new IllegalArgumentException("An error occured");
        }
    }

    public static Integer getValue(Status status) {
        if (status.equals(Status.REQUESTED)) {
            return 0;
        }else if (status.equals(Status.ACTIVE)) {
            return 1;
        } else if (status.equals(Status.BLOCKED)) {
            return 2;
        }
        else {
            throw new IllegalArgumentException("An error occured");
        }
    }
}
