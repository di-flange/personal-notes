/**
 * Homework 4
 * 
 * Anton Iskov RDIR42
 * 
 * Реализуйте библиотеку классов, представляющих геометрические фигуры -
 * окружность, ромб, параллелограмм, трапецию, треугольник. Реализуйте методы
 * вычисления площади, периметра, длины ребер. В качестве основы библиотеки
 * опишите интерфейс Figure. Для указания вершин используйте координаты
 * декартовой системы координат. Также реализуйте сортировку геометрических
 * фигур по площади, периметру в порядке возрастания и убывания. (15 баллов)
 */

package figures;

import java.util.Arrays;

/**
 * Set of test scripts. 
 * 
 * @author Anton Ishkov
 */
public class Main
{
    /**
     * Test script.
     * 
     * @param args
     *            Not used.
     */
    public static void main(String[] args)
    {
        // Tests
        // Main.testCircle();
        // Main.testPoints();
        // Main.testTriangle();
        // Main.testTrapezium();
        // Main.testParallelogram();
        // Main.testRhombus();
        Main.testSort();
    }

    /**
     * Test of sort orders.
     */
    public static void testSort()
    {
        // Declare variables
        Figure arr[] =
                new Figure[]
                {
                        new Rhombus(new Point(9.0, 0.0), new Point(0.0, 0.0),
                                new Point(9.0, 9.0), new Point(0.0, 9.0)),
                        new Rhombus(new Point(0.0, 0.0), new Point(3.0, 0.0),
                                new Point(3.0, 3.0), new Point(0.0, 3.0)),

                        new Parallelogram(new Point(0.0, 0.0), new Point(6.0,
                                0.0), new Point(1.0, 5.0), new Point(7.0, 5.0)),
                        new Parallelogram(new Point(0.0, 0.0), new Point(3.0,
                                0.0), new Point(3.0, 3.0), new Point(0.0, 3.0)),

                        new Trapezium(new Point(0.0, 0.0), new Point(4.0, 4.0),
                                new Point(4.0, 5.0), new Point(0.0, 6.0)),
                        new Trapezium(new Point(0.0, 0.0), new Point(3.0, 0.0),
                                new Point(3.0, 1.0), new Point(1.0, 2.0)),

                        new Triangle(new Point(0.0, 0.0),
                                new Point(10.0, 10.0), new Point(31.0, 12.0)),
                        new Triangle(new Point(5.0, 3.0), new Point(2.0, 1.0),
                                new Point(34.0, 10.0)),

                        new Circle(new Point(0.0, 0.0), new Point(10.0, 10.0)),
                        new Circle(new Point(10.0, 10.0), new Point(0.0, 0.0))
                };
        
        // Test one:
        System.out.println("Test 1:");
        
        // Sort
        Arrays.sort(arr, new SquareComparator(true));
        
        // Print report
        for(int i = 0; i < arr.length; i++)
        {
           System.out.println((int)arr[i].getSquare());
        }
        
        // Test one:
        System.out.println("\nTest 2:");
        
        // Sort
        Arrays.sort(arr, new PerimetrComparator(false));
        
        // Print report
        for(int i = 0; i < arr.length; i++)
        {
           System.out.println((int)arr[i].getPerimetr());
        }
    }

    /**
     * Test of Rhombus class.
     */
    public static void testRhombus()
    {
        // Print report
        System.out.println("Rhombus test:");
        System.out.println(new Rhombus(new Point(9.0, 0.0),
                new Point(0.0, 0.0), new Point(9.0, 9.0), new Point(0.0, 9.0)));
        System.out.println(new Rhombus(new Point(0.0, 0.0),
                new Point(3.0, 0.0), new Point(3.0, 3.0), new Point(0.0, 3.0)));

        System.out.println("Edge: "
                + new Rhombus(new Point(9.0, 0.0), new Point(0.0, 0.0),
                        new Point(9.0, 9.0), new Point(0.0, 9.0)).getEdge(2));
        System.out.println("Edge: "
                + new Rhombus(new Point(9.0, 0.0), new Point(0.0, 0.0),
                        new Point(9.0, 9.0), new Point(0.0, 9.0)).getEdge(1));

        System.out
                .println("Perimeter: "
                        + new Rhombus(new Point(9.0, 0.0), new Point(0.0, 0.0),
                                new Point(9.0, 9.0), new Point(0.0, 9.0))
                                .getPerimetr());
        System.out
                .println("Perimeter: "
                        + new Rhombus(new Point(0.0, 0.0), new Point(3.0, 0.0),
                                new Point(3.0, 3.0), new Point(0.0, 3.0))
                                .getPerimetr());

        System.out.println("Square: "
                + new Rhombus(new Point(9.0, 0.0), new Point(0.0, 0.0),
                        new Point(9.0, 9.0), new Point(0.0, 9.0)).getSquare());
        System.out.println("Square: "
                + new Rhombus(new Point(0.0, 0.0), new Point(3.0, 0.0),
                        new Point(3.0, 3.0), new Point(0.0, 3.0)).getSquare());

    }

    /**
     * Test of Parallelogram class
     */
    public static void testParallelogram()
    {
        // Print report
        System.out.println("Parallelogram test:");
        System.out.println(new Parallelogram(new Point(0.0, 0.0), new Point(
                6.0, 0.0), new Point(1.0, 5.0), new Point(7.0, 5.0)));
        System.out.println(new Parallelogram(new Point(0.0, 0.0), new Point(
                3.0, 0.0), new Point(3.0, 3.0), new Point(0.0, 3.0)));

        System.out.println("Edge: "
                + new Parallelogram(new Point(0.0, 0.0), new Point(6.0, 0.0),
                        new Point(1.0, 5.0), new Point(7.0, 5.0)).getEdge(2));
        System.out.println("Edge: "
                + new Parallelogram(new Point(0.0, 0.0), new Point(6.0, 0.0),
                        new Point(1.0, 5.0), new Point(7.0, 5.0)).getEdge(1));

        System.out
                .println("Perimeter: "
                        + new Parallelogram(new Point(0.0, 0.0), new Point(6.0,
                                0.0), new Point(1.0, 5.0), new Point(7.0, 5.0))
                                .getPerimetr());
        System.out
                .println("Perimeter: "
                        + new Parallelogram(new Point(0.0, 0.0), new Point(3.0,
                                0.0), new Point(3.0, 3.0), new Point(0.0, 3.0))
                                .getPerimetr());

        System.out.println("Square: "
                + new Parallelogram(new Point(0.0, 0.0), new Point(6.0, 0.0),
                        new Point(1.0, 5.0), new Point(7.0, 5.0)).getSquare());
        System.out.println("Square: "
                + new Parallelogram(new Point(0.0, 0.0), new Point(3.0, 0.0),
                        new Point(3.0, 3.0), new Point(0.0, 3.0)).getSquare());
    }

    /**
     * Test of Trapezium class
     */
    public static void testTrapezium()
    {
        // Print report
        System.out.println("Trapezium test:");
        System.out.println(new Trapezium(new Point(0.0, 0.0), new Point(4.0,
                4.0), new Point(4.0, 5.0), new Point(0.0, 6.0)));
        System.out.println(new Trapezium(new Point(0.0, 0.0), new Point(3.0,
                0.0), new Point(3.0, 1.0), new Point(1.0, 2.0)));

        System.out.println("Edge: "
                + new Trapezium(new Point(0.0, 0.0), new Point(4.0, 4.0),
                        new Point(4.0, 5.0), new Point(0.0, 6.0)).getEdge(2));
        System.out.println("Edge: "
                + new Trapezium(new Point(0.0, 0.0), new Point(4.0, 4.0),
                        new Point(4.0, 5.0), new Point(0.0, 6.0)).getEdge(1));

        System.out
                .println("Perimeter: "
                        + new Trapezium(new Point(0.0, 0.0),
                                new Point(4.0, 4.0), new Point(4.0, 5.0),
                                new Point(0.0, 6.0)).getPerimetr());
        System.out.println("Perimeter: "
                + new Trapezium(new Point(0.0, 0.0), new Point(6.0, 0.0),
                        new Point(- 4.0, 8.0), new Point(- 3.0, 8.0))
                        .getPerimetr());

        System.out.println("Square: "
                + new Trapezium(new Point(0.0, 0.0), new Point(4.0, 4.0),
                        new Point(4.0, 5.0), new Point(0.0, 6.0)).getSquare());
        System.out.println("Square: "
                + new Trapezium(new Point(0.0, 0.0), new Point(6.0, 0.0),
                        new Point(- 4.0, 8.0), new Point(- 3.0, 8.0))
                        .getSquare());
    }

    /**
     * Test of Triangle class
     */
    public static void testTriangle()
    {
        // Print reports
        System.out.println("Triangle test:");
        System.out.println(new Triangle(new Point(0.0, 0.0), new Point(10.0,
                10.0), new Point(31.0, 12.0)));
        System.out.println(new Triangle(new Point(5.0, 3.0),
                new Point(2.0, 1.0), new Point(34.0, 10.0)));

        System.out.println("Edge: "
                + new Triangle(new Point(0.0, 0.0), new Point(10.0, 10.0),
                        new Point(31.0, 12.0)).getEdge(2));
        System.out.println("Edge: "
                + new Triangle(new Point(0.0, 0.0), new Point(10.0, 10.0),
                        new Point(31.0, 12.0)).getEdge(1));

        System.out.println("Perimeter: "
                + new Triangle(new Point(0.0, 0.0), new Point(10.0, 10.0),
                        new Point(31.0, 12.0)).getPerimetr());
        System.out.println("Perimeter: "
                + new Triangle(new Point(0.0, 0.0), new Point(10.0, 10.0),
                        new Point(31.0, 12.0)).getPerimetr());

        System.out.println("Square: "
                + new Triangle(new Point(0.0, 0.0), new Point(10.0, 10.0),
                        new Point(31.0, 12.0)).getSquare());
        System.out.println("Square: "
                + new Triangle(new Point(0.0, 0.0), new Point(10.0, 10.0),
                        new Point(31.0, 12.0)).getSquare());
    }

    /**
     * Test of Circle class
     */
    public static void testCircle()
    {
        // Print reports
        System.out.println("Circle test:");
        System.out.println(new Circle(new Point(0.0, 0.0),
                new Point(10.0, 10.0)));
        System.out.println(new Circle(new Point(10.0, 10.0),
                new Point(0.0, 0.0)));

        System.out.println("Edge: "
                + new Circle(new Point(0.0, 0.0), new Point(10.0, 10.0))
                        .getEdge(0));
        System.out.println("Edge: "
                + new Circle(new Point(10.0, 10.0), new Point(0.0, 0.0))
                        .getEdge(0));

        System.out.println("Perimeter: "
                + new Circle(new Point(0.0, 0.0), new Point(10.0, 10.0))
                        .getPerimetr());
        System.out.println("Perimeter: "
                + new Circle(new Point(10.0, 10.0), new Point(0.0, 0.0))
                        .getPerimetr());

        System.out.println("Square: "
                + new Circle(new Point(0.0, 0.0), new Point(10.0, 10.0))
                        .getSquare());
        System.out.println("Square: "
                + new Circle(new Point(10.0, 10.0), new Point(0.0, 0.0))
                        .getSquare());
    }

    /**
     * Test of Point class
     */
    public static void testPoints()
    {
        // Print reports
        System.out.println("Points test:");
        System.out.println("Point: " + new Point(0, 0));
        System.out.println("Point: " + new Point(14, 19));

        System.out.println("Distance: "
                + new Point(0, 0).distance(new Point(7, 10)));
        System.out.println("Distance: "
                + new Point(0, 0).distance(new Point(55, 10)));

        System.out.println("Angle: "
                + new Point(0, 0).angle(new Point(1, 0), new Point(0, 1)));
        System.out.println("Angle: "
                + new Point(0, 0).angle(new Point(1, 0), new Point(1, 1)));
    }
}