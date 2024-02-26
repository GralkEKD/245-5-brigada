package PPEVM.lab3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book[] books = ArraysOfObjects.booksArray();
        System.out.println("Книги за авторством Лермонтова:");
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            if (book.checkAuthor("Лермонтов")) sb.append(book);
        }
        System.out.println(sb);
        System.out.println("Книги, выпущенные издательством \"Ленинград\":");
        sb = new StringBuilder();
        for (Book book : books) {
            if (book.checkPublishing("Ленинград")) sb.append(book);
        }
        System.out.println(sb);
        System.out.println("Книги, изданые после 1980 года:");
        sb = new StringBuilder();
        for (Book book : books) {
            if (book.checkYear(1980)) sb.append(book);
        }
        System.out.println(sb);

        Interval[] intervals = ArraysOfObjects.intervalsArray();
        System.out.println("Объединение интервалов 1 и 2: " +
                intervals[0].union(intervals[1]) + "\n" +
                "Пересечение интервалов 2 и 3: " +
                intervals[1].intersect(intervals[2]) + "\n" +
                "Объединение интервалов 2 и 4: " +
                intervals[1].union(intervals[3]) + "\n" +
                "Пересечение интервалов 1 и 4: " +
                intervals[0].intersect(intervals[3]));
        System.out.printf("Расстояние между двумя самыми отдаленными точками = %.1f\n",
                ArraysOfObjects.lengthBetweenTwoFurthestPoints(intervals));

        Complex[] complexes = ArraysOfObjects.complexArray();
        ArrayList<Complex> numbersList = new ArrayList<>(List.of(complexes));
        System.out.println("Пары комплексных координат: ");
        for (Complex num : complexes) {
            System.out.println(num);
        }
        System.out.println("Сумма: " + Complex.sum(numbersList));
        System.out.println("Произведение: " + Complex.product(numbersList));
    }
}
