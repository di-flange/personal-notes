/**
 * Homework9-1
 */
package bank;

import java.util.concurrent.locks.*;

/**
 * Класс банка, который перекидывает деньги с одного счёта на другой. Мы
 * предпологаем что операции первода могут запрашиваться из разных потоков. При
 * этом нам необходимо защитить наши данные от повреждения такой хаотичной
 * работой потоков. Для чего мы блокируем выполнение других запросов к банку на
 * время переводаю.
 * 
 * Так же реализована ситуация не готовности перевода. Так если денег не хватает
 * для перевода суммы, мы усыпляем поток до момента когда будет осуществлён
 * какой либо другой перевод. (Если перевод был к нам и денег теперь хватит то
 * мы продолжим работать, но если денег до сих пор не хватает то мы опять
 * засыпаем).
 * 
 * A bank with a number of bank accounts that uses locks for serializing access.
 * 
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class Bank
{
    /**
     * Constructs the bank.
     * 
     * Собираемся, а именно вносим количество счетов и баланс.
     * 
     * @param n
     *            the number of accounts
     * @param initialBalance
     *            the initial balance for each account
     */
    public Bank(int n, double initialBalance)
    {
        // Массив счетов
        accounts = new double[n];

        // Проставляем балансы
        for(int i = 0; i < accounts.length; i++ )
        {
            accounts[i] = initialBalance;
        }

        // Создаём засчёлку =)
        bankLock = new ReentrantLock();

        // Отмемечаем состояние потока
        sufficientFunds = bankLock.newCondition();
    }

    /**
     * Transfers money from one account to another.
     * 
     * Переводим деньги со счёта на счёт.
     * 
     * @param from
     *            the account to transfer from
     * @param to
     *            the account to transfer to
     * @param amount
     *            the amount to transfer
     */
    public void transfer(int from, int to, double amount)
            throws InterruptedException
    {
        // Закрываем бынк от возможности других запросов.
        bankLock.lock();

        try
        {
            // Спим если денег нет, а чего ещё остаёться ^^
            while(accounts[from] < amount)
                sufficientFunds.await();

            // Пишем текущий поток в консоль
            System.out.print(Thread.currentThread());

            // Снимаем деньги и пишем об этом отчёт
            accounts[from] -= amount;

            System.out.printf(" %10.2f from %d to %d", amount, from, to);

            // Повышаем баланс получателя
            accounts[to] += amount;

            // Не дня без строчки, общий баланс как был так и остался
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

            // Будим всех может им пора за работу =)
            sufficientFunds.signalAll();
        }
        finally
        {
            // Разблокировались
            bankLock.unlock();
        }
    }

    /**
     * Сумма всех счетов, в теории это класс должен быть приватным раз не
     * запрашиваеться из вне и тогда блокировку делать не надо так как она
     * происходит при старте перевода (единственного места где эта информация
     * запрашивается) хотя я и не исключаю что есть в этом и какой-то сокральный
     * смысл.
     * 
     * Gets the sum of all account balances.
     * 
     * @return the total balance
     */
    public double getTotalBalance()
    {
        // Блокируемся
        bankLock.lock();
        try
        {
            // Считаем всё
            double sum = 0;

            for(double a : accounts)
                sum += a;

            return sum;
        }
        finally
        {
            // Разблокируемся
            bankLock.unlock();
        }
    }

    /**
     * Количество счетов в банке.
     * 
     * Gets the number of accounts in the bank.
     * 
     * @return the number of accounts
     */
    public int size()
    {
        return accounts.length;
    }

    /**
     * Массив счетов.
     */
    private final double[] accounts;
    /**
     * Блокировщик банковских операций.
     */
    private Lock           bankLock;
    /**
     * Состояние потока.
     */
    private Condition      sufficientFunds;
}