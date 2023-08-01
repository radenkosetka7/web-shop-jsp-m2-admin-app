package com.example.admin_app.dao;

import com.example.admin_app.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDAO {

    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_CATEGORY = "DELETE FROM web_shop.category WHERE id=?";

    public static void deleteCategory(Category category) {
        Connection c = null;
        PreparedStatement ps=null;

        try {

            for(Attribute attribute:category.getAttributes())
            {
                AttributeDAO.deleteAttribute(attribute.getId());
            }
            for(Product product:category.getProducts())
            {
                ProductDAO.deleteProduct(product);
            }
            c = connectionPool.checkOut();
            ps =DBUtil.prepareStatement(c, SQL_DELETE_CATEGORY, false);
            ps.setInt(1, category.getId());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }


}
