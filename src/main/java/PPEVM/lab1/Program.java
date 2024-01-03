package PPEVM.lab1;

import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        // Отобразить в окне консоли аргументы командной строки в обратном порядке.
        int i = args.length - 1;
        while(i >= 0) {
            System.out.println(args[i--]);
        }
        /* Ввести с консоли n целых чисел. На консоль вывести  Все трехзначные числа, в десятичной записи которых нет
        одинаковых цифр.
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Введите количество чисел: ");
        int n = input.nextInt();
        int[] numbers = new int[n];
        for (i = 0; i < n; i++) {
            System.out.printf("%d-е число: ", i + 1);
            numbers[i] = input.nextInt();
        }
        for (int v: numbers) {
            if (v > 99 && v < 1000) {
                if (v % 10 != (v / 10) % 10 && v % 10 != v / 100 && v / 100 != (v / 10) % 10) {
                    System.out.println(v);
                }
            }
        }
    }
}
