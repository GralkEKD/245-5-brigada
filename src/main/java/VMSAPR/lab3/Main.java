package VMSAPR.lab3;

public class Main {
    public static void main(String[] args) {
        final double a = 0;
        final double b = 1;
        HalfDivision halfDivision = new HalfDivision(a, b);
        Chords chords = new Chords(a, b);
        Newtons newtons = new Newtons(a, b);
        SimpleIteration simpleIteration = new SimpleIteration(a, b);
        System.out.println(halfDivision.division());
        System.out.println(chords.chordsMethod());
        System.out.println(newtons.tangents());
        System.out.println(simpleIteration.iteration());

    }
}
