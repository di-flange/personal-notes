/**
 * Homework 6.2
 * 
 * Anton Iskov RDIR 42
 * 
 * Изучите самостоятельно компонент JTable. Используя данные текстового файла
 * Books.txt создайте таблицу.
 * 
 * При выборе соответствующей категории из списка в таблице отображаются книги
 * данной категории. При выборе ALL отображаются все книги.
 * 
 * Внизу таблицы должна отображаться информация — сколько книг в таблице и на
 * какую сумму.
 * 
 * Дополнительно можно получить баллы, если реализовать сохранение изменений в
 * файл (где есть возможность добавить список для редактирования в таблицу).
 * 
 */
package table;

import java.awt.*;

/**
 * Main class only create queue of treads and main window.
 * 
 * @author Anton Ishkov
 */
public class Main
{

    /**
     * Window run.
     * 
     * @param args
     *            not used
     */
    public static void main(String[] args)
    {
        // Create new tread
        EventQueue.invokeLater(new Runnable()
        {
            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Runnable#run()
             */
            @Override
            public void run()
            {
                // Create form window
                @SuppressWarnings("unused")
                MainGui frame = new MainGui();
            }
        });
    }
}