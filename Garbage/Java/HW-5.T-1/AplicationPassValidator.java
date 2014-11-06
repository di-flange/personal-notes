/**
 * Homework 5
 */
package form;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Validation of pass field.
 * 
 * @author Anton Ishkov
 */
public class AplicationPassValidator implements FocusListener
{
    /**
     * Construct with setting target.
     * 
     * @param target
     *            Field.
     * @param type
     *            Number of field.
     */
    public AplicationPassValidator(JPasswordField target, int type)
    {
        // Set values
        this.target = target;
        this.type = type;
    }

    /**
     * Construct with setting target.
     * 
     * @param target
     *            Field.
     * @param type
     *            Number of field.
     * @param value
     *            First field.
     */
    public AplicationPassValidator(JPasswordField target, int type,
            JPasswordField value)
    {
        // Set values
        this.target = target;
        this.type = type;
        this.value = value;
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
        String text = String.valueOf(this.target.getPassword());

        // Validate
        if(this.type == 1)
        {
            if(! text.matches("^[A-Za-z0-9]{6,22}?$"))
            {
                this.target.setBackground(Color.RED);
            }
            else
            {
                this.target.setBackground(Color.GREEN);
            }
        }
        else if(this.type == 2)
        {
            if(! text.equals(String.valueOf(this.value.getPassword()))
                    || ! text.matches("^[A-Za-z0-9]{6,22}?$"))
            {
                this.target.setBackground(Color.RED);
            }
            else
            {
                this.target.setBackground(Color.GREEN);
            }
        }
    }

    /**
     * Target field.
     */
    private JPasswordField target;

    /**
     * Type of validation.
     */
    private int            type;

    /**
     * Check value
     */
    private JPasswordField value;
}