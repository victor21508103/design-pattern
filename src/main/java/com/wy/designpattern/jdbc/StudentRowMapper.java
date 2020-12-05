package com.wy.designpattern.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRowMapper implements IRowMapper<List<StudentBean>>{

    public List<StudentBean> rowMapper(ResultSet rs) {
        List<StudentBean> stus = new ArrayList<StudentBean>();
        while (rs.next()){
            StudentBean stu = new StudentBean(rs.getString("id"), rs.getString("name"), rs.getInt("age"));
            stus.add(stu);
        }
        return stus;
    }

}
