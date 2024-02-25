package PPEVM.lab3;

/**
 * Непроверяемое исключение, бросаемое конструкторами класса {@link Interval}, если в качестве параметра границы
 * интервала было передано значение {@code Double.NaN}, или если правая граница интервала оказывается меньше левой
 */
public class IllegalBoundaryValueException extends RuntimeException{

    public IllegalBoundaryValueException(String message) { super(message); }
}
