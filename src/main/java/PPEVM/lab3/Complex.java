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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Complex complex = (Complex) obj;
        return real == complex.real && imaginary == complex.imaginary;
    }

    public Complex copy() { return new Complex(this.real, this.imaginary); }

    public static Complex sum(Collection<Complex> numbers) {
        Complex result = new Complex(0,0);
        for (Complex num : numbers) {
            result.real += num.real;
            result.imaginary += num.imaginary;
        }
        return result;
    }

    public static Complex product(Collection<Complex> numbers) {
        Iterator<Complex> iterator = numbers.iterator();
        Complex result;
        if (!iterator.hasNext()) {
            result = new Complex(0, 0);
        } else {
            result = iterator.next().copy();
        }
        iterator.forEachRemaining((item) -> {
            double tempReal = result.real * item.getReal() - result.imaginary * item.getImaginary();
            double tempImaginary = result.real * item.getImaginary() + result.imaginary * item.getReal();
            result.real = tempReal;
            result.imaginary = tempImaginary;
        });
        return result;
    }

    @Override
    public String toString() {
        return real + (imaginary >= 0 ? " + " : " - ") + Math.abs(imaginary) + "j";
    }
}
