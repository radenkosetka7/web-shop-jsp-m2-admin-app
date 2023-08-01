package com.example.admin_app.dto;

import com.example.admin_app.dto.enums.Type;

import java.io.Serializable;

public class Attribute implements Serializable {

    private Integer id;
    private String name;
    private Type type;

    public Attribute()
    {
    }

    public Attribute(Integer id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
