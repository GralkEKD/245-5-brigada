package PPEVM.lab2;

public class Task2 {
    public static void task2() {
        System.out.println("Число значащих нулей в двоичной записи числа 129: " +
                (32 - Integer.numberOfLeadingZeros(129) - Integer.bitCount(129)));
    }
}
