package com.example.admin_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {


    public static PreparedStatement prepareStatement(Connection c, String sql,
                                                     boolean retGenKeys, Object... values) throws SQLException {
        PreparedStatement ps = c.prepareStatement(sql,
                retGenKeys ? Statement.RETURN_GENERATED_KEYS
                        : Statement.NO_GENERATED_KEYS);

        for (int i = 0; i < values.length; i++)
            ps.setObject(i + 1, values[i]);

        return ps;
    }
}