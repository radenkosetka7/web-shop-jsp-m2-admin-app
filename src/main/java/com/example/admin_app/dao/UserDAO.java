package com.example.admin_app.dao;

import com.example.admin_app.dto.Admin;
import com.example.admin_app.dto.CustomUser;
import com.example.admin_app.dto.enums.Role;
import com.example.admin_app.dto.enums.Status;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM web_shop.user WHERE status=1 and role=0 and username=?";
    private static final String SQL_SELECT_ALL_USERS="SELECT * FROM web_shop.user";
    private static final String SQL_SELECT_USER_BY_ID="SELECT * from web_shop.user where id=?";
    private static final String SQL_UPDATE_USER_STATUS="UPDATE web_shop.user SET status=? where id=?";
    private static final String SQL_UPDATE_USER="UPDATE web_shop.user set name=?, surname=?, city=?, username=?, password=?, avatar=?, role=?, status=?, mail=? where id=?";
    private static final String SQL_ADD_USER= "INSERT INTO web_shop.user (name,surname,city,username,password,avatar,role,status,mail) values (?,?,?,?,?,?,?,?,?)";


    public UserDAO() {
    }


    public static Admin getUserByUsername(String username)
    {
        Admin admin = null;
        Connection c = null;
        ResultSet rs= null;
        try {
            c = connectionPool.checkOut();
            PreparedStatement ps = DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_USERNAME, false);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"),
                        rs.getString("avatar"),rs.getString("password"),rs.getString("mail"), Status.valueOf(rs.getString("status")),Role.valueOf(rs.getString("role")));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return admin;
    }

    public static List<CustomUser> getAll() {
        List<CustomUser> users = new ArrayList<>();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_USERS, false);
            rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new CustomUser(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"),
                        rs.getString("avatar"),rs.getString("password"),rs.getString("mail"), Status.valueOf(rs.getString("status")),Role.valueOf(rs.getString("role"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return users;
    }

    public static CustomUser getById(int id) {
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        CustomUser customUser=null;

        try {
            c = connectionPool.checkOut();
            ps =DBUtil.prepareStatement(c, SQL_SELECT_USER_BY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                customUser=(new CustomUser(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("password"),
                        rs.getString("avatar"),rs.getString("password"),rs.getString("mail"), Status.valueOf(rs.getString("status")),Role.valueOf(rs.getString("role"))));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return customUser;
    }

    public static boolean updateUserStatus(Integer id,Status status) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_UPDATE_USER_STATUS, false);
            ps.setString(1, status.toString());
            ps.setInt(2, id);
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static boolean updateUser(CustomUser customUser) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_UPDATE_USER, false);
            ps.setString(1,customUser.getName());
            ps.setString(2,customUser.getSurname());
            ps.setString(3,customUser.getCity());
            ps.setString(4,customUser.getUsername());
            ps.setString(5,bCryptPasswordEncoder.encode(customUser.getPassword()));
            ps.setString(6,customUser.getAvatar());
            ps.setString(7,customUser.getRole().toString());
            ps.setString(8,customUser.getStatus().toString());
            ps.setString(9,customUser.getMail());
            ps.setInt(10, customUser.getId());
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static boolean insertUser(CustomUser customUser) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_ADD_USER, false);
            ps.setString(1,customUser.getName());
            ps.setString(2,customUser.getSurname());
            ps.setString(3,customUser.getCity());
            ps.setString(4,customUser.getUsername());
            ps.setString(5,bCryptPasswordEncoder.encode(customUser.getPassword()));
            ps.setString(6,customUser.getAvatar());
            ps.setString(7,customUser.getRole().toString());
            ps.setString(8,customUser.getStatus().toString());
            ps.setString(9,customUser.getMail());
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

}
