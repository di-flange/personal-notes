/**
 * Homework 5
 */
package form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Listener which save information from fields.
 * 
 * @author Anton Ishkov
 */
public class FormSaver implements ActionListener
{
    /**
     * Constructor which set saved fields.
     * 
     * @param field
     *            Array of fields.
     */
    public FormSaver(Component[] field)
    {
        this.field = field;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
     * )
     */
    /**
     * Listen click to the button and save data from form.
     */
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // Get data
        System.out.println("Name: " + ( (JTextField)this.field[0] ).getText()
                + ".");
        System.out.println("Surname: "
                + ( (JTextField)this.field[1] ).getText() + ".");
        System.out.println("Alias: " + ( (JTextField)this.field[2] ).getText()
                + ".");
        System.out.println("e-Mail: " + ( (JTextField)this.field[3] ).getText()
                + ".");

        if(String.valueOf(( (JPasswordField)this.field[4] ).getPassword())
                .equals(
                        String.valueOf(( (JPasswordField)this.field[5] )
                                .getPassword())))
        {
            System.out.println("Pass: "
                    + String.valueOf(( (JPasswordField)this.field[4] )
                            .getPassword()) + ".");
        }

        if(( (JRadioButton)this.field[7] ).isSelected())
        {
            System.out.println("Sex: Male.");
        }
        else if(( (JRadioButton)this.field[8] ).isSelected())
        {
            System.out.println("Sex: Female.");
        }

        System.out.println("Country: "
                + ( (JComboBox)this.field[9] ).getSelectedItem() + ".");
        System.out.println("City: "
                + ( (JComboBox)this.field[10] ).getSelectedItem() + ".");

        if(( (JCheckBox)this.field[11] ).isSelected())
        {
            System.out.println("Agreement: Accepted.");
        }
        else
        {
            System.out.println("Agreement: Rejected.");
        }

    }

    /**
     * Fields for saving.
     */
    private Component[] field;
}