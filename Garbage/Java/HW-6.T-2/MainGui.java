/**
 * Homework 6.2
 */
package table;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

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
        this.createLayout();

        // Show frame
        super.setVisible(true);
    }

    /**
     * Compile frame layout.
     */
    private void createLayout()
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

        // Set main zone layout and compile it
        panelMain.add(this.combobox);

        JScrollPane scroll = new JScrollPane(this.table);
        this.table.setFillsViewportHeight(true);
        scroll.setPreferredSize(new Dimension(550, 200));

        panelMain.add(scroll);

        // Compile footer
        for(byte i = 0; this.buttons.length > i; i++ )
        {
            panelFooter.add(this.buttons[i]);
        }

        // Compile page
        super.setLayout(new BorderLayout());

        workZone.add(panelHeader, BorderLayout.NORTH);
        workZone.add(panelMain, BorderLayout.CENTER);
        workZone.add(panelFooter, BorderLayout.SOUTH);
    }

    /**
     * Initialize text elements of frame.
     */
    private void initComponents()
    {
        // Initialize combo boxes
        this.combobox = new JComboBox(this.booksList.getCategorys());
        this.combobox.addItem("All");
        this.combobox.setSelectedItem("All");

        // Initialize table
        this.table = new JTable(new BooksTableModel());

        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(50);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(50);

        // Initialize buttons
        this.buttons = new JButton[]
        {
                new JButton("Insert"), new JButton("Delete")
        };
    }

    /**
     * Loader for listeners
     */
    private void initListeners()
    {
        // Initiate group selecting
        this.combobox.addActionListener(new ActionListener()
        {
            /**
             * Change category.
             */
            public void actionPerformed(ActionEvent arg0)
            {
                ( (BooksTableModel)table.getModel() )
                        .changeCategory((String)combobox.getSelectedItem());
            }
        });

        // Initiate buttons
        this.buttons[0].addActionListener(new ActionListener()
        {
            /**
             * Insert row.
             */
            public void actionPerformed(ActionEvent arg0)
            {
                String record =
                        JOptionPane
                                .showInputDialog("Enter new record in format (ID,Type,Title,Author,Price,Qty):");
                /*
                 * if(! record .matches("^[A-Za-z0-9]*[,][A-Za-z]*[,].*")) {//
                 * [A
                 * -Za-z0-9.,#@!$%^&*()_+-]+[,][A-Za-z.,-]+[,][0-9.]+[,][0-9]+$
                 * JOptionPane.showMessageDialog(null,
                 * "ERROR: You record is corrupted."); return; }
                 */
                StringTokenizer cell = new StringTokenizer(record, ",");
                String[] dataBuffer =
                {
                        "", "", "", "", "", "", ""
                };

                for(int i = 0; cell.hasMoreTokens() && i < 6; i++ )
                {
                    dataBuffer[i] = cell.nextToken();
                }

                try
                {
                    booksList.addBook(dataBuffer[2], dataBuffer[3],
                            dataBuffer[0], dataBuffer[1], Float
                                    .valueOf(dataBuffer[4]), Integer
                                    .valueOf(dataBuffer[5]));
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,
                            "ERROR: You record is corrupted.");
                }
            }
        });
        this.buttons[1].addActionListener(new ActionListener()
        {
            /**
             * Delete row.
             */
            public void actionPerformed(ActionEvent arg0)
            {
                ( (BooksTableModel)table.getModel() ).remove(table
                        .getSelectedRow());
            }
        });
    }

    /**
     * Serial version UID
     */
    private static final long   serialVersionUID = 1L;

    /**
     * Options: Main frame width.
     */
    private final static int    FRAME_WIDTH      = 600;

    /**
     * Options: Main frame height.
     */
    private final static int    FRAME_HEIGTH     = 350;

    /**
     * Options: Main frame margin out of display border.
     */
    private final static int    FRAME_MARGIN     = 25;

    /**
     * Options: Main frame title.
     */
    private final static String FRAME_TITLE      = "Books Collection";

    /**
     * Form buttons.
     */
    private JButton             buttons[];

    /**
     * Form list.
     */
    private JTable              table;

    /**
     * Form combobox.
     */
    private JComboBox           combobox;

    /**
     * Links list
     */
    private BooksCollection     booksList        = new BooksCollection();

    /**
     * Books table model
     */
    private class BooksTableModel extends AbstractTableModel
    {
        /**
         * Constructor which get all data from collection.
         */
        private BooksTableModel()
        {
            this.data = booksList.getBooks();
        }

        /**
         * Count of rows.
         */
        public int getRowCount()
        {
            return this.data.length + 1;
        }

        /**
         * Count of fields.
         */
        public int getColumnCount()
        {
            return 6;
        }

        /**
         * Get headers
         */
        public String getColumnName(int index)
        {
            switch(index)
            {
                case 0:
                    return "Code";
                case 1:
                    return "Category";
                case 2:
                    return "Title";
                case 3:
                    return "Author";
                case 4:
                    return "Price";
                case 5:
                    return "Count";
                default:
                    return null;
            }
        }

        /**
         * Block of editing
         */
        public boolean isCellEditable(int rowIndex, int columnIndex)
        {
            return false;
        }

        /**
         * Get values of cell's.
         */
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            if(rowIndex == this.data.length)
            {
                if(columnIndex == 4)
                {
                    float sum = 0;
                    for(int i = 0; i < this.data.length; i++ )
                    {
                        sum += Float.parseFloat(this.data[i][columnIndex]);
                    }
                    BigDecimal bd = new BigDecimal(String.valueOf(sum));

                    return bd.setScale(2, BigDecimal.ROUND_DOWN).toString();
                }
                else if(columnIndex == 5)
                {
                    int sum = 0;
                    for(int i = 0; i < this.data.length; i++ )
                    {
                        sum += Integer.valueOf(this.data[i][columnIndex]);
                    }
                    return String.valueOf(sum);
                }
                return "";
            }

            return this.data[rowIndex][columnIndex];
        }

        /**
         * Change category of table items.
         */
        public void changeCategory(String category)
        {
            if(category.equals("All"))
            {
                this.data = booksList.getBooks();
                this.category = "All";
            }
            else
            {
                this.data = booksList.getBooks(category);
                this.category = category;
            }
            table.updateUI();
        }

        /**
         * Get code
         */
        public void remove(int row)
        {
            if(row >= - 1 && row < this.data.length)
            {
                booksList.removeBook(this.data[row][0]);
                this.changeCategory(this.category);
            }
        }

        /**
         * Standard serial version UID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Getting data table.
         */
        private String[][]        data;

        /**
         * Current category
         */
        private String            category         = "All";
    }
}