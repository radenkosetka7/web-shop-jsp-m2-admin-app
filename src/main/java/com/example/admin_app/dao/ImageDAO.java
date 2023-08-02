package com.example.admin_app.dao;

import com.example.admin_app.dto.Comment;
import com.example.admin_app.dto.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_IMAGE = "DELETE FROM web_shop.image WHERE id=?";


    private static final String SQL_SELECT_ALL_BY_PRODUCT_ID = "SELECT * FROM webshop_ip.comment i where i.product_id=?;";



        public static List<Image> getAllByProductId(Integer id)
    {
        List<Image> images = new ArrayList<>();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_BY_PRODUCT_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
            {
                images.add(new Image(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return images;
    }

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
