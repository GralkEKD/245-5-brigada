package PPEVM.lab3;

import java.util.Objects;

/**
 * Класс {@code Interval} - представление математического интервала/полуинтервала/отрезка. В классе определены методы
 * для нахождения пересечения и объединения двух интервалов.
 */
public class Interval {
    /**
     * Поля класса, определяющие свойства интервала.
     * {@code isLeftInclusive} и {@code isRightInclusive} - поля, определяющие включение или невключение соответственно
     * левой и правой границ интервала. {@code false} - граница не включена, {@code true} - граница включена
     * {@code leftBoundary} и {@code rightBoundary} - значение соответсвенно левой и правой границ интервала.
     */
    private boolean isLeftInclusive = false;
    private boolean isRightInclusive = false;
    private final double leftBoundary;
    private final double rightBoundary;

    /**
     * Статическая константа, обозначающая пустое множество. Возвращается методами для нахождения пересечения или
     * объединения в случае, если интервалы не имеют общих точек. Не может быть создана искусственно, так как при
     * передаче {@code Double.NaN} в любой из конструкторов бросается {@link IllegalBoundaryValueException}.
     */

    public static final Interval EMPTY_SET = new Interval();

    /**
     * Конструктор по умолчанию. Вызывается только для инициализации пустого множества, поэтому объявлен, как
     * {@code private}.
     */
    private Interval() {
        leftBoundary = Double.NaN;
        rightBoundary = Double.NaN;
    }

    /**
     * Конструктор, принимающий как параметры только левую и правую границы интервала. При его вызове границы
     * выставляются, как не входящие, если границы не равны, и как входящие в ином случае (тогда интервал будет
     * считаться точкой). Бросает {@code IllegalBoundaryValueException}
     * @param leftBoundary левая граница интервала
     * @param rightBoundary правая граница интервала
     * @throws IllegalBoundaryValueException бросается в случае, если в качестве параметра границы интервала было
     * передано занчение NaN, или если правая граница интервала оказывается меньше левой.
     */
    public Interval(double leftBoundary, double rightBoundary) throws IllegalBoundaryValueException{

        if (leftBoundary > rightBoundary) {
            throw new IllegalBoundaryValueException(
                    "The right boundary of the interval cannot be less than its left boundary");
        }

        if (Double.isNaN(leftBoundary) || Double.isNaN(rightBoundary)) {
            throw new IllegalBoundaryValueException(
                    "Expected a numerical value or positive/negative infinity, found NaN");
        }

        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
        if (leftBoundary == rightBoundary) {
            isLeftInclusive = true;
            isRightInclusive = true;
        }
    }

    /**
     * Конструктор, принимающий левую и правую границы интервала и булевские значения, определяющие вхождение границ
     * в интервал ({@code true} - граница входящая, {@code false} - граница невходящая.
     * @param leftBoundary значение левой границы
     * @param isLeftInclusive вхождение левой гранциы
     * @param rightBoundary значение правой границы
     * @param isRightInclusive вхождение правой границы
     * @throws IllegalBoundaryValueException бросается в случае, если в качестве параметра границы интервала было
     * передано занчение NaN, или если правая граница интервала оказывается меньше левой.
     */
    public Interval(double leftBoundary, boolean isLeftInclusive, double rightBoundary, boolean isRightInclusive)
    throws IllegalBoundaryValueException {

        if (leftBoundary > rightBoundary) {
            throw new IllegalBoundaryValueException(
                    "The right boundary of the interval cannot be less than its left boundary");
        }

        if (Double.isNaN(leftBoundary) || Double.isNaN(rightBoundary)) {
            throw new IllegalBoundaryValueException(
                    "Expected a numerical value or positive/negative infinity, found NaN");
        }

        this.leftBoundary = leftBoundary;
        if (leftBoundary != Double.NEGATIVE_INFINITY) {
            this.isLeftInclusive = isLeftInclusive;
        }
        this.rightBoundary = rightBoundary;
        if (rightBoundary != Double.POSITIVE_INFINITY) {
            this.isRightInclusive = isRightInclusive;
        }
    }

    public boolean isLeftInclusive() {
        return isLeftInclusive;
    }

    public boolean isRightInclusive() {
        return isRightInclusive;
    }

    public double getLeftBoundary() {
        return leftBoundary;
    }

    public double getRightBoundary() {
        return rightBoundary;
    }

    /**
     * Метод, возвращающий {@code true}, если переданный в него объект совпадает с интервалом, у которого вызван метод,
     * и {@code false} в ином случае.
     * @param o объект, сравниваемый с интервалом
     * @return {@code true} или {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return isLeftInclusive == interval.isLeftInclusive && isRightInclusive == interval.isRightInclusive &&
                Double.compare(interval.leftBoundary, leftBoundary) == 0 && Double.compare(interval.rightBoundary, rightBoundary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLeftInclusive, isRightInclusive, leftBoundary, rightBoundary);
    }

    /**
     * Метод, выполняющий операцию объединения двух интервалов. Ниже рассмотрены разные случаи расположения двух
     * интервалов.
     * @param interval интервал, с которым будет выполнено объединение данного
     * @return новый интервал, являющийся результатом объединения
     */
    public Interval union (Interval interval) {
        /*
        По условию задания, если интервалы не имеют общих точек, то они не могут объединяться. По определению, пустое
        множество не имеет никаких точек, следовательно возвращается пустое множество.
         */
        if (this.equals(EMPTY_SET) || interval.equals(EMPTY_SET)) {
            return EMPTY_SET;
        }

        /*
        Чтобы не писать много одинаковых условий для симметричных случаев, решение сводится к случаю, когда левая
        граница первого переданного в метод интервала левее соответсвенной границы правого интервала.

        Особый случай возникает, когда левые границы совпадают. Тогда решение сводится к виду, где правая граница
        второго интервала левее правой границы левого, таким образом, образуя частный случай (2).
         */
        if (interval.getLeftBoundary() < this.getLeftBoundary() ||
                leftBoundary == interval.leftBoundary && rightBoundary < interval.rightBoundary) {
            return interval.union(this);
        }

        /*
        (1) Описывается следующая ситуация:
              l1          r1
        ------*-----------*------|------> this
              |                  |
              |                  |
              |    l2            r2
        ------|----*-------------*------> interval
        Объединением явялется интервал (l1; r2), причем включение/невключение его границ определяется тем,
        включены ли эти границы в исходных интервалах.
         */
        if (this.getLeftBoundary() < interval.getLeftBoundary() &&
                interval.getLeftBoundary() < this.getRightBoundary() &&
                this.getRightBoundary() < interval.getRightBoundary()) {
            return new Interval(
                    this.getLeftBoundary(),
                    this.isLeftInclusive(),
                    interval.getRightBoundary(),
                    interval.isRightInclusive()
            );
        }
        /*
        (2) Описывается следующая ситуация:
              l1                r1
        ------*------------------*------> this
              |                  |
              |                  |
              |    l2     r2     |
        ------|----*------*------|------> interval
        Объединением является исходный интервал.
        В частном случае (l1 == l2 || r1 == r2). Тогда включены будут те границы, которые включены хотя бы в одном из
        двух интервалов.
         */
        else if (this.getLeftBoundary() <= interval.getLeftBoundary() &&
                interval.getRightBoundary() <= this.getRightBoundary()) {
            return new Interval(
                    this.getLeftBoundary(),
                    this.getLeftBoundary() == interval.getLeftBoundary() ?
                            this.isLeftInclusive() || interval.isLeftInclusive() :
                            this.isLeftInclusive(),
                    this.getRightBoundary(),
                    this.getRightBoundary() == interval.getRightBoundary() ?
                            this.isRightInclusive() || interval.isRightInclusive() :
                            this.isRightInclusive()
            );
        }

        /*
        (3) Если интервалы не имеют общих точек, возвращается EMPTY_SET
         */
        return EMPTY_SET;
    }
    public Interval intersect (Interval interval) {
        /*
        По условию задания, если интервалы не имеют общих точек, то они не могут пересекаться. По определению, пустое
        множество не имеет никаких точек, следовательно возвращается пустое множество.
         */
        if (this.equals(EMPTY_SET) || interval.equals(EMPTY_SET)) {
            return EMPTY_SET;
        }

        /*
        Чтобы не писать много одинаковых условий для симметричных случаев, решение сводится к случаю, когда левая
        граница первого переданного в метод интервала левее соответсвенной границы правого интервала.

        Особый случай возникает, когда левые границы совпадают. Тогда решение сводится к виду, где правая граница
        второго интервала левее правой границы левого, таким образом, образуя частный случай (2).
         */
        if (interval.getLeftBoundary() < this.getLeftBoundary() ||
                leftBoundary == interval.leftBoundary && rightBoundary < interval.rightBoundary) {
            return interval.intersect(this);
        }
        /*
        (1) Описывается следующая ситуация:
              l1          r1
        ------*----|------*-------------> this
                   |      |
                   |      |
                   l2     |      r2
        -----------*------|------*------> interval
        Пересечением явялется интервал (l2; r1), причем включение/невключение его границ определяется тем,
        включены ли эти границы в исходных интервалах.
         */
        if (this.getLeftBoundary() < interval.getLeftBoundary() &&
                interval.getLeftBoundary() < this.getRightBoundary() &&
                this.getRightBoundary() < interval.getRightBoundary()) {
            return new Interval(
                    interval.getLeftBoundary(),
                    interval.isLeftInclusive(),
                    this.getRightBoundary(),
                    this.isRightInclusive()
            );
        }
        /*
        (2) Описывается следующая ситуация:
              l1                r1
        ------*----|------|------*------> this
                   |      |
                   |      |
                   l2     r2
        -----------*------*-------------> interval
        Пересечением является сам интервал interval.
        В частном случае (l1 == l2 || r1 == r2). Тогда включены будут те границы, которые включены в обоих интервалах.
         */
        else if (this.getLeftBoundary() <= interval.getLeftBoundary() && interval.getRightBoundary() <= this.getRightBoundary()) {
            return new Interval(
                    interval.getLeftBoundary(),
                    this.isLeftInclusive() && interval.isLeftInclusive(),
                    interval.getRightBoundary(),
                    this.getRightBoundary() == interval.getRightBoundary() ?
                            this.isRightInclusive() && interval.isRightInclusive() :
                            interval.isRightInclusive()
            );
        }

        /*
        Если интервалы не имеют общих точек, возвращается EMPTY_SET
         */
        return EMPTY_SET;
    }

    /**
     * Метод, приводящий интервал к строке вида /x; y\, где / - "(", если конец невходящий, "[", если конец входящий,
     * x - левая граница интервала, y - правая граница интервала, \ - аналогично случаю с / для символов ")" и "]".
     * @return строка, описывающая интервал
     */
    @Override
    public String toString() {
        if (this.equals(EMPTY_SET)) { return "EMPTY SET"; }
        String interval = isLeftInclusive ? "[" : "(";
        interval += leftBoundary + "; " + rightBoundary;
        interval += isRightInclusive ? "]" : ")";
        return interval;
    }
}
