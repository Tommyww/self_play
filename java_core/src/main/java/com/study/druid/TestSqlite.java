package com.study.druid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by wangwei on 17/6/3.
 */
public class TestSqlite {

    public static void main(String[] args) throws Exception {
        String sql = "jdbc:sqlite:/Users/apple/Downloads/test.sqlite";
        Class.forName("org.sqlite.JDBC");

        Connection conn = DriverManager.getConnection(sql);
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from student;"); // 查询数据
        while (rs.next()) {
            System.out.print("name = " + rs.getString("name") + ", "); // 列属性一
        }
        rs.close();
    }
}
