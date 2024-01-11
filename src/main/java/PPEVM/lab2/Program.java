package PPEVM.lab2;
import java.util.Scanner;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите количество чисел: ");
        int n = input.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("%d-е число: ", i + 1);
            numbers[i] = input.nextInt();
        }
        task1(numbers);
        task2();
        System.out.print("Введите размерность матрицы N x N: ");
        n = input.nextInt();
        System.out.print("Введите число k <= N: ");
        int k = input.nextInt();
        task3(n, k);
    }
    static void task1(int[] nums) {
        int count = 0;
        for (int elem : nums) {
            boolean flag = true;
            while (elem > 0) {
                if (elem % 2 != 0) {
                    flag = false;
                    break;
                }
                elem /= 10;
            }
            if (flag) {
                count++;
            }
        }
        System.out.printf("Количество чисел: %d\n", count);
    }
    static void task2() {
        int number = 129;
        int count = 0;
        while (number > 0) {
            if (number % 2 == 0) {
                count++;
            }
            number /= 2;
        }
        System.out.printf("Количество значащих нулей в числе 129 = %d\n", count);
    }
    static void task3(int n, int k) {
        Random random = new Random(System.currentTimeMillis());
        int[][] matrix = new int[n][n];
        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aux[j] = random.nextInt();
            }
            System.arraycopy(aux, 0, matrix[i], 0, matrix[i].length);
        }
        System.out.println("Числа из матрицы от 1-го до k-го по диагонали: ");
        for (int i = 0; i < k; i++) {
            System.out.print(matrix[i][i] + " ");
        }
    }
}
