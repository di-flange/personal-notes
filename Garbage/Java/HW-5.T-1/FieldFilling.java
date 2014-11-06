/**
 * Homework 5
 */
package form;

import java.awt.event.*;
import javax.swing.*;

/**
 * Auto filling of text fields.
 * 
 * @author Anton Ishkov
 */
public class FieldFilling implements FocusListener
{
    /**
     * Constructor which set options.
     * 
     * @param target
     *            Operated field.
     * @param value
     *            Default value of field.
     */
    public FieldFilling(JTextField target, String value)
    {
        this.target = target;
        this.value = value;
    }

    /**
     * Clean form under focus.
     * 
     * @param arg0
     *            not used
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent arg0)
    {
        // Clean form under focus if it have default value.
        if(this.target.getText().equalsIgnoreCase(this.value))
        {
            this.target.setText("");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    /**
     * Fill form if it empty.
     * 
     * @param arg0
     *            not used
     */
    @Override
    public void focusLost(FocusEvent arg0)
    {
        if(this.target.getText().trim().equalsIgnoreCase(""))
        {
            this.target.setText(this.value);
        }
    }

    /**
     * Operated field.
     */
    private JTextField target;

    /**
     * Default value of field.
     */
    private String     value;
}