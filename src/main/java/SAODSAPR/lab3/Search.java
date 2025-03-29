package SAODSAPR.lab3;

public class Search {

    public static int compCount = 0;

    public static int linearSearch(Object[] array, Object element) {
        compCount = 0;
        for (int i = 0; i < array.length; i++) {
            compCount++;
            if (array[i].equals(element)) return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearchIter(T[] array, T element) {
        compCount = 0;
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int p = Math.floorDiv(low + high, 2);
            compCount++;
            if (array[p].equals(element)) return p;
            compCount++;
            if (element.compareTo(array[p]) < 0) high = p - 1;
            else low = p + 1;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearchRec(T[] array, T element) {
        compCount = 0;
        return binarySearchRec(array, element, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> int binarySearchRec(T[] array, T element, int low, int high) {
        if (low > high) return -1;
        int p = Math.floorDiv(low + high, 2);
        compCount++;
        if (element.equals(array[p])) return p;
        compCount++;
        if (element.compareTo(array[p]) < 0) return binarySearchRec(array, element, low, p - 1);
        else return binarySearchRec(array, element, p + 1, high);
    }

    public static int interpolationSearch(Integer[] array, Integer element) {
        compCount = 0;
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int p;
            try  {
                p = low + Math.floorDivExact((element - array[low]) * (high - low), array[high] - array[low]);
            } catch (ArithmeticException e) {
                return -1;
            }
            compCount++;
            if (array[p].equals(element)) return p;
            compCount++;
            if (element.compareTo(array[p]) < 0) high = p - 1;
            else low = p + 1;
        }
        return -1;
    }
    public static int interpolationSearch(Long[] array, Long element) {
        compCount = 0;
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int p;
            try  {
                p = (int) (low + Math.floorDivExact((element - array[low]) * (high - low), array[high] - array[low]));
            } catch (ArithmeticException e) {
                return -1;
            }
            compCount++;
            if (array[p].equals(element)) return p;
            compCount++;
            if (element.compareTo(array[p]) < 0) high = p - 1;
            else low = p + 1;
        }
        return -1;
    }
}
