package com.wy.designpattern.jdbc;

import java.sql.*;

public class JdbcUtil02 {

    public static void update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil01.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil01.close(ps, conn);
        }
    }

    public static <T> T query(String sql, IRowMapper<T> rsh, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil01.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet resultSet = ps.executeQuery();
            return rsh.rowMapper(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil01.close(ps, conn);
        }
        return null;
    }


}
