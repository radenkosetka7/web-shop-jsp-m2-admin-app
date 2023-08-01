package com.example.admin_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttributeValueDAO
{
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_ATTRIBUTE_VALUE = "DELETE FROM web_shop.attribute_value WHERE attribute_id=?";


    public static void deleteAttributeValue(Integer attributeId) {
        Connection c = null;
        PreparedStatement ps=null;

        try {
            c = connectionPool.checkOut();
            ps =DBUtil.prepareStatement(c, SQL_DELETE_ATTRIBUTE_VALUE, false);
            ps.setInt(1, attributeId);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }
}
