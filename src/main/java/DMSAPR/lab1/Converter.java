package DMSAPR.lab1;


public class Converter {

    /**
     * Метод для подсчета отрезков (ребер, дуг и петель) в графе, описаном матрицей смежности {@code matrix}
     * @param matrix исходная матрица
     * @param isArch предикат для проверки принадлежности вершины дуге
     * @param isEdge предикат для проверки принадлежности вершины ребру
     * @param isLoop предикат для проверки принадлежности вершины петле
     * @return число отрезков в графе
     */
    private static int countSegments(
            int[][] matrix,
            IntMatrixPredicate isArch,
            IntMatrixPredicate isEdge,
            IntMatrixPredicate isLoop) {
        int segments = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int j = i; j < vertexes; j++) {
                if (isArch.test(matrix, i, j) ||
                        isEdge.test(matrix, i, j) ||
                        isLoop.test(matrix, i, j)) {
                    segments++;
                }
            }
        }
        return segments;
    }

    /**
     * Метод, считающий сумму элементов столбца матрицы инцедентности
     * @param matrix исходная матрица
     * @param colIndex индекс столбца
     * @return сумма элементов столбца
     */
    private static int columnSum(int[][] matrix, int colIndex) {
        int sum = 0;
        for (int[] row : matrix) {
            sum += row[colIndex];
        }
        return sum;
    }

    /**
     * Статическое поле, содержащее число вершин графа. В общем случае, равно числу строк матрицы. Инициализируется в
     * сооветствующих методах.
     */
    private static int vertexes;

    /**
     * Метод, возвращающий матрицу инцидентности, полученную из переданой матрицы смежности
     * @param adjacent матрица смежности
     * @return матрица инцидентности
     */
    public static int[][] adjacentToIncident(int[][] adjacent) {
        vertexes  = adjacent.length;

        /*
        Предикат для проверки принадлежности вершин xi, xj дуге
         */
        IntMatrixPredicate isArch = (arr, i, j) -> arr[i][j] != arr[j][i];

        /*
        Предикат для проверки принадлежности вершин xi, xj ребру
         */
        IntMatrixPredicate isEdge = (arr, i, j) -> arr[i][j] == 1 && arr[i][j] == arr[j][i] && i != j;

        /*
        Предикат для проверки принадлежности вершины xi петле
         */
        IntMatrixPredicate isLoop = (arr, i, j) -> i == j && arr[i][j] == 1;
        int segments = countSegments(adjacent, isArch, isEdge, isLoop);
        int[][] incident = new int[vertexes][segments];
        int currentSegment = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int j = i; j < vertexes; j++) {
                if (isEdge.test(adjacent, i, j)) {
                    incident[i][currentSegment] = 1;
                    incident[j][currentSegment++] = 1;
                } else if (isArch.test(adjacent, i, j)) {
                    if (adjacent[i][j] == 1) {
                        incident[i][currentSegment] = 1;
                        incident[j][currentSegment++] = -1;
                    } else {
                        incident[j][currentSegment] = 1;
                        incident[i][currentSegment++] = -1;
                    }
                } else if (isLoop.test(adjacent, i, j)) {
                    incident[i][currentSegment++] = 1;
                }
            }
        }
        return incident;
    }

    /**
     * Метод, возвращающий матрицу смежности, полученную из переданой матрицы инцидентности
     * @param incident матрица инцидентности
     * @return матрица смежности
     */
    public static int[][] incidentToAdjacent(int[][] incident) {
        vertexes = incident.length;

        IntMatrixPredicate isArch = (matrix, rowIndex, colIndex) -> columnSum(matrix, colIndex) == 0;

        IntMatrixPredicate isEdge = (matrix, rowIndex, colIndex) -> columnSum(matrix, colIndex) == 2;

        IntMatrixPredicate isLoop = (matrix, rowIndex, colIndex) -> columnSum(matrix, colIndex) == 1;

        int segments = incident[0].length;
        int[][] adjacent = new int[vertexes][vertexes];
        for (int j = 0; j < segments; j++) {
            if (isEdge.test(incident, 0, j)) {
                int adjRow = -1;
                for (int i = 0; i < vertexes; i++) {
                    if (incident[i][j] == 1 && adjRow == -1) {
                        adjRow = i;
                    } else if (incident[i][j] == 1) {
                        adjacent[adjRow][i] = 1;
                        adjacent[i][adjRow] = 1;
                        break;
                    }
                }
            } else if (isArch.test(incident, 0, j)) {
                int adjRow = -1, adjCol = -1;
                for (int i = 0; i < vertexes; i++) {
                    if (incident[i][j] == 1) {
                        adjRow = i;
                    } else if (incident[i][j] == -1) {
                        adjCol = i;
                    }
                }
                adjacent[adjRow][adjCol] = 1;
            } else if (isLoop.test(incident, 0, j)) {
                for (int i = 0; i < vertexes; i++) {
                    if (incident[i][j] == 1) {
                        adjacent[i][i] = 1;
                        break;
                    }
                }
            }
        }
        return adjacent;
    }
}
