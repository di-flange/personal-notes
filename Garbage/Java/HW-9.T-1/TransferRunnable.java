package bank;

/**
 * Класс посути создаёт польщователя который может жить в своём потоке и
 * распоряжаться своим счётом.
 * 
 * A runnable that transfers money from an account to other accounts in a bank.
 * 
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class TransferRunnable implements Runnable
{
    /**
     * Собираемся: задаём окружение и параметры генериремых переводов.
     * 
     * Constructs a transfer runnable.
     * 
     * @param b
     *            the bank between whose account money is transferred
     * @param from
     *            the account to transfer money from
     * @param max
     *            the maximum amount of money in each transfer
     */
    public TransferRunnable(Bank b, int from, double max)
    {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    /**
     * Так как в итоге объект помещаеться в поток нам нужен метод который будет
     * в нём выполняться.
     */
    public void run()
    {
        try
        {
            /**
             * Программа о которой мечтает каждый отмыватель денег =)
             * 
             * Моделирует активность владельца счёта который шлёт свои деньги на
             * разные счета. Для экономии китайцев выполнена на базе рандома.
             * 
             * Тоесть на случайный счёт в случайный промежуток времени посылаем
             * случайную сумму.
             */
            while(true)
            {
                int toAccount = (int)( bank.size() * Math.random() );
                double amount = maxAmount * Math.random();

                bank.transfer(fromAccount, toAccount, amount);

                Thread.sleep((int)( DELAY * Math.random() ));
            }
        }
        catch(InterruptedException e)
        {
            // 0_о
        }
    }

    /**
     * Наш банк.
     */
    private Bank   bank;
    /**
     * Отправитель.
     */
    private int    fromAccount;
    /**
     * Максимум перевода
     */
    private double maxAmount;
    /**
     * Коофицент увеличения задержки
     */
    private int    DELAY = 10;
}