package PPEVM.lab2;

import java.util.Scanner;

public class Task1 {

    private static int[] insertedNumbers;

    private static boolean hasOnlyEvenNumbers(int number) {
        int copy = number;
        while (copy > 0) {
            if (copy % 2 != 0) {
                return false;
            }
            copy /= 10;
        }
        return true;
    }

    private static boolean hasEqualEvensAndOdds(int number) {
        int copy = number;
        int evenCount = 0, oddCount = 0;
        while (copy > 0) {
            if (copy % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            copy /= 10;
        }
        return evenCount == oddCount;
    }

    private static int countNumbersWithOnlyEvens() {
        int counter = 0;
        for (int number : insertedNumbers) {
            if (hasOnlyEvenNumbers(number)) {
                counter++;
            }
        }
        return counter;
    }

    private static int countNumbersWithEqualEvensAndOdds() {
        int counter = 0;
        for (int number : insertedNumbers) {
            if (hasEqualEvensAndOdds(number)) {
                counter++;
            }
        }
        return counter;
    }

    private static void insertValues() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите количество N вводимых чисел: ");
        int n = input.nextInt();
        insertedNumbers = new int[n];
        for (int i = 1; i <= n; i++) {
            System.out.printf("Введите %d-е число: ", i);
            insertedNumbers[i - 1] = input.nextInt();
        }
    }

    public static void task1() {
        insertValues();
        int onlyEvensCounter = countNumbersWithOnlyEvens();
        int equalEvensAndOddsCounter = countNumbersWithEqualEvensAndOdds();
        System.out.println("Количество чисел, содержащих только четные цифры: " + onlyEvensCounter);
        System.out.println("Количество чисел, содержащих равное количество четных и нечетных цифр: " +
                equalEvensAndOddsCounter);
    }
}
