/**
 * Homework 5
 */
package form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Validation of text fields.
 * 
 * @author Anton Ishkov
 */
public class AplicationFormValidator implements FocusListener
{
    /**
     * Construct with setting target.
     * 
     * @param target
     *            Field.
     * @param type
     *            Type of field.
     */
    public AplicationFormValidator(JTextField target, String type)
    {
        this.target = target;
        this.type = type;
    }

    /**
     * Not used.
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent arg0)
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    /**
     * Check validation.
     * 
     * @param arg0
     *            Not used.
     */
    @Override
    public void focusLost(FocusEvent arg0)
    {
        // Declare variable
        String text = target.getText();

        // Validate
        if(type.equalsIgnoreCase("name"))
        {
            if(! text.matches("^[A-Za-z0-9]{4,10}?$"))
            {
                target.setBackground(Color.RED);
            }
            else
            {
                target.setBackground(Color.GREEN);
            }
        }
        else if(type.equalsIgnoreCase("mail"))
        {
            if(! text
                    .matches("^[A-Za-z0-9]{2,30}@[A-Za-z0-9]{2,30}[.][A-Za-z]{2,3}?$"))
            {
                target.setBackground(Color.RED);
            }
            else
            {
                target.setBackground(Color.GREEN);
            }
        }

    }

    /**
     * Target field.
     */
    private JTextField target;

    /**
     * Type of validation.
     */
    private String     type;
}