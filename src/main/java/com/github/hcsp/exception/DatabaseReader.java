package com.github.hcsp.exception;

import java.io.File;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseReader {
    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        String jdbcUrl = "jdbc:h2:file:" + new File(projectDir, "test").getAbsolutePath();
        System.out.println(jdbcUrl);
        //Connection connection = null;
        //PreparedStatement statement = null;
        //ResultSet resultSet = null;
        try(Connection connection = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            try (PreparedStatement statement = connection.prepareStatement("select * from PULL_REQUESTS where number > ?")) {
                statement.setInt(1, 0);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(
                                resultSet.getInt(1)
                                        + " "
                                        + resultSet.getString(2)
                                        + " "
                                        + resultSet.getString(2));
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
