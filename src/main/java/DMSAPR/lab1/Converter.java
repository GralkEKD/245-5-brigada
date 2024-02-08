package DMSAPR.lab1;


public class Converter {
    /*
    Перевод смежной матрицы в инцидентную
     */
    public static int[][] adjacentToIncident(int[][] adjacent) {
        int vertexes = adjacent.length; // Число вершин, равно размерности матрицы

        /*
        Предикат для проверки принадлежности вершин xi, xj дуге
         */
        IntMatrixPredicate isArch = (arr, i, j) -> arr[i][j] != arr[j][i];

        /*
        Предикат для проверки принадлежности вершин xi, xj ребру
         */
        IntMatrixPredicate isEdge = (arr, i, j) -> arr[i][j] == arr[j][i] && arr[i][j] == 1;

        /*
        Предикат для проверки принадлежности вершины xi петле
         */
        IntMatrixPredicate isLoop = (arr, i, j) -> i == j && arr[i][j] == 1;
        int segments = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int j = i; j < vertexes; j++) {
                if (isArch.test(adjacent, i, j) ||
                        isEdge.test(adjacent, i, j) ||
                        isLoop.test(adjacent, i, j)) {
                    segments++;
                }
            }
        }
        int[][] incident = new int[vertexes][segments];
        int currentSegment = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int j = i; j < vertexes; j++) {
                if (isEdge.test(adjacent, i, j)) {
                    incident[++currentSegment][i] = 1;
                    incident[currentSegment][j] = 1;
                } else if (isArch.test(adjacent, i, j)) {
                    if (adjacent[i][j] == 1) {
                        incident[++currentSegment][i] = 1;
                        incident[currentSegment][j] = -1;
                    } else {
                        incident[++currentSegment][j] = 1;
                        incident[currentSegment][i] = -1;
                    }
                } else if (isLoop.test(adjacent, i, j)) {
                    incident[++currentSegment][i] = 2;
                }
            }
        }
        return incident;
    }
}
