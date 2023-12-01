package VMSAPR.lab2;

public class Gauss {
    private double[] result;
    /**
     * Прямой ход Гаусса. Заменяет исходную матрицу на ступенчатую с единицами на главной диагонали,
     * ничего не возвращает
     * @param matrix исходная матрица
     * @param n ранг матрицы
     */
    public void forward(double[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            double[] c = new double[n + 1];
            for (int j = 0; j < n + 1; j++){
                /*
                Из-за специфики работы ссылочных типов данных, в Java нельзя напрямую заменить элемент в массиве,
                поэтому создается новый массив, которым заменяется вся строка в исходном массиве.
                 */
                c[j] = matrix[i][j] / matrix[i][i];
            }
            matrix[i] = c;
            for (int j = i + 1; j < n; j++) {
                double[] cj = new double[n + 1];
                for (int k = i; k < n + 1; k++) {
                    cj[k] = matrix[j][k] - matrix[j][i] * matrix[i][k];
                }
                matrix[j] = cj;
            }
        }
    }

    /**
     * Обратный ход Гаусса. Возвращает вектор-столбец переменых.
     * @param matrix ступенчатая матрица с единицами на главной диагонали - результат прямого хода Гаусса
     * @param n ранг матрицы
     * @return xVector - вектор-столбец переменных - массив длиной n
     */
    public double[] backward(double[][] matrix, int n) {
        double[] xVector = new double[n];
        xVector[n - 1] = matrix[n - 1][n] / matrix[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j <= n; j++) {
                sum += matrix[i][j - 1] * xVector[j - 1];
            }
            xVector[i] = matrix[i][n] - sum;
        }
        return xVector;
    }
    public void eval(double[][] matrix, int n) {
        forward(matrix, n);
        result = backward(matrix, n);
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (double v : result) {
            sb.append(v).append("\n");
        }
        return(sb.toString());
    }
}
