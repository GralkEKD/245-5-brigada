package VMSAPR.lab2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        System.out.print("Введите ранг матрицы: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        double[][] matrix = new double[n][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("a" + (i + 1) + (j + 1) + ": ");
                matrix[i][j] = input.nextDouble();
            }
            System.out.print("a" + (i + 1) + (n + 1) + ": ");
            matrix[i][n] = input.nextDouble();
        }
        input.close();
         */
        double[][] matrix = {{2, 1, 0, 0, 0, 2.4},
                             {1, 4, 1, 0, 0, 3.6},
                             {0, 1, 4, 1, 0, -1.2},
                             {0, 0, 1, 4, 1, -3.6},
                             {0, 0, 0, 2, 4, -1.2}};
        int n = 5;
        Gauss gauss = new Gauss(n, matrix);
        System.out.println("Вектор-столбец корней:\nМетод Гаусса:");
        gauss.eval();
        System.out.println(gauss);
        Determinant determinant = new Determinant(n, matrix);
        System.out.println("Определитель матрицы: " + determinant.determinant(matrix) + "\n");
        /*
        System.out.println("Метод прогонки:");
        RunThrough runThrough = new RunThrough(n, matrix);
        runThrough.eval();
        System.out.println(runThrough);
        System.out.println("LU-разложение:");
        LU lu = new LU(n, matrix);
        lu.eval(matrix);
        System.out.println(lu);
        ReciprocalMatrix reciprocalMatrix = new ReciprocalMatrix(n, matrix);
        reciprocalMatrix.eval();
        System.out.println("Обратная матрица:");
        System.out.println(reciprocalMatrix);
        Jacobi jacobi = new Jacobi(n, matrix);
        System.out.println("Метод Якоби:");
        jacobi.eval();
        System.out.println(jacobi);
        Zeidel zeidel = new Zeidel(n, matrix);
        System.out.println("Метод Зейделя");
        zeidel.eval();
        System.out.println(zeidel);
         */
    }
}
