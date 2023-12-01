package VMSAPR.lab2;
//import java.util.Scanner;

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
        double [][] matrix = {{3.1, 1.5, 1.0, 10.83},
                              {1.5, 2.5, 0.5, 9.20},
                              {1.0, 0.5, 4.2, 17.10}};
                /*,
                              {0.9, 2.5, 1.3, 12.1, 24.6}};*/
        int n = 3;
        Gauss gauss = new Gauss();
        RunThrough runThrough = new RunThrough(n, matrix);
        LU lu = new LU();
        gauss.forward(matrix, n);
        System.out.println("Вектор-столбец корней:\nМетод Гаусса:");
        gauss.eval(matrix, n);
        System.out.println(gauss);
        System.out.println("Метод прогонки:");
        runThrough.eval();
        System.out.println(runThrough);
        System.out.println("LU-разложение:");
        lu.eval(n, matrix);
        System.out.println(lu);
    }
}