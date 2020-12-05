package com.wy.designpattern.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将加载驱动类，通过驱动类获取数据库的连接对象，关闭jdbc抽成共工方法
 */
public class JdbcDao02 {

    public void save(StudentBean stu) {
        String sql = "insert into student(id, name, age) values (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil01.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getId());
            ps.setString(2, stu.getName());
            ps.setInt(3, stu.getAge());
            ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            JdbcUtil01.close(ps, conn);
        }
    }

    public void delete(int id) {
        String sql = "delete from student where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JdbcUtil01.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil01.close(ps, con);
        }
    }

    public StudentBean get(int id) {
        String sql = "select * from student where id = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil01.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean stu = new StudentBean(rs.getString("id"), rs.getString("name"), rs.getInt("age"));
                return stu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil01.close(ps, con);
        }
        return null;
    }

    public List<StudentBean> list() {
        List<StudentBean> stus = new ArrayList<StudentBean>();
        String sql = "select * from student";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil01.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean stu = new StudentBean(rs.getString("id"), rs.getString("name"), rs.getInt("age"));
                stus.add(stu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil01.close(ps, con);
        }
        return stus;
    }
}
