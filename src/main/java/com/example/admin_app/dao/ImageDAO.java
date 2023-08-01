package com.example.admin_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_IMAGE = "DELETE FROM web_shop.image WHERE id=?";


    public static void deleteImage(Integer id) {
        Connection c = null;
        PreparedStatement ps=null;

        try {
            c = connectionPool.checkOut();
            ps =DBUtil.prepareStatement(c, SQL_DELETE_IMAGE, false);
            ps.setInt(1, id);
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }
}
