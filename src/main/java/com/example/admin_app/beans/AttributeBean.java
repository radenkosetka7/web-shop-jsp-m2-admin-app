package com.example.admin_app.beans;

import com.example.admin_app.dao.AttributeDAO;
import com.example.admin_app.dto.Attribute;

import java.io.Serializable;

public class AttributeBean implements Serializable {

    public AttributeBean() {

    }

    public void deleteAttribute(Integer id)
    {
        AttributeDAO.deleteAttribute(id);
    }

    public boolean updateAttribute(Attribute attribute)
    {
        return AttributeDAO.updateAttribute(attribute);
    }

    public boolean insertAttribute(Attribute attribute,Integer id)
    {
        return AttributeDAO.insertAttribute(attribute,id);
    }
}
