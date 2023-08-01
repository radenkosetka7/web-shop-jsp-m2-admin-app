package com.example.admin_app.dao;

import com.example.admin_app.dto.Comment;
import com.example.admin_app.dto.Image;
import com.example.admin_app.dto.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO
{
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_PRODUCT = "DELETE FROM web_shop.product WHERE id=?";

    public static void deleteProduct(Product product) {
        Connection c = null;
        PreparedStatement ps=null;

        try {
            for(Image image:product.getImages())
            {
                ImageDAO.deleteImage(image.getId());
            }
            for(Comment comment:product.getComments())
            {
                CommentDAO.deleteComment(comment.getId());
            }
            c = connectionPool.checkOut();
            ps =DBUtil.prepareStatement(c, SQL_DELETE_PRODUCT, false);
            ps.setInt(1, product.getId());
            ps.executeQuery();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }

}
