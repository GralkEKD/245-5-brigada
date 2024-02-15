package PPEVM.lab2;

public class Main {

    private static void printProgrammer() {
        System.out.println("""
                Программисты:
                Бурцев Никита, Кочеткова Ирина, бригада №5
                Дата получения задания:
                12 февраля 2024 г.
                """);
    }

    public static void main(String[] args) {
        printProgrammer();
        Task1.task1();
        Task2.task2();
        Task3.task3();
    }
}
