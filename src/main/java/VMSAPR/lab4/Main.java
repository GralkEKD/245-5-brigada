package VMSAPR.lab4;
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static int n = 7;
    public static double[] x = {0.5, 1, 1.6, 2.3, 3.1, 4.0, 5.0};
    public static double[] y = {-2.3, 0.5, 11.5, 17.5, 17.9, 23, 27.7};
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
        while (!check.equalsIgnoreCase("n")) {
            System.out.print("Аргумент: ");
            double in = input.nextDouble();
            System.out.println("Метод Лагранжа: " + lagrange.eval(in));
            System.out.println("Метод Эйткена: " + aitken.eval(in));
            System.out.println("Метод сплайнов: " + splines.eval(in));
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
