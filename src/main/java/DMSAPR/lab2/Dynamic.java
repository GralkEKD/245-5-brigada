package DMSAPR.lab2;

import DMSAPR.function.*;

import java.util.Arrays;

public class Dynamic {
    private static int min(int... nums) {
        int min = Integer.MAX_VALUE;
        for (int elem : nums) {
            if (elem < min) min = elem;
        }
        return min;
    }
    public static int[][] sortedIncident(int[][] incidence) {
        IntMatrixConsumer nullifyColumn = ((matrix, rowIndex, colIndex) -> {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][colIndex] = Integer.MAX_VALUE;
            }
        });
        IntMatrixPredicate isRowLessOrEqualZero = ((matrix, rowIndex, colIndex) -> {
            for (int num : matrix[rowIndex]) {
                if (num > 0 && num != Integer.MAX_VALUE || num == Integer.MIN_VALUE) return false;
            }
            return true;
        });
        int[][] copy = new int[incidence.length][];
        int[][] returned = new int[incidence.length][];
        for (int row = 0; row < incidence.length; row++) {
            copy[row] = new int[incidence[row].length];
            System.arraycopy(incidence[row], 0, copy[row], 0, incidence[row].length);
            returned[row] = new int[incidence[row].length];
        }
        int i = incidence.length;
        while (i > 0) {
            for (int k = incidence.length - 1; k >= 0; k--) {
                if(isRowLessOrEqualZero.test(copy, k, 0)) {
                    for (int j = 0; j < incidence[k].length; j++) {
                        if (incidence[k][j] != 0) nullifyColumn.apply(copy, k, j);
                        copy[k][j] = Integer.MIN_VALUE;
                    }
                    System.arraycopy(incidence[k], 0, returned[--i], 0, incidence[k].length);
                    break;
                }
            }
        }
        return returned;
    }

    public static int shortestWayAdjacent(int[][] adjacency) {
        int[] marks = new int[adjacency.length];
        Arrays.fill(marks, Integer.MAX_VALUE);
        marks[0] = 0;
        for (int i = 1; i < adjacency.length; i++) {
            int[] nums = new int[adjacency.length];
            for (int j = 0; j < adjacency.length; j++) {
                if (adjacency[j][i] == Integer.MAX_VALUE || marks[j] == Integer.MAX_VALUE) {
                    nums[j] = Integer.MAX_VALUE;
                } else {
                    nums[j] = marks[j] + adjacency[j][i];
                }
            }
            marks[i] = min(nums);
        }
        return marks[marks.length - 1];
    }

    public static int[][] sortedAdjacent(int[][] adjacency) {
        IntMatrixPredicate isRowZero = ((matrix, rowIndex, colIndex) -> {
            int s = 0, k = 0;
            for (int elem : matrix[rowIndex]) {
                if (elem != Integer.MIN_VALUE) s += elem;
                else k += 1;
            }
            return s == 0 && k != matrix.length;
        });

        int[][] copy = new int[adjacency.length][];
        int[][] returned = new int[adjacency.length][];
        for (int row = 0; row < adjacency.length; row++) {
            copy[row] = new int[adjacency[row].length];
            System.arraycopy(adjacency[row], 0, copy[row], 0, adjacency[row].length);
            returned[row] = new int[adjacency[row].length];
        }
        int i = adjacency.length;
        int[] indices = new int[i];
        while (i > 0) {
            for (int k = 0; k < adjacency.length; k++) {
                if (isRowZero.test(copy, k, 0)) {
                    for (int j = 0; j < adjacency.length; j++) {
                        copy[j][k] = Integer.MIN_VALUE;
                        copy[k][j] = Integer.MIN_VALUE;
                    }
                    indices[--i] = k;
                    break;
                }
            }
        }
        for (i = 0; i < adjacency.length; i++) {
            for (int j = 0; j < adjacency.length; j++) {
                int inserted = adjacency[indices[i]][indices[j]];
                returned[i][j] = inserted == 0 ? Integer.MAX_VALUE : inserted;
            }
        }
        return returned;
    }

    public static void printMatrix(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : matrix) {
            for (int elem : row) {
                stringBuilder.append(elem == Integer.MAX_VALUE ? "%" : elem).append("\t");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
