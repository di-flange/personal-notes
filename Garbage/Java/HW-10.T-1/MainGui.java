package db;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Main window class.
 * 
 * @author Anton Ishkov
 */
public class MainGui extends JFrame
{
    public MainGui() throws SQLException, ClassNotFoundException
    {
        super.setTitle(MainGui.FRAME_TITLE);

        super.setBounds(MainGui.FRAME_MARGIN, MainGui.FRAME_MARGIN,
                MainGui.FRAME_WIDTH, MainGui.FRAME_HEIGTH);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setResizable(false);

        this.initComponents();
        this.initListeners();
        this.createLayout();

        this.connection = MySQLConnector.getConnection();

        super.setVisible(true);
    }

    private void createLayout()
    {
        Container workZone = getContentPane();
        JPanel panelMain = new JPanel();

        JScrollPane scroll = new JScrollPane(this.list);
        scroll.setPreferredSize(new Dimension(200, 200));

        panelMain.add(this.text);
        panelMain.add(this.buttons[0]);
        panelMain.add(scroll);
        panelMain.add(this.buttons[1]);
        panelMain.add(this.buttons[2]);

        super.setLayout(new BorderLayout());

        workZone.add(panelMain, BorderLayout.CENTER);
    }

    private void initComponents()
    {
        this.text = new JTextField();
        this.text.setPreferredSize(new Dimension(120, 25));

        this.list = new JList();

        this.list.setListData(new String[]
        {
                " "
        });

        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.buttons =
                new JButton[]
                {
                        new JButton("Search"),
                        new JButton("Insert"),
                        new JButton("Delete")
                };
    }

    private void initListeners()
    {
        this.buttons[0].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                list.removeAll();
                try
                {
                    ResultSet rs =
                            connection
                                    .createStatement()
                                    .executeQuery(
                                            "SELECT word FROM words WHERE id IN ("
                                                    + "SELECT resp FROM dict WHERE target IN ("
                                                    + "SELECT id FROM words WHERE word LIKE '%"
                                                    + text.getText()
                                                    + "%'"
                                                    + ")) ORDER BY word ASC LIMIT 100;");

                    ArrayList<String> words = new ArrayList<String>();

                    while(rs.next())
                    {
                        words.add(rs.getString("word"));
                    }

                    rs.close();

                    list.setListData(words.toArray());
                }
                catch(SQLException e)
                {
                    return;
                }
            }
        });

        this.buttons[1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    String targetWord =
                            JOptionPane.showInputDialog("Enter new word:");
                    String respWord =
                            JOptionPane.showInputDialog("Enter new meaning:");

                    connection.createStatement().executeUpdate(
                            "INSERT IGNORE INTO words SET word = '"
                                    + targetWord + "';");
                    connection.createStatement().executeUpdate(
                            "INSERT IGNORE INTO words SET word = '"
                                    + respWord + "';");

                    connection
                            .createStatement()
                            .executeUpdate(
                                    "INSERT IGNORE INTO dict SET target = (SELECT id FROM words WHERE word = '"
                                            + targetWord
                                            + "'), resp = (SELECT id FROM words WHERE word = '"
                                            + respWord + "');");

                    JOptionPane.showMessageDialog(null, "Data was saved");

                    list.removeAll();

                    ResultSet rs =
                            connection
                                    .createStatement()
                                    .executeQuery(
                                            "SELECT word FROM words WHERE id IN ("
                                                    + "SELECT resp FROM dict WHERE target IN ("
                                                    + "SELECT id FROM words WHERE word LIKE '%"
                                                    + text.getText()
                                                    + "%'"
                                                    + ")) ORDER BY word ASC LIMIT 100;");

                    ArrayList<String> words = new ArrayList<String>();

                    while(rs.next())
                    {
                        words.add(rs.getString("word"));
                    }

                    rs.close();

                    list.setListData(words.toArray());

                    text.setText(targetWord);

                }
                catch(Exception e1)
                {
                    System.out.println(e1.toString());
                    return;
                }
            }
        });

        this.buttons[2].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    connection
                            .createStatement()
                            .executeUpdate(
                                    "DELETE FROM dict WHERE target IN (SELECT id FROM words WHERE word LIKE '%"
                                            + text.getText()
                                            + "%') AND resp IN (SELECT id FROM words WHERE word LIKE '%"
                                            + list.getSelectedValue() + "%')");
                }
                catch(SQLException e1)
                {
                    return;
                }

                list.removeAll();
                try
                {
                    ResultSet rs =
                            connection
                                    .createStatement()
                                    .executeQuery(
                                            "SELECT word FROM words WHERE id IN ("
                                                    + "SELECT resp FROM dict WHERE target IN ("
                                                    + "SELECT id FROM words WHERE word LIKE '%"
                                                    + text.getText()
                                                    + "%'"
                                                    + ")) ORDER BY word ASC LIMIT 100;");

                    ArrayList<String> words = new ArrayList<String>();

                    while(rs.next())
                    {
                        words.add(rs.getString("word"));
                    }

                    rs.close();

                    list.setListData(words.toArray());
                }
                catch(SQLException e1)
                {
                    return;
                }

                JOptionPane.showMessageDialog(null, "Data was deleted");
            }
        });
    }

    private static final long   serialVersionUID = 1L;
    private final static int    FRAME_WIDTH      = 250;
    private final static int    FRAME_HEIGTH     = 300;
    private final static int    FRAME_MARGIN     = 25;
    private final static String FRAME_TITLE      = "Dictinary";
    private JButton             buttons[];
    private JList               list;
    private JTextField          text;
    private Connection          connection;
}