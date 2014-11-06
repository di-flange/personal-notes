package db;

import java.sql.SQLException;

/**
 * Всего два класса майн запускает всё, конектор подключает mySql. MainGui
 * содержит интерфейс пользователя который отображает и добавляет информацию.
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