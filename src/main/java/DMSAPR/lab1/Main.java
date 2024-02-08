package DMSAPR.lab1;

import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] adjacent = new int[6][6];
        for (int j = 0; j < adjacent[0].length; j++) {
            String row = input.nextLine();
            int rowIndex = 0;
            int[] matrixRow = new int[6];
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == '1') {
                    matrixRow[rowIndex++] = 1;
                } else if (row.charAt(i) == '0') {
                    matrixRow[rowIndex++] = 0;
                }
            }
            System.arraycopy(matrixRow, 0, adjacent[j], 0, matrixRow.length);
        }
        int[][] incident = Converter.adjacentToIncident(adjacent);
        for (int[] row : incident) {
            System.out.println(Arrays.toString(row));
        }
    }
}
