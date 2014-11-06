/**
 * Homework 5
 */
package form;

import java.awt.*;
import javax.swing.*;

/**
 * Main window class.
 * 
 * @author Anton Ishkov
 */
public class MainGui extends JFrame
{
    /**
     * Simple constructor which create frame and main elements of this frame.
     */
    public MainGui()
    {
        // Set options
        super.setTitle(MainGui.FRAME_TITLE);

        super.setBounds(MainGui.FRAME_MARGIN, MainGui.FRAME_MARGIN,
                MainGui.FRAME_WIDTH, MainGui.FRAME_HEIGTH);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setResizable(false);

        // Load frame layout
        this.initComponents();
        this.initListeners();
        this.createGrid();
        this.createForm();

        // Show frame
        super.setVisible(true);
    }

    /**
     * Compile frame layout.
     */
    private void createForm()
    {
        // Declare variables
        Container workZone = getContentPane();

        JPanel panelHeader = new JPanel();
        JPanel panelMain = new JPanel();
        JPanel panelFooter = new JPanel();

        JLabel headerText = new JLabel(MainGui.FRAME_TITLE);

        // Compile header panel
        headerText.setFont(new Font("Arial", Font.PLAIN, 24));
        panelHeader.add(headerText);

        // Set main zone layout
        GridBagLayout layout = new GridBagLayout();

        // Compile main panel
        panelMain.setLayout(layout);

        for(byte i = 0; this.cells.length > i * 2 && this.label.length > i; i++ )
        {
            // Title fields
            layout.setConstraints(this.label[i], this.cells[i * 2]);

            panelMain.add(this.label[i]);
        }

        for(byte i = 0; this.cells.length > i * 2 - 1 && this.field.length > i; i++ )
        {
            // Fields
            if(i > 7)
            {
                layout.setConstraints(this.field[i], this.cells[i * 2 - 1]);

                panelMain.add(this.field[i]);
            }
            else if(i == 7)
            {
                panelMain.add(new JLabel("Male   "));
                panelMain.add(this.field[7]);
                panelMain.add(new JLabel("       Female "));
                panelMain.add(this.field[8]);
                i++ ;
            }
            else
            {
                layout.setConstraints(this.field[i], this.cells[i * 2 + 1]);

                panelMain.add(this.field[i]);
            }
        }

        // Compile footer
        panelFooter.add(this.buttons);

        // Compile page
        super.setLayout(new BorderLayout());

        workZone.add(panelHeader, BorderLayout.NORTH);
        workZone.add(panelMain, BorderLayout.CENTER);
        workZone.add(panelFooter, BorderLayout.SOUTH);
    }

    /**
     * Create layout grid for main layout.
     */
    private void createGrid()
    {
        // Create cells
        for(byte i = 0; this.cells.length > i; i++ )
        {
            this.cells[i] = new GridBagConstraints();
        }

        // Set up grid
        for(byte i = 0; this.cells.length > i; i++ )
        {
            if(i % 2 == 0)
            {
                this.cells[i].anchor = GridBagConstraints.NORTHWEST;
                this.cells[i].gridwidth = 150;

                this.cells[i].gridx = 1;
                this.cells[i].gridy = i / 2;

                this.cells[i].insets = new Insets(20, 0, 0, 20);
            }
            else
            {
                this.cells[i].anchor = GridBagConstraints.NORTHEAST;

                this.cells[i].gridwidth = GridBagConstraints.REMAINDER;

                this.cells[i].gridx = 0;
                this.cells[i].gridy = i / 2;

                this.cells[i].insets = new Insets(20, 150, 0, 0);
            }
        }
    }

    /**
     * Initialize text elements of frame.
     */
    private void initComponents()
    {
        for(byte i = 0; this.label.length > i; i++ )
        {
            this.label[i] = new JLabel(MainGui.DEF_TITLE[i]);
        }

        for(byte i = 0; MainGui.DEF_TEXT.length > i && this.field.length > i; i++ )
        {
            this.field[i] =
                    new JTextField(MainGui.DEF_TEXT[i], MainGui.FIELD_WIDTH);
        }

        this.field[4] = new JPasswordField(MainGui.FIELD_WIDTH);
        this.field[5] = new JPasswordField(MainGui.FIELD_WIDTH);

        this.field[6] = new JTextArea(1, MainGui.FIELD_WIDTH);
        ( (JTextArea)this.field[6] ).setLineWrap(true);
        ( (JTextArea)this.field[6] ).setWrapStyleWord(true);
        this.field[6].setMaximumSize(new Dimension(1, MainGui.FIELD_WIDTH));

        ButtonGroup sexGroup = new ButtonGroup();

        this.field[7] = new JRadioButton();
        this.field[8] = new JRadioButton();

        sexGroup.add((JRadioButton)this.field[7]);
        sexGroup.add((JRadioButton)this.field[8]);

        this.field[9] = new JComboBox(new String[]
        {
                "Estonia", "Lithunia", "Latvia", "Russia"
        });

        this.field[10] = new JComboBox(new String[]
        {
                "Tallinn", "Vilnus", "Riga", "Moscow"
        });

        this.field[11] = new JCheckBox();
    }

    /**
     * Loader for listeners
     */
    private void initListeners()
    {
        // Set buttons listeners
        this.buttons.addActionListener(new FormSaver(this.field));

        // Set fields filling listeners
        for(int i = 0; i < 4; i++ )
        {
            this.field[i].addFocusListener(new FieldFilling(
                    (JTextField)this.field[i], MainGui.DEF_TEXT[i]));
        }

        // Set fields validation.
        for(int i = 0; i < 3; i++ )
        {
            this.field[i].addFocusListener(new AplicationFormValidator(
                    (JTextField)this.field[i], "name"));
        }
        this.field[3].addFocusListener(new AplicationFormValidator(
                (JTextField)this.field[3], "mail"));

        this.field[4].addFocusListener(new AplicationPassValidator(
                (JPasswordField)this.field[4], 1));
        this.field[5]
                .addFocusListener(new AplicationPassValidator(
                        (JPasswordField)this.field[5], 2,
                        (JPasswordField)this.field[4]));

    }

    /**
     * Serial version UID
     */
    private static final long       serialVersionUID = 1L;

    /**
     * Options: Main frame width.
     */
    protected final static int      FRAME_WIDTH      = 350;

    /**
     * Options: Main frame height.
     */
    protected final static int      FRAME_HEIGTH     = 650;

    /**
     * Options: Main frame margin out of display border.
     */
    protected final static int      FRAME_MARGIN     = 25;

    /**
     * Options: Main frame title.
     */
    protected final static String   FRAME_TITLE      = "Aplication form";

    /**
     * Options: Width of text and password fields.
     */
    protected final static int      FIELD_WIDTH      = 15;

    /**
     * Options: Count of fields
     */
    protected final static int      FIELDS_COUNT     = 12;

    /**
     * Options: Count of cells
     */
    protected final static int      CELLS_COUNT      = 22;

    /**
     * Options: Default value for name field.
     */
    protected final static String[] DEF_TEXT         =
                                                     {
            "Your name", "Your surname", "Your alias", "Your e-mail"
                                                     };

    /**
     * Options: Default titles
     */
    protected final static String[] DEF_TITLE        =
                                                             {
            "Name: ",
            "Surname: ",
            "Alias: ",
            "e-Mail: ",
            "Password: ",
            "Retype password: ",
            "Bio: ",
            "Sex: ",
            "Country: ",
            "City: ",
            "Accept user agreement: "
                                                             };

    /**
     * Layout cells
     */
    private GridBagConstraints      cells[]          =
                                                             new GridBagConstraints[CELLS_COUNT];

    /**
     * Text elements.
     */
    private JLabel                  label[]          =
                                                             new JLabel[MainGui.DEF_TITLE.length];

    /**
     * Form fields.
     */
    private Component               field[]          =
                                                             new Component[FIELDS_COUNT];

    /**
     * Form buttons.
     */
    private JButton                 buttons          = new JButton("Save");
}