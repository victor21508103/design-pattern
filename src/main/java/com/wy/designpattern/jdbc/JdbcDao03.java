package com.wy.designpattern.jdbc;

import java.sql.ResultSet;
import java.util.List;

/**
 * 将添加，删除抽成公共方法
 */
public class JdbcDao03 {

    public void save(StudentBean stu) {
        String sql = "insert into student(id, name, age) values (?, ?, ?)";
        JdbcUtil02.update(sql, stu.getId(), stu.getName(), stu.getAge());
    }

    public void delete(int id) {
        String sql = "delete from student where id = ?";
        JdbcUtil02.update(sql, id);
    }

    public List<StudentBean> query() {
        String sql = "select * from student";
        List<StudentBean> students = JdbcUtil02.query(sql, new StudentRowMapper());
        return students;
    }

    public StudentBean get(int id) {
        String sql = "select * from student where id = ?";
        List<StudentBean> students = JdbcUtil02.query(sql, new StudentRowMapper(), id);
        return students.size() > 0 ? students.get(0) : null;
    }

    public StudentBean count() {
        String sql = "select * from student";
        JdbcUtil02.query(sql, new IRowMapper<Long>() {
            public Long rowMapper(ResultSet rs) {
                Long total = 0L;
                while (rs.next()) {
                    total += rs.getLong("total");
                }
                return total;
            }
        });
    }

}
