package com.example.admin_app.dto;

import java.io.Serializable;

public class Image implements Serializable {

    private Integer id;

    public Image() {
    }

    public Image(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
