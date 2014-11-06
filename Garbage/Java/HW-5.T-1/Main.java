/**
 * Homework 5
 * 
 * Anton Iskov RDIR 42
 * 
 * Создайте графическое приложение, в котором продемонстрируйте работу со
 * следующими компонентами: JLabel, JTextField, JPasswordField, JTextArea,
 * JCheckBox, JRadioButton, JButton. Используйте свойства, методы, события
 * данных компонентов. (20 баллов)
 */
package form;

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