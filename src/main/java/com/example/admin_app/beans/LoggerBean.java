package com.example.admin_app.beans;

import com.example.admin_app.dao.LoggerDAO;
import com.example.admin_app.dto.Logger;

import java.io.Serializable;
import java.util.List;

public class LoggerBean implements Serializable {

    public LoggerBean() {
    }

    public List<Logger> getAll()
    {
        return LoggerDAO.getAll();
    }
}
