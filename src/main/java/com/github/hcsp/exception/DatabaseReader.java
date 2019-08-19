package com.github.hcsp.exception;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseReader {
    public static void main(String[] args) {
        File projectDir = new File(System.getProperty("basedir", System.getProperty("user.dir")));
        String jdbcUrl = "jdbc:h2:file:" + new File(projectDir, "test").getAbsolutePath();
        System.out.println(jdbcUrl);

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        PreparedStatement statement =
                null;
        try {
            statement = Objects.requireNonNull(connection).prepareStatement("select * from PULL_REQUESTS where number > ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Objects.requireNonNull(statement).setInt(1, 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = Objects.requireNonNull(statement).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!Objects.requireNonNull(resultSet).next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(
                        Objects.requireNonNull(resultSet).getInt(1)
                                + " "
                                + resultSet.getString(2)
                                + " "
                                + resultSet.getString(2));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
