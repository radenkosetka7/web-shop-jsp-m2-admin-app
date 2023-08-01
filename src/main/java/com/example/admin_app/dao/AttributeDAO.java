package com.example.admin_app.dao;

import com.example.admin_app.dto.Attribute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttributeDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_ATTRIBUTE = "DELETE FROM web_shop.attribute WHERE id=?";
    private static final String SQL_UPDATE_ATTRIBUTE="UPDATE web_shop.user set name=?, type=? where id=?";
    private static final String SQL_ADD_ATTRIBUTE= "INSERT INTO web_shop.user (name,type,category_id) values (?,?,?)";



    public static void deleteAttribute(Integer id) {
        Connection c = null;
        PreparedStatement ps=null;

        try {
            AttributeValueDAO.deleteAttributeValue(id);
            c = connectionPool.checkOut();
            ps =DBUtil.prepareStatement(c, SQL_DELETE_ATTRIBUTE, false);
            ps.setInt(1, id);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }

    public static boolean updateAttribute(Attribute attribute) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_UPDATE_ATTRIBUTE, false);
            ps.setString(1,attribute.getName());
            ps.setString(2,attribute.getType().toString());
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static boolean insertAttribute(Attribute attribute,Integer categoryId) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_ADD_ATTRIBUTE, false);
            ps.setString(1,attribute.getName());
            ps.setString(2,attribute.getType().toString());
            ps.setInt(3,categoryId);
            result=ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

}
