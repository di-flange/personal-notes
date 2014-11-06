/**
 * Homework 6.1
 * 
 * Работа со списками. Имеется коллекция интернет-адресов с полезными ссылками
 * по Java. Ссылки собраны по категориям. Создайте выпадающий список из
 * категорий. При выборе категории в списке JList отображаются ссылки. Также
 * имеется другой список JList, который заполняется через каждые 5 секунд
 * случайно выбранными 10 ссылками. Осуществите добавление и удаление ссылок из
 * первого Jlist. При добавлении новой ссылки — проверка на корректность ввода.
 * Реализуйте задачу, применив различные менеджеры компоновок.
 */
package links;

import java.awt.*;
import java.awt.event.*;

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
        panelMain.setLayout(new GridBagLayout());

        GridBagConstraints cells[] =
                new GridBagConstraints[]
                {
                        new GridBagConstraints(),
                        new GridBagConstraints(),
                        new GridBagConstraints()
                };
        cells[0].gridy = 1;
        cells[0].gridx = 1;
        cells[1].gridy = 2;
        cells[1].gridx = 1;
        cells[2].gridy = 2;
        cells[2].gridx = 2;

        cells[0].insets = new Insets(0, 0, 0, 350);
        cells[1].insets = new Insets(0, 0, 0, 350);
        cells[2].insets = new Insets(0, 350, 0, 0);

        cells[0].anchor = GridBagConstraints.NORTHWEST;
        cells[1].anchor = GridBagConstraints.NORTHWEST;
        cells[2].anchor = GridBagConstraints.NORTHEAST;

        cells[0].gridwidth = 350;
        cells[1].gridwidth = 350;
        cells[2].gridwidth = 300;

        ( (GridBagLayout)panelMain.getLayout() ).setConstraints(this.combobox,
                cells[0]);
        panelMain.add(this.combobox);

        JScrollPane scroll = new JScrollPane(this.lists[0]);

        scroll.setPreferredSize(new Dimension(300, 200));
        ( (GridBagLayout)panelMain.getLayout() ).setConstraints(scroll,
                cells[1]);

        this.lists[1].setPreferredSize(new Dimension(300, 200));
        ( (GridBagLayout)panelMain.getLayout() ).setConstraints(this.lists[1],
                cells[2]);

        panelMain.add(scroll);
        panelMain.add(this.lists[1]);

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
        this.combobox = new JComboBox(this.linkList.getCategorys());
        this.combobox.addItem("All");
        this.combobox.setSelectedItem("All");

        // Initialize lists
        this.lists = new JList[]
        {
                new JList(), new JList()
        };

        this.lists[0].setListData(this.linkList.getLinks());
        this.lists[1].setListData(this.linkList.getRandomLinks(10));

        for(int i = 0; i < this.lists.length; i++ )
        {
            this.lists[i].setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

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
        // Initiate reloading by timer
        new Timer(5000, new ActionListener()
        {
            /**
             * Refresh of random list.
             */
            public void actionPerformed(ActionEvent evt)
            {
                lists[1].removeAll();
                lists[1].setListData(linkList.getRandomLinks(10));
            }
        }).start();

        // Initiate group selecting
        this.combobox.addActionListener(new ActionListener()
        {
            /**
             * Change category.
             */
            public void actionPerformed(ActionEvent arg0)
            {
                lists[0].removeAll();

                if("All" == (String)combobox.getSelectedItem())
                {
                    lists[0].setListData(linkList.getLinks());
                }
                else
                {
                    lists[0].setListData(linkList.getLinks((String)combobox
                            .getSelectedItem()));
                }
            }
        });

        // Initialize buttons listeners
        this.buttons[0].addActionListener(new ActionListener()
        {
            /**
             * Create new link.
             */
            public void actionPerformed(ActionEvent event)
            {
                String link = JOptionPane.showInputDialog("Enter new link:");

                if(! link
                        .matches("^http[s]{0,1}://[a-zA-Z0-9_-]+[.][a-zA-Z]{2,5}[a-zA-Z0-9_:@&?=+,.!/+~*'%$-]*$"))
                {

                    JOptionPane.showMessageDialog(null,
                            "ERROR: You link is corrupted.");
                    return;
                }

                linkList.addLink(link, (String)combobox.getSelectedItem());

                lists[0].removeAll();

                if("All" == (String)combobox.getSelectedItem())
                {
                    lists[0].setListData(linkList.getLinks());
                }
                else
                {
                    lists[0].setListData(linkList.getLinks((String)combobox
                            .getSelectedItem()));
                }
            }
        });

        this.buttons[1].addActionListener(new ActionListener()
        {
            /**
             * Delete link.
             */
            public void actionPerformed(ActionEvent event)
            {
                linkList.removeLink((String)lists[0].getSelectedValue());

                lists[0].removeAll();

                if("All" == (String)combobox.getSelectedItem())
                {
                    lists[0].setListData(linkList.getLinks());
                }
                else
                {
                    lists[0].setListData(linkList.getLinks((String)combobox
                            .getSelectedItem()));
                }
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
    private final static int    FRAME_WIDTH      = 700;

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
    private final static String FRAME_TITLE      = "Links Collection";

    /**
     * Form buttons.
     */
    private JButton             buttons[];

    /**
     * Form list.
     */
    private JList               lists[];

    /**
     * Form combobox.
     */
    private JComboBox           combobox;

    /**
     * Links list
     */
    private LinkCollection      linkList         = new LinkCollection();
}