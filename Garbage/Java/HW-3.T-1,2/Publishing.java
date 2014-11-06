/**
 * RDIR-42 
 * Anton Ishkov
 * 
 * Homework 3 1.
 * 
 * Представьте себе издательскую компанию, которая торгует книгами
 * и аудио-записями этих книг. Создайте класс Publication, в котором хранятся
 * код книги (автосчетчик), название книги, автор, на каком языке (можно
 * использовать перечисление - enum), описание. От этого класса наследуются еще
 * два класса:
 * 
 * Book, который содержит информацию о количестве страниц в книге, цена.
 * 
 * Type, который содержит время записи книги в минутах, секундах, формат записи
 * файла (можно использовать перечисление), размер файла
 * 
 * В каждом из этих трех классов должен быть метод getData(), через который
 * можно получать данные от пользователя с клавиатуры, и toString(),
 * предназначенный для вывода этих данных. Также необходимо реализовать методы –
 * поиск книги по названию и автору, сортировка книг по цене. Придумайте и
 * реализуйте свои 2-3 метода в классах.
 * 
 * Изучите самостоятельно, как можно создавать документацию к классам с
 * использованием java doc. Создайте документацию для Ваших созданных классов.
 */
package publishing;

import java.io.*;

/**
 * Test script for main functionality.
 * 
 * @author Anton Ishkov
 */
public class Publishing
{
    /**
     * Test script for main functionality.
     * 
     * @param args
     *            Not used
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        // Declare variables
        Publication pub[] = new Publication[]
        {
                new Publication("Beginning Cryptography in Java", "David Hook",
                        Language.EN,
                        "While cryptography can still be a controversial..."),
                new Publication("Java 2: Reference Guide", "Barcharts",
                        Language.EN,
                        "Platform-independent programming language (code)...")};
        // ---
        Book book[] = new Book[]
        {
                new Book(702, (float)724.00, "Java Concepts for Java 5 and 6.",
                        "Cay S. Horstmann", Language.EN,
                        "Let Cay Horstmann teach you to think like a programmer!"),
                new Book(235, (float)11.99,
                        "The Geeks' Guide to World Domination", "Garth Sundem",
                        Language.EN, "Sorry, beautiful people."),
                new Book(480, (float)420.00, pub[0]),
                new Book(490, (float)422.00, pub[1])};
        // ---
        Type type[] = new Type[]
        {
                new Type((float)435.7, "wav", (float)72.00,
                        "Java Concepts for Java 5 and 6.", "Cay S. Horstmann",
                        Language.EN,
                        "Let Cay Horstmann teach you to think like a programmer!"),
                new Type((float)100.50, "mp3", (float)10.5, pub[0])};
        // ---

        // Print report
        System.out.println("Object created:");
        System.out.println("   Publication1 - " + pub[0]);
        System.out.println("   Publication2 - " + pub[1]);
        System.out.println("   Book1 - " + book[0] + "EEK");
        System.out.println("   Book2 - " + book[1] + "EEK");
        System.out.println("   Type1 - " + type[0] + "EEK");
        System.out.println("   Type2 - " + type[1] + "EEK");
        // ---
        System.out.println("Get data:");
        if(pub[0].getData("all"))
        {
            System.out.println("   Publication1 - " + pub[0]);
        }
        else
        {
            System.out.println("   Publication1 - INPUT ERROR");
        }
        if(pub[1].getData("name"))
        {
            System.out.println("   Publication2 - " + pub[1]);
        }
        else
        {
            System.out.println("   Publication2 - INPUT ERROR");
        }
        if(book[3].getData("all"))
        {
            System.out.println("   Book4 - " + book[3]);
        }
        else
        {
            System.out.println("   Book4 - INPUT ERROR");
        }
        if(type[0].getData("all"))
        {
            System.out.println("   Type1 - " + type[0] + " "
                    + type[0].getLength());
        }
        else
        {
            System.out.println("   Type1 - INPUT ERROR");
        }
        // ---
        System.out.println("Search:");
        // This line can drop program with exception if author not found. It in
        // current set because it is using for test.
        System.out.println("   Book where author is Cay S. Horstmann - "
                + book[Book.search("Cay S. Horstmann", book, "author")]);
        // ---
        System.out.println("Sort:");
        System.out.println("   Book1 - " + book[0]);
        System.out.println("   Book2 - " + book[1]);
        System.out.println("   Book3 - " + book[2]);
        System.out.println("   Book4 - " + book[3]);
        Book.sort(book, "asc");
        System.out.println("   Book1 - " + book[0]);
        System.out.println("   Book2 - " + book[1]);
        System.out.println("   Book3 - " + book[2]);
        System.out.println("   Book4 - " + book[3]);
        // ---
    }
}