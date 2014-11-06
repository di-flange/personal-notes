package db;

import java.sql.SQLException;

/**
 * ����� ��� ������ ���� ��������� ��, �������� ���������� mySql. MainGui
 * �������� ��������� ������������ ������� ���������� � ��������� ����������.
 * 
 * @author Anton Ishkov
 */
public class Main
{
    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException
    {
        @SuppressWarnings("unused")
        MainGui app = new MainGui();
    }
}