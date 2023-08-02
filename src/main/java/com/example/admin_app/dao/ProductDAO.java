package com.example.admin_app.dao;

import com.example.admin_app.dto.Attribute;
import com.example.admin_app.dto.Comment;
import com.example.admin_app.dto.Image;
import com.example.admin_app.dto.Product;
import com.example.admin_app.dto.enums.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO
{
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SQL_DELETE_PRODUCT = "DELETE FROM web_shop.product WHERE id=?";
    private static final String SQL_SELECT_ALL_BY_CATEGORY_ID = "SELECT * FROM webshop_ip.product p where p.category_id=?;";



    public static List<Product> getAllByCategoryId(Integer id)
    {
        List<Product> products = new ArrayList<>();

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
                Product product = new Product(rs.getInt("id"));
                product.setComments(CommentDAO.getAllByProductId(product.getId()));
                product.setImages(ImageDAO.getAllByProductId(product.getId()));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return products;
    }

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
