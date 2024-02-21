package PPEVM.lab3;

import java.util.Objects;

public class Interval {
    private boolean isLeftInclusive = false;
    private boolean isRightInclusive = false;

    private final double leftEnd, rightEnd;

    public static final Interval EMPTY_SET = new Interval(1, -1); // Возвращается, если два интервала не пересекаются

    public Interval(double leftEnd, double rightEnd) {
        this.leftEnd = leftEnd;
        this.rightEnd = rightEnd;
    }

    public Interval(double leftEnd, boolean isLeftInclusive, double rightEnd, boolean isRightInclusive) {
        this.leftEnd = leftEnd;
        if (leftEnd != Double.NEGATIVE_INFINITY) {
            this.isLeftInclusive = isLeftInclusive;
        }
        this.rightEnd = rightEnd;
        if (rightEnd != Double.POSITIVE_INFINITY) {
            this.isRightInclusive = isRightInclusive;
        }
    }

    public boolean isLeftInclusive() {
        return isLeftInclusive;
    }

    public boolean isRightInclusive() {
        return isRightInclusive;
    }

    public double getLeftEnd() {
        return leftEnd;
    }

    public double getRightEnd() {
        return rightEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return isLeftInclusive == interval.isLeftInclusive && isRightInclusive == interval.isRightInclusive &&
                Double.compare(interval.leftEnd, leftEnd) == 0 && Double.compare(interval.rightEnd, rightEnd) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLeftInclusive, isRightInclusive, leftEnd, rightEnd);
    }

    public Interval union (Interval interval) {
        /*
        По условию задания, если интервалы не имеют общих точек, то они не могут объединяться. По определению, пустое
        множество не имеет никаких точек, следовательно возвращается пустое множество.
         */
        if (this.equals(EMPTY_SET) || interval.equals(EMPTY_SET)) { return EMPTY_SET; }

        if (interval.getLeftEnd() < this.getLeftEnd() ||
                leftEnd == interval.leftEnd && rightEnd < interval.rightEnd) { return interval.union(this); }
        /*
        Здесь и далее: this: (l1; r1), interval: (l2, r2)

        Описывается следующая ситуация:
              l1          r1
        ------*-----------*------|------>
              |                  |
              |                  |
              |    l2            r2
        ------|----*-------------*------>
        Объединением явялется интервал (l1; r2), причем включение/невключение его границ определяется тем,
        включены/не включены ли эти границы в исходном интервале.
         */
        if (this.getLeftEnd() < interval.getLeftEnd() &&
                interval.getLeftEnd() < this.getRightEnd() &&
                this.getRightEnd() < interval.getRightEnd()) {
            return new Interval(
                    this.getLeftEnd(),
                    this.isLeftInclusive(),
                    interval.getRightEnd(),
                    interval.isRightInclusive()
            );
        }
        /*
        Описывается следующая ситуация:
              l1                r1
        ------*------------------*------>
              |                  |
              |                  |
              |    l2     r2     |
        ------|----*------*------|------>
        Объединением является исходный интервал.
        В частном случае (l1 == l2 || r1 == r2). Тогда включены будут те границы, которые включены хотя бы в одном из
        двух интервалов.
         */
        else if (this.getLeftEnd() <= interval.getLeftEnd() && interval.getRightEnd() <= this.getRightEnd()) {
            return new Interval(
                    this.getLeftEnd(),
                    this.getLeftEnd() == interval.getLeftEnd() ?
                            this.isLeftInclusive() || interval.isLeftInclusive() :
                            this.isLeftInclusive(),
                    this.getRightEnd(),
                    this.getRightEnd() == interval.getRightEnd() ?
                            this.isRightInclusive() || interval.isRightInclusive() :
                            this.isRightInclusive()
            );
        }

        /*
        Если интервалы не пересекаются, возвращается EMPTY_SET
         */
        return EMPTY_SET;
    }
    public Interval intersect (Interval interval) {
        /*
        По условию задания, если интервалы не имеют общих точек, то они не могут пересекаться. По определению, пустое
        множество не имеет никаких точек, следовательно возвращается пустое множество.
         */
        if (this.equals(EMPTY_SET) || interval.equals(EMPTY_SET)) { return EMPTY_SET; }

        if (interval.getLeftEnd() < this.getLeftEnd() ||
                leftEnd == interval.leftEnd && rightEnd < interval.rightEnd) { return interval.intersect(this); }
        /*
        Здесь и далее: this: (l1; r1), interval: (l2, r2)

        Описывается следующая ситуация:
              l1          r1
        ------*----|------*------------->
                   |      |
                   |      |
                   l2     |      r2
        -----------*------|------*------>
        Пересечением явялется интервал (l2; r1), причем включение/невключение его границ определяется тем,
        включены/не включены ли эти границы в исходном интервале.
         */
        if (this.getLeftEnd() < interval.getLeftEnd() &&
                interval.getLeftEnd() < this.getRightEnd() &&
                this.getRightEnd() < interval.getRightEnd()) {
            return new Interval(
                    interval.getLeftEnd(),
                    interval.isLeftInclusive(),
                    this.getRightEnd(),
                    this.isRightInclusive()
            );
        }
        /*
        Описывается следующая ситуация:
              l1                r1
        ------*----|------|------*------>
                   |      |
                   |      |
                   l2     r2
        -----------*------*------------->
        Пересечением является сам интервал interval.
        В частном случае (l1 == l2 || r1 == r2). Тогда включены будут те границы, которые включены в обоих интервалах.
         */
        else if (this.getLeftEnd() <= interval.getLeftEnd() && interval.getRightEnd() <= this.getRightEnd()) {
            return new Interval(
                    interval.getLeftEnd(),
                    this.isLeftInclusive() && interval.isLeftInclusive(),
                    interval.getRightEnd(),
                    this.getRightEnd() == interval.getRightEnd() ?
                            this.isRightInclusive() && interval.isRightInclusive() :
                            interval.isRightInclusive()
            );
        }

        /*
        Если интервалы не пересекаются, возвращается EMPTY_SET
         */
        return EMPTY_SET;
    }

    @Override
    public String toString() {
        if (this.equals(EMPTY_SET)) { return "EMPTY SET"; }
        String interval = isLeftInclusive ? "[" : "(";
        interval += leftEnd + "; " + rightEnd;
        interval += isRightInclusive ? "]" : ")";
        return interval;
    }
}
