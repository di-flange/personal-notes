/**
 * 
 * Anton Ishkov Homework 7
 * 
 * Не успел дописать, по этому редактирование не предусмотренно.
 * 
 * Студенты Колледжа. Создайте структуру специальности - группы с помощью
 * компонента TreeView. При выборе группы в таблице в алфавитном порядке
 * отображается список студентов. Также выводится информация о специальности,
 * количество студентов всего в колледже, по каждой специальности и выбранной
 * группе. Осуществите возможность редактирования данных: специальности, группы,
 * студенты, поиск студента по фамилии.
 */
package students;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @author Anton Ishkov
 * 
 */
public class MainGui
{
    /**
     * Constructor which start frame creation.
     */
    public MainGui()
    {
        // Initialize fields
        this.display = new Display();
        this.shell = new Shell();

        // Create GUI layout
        this.shell.setText(MainGui.TITLE);
        this.shell.setLayout(null);
        this.shell.setLocation(MainGui.MARGIN, MainGui.MARGIN);
        this.shell.setSize(new Point(MainGui.WIDTH, MainGui.HEIGHT));

        // Create elements
        this.createItems();
        this.initListeners();

        this.shell.open();

        // Disposing of frame
        while(! this.shell.isDisposed())
        {
            if(! this.display.readAndDispatch())
            {
                // ZZZzzzzzZZZZZZzzzzz
                this.display.sleep();
            }
        }

        this.display.dispose();
    }

    private void createItems()
    {
        // Create information label
        this.information = new Label(this.shell, SWT.BORDER);
        this.information.setBounds(new Rectangle(220, 160, 410, 170));

        // Create tree
        this.tree = new Tree(this.shell, SWT.BORDER);

        // Set position
        this.tree.setLocation(new Point(10, 1));
        this.tree.setSize(new Point(192, 151));

        // Create root
        this.root = new TreeItem(this.tree, SWT.NULL);
        this.root.setText("TTU VK");

        // Get top branch
        String[] topBranchText = this.studentsList.getGroups();

        // Create top level elements
        this.topBranchItems = new TreeItem[topBranchText.length];
        this.lowBranchItems = new TreeItem[topBranchText.length][];

        for(int i = 0; i < topBranchText.length; i++ )
        {
            this.topBranchItems[i] = new TreeItem(this.root, SWT.NULL);
            this.topBranchItems[i].setText(topBranchText[i]);

            // Get low branch
            Integer[] lowBranchText =
                    this.studentsList.getSemesters(topBranchText[i]);

            // Create low level elements branch
            this.lowBranchItems[i] = new TreeItem[lowBranchText.length];

            for(int j = 0; j < lowBranchText.length; j++ )
            {
                this.lowBranchItems[i][j] =
                        new TreeItem(this.topBranchItems[i], SWT.NULL);
                this.lowBranchItems[i][j].setText(topBranchText[i] + "0"
                        + lowBranchText[j]);
            }
        }

        this.tree.setSize(200, 330);

        // Create table
        this.table =
                new Table(this.shell, SWT.BORDER | SWT.CHECK | SWT.MULTI
                        | SWT.FULL_SELECTION);

        // Set table options
        this.table.setHeaderVisible(true);
        this.table.setLinesVisible(true);

        this.table.setBounds(new Rectangle(220, 1, 410, 150));

        TableColumn[] column = new TableColumn[4];

        for(int i = 0; i < column.length; i++ )
        {
            column[i] = new TableColumn(this.table, SWT.LEFT);
        }

        column[0].setText("v");
        column[1].setText("First Name");
        column[2].setText("Last Name");
        column[3].setText("Group");

        column[0].setWidth(20);
        column[1].setWidth(155);
        column[2].setWidth(155);
        column[3].setWidth(76);

        TableEditor editor = new TableEditor(this.table);
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;

        // Search field
        this.textInput = new Text(this.shell, SWT.SINGLE | SWT.BORDER);
        this.textInput.setText("");
        this.textInput.setBounds(new Rectangle(10, 340, 140, 20));

        this.buttonSearch = new Button(this.shell, SWT.BORDER | SWT.PUSH);
        this.buttonSearch.setBounds(new Rectangle(160, 338, 50, 25));
        this.buttonSearch.setText("Search");
    }

    /**
     * Initialize listeners.
     */
    private void initListeners()
    {
        this.buttonSearch.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                table.removeAll();
                int count = 0;
                String[][] students =
                        studentsList.getStudents(tree.getSelection()[0]
                                .getText());

                for(int i = 0; i < students.length; i++ )
                {
                    if(textInput.getText().equalsIgnoreCase(students[i][3]))
                    {
                        ++count;

                        TableItem row = new TableItem(table, SWT.NONE);

                        row.setText(1, students[i][3]);
                        row.setText(2, students[i][4]);
                        row.setText(3, students[i][1] + "0" + students[i][2]);
                    }
                }

                if(count > 0)
                {
                    information.setText("Search results");
                }
                else
                {
                    information.setText("Not found");
                }
            }
        });

        // Tree listening
        this.tree.addSelectionListener(new SelectionListener()
        {
            @Override
            public void widgetSelected(SelectionEvent arg0)
            {
                if(tree.getSelection()[0].getText().equals("TTU VK"))
                {
                    information.setText(tree.getSelection()[0].getText()
                            + ": Information here\n\nStudents in Kolledz:"
                            + studentsList.size());
                    return;
                }

                for(int i = 0; i < topBranchItems.length; i++ )
                {
                    if(tree.getSelection()[0].getText().equals(
                            topBranchItems[i].getText()))
                    {
                        information.setText(topBranchItems[i].getText()
                                + ": Information here");
                        return;
                    }
                }

                table.removeAll();

                String[][] students =
                        studentsList.getStudents(tree.getSelection()[0]
                                .getText());

                for(int i = 0; i < students.length; i++ )
                {
                    TableItem row = new TableItem(table, SWT.NONE);

                    row.setText(1, students[i][3]);
                    row.setText(2, students[i][4]);
                    row.setText(3, students[i][1] + "0" + students[i][2]);
                }

                information.setText(tree.getSelection()[0].getText()
                        + ": Information here\n\n Student in speciality: "
                        + students.length + "\n Students in Kolledz:"
                        + studentsList.size());
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent arg0)
            {
                // Empty
            }
        });

    }

    /**
     * OPTIONS: Title of frame.
     */
    public final static String TITLE        = "Students list";
    /**
     * OPTIONS: Width of frame.
     */
    public final static int    WIDTH        = 650;
    /**
     * OPTIONS: Height of frame.
     */
    public final static int    HEIGHT       = 400;
    /**
     * OPTIONS: Margin of frame.
     */
    public final static int    MARGIN       = 50;

    /**
     * GUI display.
     */
    private Display            display;
    /**
     * 
     */
    private Shell              shell;
    /**
     * Groups tree.
     */
    private Tree               tree;
    /**
     * Root point of tree.
     */
    private TreeItem           root;
    /**
     * Top level of tree, groups.
     */
    private TreeItem[]         topBranchItems;
    /**
     * Low level of tree, groups by semesters.
     */
    private TreeItem[][]       lowBranchItems;
    private Label              information;
    private Table              table;
    private Text               textInput;
    private Button             buttonSearch;
    private StudentsList       studentsList = new StudentsList();
}