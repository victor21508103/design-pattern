package com.wy.designpattern.jdbc;

import java.sql.*;

public class JdbcUtil01 {

    private static final String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&charactorEncoding=UTF-8";
    private static String userName = "root";
    private static String passwd = "123456";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, passwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void close(PreparedStatement ps, Connection con) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(con != null){
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
