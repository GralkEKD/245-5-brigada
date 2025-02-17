package SAODSAPR.lab1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {7, 6, 0, 0, 0, 9},
                {0, 2, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 9, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 4, 0, 0, 6, 0}
        };
        Variant2 hello = new Variant2(matrix);
        Variant7 haiiiXD = new Variant7(matrix);
        System.out.println("Структура смежности по строкам");
        System.out.println("AL: " + Arrays.toString(hello.getAL()));
        System.out.println("J: " + Arrays.toString(hello.getJ()));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Модифицированная строчно-столбцовая схема упаковки");
        System.out.println("AL: " + Arrays.toString(haiiiXD.getAL()));
        System.out.println("K: " + Arrays.toString(haiiiXD.getK()));
    }
}
