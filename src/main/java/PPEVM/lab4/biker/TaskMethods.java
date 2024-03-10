package PPEVM.lab4.biker;

import PPEVM.lab4.biker.protection.*;

import java.util.*;

public class TaskMethods {
    private static Material chooseMaterial(Scanner scanner) {
        System.out.println("""
                Выберите материал.
                Доступные материалы: пластик, карбон, полиуретан, металл""");
        String material = scanner.nextLine().toLowerCase(Locale.ROOT);
        switch (material) {
            case "пластик" -> { return Material.PLASTIC; }
            case "карбон" -> { return Material.CARBON_FIBER; }
            case "полиуретан" -> { return Material.POLYURETHANE; }
            case "металл" -> { return Material.METAL; }
            default -> {
                System.out.println("Ошибка: неверно введен материал");
                return chooseMaterial(scanner);
            }
        }
    }

    private static Size chooseSize(Scanner scanner) {
        System.out.println("""
                Выберите размер.
                Доступные размеры: XS, S, M, L, XL, XXL""");
        try {
            return Size.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверно введен размер");
            return chooseSize(scanner);
        }
    }

    private static double choosePrice(Scanner scanner) {
        System.out.print("Введите цену: ");
        double price = scanner.nextDouble();
        if (price <= 0) {
            System.out.println("Ошибка: цена должна быть положительным числом");
            return choosePrice(scanner);
        }
        return price;
    }

    private static double chooseWeight(Scanner scanner) {
        System.out.print("Введите вес: ");
        double weight = scanner.nextDouble();
        if (weight <= 0) {
            System.out.println("Ошибка: вес должен быть положительным числом");
            return choosePrice(scanner);
        }
        return weight;
    }

    public static Biker equipBiker(Scanner scanner) {
        System.out.println("Шлем:");
        Helmet helmet = new Helmet(
                chooseMaterial(scanner),
                chooseSize(scanner),
                choosePrice(scanner),
                chooseWeight(scanner));
        System.out.println("Налокотники:");
        ElbowPads elbowPads = new ElbowPads(
                chooseMaterial(scanner),
                chooseSize(scanner),
                choosePrice(scanner),
                chooseWeight(scanner));
        System.out.println("Наколенники:");
        KneePads kneePads = new KneePads(
                chooseMaterial(scanner),
                chooseSize(scanner),
                choosePrice(scanner),
                chooseWeight(scanner));
        return new Biker(helmet, elbowPads, kneePads);
    }

    public static double countPrice(Biker biker) {
        List<Protection> protectionList = biker.getProtectionList();
        double sum = 0;
        for (Protection piece : protectionList) {
            sum += piece.getPrice();
        }
        return sum;
    }

    public static void sortByWeight(Biker biker) {
        List<Protection> protectionList = biker.getProtectionList();
        Comparator<Protection> compareWeight = (piece1, piece2) -> {
            double num = piece1.getWeight() - piece2.getWeight();
            if (num < 0) return -1;
            else if (num > 0) return 1;
            else return 0;
        };
        protectionList.sort(compareWeight);
    }

    public static String getProtectionInRange(double priceL, double priceH, Biker biker) {
        List<Protection> protectionList = biker.getProtectionList();
        List<Protection> protectionInRange = new ArrayList<>();
        for (Protection piece : protectionList) {
            if (priceL <= piece.getPrice() && piece.getPrice() <= priceH) {
                protectionInRange.add(piece);
            }
        }
        return Biker.getProtectionByName(protectionInRange);
    }
}
