package PPEVM.lab3;

import java.util.Collection;
import java.util.Iterator;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.imaginary = imaginary;
        this.real = real;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public static Complex sum(Collection<Complex> numbers) {
        Iterator<Complex> iterator = numbers.iterator();
        if (!iterator.hasNext()) return new Complex(0, 0);

        Complex result = new Complex(0, 0);
        iterator.forEachRemaining((item) -> {
            result.real += item.getReal();
            result.imaginary += item.getImaginary();
        });
        return result;
    }

    public static Complex prod(Collection<Complex> numbers) {
        Iterator<Complex> iterator = numbers.iterator();
        Complex result;
        if (!iterator.hasNext()) {
            return new Complex(0, 0);
        } else {
            result = iterator.next();
        }
        iterator.forEachRemaining((item) -> {
            result.real = result.real * item.getReal() - result.imaginary * item.getImaginary();
            result.imaginary = result.real * item.getImaginary() + result.imaginary * item.getReal();
        });
        return result;
    }
}
