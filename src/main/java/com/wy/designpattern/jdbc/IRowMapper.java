package com.wy.designpattern.jdbc;

import java.sql.ResultSet;

public interface IRowMapper<T> {

    T rowMapper(ResultSet rs);

}
