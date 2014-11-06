package db;

import java.sql.*;

public class MySQLConnector
{
    public static Connection getConnection() throws SQLException,
            ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost/test", "root",
                        "A123456s");
        return conn;
    }
}