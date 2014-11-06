<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<!--
    Anton Ishkov
    RDIR-42
    
    HomeWork12

    Создайте web-приложение (используйте JSP, Servlet, Bean) Адресная книга.
    Приложение должно давать возможности вставлять записи, удалять записи и
    осуществлять поиск записей.
-->       
<%
    Connection data = DBcon.getConnection();
    PreparedStatement ps;

    if(request.getParameter("delete") != null && request.getParameter("id") != null)
    {
        ps = data.prepareStatement("DELETE FROM AddressBook WHERE id = ?");

        ps.setString(1, request.getParameter ("id"));
        ps.executeUpdate();

        ps.close ();
    }

    if(request.getParameter ("name") != null && request.getParameter ("address") != null)
    {
        ps = data.prepareStatement("INSERT INTO AddressBook(name, address) VALUES(?, ?)");

        ps.setString (1, request.getParameter("name"));
        ps.setString (2, request.getParameter("address"));
        ps.executeUpdate ();
    }

    if(request.getParameter ("search") != null && request.getParameter ("clear") == null)
    {
        ps = data.prepareStatement("SELECT * FROM AddressBook WHERE name LIKE ? OR address LIKE ? ORDER BY name ASC;");

        String word = "%" + request.getParameter ("search") + "%";

        ps.setString(1, word);
        ps.setString(2, word);
    }
    else
    {
        ps = data.prepareStatement("SELECT * FROM AddressBook ORDER BY name ASC;");
    }

    ResultSet rs = ps.executeQuery();
    ArrayList<String[]> ls = new ArrayList<String[]>();

    while(rs.next())
    {
        String[] str = new String[3];

        // Берём все строками тк операции над числами нам не нужны
        str[0] = rs.getString("id");
        str[1] = rs.getString("name");
        str[2] = rs.getString("address");

        ls.add(str);
    }

    rs.close();
    ps.close();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="keywords" content="adress book contakts names" /> 
        <meta name="description" content="address book" />

        <title>AddressBook</title>

        <link rel="stylesheet" type="text/css" href="css/main.css" />
    </head>

    <body>
        <h1>AddressBook</h1>
        <form name="search" method="post">
            <input type="text" name="search" />
            <input type="submit" value="Search" />
            <input type="submit" name="clear" value="Clear">
        </form>
        <hr />
        <table>
            <tr>
                <td>Name</td>
                <td>Address</td>
                <td>Control</td>
            </tr>
            <%
            if(ls.size() < 1)
            {
                %>
            <tr>
                <td>&nbsp;</td>
                <td>Not found</td>
                <td>&nbsp;</td>
            </tr>
                <%
            }
            else
            {
                for(int i = 0; i < ls.size(); i++)
                {
                    %>
            <tr
                <form method="post" name="delete">
                    <td><%= ls.get(i)[1] %></td>
                    <td><%= ls.get(i)[2] %></td>
                    <td><input type="submit" name="delete" value="Delete"></td>
                    <input type="hidden" name="id" value="<%= ls.get(i)[0] %>">
                </form>
            </tr>
                    <%
                }
            }
            %>
            <tr>
                <form method="post" name="add">
                    <td><input type="text" name="name" /></td>
                    <td><input type="text" name="address" /></td>
                    <td><input type="submit" value="add" /></td>
                </form>
        </table>
        <hr />
    </body>
</html>
