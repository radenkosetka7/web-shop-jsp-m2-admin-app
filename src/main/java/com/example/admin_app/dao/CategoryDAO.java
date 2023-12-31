package com.example.admin_app.dao;

import com.example.admin_app.dto.Attribute;
import com.example.admin_app.dto.Category;
import com.example.admin_app.dto.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static final String SQL_DELETE_CATEGORY = "DELETE FROM web_shop.category WHERE id=?";
    private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT * FROM web_shop.category;";
    private static final String SQL_SELECT_CATEGORY_BY_ID = "SELECT * from web_shop.category c where c.id=?";
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO web_shop.category (name) VALUES (?);";
    private static final String SQL_UPDATE_CATEGORY = "UPDATE web_shop.category k SET name=? WHERE k.id=?;";
    private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    public static int insertCategory(Category category) {
        Connection c = null;
        PreparedStatement ps = null;
        int tempId=0;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_INSERT_CATEGORY, true);
            ps.setString(1, category.getName());
            int resultTemp = ps.executeUpdate();
            if (resultTemp == 1) {
                ResultSet newResultSet=ps.getGeneratedKeys();
                if(newResultSet.next())
                {
                    tempId=newResultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return tempId;
    }

    public static boolean updateCategory(Category category) {
        Connection c = null;
        PreparedStatement ps = null;
        boolean result = false;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_UPDATE_CATEGORY, false);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            result = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
        return result;
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_ALL_CATEGORIES, false);
            rs = ps.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));
                category.setAttributes(AttributeDAO.getAllByCategoryId(category.getId()));
                category.setProducts(ProductDAO.getAllByCategoryId(category.getId()));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return categories;
    }

    public static Category getAllCategoryById(Integer id) {

        Category category = null;
        Connection c = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_SELECT_CATEGORY_BY_ID, false);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
                category.setAttributes(AttributeDAO.getAllByCategoryId(category.getId()));
                category.setProducts(ProductDAO.getAllByCategoryId(category.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }

        return category;
    }

    public static void deleteCategory(Category category) {
        Connection c = null;
        PreparedStatement ps = null;

        try {

            for (Attribute attribute : category.getAttributes()) {
                AttributeDAO.deleteAttribute(attribute.getId());
            }
            for (Product product : category.getProducts()) {
                ProductDAO.deleteProduct(product);
            }
            c = connectionPool.checkOut();
            ps = DBUtil.prepareStatement(c, SQL_DELETE_CATEGORY, false);
            ps.setInt(1, category.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(c);
        }
    }


}
