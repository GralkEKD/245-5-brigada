package SAODSAPR.lab2;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        Variant 1
        int[][] TA = {
                {1, 2, 6},
                {2, 5, 0},
                {0, 0, 0},
                {2, 5, 0},
                {0, 0, 0},
                {1, 2, 5}
        };

        Integer[][] TC = {
                {7, 6, 9},
                {2, 1, 0},
                {0, 0, 0},
                {3, 9, 0},
                {0, 0, 0},
                {1, 4, 6}
        };

        Variant1 variant1 = new Variant1(TA, TC);

        System.out.println("Таблица связей");
        for (Number[] row : variant1.getMatrix()) {
            System.out.print("[ ");
            for (Number num : row) {
                if (Objects.isNull(num)) System.out.print("0" + " ");
                else System.out.print(num + " ");
            }
            System.out.println(']');
        }
        System.out.println();

//        Variant 2
//        Integer[] AL = {0, 0, 2, 1, 0, 1, 9, 0, 3, 9, 0, 2, 7, 0, 0};
//        int[] J = {1, 2, 2, 5, 3, 4, 5, 4, 2, 5, 5, 2, 4, 6, 0};
//        Variant2 variant2 = new Variant2(AL, J);

//        Variant 3
//        Integer[] AL =  {0, 0, 5, 4, 3, 0, 2, 7, 0, 6, 0, 1, 9, 0};
//        int[] I =       {1, 2, 1, 3, 4, 3, 1, 2, 4, 3, 5, 3, 4, 0};
//        Variant3 variant3 = new Variant3(AL, I);

//        Variant 4
//        Integer[] AL = {7, 6, 9, 2, 1, 3, 9, 1, 4, 6};
//        int[] J = {1, 2, 6, 2, 5, 2, 5, 1, 2, 5};
//        int[] IC = {1, 4, 6, 6, 8, 8, 11};
//
//        Variant4 variant4 = new Variant4(AL, J, IC);

//        Variant 5
//        Integer[] AL = {5, 4, 3, 2, 7, 6, 1, 9};
//        int[] I = {1, 3, 4, 1, 2, 3, 3, 4};
//        int[] JC = {1, 1, 4, 6, 7, 9};
//
//        Variant5 variant5 = new Variant5(AL, I, JC);

//        Variant 6
//        Integer[] AL = {5, 2, 7, 4, 6, 1, 3, 9};
//        int[] I = {1, 1, 2, 3, 3, 3, 4, 4};
//        int[] J = {2, 3, 3, 2, 4, 5, 2 ,5};
//
//        Variant6 variant6 = new Variant6(AL, I, J);

//        Variant 7
        Integer[] AL = {7, 6, 9, 2, 1, 3, 9, 1, 4, 6};
        int[] K = {1, 7, 31, 8, 26, 10, 28, 6, 12, 30};

        Variant7 variant7 = new Variant7(AL, K);

        System.out.println("Строчная схема упаковки");
        for (Number[] row : variant7.getMatrix()) {
            System.out.print("[ ");
            for (Number num : row) {
                if (Objects.isNull(num)) System.out.print("0" + " ");
                else System.out.print(num + " ");
            }
            System.out.println(']');
        }
    }
}
