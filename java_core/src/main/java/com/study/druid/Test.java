package com.study.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangwei on 17/6/3.
 */
public class Test {

    public static void main(String[] args) {


        try {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName("org.sqlite.JDBC");
            dataSource.setUrl("jdbc:sqlite:/Users/apple/Downloads/test.sqlite");
            dataSource.setInitialSize(1);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(10);

            String sql = "select name from student";

            DruidPooledConnection con = dataSource.getConnection();


            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.print("name = " + rs.getString("name") + ", ");
            }



            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
