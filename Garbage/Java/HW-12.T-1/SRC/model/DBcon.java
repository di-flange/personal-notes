package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon
{
    public static Connection getConnection()
            throws SQLException, ClassNotFoundException
    {
        // MySQL
        Class.forName("com.mysql.jdbc.Driver");

        Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost/AddressBook?characterEncoding=utf8&connectionCollation=utf8_general_ci",
                    "root",
                    "7uvola5lf");

        return connect;
    }
}