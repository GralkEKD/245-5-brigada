package PPEVM.lab3;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    public static void task1() {
        Book[] books = ArraysOfObjects.booksArray();
        System.out.println("Книги за авторством Лермонтова:\n" +
                booksByAuthor(books));
        System.out.println("Книги, выпущенные издательством \"Ленинград\":\n" +
                booksByPublishing(books));
        System.out.println("Книги, изданые после 1980 года:\n" +
                booksByYear(books));
    }

    public static void task2() {
        Interval[] intervals = ArraysOfObjects.intervalsArray();
        System.out.println(intersectionsAndUnions(intervals));
        System.out.printf("Расстояние между двумя самыми отдаленными точками = %.1f\n",
                lengthBetweenTwoFurthestPoints(intervals));
    }

    public static void task3() {
        Complex[] complexes = ArraysOfObjects.complexArray();
        System.out.println(complexesWithSumAndProduct(complexes));
    }
    private static String booksByAuthor(Book[] books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            if (book.checkAuthor("Лермонтов")) sb.append(book);
        }
        return sb.toString();
    }

    private static String booksByPublishing(Book[] books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            if (book.checkPublishing("Ленинград")) sb.append(book);
        }
        return sb.toString();
    }

    private static String booksByYear(Book[] books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            if (book.checkYear(1980)) sb.append(book);
        }
        return sb.toString();
    }

    private static String intersectionsAndUnions(Interval[] intervals) {
        return "Объединение интервалов 1 и 2: " +
                intervals[0].union(intervals[1]) + "\n" +
                "Пересечение интервалов 2 и 3: " +
                intervals[1].intersect(intervals[2]) + "\n" +
                "Объединение интервалов 2 и 4: " +
                intervals[1].union(intervals[3]) + "\n" +
                "Пересечение интервалов 1 и 4: " +
                intervals[0].intersect(intervals[3]);
    }

    public static double lengthBetweenTwoFurthestPoints(Interval[] intervals) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (Interval interval : intervals) {
            if (interval.getLeftBoundary() < min && interval.getLeftBoundary() != Double.NEGATIVE_INFINITY) {
                min = interval.getLeftBoundary();
            }
            if (interval.getRightBoundary() > max && interval.getRightBoundary() != Double.POSITIVE_INFINITY) {
                max = interval.getRightBoundary();
            }
        }
        return max - min;
    }

    private static String complexesWithSumAndProduct(Complex[] complexes) {
        ArrayList<Complex> numbersList = new ArrayList<>(List.of(complexes));
        StringBuilder sb = new StringBuilder("Пары комплексных координат:\n");

        for (Complex num : complexes) {
            sb.append(num).append("\n");
        }
        sb.append("Сумма:\n").append(Complex.sum(numbersList)).append("\n");
        sb.append("Произведение:\n").append(Complex.product(numbersList));
        return sb.toString();
    }
}