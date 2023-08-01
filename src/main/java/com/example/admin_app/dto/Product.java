package com.example.admin_app.dto;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private Integer id;
    private List<Comment> comments;
    private List<Image> images;

    public Product()
    {

    }

    public Product(Integer id, List<Comment> comments, List<Image> images) {
        this.id = id;
        this.comments = comments;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
