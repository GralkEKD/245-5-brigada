package PPEVM.lab4.biker;

import PPEVM.lab4.biker.protection.Protection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskMethods {

    public static double countPrice(List<Protection> protectionList) {
        double sum = 0;
        for (Protection piece : protectionList) {
            sum += piece.getPrice();
        }
        return sum;
    }

    public static void sortByWeight(List<Protection> protectionList) {
        Comparator<Protection> compareWeight = (piece1, piece2) -> {
            double num = piece1.getWeight() - piece2.getWeight();
            if (num < 0) return -1;
            else if (num > 0) return 1;
            else return 0;
        };
        protectionList.sort(compareWeight);
    }

    public static List<Protection> getProtectionInRange(double priceL, double priceH, List<Protection> protectionList) {
        List<Protection> protectionInRange = new ArrayList<>();
        for (Protection piece : protectionList) {
            if (priceL <= piece.getPrice() && piece.getPrice() <= priceH) {
                protectionInRange.add(piece);
            }
        }
        return protectionInRange;
    }
}
