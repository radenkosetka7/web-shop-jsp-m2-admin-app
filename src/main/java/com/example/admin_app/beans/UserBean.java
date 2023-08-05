package com.example.admin_app.beans;

import com.example.admin_app.dao.UserDAO;
import com.example.admin_app.dto.Admin;
import com.example.admin_app.dto.CustomUser;
import com.example.admin_app.dto.User;
import com.example.admin_app.dto.enums.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {


    private CustomUser customUser;
    public UserBean()
    {
    }


    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public List<CustomUser> getAllUsers()
    {
        return UserDAO.getAll();
    }

    public CustomUser getUserById(Integer id)
    {
        return UserDAO.getById(id);
    }

    public boolean updateUserStatus(Integer id, Integer status)
    {
        return UserDAO.updateUserStatus(id,status);
    }

    public boolean updateUser(CustomUser customUser)
    {
        return UserDAO.updateUser(customUser);
    }

    public boolean insertUser(CustomUser customUser)
    {
        return UserDAO.insertUser(customUser);
    }
}
