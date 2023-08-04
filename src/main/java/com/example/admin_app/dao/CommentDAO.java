package com.example.admin_app.dao;

import com.example.admin_app.dto.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    private static final String SQL_DELETE_COMMENT = "DELETE FROM web_shop.comment WHERE id=?";
    private static final String SQL_SELECT_ALL_BY_PRODUCT_ID = "SELECT * FROM web_shop.comment c where c.product_id=?;";
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public static List<Comment> getAllByProductId(Integer id) {
        List<Comment> comments = new ArrayList<>();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_BY_PRODUCT_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                comments.add(new Comment(rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return comments;
    }

    public static void deleteComment(Integer id) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_DELETE_COMMENT, false);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }
}
