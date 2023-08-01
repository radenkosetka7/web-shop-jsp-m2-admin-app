package com.example.admin_app.dto;

import java.io.Serializable;

public class Comment implements Serializable {

    private Integer id;

    public Comment()
    {
    }

    public Comment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
