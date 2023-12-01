package VMSAPR.lab6;

public class Main {
    public static void main(String[] args) {
        double a = 0;
        double b = 2;
        Rectangles rectangles = new Rectangles(a, b);
        Trapezoid trapezoid = new Trapezoid(a, b);
        Simpsons simpsons = new Simpsons(a, b);
        Newtons newtons = new Newtons(a, b);
        System.out.println("Прямоугольники:");
        System.out.println(rectangles.evalLeft() + " +- " + FunctionAndPrecision.epsilon);
        System.out.println(rectangles.evalRight() + " +- " + FunctionAndPrecision.epsilon);
        System.out.println(rectangles.evalMiddle() + " +- " + FunctionAndPrecision.epsilon);
        System.out.println("Трапеции:");
        System.out.println(trapezoid.eval() + " +- " + FunctionAndPrecision.epsilon);
        System.out.println("Формула Симпсона:");
        System.out.println(simpsons.eval() + " +- " + FunctionAndPrecision.epsilon);
        System.out.println("Формула Ньютона:");
        System.out.println(newtons.eval() + " +- " + FunctionAndPrecision.epsilon);
    }
}
