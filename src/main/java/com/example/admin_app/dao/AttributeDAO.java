package com.example.admin_app.dao;

import com.example.admin_app.dto.Attribute;
import com.example.admin_app.dto.enums.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttributeDAO {

    private static final String SQL_DELETE_ATTRIBUTE = "DELETE FROM web_shop.attribute WHERE id=?";
    private static final String SQL_UPDATE_ATTRIBUTE = "UPDATE web_shop.attribute set name=?, type=? where id=?";
    private static final String SQL_ADD_ATTRIBUTE = "INSERT INTO web_shop.attribute (name,type,category_id) values (?,?,?)";
    private static final String SQL_SELECT_ALL_BY_CATEGORY_ID = "SELECT * FROM web_shop.attribute a where a.category_id=?;";
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();


    public static List<Attribute> getAllByCategoryId(Integer id)
    {
        List<Attribute> attributes = new ArrayList<>();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_BY_CATEGORY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                attributes.add(new Attribute(rs.getInt("id"),rs.getString("name"), Type.valueOf(rs.getString("type"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return attributes;
    }


    public static void deleteAttribute(Integer id) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            AttributeValueDAO.deleteAttributeValue(id);
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_DELETE_ATTRIBUTE, false);
            ps.setInt(1, id);
            ps.execute();
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
            ps.setString(1, attribute.getName());
            ps.setString(2, attribute.getType().toString());
            result = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static boolean insertAttribute(Attribute attribute, Integer categoryId) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_ADD_ATTRIBUTE, false);
            ps.setString(1, attribute.getName());
            ps.setString(2, attribute.getType().toString());
            ps.setInt(3, categoryId);
            result = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

}
