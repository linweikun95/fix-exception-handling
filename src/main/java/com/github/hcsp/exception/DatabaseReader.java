package com.github.hcsp.exception;

import java.io.File;
//import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseReader {
    public static void main(String[] args) throws SQLException {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        String jdbcUrl = "jdbc:h2:file:" + new File(projectDir, "test").getAbsolutePath();
        System.out.println(jdbcUrl);

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, "sa", "");
            statement = connection.prepareStatement("select * from PULL_REQUESTS where number > ?");
            statement.setInt(1, 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1)
                                + " "
                                + resultSet.getString(2)
                                + " "
                                + resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection !=null){
                connection = null;
            }
            if (statement != null){
                statement = null;
            }
        }


    }
}
