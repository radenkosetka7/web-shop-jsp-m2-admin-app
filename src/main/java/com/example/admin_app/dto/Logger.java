package com.example.admin_app.dto;

import java.io.Serializable;
import java.util.Date;

public class Logger implements Serializable {

    private Integer id;
    private String message;
    private String level;
    private String log;
    private Date date;

    public Logger() {
    }

    public Logger(Integer id, String message, String level, String log, Date date) {
        this.id = id;
        this.message = message;
        this.level = level;
        this.log = log;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
