package SAODSAPR.lab2;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        Variant 1
//        int[][] TA = {
//                {2, 3, 0},
//                {3, 0, 0},
//                {2, 4, 5},
//                {2, 5, 0},
//                {0, 0, 0}
//        };
//
//        Integer[][] TC = {
//                {5, 2, 0},
//                {7, 0, 0},
//                {4, 6, 1},
//                {3, 9, 0},
//                {0, 0, 0}
//        };
//
//        Variant1 variant1 = new Variant1(TA, TC);

//        Variant 2
//        Integer[] AL = {0, 5, 2, 0, 7, 0, 4, 6, 1, 0, 3, 9, 0, 0};
//        int[] J = {1, 2, 3, 2, 3, 3, 2, 4, 5, 4, 2, 5, 5, 0};
//        Variant2 variant2 = new Variant2(AL, J);

        Integer[] AL =  {0, 0, 5, 4, 3, 0, 2, 7, 0, 6, 0, 1, 9, 0};
        int[] I =       {1, 2, 1, 3, 4, 3, 1, 2, 4, 3, 5, 3, 4, 0};
        Variant3 variant3 = new Variant3(AL, I);
        for (Number[] row : variant3.getMatrix()) {
            System.out.print("[ ");
            for (Number num : row) {
                if (Objects.isNull(num)) System.out.print("0" + " ");
                else System.out.print(num + " ");
            }
            System.out.println(']');
        }
    }
}
