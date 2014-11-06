package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * –исуем окошко, формочки, кнопочки после чего прописываем на кнопку, старт
 * гонки. ќн грузит новые потоки дл€ каждого гонщика. ѕри старте потока каждый
 * гонщик начинает двигатьс€ к финишу до тех пор пока его не достигнет. ѕри этом
 * он посто€нно провер€ет переменную ЁЌƒ не подошла ли гонка к концу. ≈сли он
 * достигает финиша первым то по средствам этой переменной он информирует других
 * гонщиков что дальше можно не ехать, они проиграли, на этом учатке они
 * синхронизированы и не могут зайти в состо€ние сохранени€ своего результата
 * одновременно.
 * 
 * @author Anton Ishkov
 */
public class MainGui extends JFrame
{
    public MainGui()
    {
        super.setTitle(MainGui.FRAME_TITLE);

        super.setBounds(MainGui.FRAME_MARGIN, MainGui.FRAME_MARGIN,
                MainGui.FRAME_WIDTH, MainGui.FRAME_HEIGTH);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.setResizable(false);

        this.initComponents();
        this.initListeners();
        this.createLayout();

        super.setVisible(true);
    }

    private void createLayout()
    {
        JPanel panelMain = new JPanel();

        panelMain.add(new JLabel("Your score: "));
        panelMain.add(this.score);
        panelMain.add(new JLabel("Your favorite number(1-3): "));
        panelMain.add(this.favorite);
        panelMain.add(new JLabel("Your rate: "));
        panelMain.add(this.rate);
        panelMain.add(this.button);
        panelMain.add(this.messag);

        add(panelMain);
    }

    private void initComponents()
    {
        this.score = new JLabel("100");
        this.messag = new JLabel("Try it");
        this.favorite = new JTextField(3);
        this.rate = new JTextField(5);
        this.button = new JButton("Play");

        this.racers = new Racer[]
        {
                new Racer(this, 0), new Racer(this, 1), new Racer(this, 2)
        };
    }

    synchronized protected void saveResult(int i)
    {
        if(this.end)
        {
            return;
        }
        
        this.end = true;

        int intFavorite;
        double doubleRate;

        try
        {
            intFavorite = Integer.valueOf(this.favorite.getText()) - 1;
            doubleRate = Double.valueOf(this.rate.getText());
        }
        catch(Exception e)
        {
            return;
        }

        if(i == intFavorite)
        {
            this.money += doubleRate;
            this.messag.setText("=/ You win.");
        }
        else
        {
            this.money -= doubleRate * 99;
            this.messag.setText("^^ You lost. BUgaga");
        }

        this.score.setText(String.valueOf(this.money));

    }

    private void initListeners()
    {
        this.button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                end = false;

                threads =
                        new Thread[]
                        {
                                new Thread(racers[0]),
                                new Thread(racers[1]),
                                new Thread(racers[2])
                        };

                for(int i = 0; i < threads.length; i++ )
                {
                    threads[i].setPriority(5);
                    threads[i].start();
                }
            }
        });
    }

    private static final long     serialVersionUID = 1L;
    protected final static int    FRAME_WIDTH      = 600;
    protected final static int    FRAME_HEIGTH     = 200;
    protected final static int    FRAME_MARGIN     = 25;
    protected final static String FRAME_TITLE      = "Threads: game";
    private JButton               button;
    private JTextField            rate;
    private JTextField            favorite;
    private JLabel                score;
    private Thread                threads[];
    private Racer                 racers[];
    private double                money            = 100;
    protected boolean             end              = false;
    private JLabel                messag;
}