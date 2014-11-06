package bank;

/**
 * Мы создаём объект банка который имеет 100 счетов с балансом 100000. После
 * чего создаём Для каждого счёта по "пользователю" который будет всё это время
 * пересылать деньги со своего счёта.
 * 
 * Интерес представляет работа блокировок в самом классе банк так как всё
 * остальное лишь активность создаваемая вокруг него для проверки его работы.
 * 
 * This program shows how multiple threads can safely access a data structure.
 * 
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class SynchBankTest
{
    public static void main(String[] args)
    {
        // Создаём свой банк
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;

        // Для каждого счёта создаём "пользователя" - класс TransferRunnable, и
        // момещаем его в поток.
        for(i = 0; i < NACCOUNTS; i++ )
        {
            TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);

            Thread t = new Thread(r);

            t.start();
        }
    }

    /**
     * Количество счетов
     */
    public static final int    NACCOUNTS       = 100;
    /**
     * Стартовый баланс
     */
    public static final double INITIAL_BALANCE = 1000;
}