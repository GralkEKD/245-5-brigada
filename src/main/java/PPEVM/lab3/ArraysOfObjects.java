package PPEVM.lab3;

public class ArraysOfObjects {

    public static Book[] booksArray() {
        return new Book[]{
                new Book(1, "Герой нашего времени", "Лермонтов М.Ю.", "Ленинград", 1970, 250, 500, "Твердый"),
                new Book(2, "Горе от ума", "Грибоедов А.С.", "Ленинград", 1998, 345, 650, "Твердый"),
                new Book(3, "Мцыри", "Лермонтов М.Ю.", "Печать Страны", 2005, 120, 200, "Мягкий")
        };
    }

    public static Interval[] intervalsArray() {
        return new Interval[] {
                new Interval(2.2, true, 10.7, true), // [2.2; 10.7]
                new Interval(-2.4, 7.2), // (-2.4; 7.2)
                new Interval(5.5, 5.5), // [5.5; 5.5] (точка 5.5)
                new Interval(Double.NEGATIVE_INFINITY, false, -0.7, true) // (-inf; -0.7]
        };
    }

    public static Complex[] complexArray() {
        return new Complex[] {
                new Complex(-1.4, 5.2),
                new Complex(5.3, 0.6),
                new Complex(-5.6, -7.8)
        };
    }
}
