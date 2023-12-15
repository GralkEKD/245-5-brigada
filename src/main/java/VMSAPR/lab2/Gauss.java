package VMSAPR.lab2;
public class Gauss {
    protected final double[][] matrix;
    protected final int n;
    public Gauss (int n, double[][] matrix) {
        this.n = n;
        this.matrix = new double[n][n + 1];
        System.arraycopy(matrix, 0, this.matrix, 0, matrix.length);
    }
    protected double[] result;
    /**
     * Прямой ход Гаусса. Заменяет исходную матрицу на ступенчатую с единицами на главной диагонали,
     * ничего не возвращает
     */
    protected void forward(double[][] matrix, int n) {
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
     * @return xVector - вектор-столбец переменных - массив длиной n
     */
    protected double[] backward() {
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
    public void eval() {
        forward(matrix, n);
        result = backward();
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
