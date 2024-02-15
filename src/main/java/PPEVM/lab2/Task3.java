package PPEVM.lab2;

import java.util.Random;
import java.util.Scanner;

public class Task3 {
    private static double[][] matrix;

    private static void generateMatrix(int n) {
        matrix = new double[n][n];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            double[] row = new double[n];
            for (int j = 0; j < n; j++) {
                row[j] = (random.nextDouble(-n, n));
            }
            System.arraycopy(row, 0, matrix[i], 0, n);
        }
    }

    private static int countMatchingElements(double k) {
        int count = 0;
        for (double[] row : matrix) {
            for (double elem : row) {
                if (1 <= elem && elem <= k) {
                    count++;
                }
            }
        }
        return count;
    }

    private static double[][] newMatrix(double k) {
        int N = (int) Math.ceil(Math.sqrt(countMatchingElements(k)));
        double[][] newMatrix = new double[N][N];
        double[] newRow = new double[N];
        int rowPointer = 0, colPointer = 0;
        for (double[] row : matrix) {
            for (double elem : row) {
                if (1 <= elem && elem <= k) {
                    if (colPointer >= N) {
                        System.arraycopy(newRow, 0, newMatrix[rowPointer++], 0, N);
                        colPointer = 0;
                        newRow = new double[N];
                    }
                    newRow[colPointer++] = elem;
                }
            }
        }
        System.arraycopy(newRow, 0, newMatrix[rowPointer], 0, N);
        return newMatrix;
    }

    private static void printMatrix(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            for (double elem : row) {
                sb.append(elem).append("\t");
            }
            sb.append("\n");
        }
        System.out.println("Итоговая матрица:");
        System.out.println(sb);
    }
    public static void task3() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите число N - размерность матрицы N x N: ");
        generateMatrix(input.nextInt());
        System.out.print("Введите число k - правая граница интервала от 1 до k: ");
        printMatrix(newMatrix(input.nextDouble()));
        input.close();
    }
}
