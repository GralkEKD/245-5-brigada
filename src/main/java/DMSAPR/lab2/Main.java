package DMSAPR.lab2;


public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {-10, -5, -6, 0, 0, 0, 0, 0},
                {10, 0, 0, 1, 2, 0, 0, 0},
                {0, 0, 0, -1, 0, 9, 8, 0},
                {0, 5, 0, 0, -2, -9, 0, 7},
                {0, 0, 6, 0, 0, 0, -8, -7}
        };
        int[][] sorted1 = Dynamic.sortedIncident(matrix1);
        Dynamic.printMatrix(sorted1);
        int[][] matrix2 = {
                {0, 0, 0, 0, 0},
                {18, 0, 5, 10, 0},
                {0, 0, 0, 3, 4},
                {2, 0, 0, 0, 3},
                {5, 0, 0, 0, 0}
        };
        int[][] sorted2 = Dynamic.sortedAdjacent(matrix2);
        Dynamic.printMatrix(sorted2);
        System.out.println(Dynamic.shortestWayAdjacent(sorted2));
    }
}
