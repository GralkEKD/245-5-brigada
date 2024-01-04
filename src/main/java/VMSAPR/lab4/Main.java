package VMSAPR.lab4;
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static int n = 11;
    public static double[] x = {-1, -0.8, -0.6, -0.4, -0.2, 0, 0.2, 0.4, 0.6, 0.8, 1};
    public static double[] y = {0.0385, 0.0588, 0.1, 0.2, 0.5, 1, 0.5, 0.2, 0.1, 0.0588, 0.0385};
    public static void main(String[] args) {
        /*
        Scanner input = new Scanner(System.in);
        System.out.print("Введите число известных значений: ");
        int n = input.nextInt();
        double[] x = new double[n];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double in;
            System.out.print("x" + (i + 1) + ": ");
            in = input.nextDouble();
            x[i] = in;
            System.out.print("y" + (i + 1) + ": ");
            in = input.nextDouble();
            y[i] = in;
        }
        */
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US); // формат ввода числа с ПТ с точкой в качестве десятичного разделителя
        Lagrange lagrange = new Lagrange(n, x, y);
        Aitken aitken = new Aitken(n, x, y);
        Splines splines = new Splines(n, x, y);
        String check = "";
        long t;
        double evaluatedX;
        while (!check.equalsIgnoreCase("n")) {
            System.out.print("Аргумент: ");
            double in = input.nextDouble();
            System.out.print("Метод Лагранжа: ");
            t = System.currentTimeMillis();
            evaluatedX = lagrange.eval(in);
            System.out.println(evaluatedX + " Время выполнения: " + (System.currentTimeMillis() - t) + "мс");
            System.out.print("Метод Эйткена: ");
            t = System.currentTimeMillis();
            evaluatedX = aitken.eval(in);
            System.out.println(evaluatedX + " Время выполнения: " + (System.currentTimeMillis() - t) + "мс");
            System.out.print("Метод Сплайнов: ");
            t = System.currentTimeMillis();
            evaluatedX = splines.eval(in);
            System.out.println(evaluatedX + " Время выполнения: " + (System.currentTimeMillis() - t) + "мс");
            System.out.print("Продолжить? Y/N ");
            check = input.next();
        }
        input.close();
        EventQueue.invokeLater(() -> {
            var ex = new DrawingComponent(lagrange, aitken, splines);
            ex.setVisible(true);
        });
    }
}
