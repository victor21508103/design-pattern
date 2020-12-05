package com.wy.designpattern.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 每个方法都会调连接jdbc，执行sql语句，关闭jdbc
 */
public class JdbcDao01 {

    //存数据时,数据库在存放项目数据的时候会先用UTF-8格式将数据解码成字节码，然后再将解码后的字节码重新使用GBK编码存放到数据库中。
    //取数据时：在从数据库中取数据的时候，数据库会先将数据库中的数据按GBK格式解码成字节码，然后再将解码后的字节码重新按UTF-8格式编码数据，最后再将数据返回给客户端。
    private static final String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=UTF-8";
    private static final String userName = "root";
    private static final String passwd = "123456";

    public void save(StudentBean stu) {
        String sql = "insert into student(id, name, age) values (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, passwd);
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getId());
            ps.setString(2, stu.getName());
            ps.setInt(3, stu.getAge());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void batchSave(List<StudentBean> stus) {
        String sql = "insert into student(id, name, age) values (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, passwd);
            ps = conn.prepareStatement(sql);
            for (StudentBean stu : stus) {
                ps.setString(1, stu.getId());
                ps.setString(2, stu.getName());
                ps.setInt(3, stu.getAge());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(int id) {
        String sql = "delete from student where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passwd);
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public StudentBean get(int id) {
        String sql = "select * from student where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passwd);
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean stu = new StudentBean(rs.getString("id"), rs.getString("name"), rs.getInt("age"));
                return stu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<StudentBean> list() {
        List<StudentBean> stus = new ArrayList<StudentBean>();
        String sql = "select * from student";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passwd);
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean stu = new StudentBean(rs.getString("id"), rs.getString("name"), rs.getInt("age"));
                stus.add(stu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return stus;
    }

}
