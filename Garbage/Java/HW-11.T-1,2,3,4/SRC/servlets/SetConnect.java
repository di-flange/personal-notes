package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hasado
 */
class SetConnect
{
    public static Connection getKolledzConnection() throws SQLException, ClassNotFoundException
    {
	Class.forName("org.postgresql.Driver");
	Connection conn = DriverManager.getConnection("jdbc:postgresql://www.vk.edu.ee/world_student",
	"sigurd","dodnjlwfq");

	return conn;
    }
}