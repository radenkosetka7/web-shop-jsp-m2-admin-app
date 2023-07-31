package com.example.admin_app.dto;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    private Integer id;
    private String name;
    private List<Attribute> attributes;
}
