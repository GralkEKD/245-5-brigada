package DMSAPR.lab2;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {-1, -1, -1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, -1, 0, 1, 1, 0},
                {0, 1, 0, 0, -1, -1, 0, 1},
                {0, 0, 1, 0, 0, 0, -1, -1}
        };
        int[][] sorted1 = TopologicalSorting.sortedIncident(matrix1);
        TopologicalSorting.printMatrix(sorted1);
        int[][] matrix2 = {
                {0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 0, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0}
        };
        int[][] sorted2 = TopologicalSorting.sortedAdjacent(matrix2);
        TopologicalSorting.printMatrix(sorted2);
    }
}
