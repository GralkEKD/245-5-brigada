package PPEVM.lab3;

public class Main {
    public static void main(String[] args) {
        Tasks.task1();
        Tasks.task2();
        Tasks.task3();
        checkIntervalsForEquality();
    }

    private static void checkIntervalsForEquality() {
        Interval int1_1 = new Interval(-1.57, true, 17.4, false);
        Interval int1_2 = new Interval(-1.57, true, 17.4, false);

        Interval int2_1 = new Interval(-3.4, false, 0.17, true);
        Interval int2_2 = new Interval(-3.4, false, 0.17, true);

        Interval int3_1 = new Interval(Double.NEGATIVE_INFINITY, 1.9);
        Interval int3_2 = new Interval(Double.NEGATIVE_INFINITY, 1.9);

        Interval int4_1 = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        Interval int4_2 = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

        System.out.println(int1_1.equals(int1_2) + "\n" +
                int2_1.equals(int2_2) + "\n" +
                int3_1.equals(int3_2) + "\n" +
                int4_1.equals(int4_2) + "\n" +
                int1_1.equals(int2_1) + "\n" +
                int3_1.equals(int4_1));
    }
}
