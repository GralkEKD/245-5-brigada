package DMSAPR.lab1;

import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите количество вершин графа: ");
        int n = input.nextInt();
        System.out.println("Введите матрицу смежности построчно:");
        int[][] adjacent = new int[n][n];
        input.nextLine(); // хз зачем ему надо это, но без этого не работает
        for (int j = 0; j < n; j++) {
            String row = input.nextLine();
            int rowIndex = 0;
            int[] matrixRow = new int[n];
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == '1') {
                    matrixRow[rowIndex++] = 1;
                } else if (row.charAt(i) == '0') {
                    matrixRow[rowIndex++] = 0;
                } else if (row.charAt(i) == '-' && row.charAt(i + 1) == '1') {
                    matrixRow[rowIndex++] = -1;
                }
            }
            System.arraycopy(matrixRow, 0, adjacent[j], 0, matrixRow.length);
        }
        System.out.println("Матрица инцидентности:");
        int[][] incident = Converter.adjacentToIncident(adjacent);
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : incident) {
            for (int elem : row) {
                stringBuilder.append(elem).append("\t");
            }
            stringBuilder.append("\n");
        }
        int[][] matches = Converter.directMatch(incident);
        for (int[] row :
                matches) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(stringBuilder + "\n");
        System.out.println("Матрица смежности:");
        adjacent = Converter.incidentToAdjacent(incident);
        stringBuilder = new StringBuilder();
        for (int[] row : adjacent) {
            for (int elem : row) {
                stringBuilder.append(elem).append("\t");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
