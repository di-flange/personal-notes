package game;

/**
 * √онщик начинает идти к финишу при старте потока, перемеща€сь через равные
 * промежутки времени на разную дистанцию.
 * 
 * ѕеред новым перемещением он провер€ет не закончилась ли гонка пока он спал.
 * 
 * ѕрид€ к финишу гонщик обращаетс€ к окну, данному ему при инициализации, с
 * просьбой сохранить его результат.
 * 
 * @author Anton Ishkov
 * 
 */
class Racer implements Runnable
{
    private MainGui   spot;
    private int       num;

    private int       currentX;
    private int       currentY;

    private int       layers = 100;

    private final int START  = 50;
    private final int END    = 550;

    private final int RADIUS = 10;

    public Racer(MainGui spot, int i)
    {
        this.spot = spot;

        this.currentX = this.START;
        this.currentY = this.layers + 20 * i;

        this.num = i;
    }

    public void run()
    {
        this.currentX = 0;

        try
        {
            while(this.move())
            {
                Thread.sleep((int)( 100 ));

                if(this.spot.end == true)
                {
                    this.spot.getGraphics().clearRect(this.currentX,
                            this.currentY, this.RADIUS, this.RADIUS);
                    this.currentX = this.START;

                    return;
                }
            }

            this.currentX = this.START;

            synchronized(this.spot)
            {
                this.spot.saveResult(this.num);
            }
        }
        catch(Exception e)
        {
            return;
        }
    }

    private boolean move()
    {
        this.spot.getGraphics().clearRect(this.currentX, this.currentY,
                this.RADIUS, this.RADIUS);

        this.currentX += Math.random() * 15;

        if(this.currentX >= this.END)
        {
            return false;
        }

        this.spot.getGraphics().fillOval(this.currentX, this.currentY, 10, 10);

        return true;
    }
}