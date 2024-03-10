package PPEVM.lab4.biker;

import java.util.Scanner;

public class Main {
    private static void printMenu() {
        System.out.println("""
                Выберите действие:
                c - Подсчитать стоимость аммуниции
                s - Отсортировать аммуницию по весу
                r - Найти аммуницию в заданном ценовом диапазоне
                q - Завершить работу""");
    }
    public static void main(String[] args) {
        System.out.println("Задание 2:");
        Scanner input = new Scanner(System.in);
        System.out.println("Экипируйте байкера:");
        Biker biker = TaskMethods.equipBiker(input);
        boolean cont = true;
        while (cont) {
            printMenu();
            try {
                Menu userChoice = Menu.valueOf(input.next());
                switch (userChoice) {
                    case c -> {
                        double sum = TaskMethods.countPrice(biker);
                        System.out.println("Сумма снаряжения байкера: " + sum);
                    }
                    case s -> {
                        TaskMethods.sortByWeight(biker);
                        System.out.println("Аммуниция в порядке возрастания веса:\n" +
                                Biker.getProtectionByName(biker.getProtectionList()));
                    }
                    case r -> {
                        System.out.print("Введите минимальную цену: ");
                        double min = input.nextDouble();
                        System.out.print("Введите максимальную цену: ");
                        double max = input.nextDouble();
                        String result = TaskMethods.getProtectionInRange(min, max, biker);
                        System.out.println("Аммуниция в диапазоне от " + min + " до " + max + " :\n" + result);
                    }
                    case q -> cont = false;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверно введено значение");
            }
        }
    }
}
